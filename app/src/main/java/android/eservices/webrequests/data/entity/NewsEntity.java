package android.eservices.webrequests.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NewsEntity {
    @NonNull
    @PrimaryKey
    public String id;
    public String title;
    public String authors;
    private String description;
    private String thumbUrl;

    @ColumnInfo(name = "published_date")
    private String publishedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}

