package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.entity.NewsEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface HeadlinesDao {

    @Query("SELECT * from newsentity")
    Flowable<List<NewsEntity>> loadFavorites();

    @Insert
    public Completable addNewsToFavorites(NewsEntity newsEntity);

    @Query("DELETE FROM newsentity WHERE id = :id")
    public Completable deleteNewsFromFavorites(String id);

    @Query("SELECT id from newsentity")
    Single<List<String>> getFavoriteIdList();
}
