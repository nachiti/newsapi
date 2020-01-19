package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Articles {
        @SerializedName("id")
        private String id;
        @SerializedName("source")
        private Source source;
        @SerializedName("author")
        private String author;
        @SerializedName("description")
        private String description;
        @SerializedName("title")
        private String titre;
        @SerializedName("url")
        private String url;
        @SerializedName("urlToImage")
        private String urlToimage;
        @SerializedName("publishedAt")
        private String publishedAt;


    private boolean isFavorite;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFavorite() {
        isFavorite = true;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToimage() {
        return urlToimage;
    }

    public void setUrlToimage(String urlToimage) {
        this.urlToimage = urlToimage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


}
