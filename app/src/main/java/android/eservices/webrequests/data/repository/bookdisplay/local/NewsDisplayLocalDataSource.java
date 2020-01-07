package android.eservices.webrequests.data.repository.bookdisplay.local;


        import android.eservices.webrequests.data.db.HeadlinesDatabase;
        import android.eservices.webrequests.data.entity.NewsEntity;

        import java.util.List;

        import io.reactivex.Completable;
        import io.reactivex.Flowable;
        import io.reactivex.Single;

public class NewsDisplayLocalDataSource {

    private HeadlinesDatabase headlinesDatabase;

    public NewsDisplayLocalDataSource(HeadlinesDatabase headlinesDatabase) {
        this.headlinesDatabase = headlinesDatabase;
    }

    public Flowable<List<NewsEntity>> loadFavorites() {
        return headlinesDatabase.headlinesDao().loadFavorites();
    }

    public Completable addNewsToFavorites(NewsEntity newsEntity) {
        return headlinesDatabase.headlinesDao().addNewsToFavorites(newsEntity);
    }

    public Completable deleteNewsFromFavorites(String id) {
        return headlinesDatabase.headlinesDao().deleteNewsFromFavorites(id);
    }

    public Single<List<String>> getFavoriteIdList() {
        return headlinesDatabase.headlinesDao().getFavoriteIdList();
    }
}
