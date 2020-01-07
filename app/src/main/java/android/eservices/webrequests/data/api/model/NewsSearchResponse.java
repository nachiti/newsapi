package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsSearchResponse {

    @SerializedName("items") // ***** > da fare
    List<Headlines> headlinesList;

    int totalItems;

    public List<Headlines> getNewsList() {
        return headlinesList;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
