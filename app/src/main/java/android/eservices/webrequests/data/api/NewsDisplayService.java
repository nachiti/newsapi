package android.eservices.webrequests.data.api;

import android.eservices.webrequests.data.api.model.Articles;
import android.eservices.webrequests.data.api.model.NewsSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsDisplayService {

 /*   @GET("volumes")
    Single<BookSearchResponse> searchBooks(
            @Query("q") String keywords,
            @Query("key") String apiKey);

    @GET("volumes/{bookId}")
    Single<Book> getBook(
            @Path("bookId") String bookId,
            @Query("key") String apiKey);*/


    @GET("top-headlines")
    Single<NewsSearchResponse> searchNews(
            @Query("q") String keywords,
            @Query("apiKey") String apiKey);//1

    @GET("top-headlines/{language}")
    Single<Articles> getNews(
            @Path("language") String articlesId,
            @Query("apiKey") String apiKey);
}
