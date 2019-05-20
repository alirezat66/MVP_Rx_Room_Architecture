package ir.greencode.androidmvprxroom.application;

import android.app.Application;

import ir.greencode.androidmvprxroom.R;
import ir.greencode.androidmvprxroom.di.component.ApplicationComponent;
import ir.greencode.androidmvprxroom.di.component.DaggerApplicationComponent;
import ir.greencode.androidmvprxroom.di.module.ApplicationModule;
import ir.greencode.androidmvprxroom.utility.Constant;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class App extends Application {
    private ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath(Constant.FONTH_NAME).setFontAttrId(R.attr.fontPath).build());
        initialApplicationComponent();
    }

    private void initialApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, Constant.BASE_URL))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
