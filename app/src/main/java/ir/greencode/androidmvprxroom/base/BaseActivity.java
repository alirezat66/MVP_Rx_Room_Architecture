package ir.greencode.androidmvprxroom.base;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;


import ir.greencode.androidmvprxroom.R;
import ir.greencode.androidmvprxroom.application.App;
import ir.greencode.androidmvprxroom.di.component.ApplicationComponent;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.view.View.LAYER_TYPE_SOFTWARE;

public abstract class BaseActivity extends AppCompatActivity {
    Dialog waitingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initDialog();
        onViewReady(savedInstanceState,getIntent(), LAYER_TYPE_SOFTWARE);
    }

    protected abstract int getContentView();
    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent, int LAYER_TYPE_SOFTWARE) {
        // to be used by child activities
        resolveDaggerDependency();
    }
    protected ApplicationComponent getApplicationComponent(){
        App app =
         (App) getApplication();
        return app.getApplicationComponent();
    }

    protected void resolveDaggerDependency() {
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initDialog() {
        waitingDialog = new Dialog(this);

        waitingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        waitingDialog.getWindow().setBackgroundDrawableResource(R.drawable.bgx_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(waitingDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        waitingDialog.getWindow().setAttributes(lp);
        waitingDialog.setContentView(R.layout.dialog_wait);
        waitingDialog.setCancelable(false);
    }
    public void setCancelable(){
        waitingDialog.setCancelable(true);
    }
    public void showWaitingDialog(boolean show) {
        if (waitingDialog != null)
            if (show)
                waitingDialog.show();
            else if (waitingDialog.isShowing())
                waitingDialog.dismiss();
    }
}
