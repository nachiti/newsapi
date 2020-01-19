
package android.eservices.webrequests.presentation.bookdisplay.search.adapter;

public class NewsItemViewModel {

    private String articlesId;
    private String iconUrl;
    private String newsTitle;
    private String newsAuthors;
    private boolean isFavorite;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsAuthors() {
        return newsAuthors;
    }

    public void setNewsAuthors(String newsAuthors) {
        this.newsAuthors = newsAuthors;
    }

    public String getNewsId() {
        return articlesId;
    }

    public void setNewsId(String bookId) {
        this.articlesId = bookId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
