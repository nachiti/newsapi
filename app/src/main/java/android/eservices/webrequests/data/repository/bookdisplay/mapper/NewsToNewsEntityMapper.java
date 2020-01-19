

package android.eservices.webrequests.data.repository.bookdisplay.mapper;

        import android.eservices.webrequests.data.api.model.Articles;
        import android.eservices.webrequests.data.api.model.Headlines;
        import android.eservices.webrequests.data.entity.NewsEntity;
        import android.text.TextUtils;

public class NewsToNewsEntityMapper {

    public NewsEntity map(Articles articles) {

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle(articles.getTitre());

        if (articles.getAuthor() == null) {
            newsEntity.setAuthors("N.C.");
        } else {
            //newsEntity.setAuthors(TextUtils.join(", ", articles.getAuthor()));
            articles.getAuthor();
        }

        newsEntity.setDescription(articles.getDescription());
        newsEntity.setId(articles.getSource().getId());
        newsEntity.setPublishedDate(articles.getPublishedAt());
        newsEntity.setThumbUrl(articles.getUrlToimage());
        newsEntity.setPublishedDate(articles.getPublishedAt());


        return newsEntity;
    }
}
