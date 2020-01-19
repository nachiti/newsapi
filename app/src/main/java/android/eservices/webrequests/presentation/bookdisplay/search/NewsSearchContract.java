package android.eservices.webrequests.presentation.bookdisplay.search;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.NewsItemViewModel;

import java.util.List;


public interface NewsSearchContract {

    interface View {
        void displayNews(List<NewsItemViewModel> newsItemViewModelList);

        void onNewsAddedToFavorites();

        void onNewsRemovedFromFavorites();
    }

    interface Presenter {
        void searchNews(String keywords);

        void attachView(View view);

        void cancelSubscription();

        void addNewsToFavorite(String articlesId);

        void removeNewsFromFavorites(String articlesId);

        void detachView();
    }
}
