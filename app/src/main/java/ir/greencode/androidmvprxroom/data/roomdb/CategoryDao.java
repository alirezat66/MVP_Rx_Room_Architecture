package ir.greencode.androidmvprxroom.data.roomdb;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CategoryDao {

    @Query("select * from  category")
    List<Category> listOfCats();

    @Query("select * from category where name= :name limit 1")
    Category specialCat(String name);

    @Insert(onConflict = REPLACE)
    void insertCat(Category category);

    @Query("DELETE FROM category")
    public void nukeTable();
}
