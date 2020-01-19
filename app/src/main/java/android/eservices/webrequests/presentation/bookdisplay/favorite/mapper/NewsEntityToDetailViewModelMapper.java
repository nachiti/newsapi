package android.eservices.webrequests.presentation.bookdisplay.favorite.mapper;


import android.eservices.webrequests.data.entity.NewsEntity;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailViewModel;
        import android.text.Html;

        import java.util.ArrayList;
        import java.util.List;

public class NewsEntityToDetailViewModelMapper {

    private NewsDetailViewModel map(NewsEntity newsEntity) {

        NewsDetailViewModel newsItemViewModel = new NewsDetailViewModel();

        newsItemViewModel.setNewsTitle(newsEntity.getTitle());
        newsItemViewModel.setNewsId(newsEntity.getId());
        newsItemViewModel.setIconUrl(newsEntity.getThumbUrl());
        if (newsEntity.getDescription() != null) {
            newsItemViewModel.setNewsDescription(Html.fromHtml(newsEntity.getDescription()).toString());
        }
        newsItemViewModel.setNewsAuthors(newsEntity.getAuthors());
        newsItemViewModel.setNewsPublishedDate("Published in " + newsEntity.getPublishedDate());

        /*String language = "This book is in " + languageMapper(newsEntity.getLanguage());
        newsItemViewModel.setBookLanguage(language);*/
        return newsItemViewModel;
    }

    public List<NewsDetailViewModel> map(List<NewsEntity> newsList) {
        List<NewsDetailViewModel> newsItemViewModelList = new ArrayList<>();
        for (NewsEntity articles : newsList) {
            newsItemViewModelList.add(map(articles));
        }
        return newsItemViewModelList;
    }

   /* private String languageMapper(String input) {
        switch (input) {
            case "en":
                return "English";
            case "fr":
                return "French";
            default:
                return "Unknown (" + input + ")";
        }
    }*/
}
