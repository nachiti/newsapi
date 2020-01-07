
package android.eservices.webrequests.presentation.bookdisplay.favorite;

        import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailViewModel;

        import java.util.List;

public interface NewsFavoriteContract {

    interface View {
        void displayFavorites(List<NewsDetailViewModel> newsDetailViewModelList);

        void onNewsRemoved();
    }

    interface Presenter {
        void attachView(View view);

        void getFavorites();

        void removeNewsFromFavorites(String bookId);

        void detachView();
    }
}