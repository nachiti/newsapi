package android.eservices.webrequests.data.repository.bookdisplay;

import android.eservices.webrequests.data.api.model.NewsSearchResponse;
import android.eservices.webrequests.data.entity.NewsEntity;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface NewsDisplayRepository {

    Single<NewsSearchResponse> getNewsSearchResponse(String keywords);              //5

    Flowable<List<NewsEntity>> getFavoriteNews(); // si est fait ou pas :

    Completable addNewsToFavorites(String bookId);

    Completable removeNewsFromFavorites(String bookId);

}
