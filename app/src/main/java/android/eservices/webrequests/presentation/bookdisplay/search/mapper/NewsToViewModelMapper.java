package android.eservices.webrequests.presentation.bookdisplay.search.mapper;

        import android.eservices.webrequests.data.api.model.Articles;
        import android.eservices.webrequests.presentation.bookdisplay.search.adapter.NewsItemViewModel;

        import java.util.ArrayList;
        import java.util.List;

public class NewsToViewModelMapper {

    private NewsItemViewModel map(Articles articles) {
        NewsItemViewModel newsItemViewModel = new NewsItemViewModel();

        newsItemViewModel.setNewsTitle(articles.getTitre()+"titre");
        newsItemViewModel.setNewsId(articles.getId());
        if (articles.getUrlToimage() != null) {
            newsItemViewModel.setIconUrl(articles.getUrlToimage());
        }
        newsItemViewModel.setFavorite(articles.isFavorite());
        if (articles.getAuthor() == null) {
            newsItemViewModel.setNewsAuthors("N.C.");
        } else {
            //newsItemViewModel.setNewsAuthors(TextUtils.join(", ", articles.getArticlesInfo().getAuthor());
            newsItemViewModel.setNewsAuthors(articles.getAuthor());
        }

        return newsItemViewModel;
    }

    public List<NewsItemViewModel> map(List<Articles> newsList) {
        List<NewsItemViewModel> newsItemViewModelList = new ArrayList<>();
        for (Articles articles : newsList) {
            newsItemViewModelList.add(map(articles));
        }
        return newsItemViewModelList;
    }
}
