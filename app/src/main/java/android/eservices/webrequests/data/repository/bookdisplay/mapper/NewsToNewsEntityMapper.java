

package android.eservices.webrequests.data.repository.bookdisplay.mapper;

        import android.eservices.webrequests.data.api.model.Headlines;
        import android.eservices.webrequests.data.entity.NewsEntity;
        import android.text.TextUtils;

public class NewsToNewsEntityMapper {

    public NewsEntity map(Headlines headlines) {

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle(headlines.getArticlesInfo().getTitre());

        if (headlines.getArticlesInfo().getAuthor() == null) {
            newsEntity.setAuthors("N.C.");
        } else {
            //newsEntity.setAuthors(TextUtils.join(", ", headlines.getArticlesInfo().getAuthor()));
            headlines.getArticlesInfo().getAuthor();
        }

        newsEntity.setDescription(headlines.getArticlesInfo().getDescription());
        newsEntity.setId(headlines.getArticlesInfo().getSource().getId());
        newsEntity.setPublishedDate(headlines.getArticlesInfo().getPublishedAt());
        newsEntity.setThumbUrl(headlines.getArticlesInfo().getUrl());


        return newsEntity;
    }
}
