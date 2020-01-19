package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsSearchResponse {

    @SerializedName("articles") // ***** > da fare
    List<Articles> headlinesList;

    int totalItems;

    public List<Articles> getNewsList() {
        return headlinesList;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
