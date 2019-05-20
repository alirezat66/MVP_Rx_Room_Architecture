package ir.greencode.androidmvprxroom.data.roomdb.base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ir.greencode.androidmvprxroom.data.roomdb.Category;
import ir.greencode.androidmvprxroom.data.roomdb.CategoryDao;
import ir.greencode.androidmvprxroom.utility.Constant;

@Database(entities = {Category.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract CategoryDao categoryDao();
    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constant.DB_NAME)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
