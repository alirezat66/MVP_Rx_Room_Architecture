package ir.greencode.androidmvprxroom.data.roomdb;

import java.util.List;

import io.reactivex.Flowable;
import ir.greencode.androidmvprxroom.data.roomdb.base.DataSource;

public class LocalCategoryDataSource implements DataSource<Category> {
    private CategoryDao categoryDao;

    public LocalCategoryDataSource(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }



    @Override
    public Category get(Category category) {
        return categoryDao.specialCat(category.getName());
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.listOfCats();
    }

    @Override
    public void insert(Category category) {
        categoryDao.insertCat(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.nukeTable();
    }

    @Override
    public void update(Category category) {
        categoryDao.insertCat(category);
    }
}
