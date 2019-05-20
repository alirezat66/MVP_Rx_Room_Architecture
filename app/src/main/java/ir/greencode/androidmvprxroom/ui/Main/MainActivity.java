package ir.greencode.androidmvprxroom.ui.Main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.greencode.androidmvprxroom.R;
import ir.greencode.androidmvprxroom.base.BaseActivity;
import ir.greencode.androidmvprxroom.data.loc.MySharedPreferences;
import ir.greencode.androidmvprxroom.data.roomdb.Category;
import ir.greencode.androidmvprxroom.di.component.DaggerHomeComponent;
import ir.greencode.androidmvprxroom.di.module.HomeModule;
import ir.greencode.androidmvprxroom.mvp.model.GroupResponseResult;
import ir.greencode.androidmvprxroom.mvp.presenter.HomePresenter;
import ir.greencode.androidmvprxroom.mvp.view.HomeView;
import ir.greencode.androidmvprxroom.ui.Main.adapter.GroupAdapter;

public class MainActivity extends BaseActivity implements HomeView {


    @Inject
    protected HomePresenter mPresenter;
    @Inject
    MySharedPreferences mySharedPreferencesl;
    @BindView(R.id.list)
    RecyclerView list;
    GroupAdapter adapter;

    @Inject HomeViewModel viewModel;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent, int LAYER_TYPE_SOFTWARE) {
        super.onViewReady(savedInstanceState, intent, LAYER_TYPE_SOFTWARE);
        ButterKnife.bind(this);
        init();
      //  mPresenter.getGroups();
        viewModel.insertCat(new Category("ali",Color.RED,""));
        Toast.makeText(this, viewModel.getAllCats().size()+"", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerHomeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);
    }

    private void init() {

        // initial list and adapter
        list.setHasFixedSize(true);
      /*  StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        cakeList.setLayoutManager(_sGridLayoutManager);*/
        //list.setLayoutManager(new GridLayoutManager(this, 2));
        list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        // faverit_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new GroupAdapter(getLayoutInflater(), this);
       // adapter = new HorizentalGroupAdapter(getLayoutInflater(),this);
        //  mohemList.setAdapter(adapter);
        list.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onCleareItems() {
        adapter.cleareData();
        //clear data
    }

    @Override
    public void onGroupesLoaded(List<GroupResponseResult> results) {
        // set data to your adapter
        adapter.addData(results);
    }

    @Override
    public void onGroupError(Throwable e) {
        showWaitingDialog(false);
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickOnItem(GroupResponseResult groupResponseResult) {

    }

    @Override
    public void onShowDialog() {
        showWaitingDialog(true);

    }

    @Override
    public void onUnauthorizedError() {
        // exit from project
    }

}
