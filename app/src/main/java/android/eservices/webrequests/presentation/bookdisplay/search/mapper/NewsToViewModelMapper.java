package android.eservices.webrequests.presentation.bookdisplay.search.mapper;

        import android.eservices.webrequests.data.api.model.Headlines;
        import android.eservices.webrequests.presentation.bookdisplay.search.adapter.NewsItemViewModel;

        import java.util.ArrayList;
        import java.util.List;

public class NewsToViewModelMapper {

    private NewsItemViewModel map(Headlines headlines) {
        NewsItemViewModel newsItemViewModel = new NewsItemViewModel();
        newsItemViewModel.setNewsTitle(headlines.getArticlesInfo().getTitre());
        newsItemViewModel.setNewsId(headlines.getId());
        if (headlines.getArticlesInfo().getUrlToimage() != null) {
            newsItemViewModel.setIconUrl(headlines.getArticlesInfo().getUrlToimage());
        }
        newsItemViewModel.setFavorite(headlines.isFavorite());
        if (headlines.getArticlesInfo().getAuthor() == null) {
            newsItemViewModel.setNewsAuthors("N.C.");
        } else {
            //newsItemViewModel.setNewsAuthors(TextUtils.join(", ", headlines.getArticlesInfo().getAuthor());
            newsItemViewModel.setNewsAuthors(headlines.getArticlesInfo().getAuthor());
        }

        return newsItemViewModel;
    }

    public List<NewsItemViewModel> map(List<Headlines> newsList) {
        List<NewsItemViewModel> newsItemViewModelList = new ArrayList<>();
        for (Headlines headlines : newsList) {
            newsItemViewModelList.add(map(headlines));
        }
        return newsItemViewModelList;
    }
}
