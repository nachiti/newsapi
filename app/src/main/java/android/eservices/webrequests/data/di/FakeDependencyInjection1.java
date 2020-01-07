package android.eservices.webrequests.data.di;

import android.content.Context;
import android.eservices.webrequests.data.api.NewsDisplayService;
import android.eservices.webrequests.data.db.HeadlinesDatabase;
import android.eservices.webrequests.data.repository.bookdisplay.NewsDisplayDataRepository;
import android.eservices.webrequests.data.repository.bookdisplay.NewsDisplayRepository;
import android.eservices.webrequests.data.repository.bookdisplay.local.NewsDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.bookdisplay.mapper.NewsToNewsEntityMapper;
import android.eservices.webrequests.data.repository.bookdisplay.remote.NewsDisplayRemoteDataSource;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Please never do that in a production app. Ever.
 * For the purpose of our course, this is the best option to cover interesting topics as
 * we don't have time to dig into Dependency Injection frameworks such as the famous Dagger.
 * Singleton are compulsory for some classes, such as the one here. If you don't know why, then ask me.
 * Note that this god object doesn't handle Scopes nor component lifecycles so this really shouldn't be
 * the way to go when you master the craft of your software.
 */
public class FakeDependencyInjection1 {

    private static NewsDisplayService newsDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static NewsDisplayRepository newsDisplayRepository;
    private static HeadlinesDatabase headlinesDatabase;
    private static Context applicationContext;

    public static NewsDisplayRepository getNewsDisplayRepository() {
        if (newsDisplayRepository == null) {
            newsDisplayRepository = new NewsDisplayDataRepository(
                    new NewsDisplayLocalDataSource(getHeadlinesDatabase()),
                    new NewsDisplayRemoteDataSource(getNewsDisplayService()),
                    new NewsToNewsEntityMapper()
            );
        }
        return newsDisplayRepository;
    }

    public static NewsDisplayService getNewsDisplayService() {
        if (newsDisplayService == null) {
            newsDisplayService = getRetrofit().create(NewsDisplayService.class);
        }
        return newsDisplayService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static HeadlinesDatabase getHeadlinesDatabase() {
        if (headlinesDatabase == null) {
            headlinesDatabase = Room.databaseBuilder(applicationContext,
                    HeadlinesDatabase.class, "book-database").build();
        }
        return headlinesDatabase;
    }
}
