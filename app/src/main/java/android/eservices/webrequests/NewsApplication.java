package android.eservices.webrequests;

import android.app.Application;
import android.eservices.webrequests.data.di.FakeDependencyInjection1;

import com.facebook.stetho.Stetho;

public class NewsApplication extends Application {
    public static final String API_KEY = "671a8601097943978312c9a289b3df5f";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection1.setContext(this);
    }
}
