package android.eservices.webrequests.data.repository.bookdisplay.remote;

import android.eservices.webrequests.NewsApplication;
import android.eservices.webrequests.data.api.NewsDisplayService;
import android.eservices.webrequests.data.api.model.Articles;
import android.eservices.webrequests.data.api.model.Headlines;
import android.eservices.webrequests.data.api.model.NewsSearchResponse;

import io.reactivex.Single;

public class NewsDisplayRemoteDataSource {

    private NewsDisplayService newsDisplayService;

    public NewsDisplayRemoteDataSource(NewsDisplayService newsDisplayService) {
        this.newsDisplayService = newsDisplayService;
    }

    public Single<NewsSearchResponse> getNewsSearchResponse(String keywords) {      //2
        return newsDisplayService.searchNews(keywords, NewsApplication.API_KEY);
    }

    public Single<Articles> getNewsDetails(String bookId) {
       // return newsDisplayService.getSource(bookId, NewsApplication.API_KEY);
        return newsDisplayService.getNews(bookId, NewsApplication.API_KEY);
    }
}

