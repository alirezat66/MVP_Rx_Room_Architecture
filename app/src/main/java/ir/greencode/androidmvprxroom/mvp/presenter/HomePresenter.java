package ir.greencode.androidmvprxroom.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ir.greencode.androidmvprxroom.api.MyApiService;
import ir.greencode.androidmvprxroom.base.BasePresenter;
import ir.greencode.androidmvprxroom.mvp.model.GroupResponse;
import ir.greencode.androidmvprxroom.mvp.model.GroupResponseResult;
import ir.greencode.androidmvprxroom.mvp.view.HomeView;
import retrofit2.HttpException;

public class HomePresenter  extends BasePresenter<HomeView>  implements Observer<GroupResponse> {
    @Inject
    protected MyApiService mApiService;

    @Inject
    public HomePresenter(){

    }
    public void getGroups() {
        getView().onShowDialog();

        Observable<GroupResponse> cakeResponseObservable = mApiService.getGroups(0,"1");
        subscribe(cakeResponseObservable,this);
    }
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(GroupResponse groupResponse) {
        List<GroupResponseResult> results =  new ArrayList<>();
        for(GroupResponseResult result : groupResponse.getResult()){
            results.add(result);
        }
        getView().onCleareItems();
        getView().onGroupesLoaded(results);
    }

    @Override
    public void onError(Throwable e) {
        String message = "";

        if(e instanceof HttpException)

        {
            HttpException httpException = (HttpException) e;

        /*if (httpException.code() == 400) {

        } else*/
            if (httpException.code() == 401)
                getView().onUnauthorizedError();
            else
                getView().onGroupError(e);
       /* else if (httpException.code() == 403)
            Log.d(TAG, "onError: FORBIDDEN");
        else if (httpException.code() == 404)
            Log.d(TAG, "onError: NOT FOUND");
        else if (httpException.code() == 500)
            Log.d(TAG, "onError: INTERNAL SERVER ERROR");
        else if (httpException.code() == 502)*/
            //  Log.d(TAG, "onError: BAD GATEWAY");
        }else {
            getView().onGroupError(e);
        }



    }

    @Override
    public void onComplete() {

    }
}
