
package android.eservices.webrequests.data.repository.bookdisplay;

        import android.eservices.webrequests.data.api.model.Articles;
        import android.eservices.webrequests.data.api.model.NewsSearchResponse;
        import android.eservices.webrequests.data.entity.NewsEntity;
        import android.eservices.webrequests.data.repository.bookdisplay.local.NewsDisplayLocalDataSource;
        import android.eservices.webrequests.data.repository.bookdisplay.mapper.NewsToNewsEntityMapper;
        import android.eservices.webrequests.data.repository.bookdisplay.remote.NewsDisplayRemoteDataSource;


        import java.util.List;

        import io.reactivex.Completable;
        import io.reactivex.CompletableSource;
        import io.reactivex.Flowable;
        import io.reactivex.Single;
        import io.reactivex.functions.BiFunction;
        import io.reactivex.functions.Function;

public class NewsDisplayDataRepository implements NewsDisplayRepository {

    private NewsDisplayLocalDataSource newsDisplayLocalDataSource;
    private NewsDisplayRemoteDataSource newsDisplayRemoteDataSource;
    private NewsToNewsEntityMapper newsToNewsEntityMapper;

    public NewsDisplayDataRepository(NewsDisplayLocalDataSource newsDisplayLocalDataSource,
                                     NewsDisplayRemoteDataSource newsDisplayRemoteDataSource,
                                     NewsToNewsEntityMapper newsToNewsEntityMapper) {
        this.newsDisplayLocalDataSource = newsDisplayLocalDataSource;
        this.newsDisplayRemoteDataSource = newsDisplayRemoteDataSource;
        this.newsToNewsEntityMapper = newsToNewsEntityMapper;
    }

    @Override
    public Single<NewsSearchResponse> getNewsSearchResponse(String keywords) {                                          //3
        return newsDisplayRemoteDataSource.getNewsSearchResponse(keywords)
                .zipWith(newsDisplayLocalDataSource.getFavoriteIdList(), new BiFunction<NewsSearchResponse, List<String>, NewsSearchResponse>() {
                    @Override
                    public NewsSearchResponse apply(NewsSearchResponse newsSearchResponse, List<String> idList) throws Exception {
                        for (Articles articles : newsSearchResponse.getNewsList()) {
                            if (idList.contains(articles.getId())) {
                                articles.setFavorite();
                            }
                        }
                        return newsSearchResponse;
                    }
                });
    }

    @Override
    public Flowable<List<NewsEntity>> getFavoriteNews() {
        return newsDisplayLocalDataSource.loadFavorites();
    }

    @Override
    public Completable addNewsToFavorites(String bookId) {
        return newsDisplayRemoteDataSource.getNewsDetails(bookId)
                .map(new Function<Articles, NewsEntity>() {
                    @Override
                    public NewsEntity apply(Articles articles) throws Exception {
                        return newsToNewsEntityMapper.map(articles);
                    }
                })
                .flatMapCompletable(new Function<NewsEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(NewsEntity newsEntity) throws Exception {
                        return newsDisplayLocalDataSource.addNewsToFavorites(newsEntity);
                    }
                });
    }

    @Override
    public Completable removeNewsFromFavorites(String bookId) {
        return newsDisplayLocalDataSource.deleteNewsFromFavorites(bookId);
    }
}
