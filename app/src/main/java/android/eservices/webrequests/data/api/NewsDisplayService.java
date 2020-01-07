package android.eservices.webrequests.data.api;

import android.eservices.webrequests.data.api.model.Headlines;
import android.eservices.webrequests.data.api.model.NewsSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsDisplayService {

    @GET("top-headlines")
    Single<NewsSearchResponse> searchNews(@Query("q") String keywords, @Query("key") String apiKey);//1

    @GET("top-headlines/source")
    Single<Headlines> getNews(
            @Path("name") String bookId,
            @Query("key") String apiKey);


}
