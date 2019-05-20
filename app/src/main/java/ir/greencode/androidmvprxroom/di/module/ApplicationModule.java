package ir.greencode.androidmvprxroom.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.greencode.androidmvprxroom.data.loc.MySharedPreferences;
import ir.greencode.androidmvprxroom.utility.Constant;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private String baseUrl;
    private Context mContext;
    @Inject
    MySharedPreferences mySharedPreferencesl;

    public ApplicationModule(Context mContext, String baseUrl) {
        this.baseUrl = baseUrl;
        this.mContext = mContext;

    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }


    @Singleton
    @Provides
    @Named("ok2")
    OkHttpClient provideOkHttpClient2(){

        mySharedPreferencesl = new MySharedPreferences(provideSharedPreferences());
        String token =  mySharedPreferencesl.getDataString(Constant.PREF_TOKEN);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        mySharedPreferencesl = new MySharedPreferences(provideSharedPreferences());
                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + mySharedPreferencesl.getDataString(Constant.PREF_TOKEN))
                                .build();

                        Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .callTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }


    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideRxCallFactory(){
        return  RxJava2CallAdapterFactory.create();
    }
    @Singleton
    @Provides
    Retrofit provideRetrofit(@Named("ok2") OkHttpClient client, GsonConverterFactory converterFactory , RxJava2CallAdapterFactory adapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
        return retrofit;
    }
    @Singleton
    @Provides
    Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return mContext.getSharedPreferences("contentPref",Context.MODE_PRIVATE);
    }





}
