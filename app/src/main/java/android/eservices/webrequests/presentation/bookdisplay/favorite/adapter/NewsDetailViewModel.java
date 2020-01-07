package android.eservices.webrequests.presentation.bookdisplay.favorite.adapter;

public class NewsDetailViewModel {

    private String newsId;
    private String iconUrl;
    private String newsTitle;
    private String newsAuthors;
    private String newsPublishedDate;
    private String newsDescription;

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
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsPublishedDate() {
        return newsPublishedDate;
    }

    public void setNewsPublishedDate(String newsPublishedDate) {
        this.newsPublishedDate = newsPublishedDate;
    }
    
    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }
}
