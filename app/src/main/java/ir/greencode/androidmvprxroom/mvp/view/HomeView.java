package ir.greencode.androidmvprxroom.mvp.view;

import java.util.List;

import ir.greencode.androidmvprxroom.base.BaseView;
import ir.greencode.androidmvprxroom.mvp.model.GroupResponseResult;

public interface HomeView extends BaseView {
    void onCleareItems();

    void onGroupesLoaded(List<GroupResponseResult> results);

    void onGroupError(Throwable e);

    void onClickOnItem(GroupResponseResult groupResponseResult);
}
