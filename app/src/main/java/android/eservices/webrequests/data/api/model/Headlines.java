package android.eservices.webrequests.data.api.model;


public class Headlines {
    private String id;
    private Articles articlesInfo;
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

    public Articles getArticlesInfo() {
        return articlesInfo;
    }

}
