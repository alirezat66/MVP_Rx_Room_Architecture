package ir.greencode.androidmvprxroom.di.component;

import dagger.Component;
import ir.greencode.androidmvprxroom.di.module.HomeModule;
import ir.greencode.androidmvprxroom.di.scope.PerActivity;
import ir.greencode.androidmvprxroom.ui.Main.MainActivity;


@PerActivity
@Component(modules = HomeModule.class,dependencies = ApplicationComponent.class)
public interface HomeComponent {
    void inject(MainActivity mainActivity);
}
