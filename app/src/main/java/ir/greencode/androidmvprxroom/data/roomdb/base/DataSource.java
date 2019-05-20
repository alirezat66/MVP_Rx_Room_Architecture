package ir.greencode.androidmvprxroom.data.roomdb.base;

import java.util.List;

import io.reactivex.Flowable;

public interface DataSource <T> {
    T get(T t);
    List<T> getAll();
    void insert(T t);
    void delete(T t);
    void update(T t);
}
