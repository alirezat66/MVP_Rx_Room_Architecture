package ir.greencode.androidmvprxroom.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ir.greencode.androidmvprxroom.api.MyApiService;
import ir.greencode.androidmvprxroom.data.roomdb.LocalCategoryDataSource;
import ir.greencode.androidmvprxroom.data.roomdb.base.AppDatabase;
import ir.greencode.androidmvprxroom.di.scope.PerActivity;
import ir.greencode.androidmvprxroom.mvp.view.HomeView;
import ir.greencode.androidmvprxroom.ui.Main.HomeViewModel;
import retrofit2.Retrofit;

@Module
public class HomeModule {

    HomeView mView;

    public HomeModule(HomeView mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    MyApiService provideContentApiService(Retrofit retrofit){
        return retrofit.create(MyApiService.class);
    }
    @PerActivity
    @Provides
    HomeView provideMainView(){
        return mView;
    }
    @Provides
    public HomeViewModel provideHomeViewModel(Context context){
        LocalCategoryDataSource localTaskDataSource=new LocalCategoryDataSource(AppDatabase
                .getInMemoryDatabase(context)
                .categoryDao());
        return new HomeViewModel(localTaskDataSource);
    }
}
