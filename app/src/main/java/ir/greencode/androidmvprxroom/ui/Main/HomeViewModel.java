package ir.greencode.androidmvprxroom.ui.Main;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Completable;
import ir.greencode.androidmvprxroom.data.roomdb.Category;
import ir.greencode.androidmvprxroom.data.roomdb.base.DataSource;

public class HomeViewModel extends ViewModel {
    private DataSource dataSource;

    public HomeViewModel(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Category> getAllCats(){
        return dataSource.getAll();
    }

    public Category getCat(Category category){
        return (Category) dataSource.get(category);
    }

    public void insertCat(final Category category){
            dataSource.insert(category);

    }

    public void delete(final Category cat){

            dataSource.delete(cat);

    }

    public void update(final Category category){

            dataSource.update(category);

    }
}
