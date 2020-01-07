package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.entity.NewsEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NewsEntity.class}, version = 1 ,exportSchema =false)
public abstract class HeadlinesDatabase extends RoomDatabase {
    public abstract HeadlinesDao headlinesDao();
}
