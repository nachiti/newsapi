package android.eservices.webrequests.presentation.bookdisplay.search.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjection1;
import android.eservices.webrequests.presentation.bookdisplay.search.NewsSearchPresenter;
import android.eservices.webrequests.presentation.bookdisplay.search.NewsSearchContract;

import android.eservices.webrequests.presentation.bookdisplay.search.adapter.NewsActionInterface;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.NewsAdapter;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.NewsItemViewModel;
import android.eservices.webrequests.presentation.bookdisplay.search.mapper.NewsToViewModelMapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/*
 * TODO : uncheck favorite selection in search results when favorite unchecked from Favorite fragment
 */
public class SearchFragment extends Fragment implements NewsSearchContract.View, NewsActionInterface {

    public static final String TAB_NAME = "Search";
    private View rootView;
    NewsSearchContract.Presenter newsSearchPresenter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private ProgressBar progressBar;

    private SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchView();
        setupRecyclerView();
        progressBar = rootView.findViewById(R.id.progress_bar);

        newsSearchPresenter = new NewsSearchPresenter(FakeDependencyInjection1.getNewsDisplayRepository(), new NewsToViewModelMapper());
        newsSearchPresenter.attachView(this);
    }

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    newsSearchPresenter.cancelSubscription();
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            newsSearchPresenter.searchNews(s);
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);

        newsAdapter = new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayNews(List<NewsItemViewModel> newsItemViewModelList) {
        progressBar.setVisibility(View.GONE);
        newsAdapter.bindViewModels(newsItemViewModelList);
    }

    @Override
    public void onFavoriteToggle(String bookId, boolean isFavorite) {
        if (isFavorite) {
            newsSearchPresenter.addNewsToFavorite(bookId);
        } else {
            newsSearchPresenter.removeNewsFromFavorites(bookId);
        }
    }

    @Override
    public void onNewsAddedToFavorites() {
        //Do nothing
    }

    @Override
    public void onNewsRemovedFromFavorites() {
        //Do nothing
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        newsSearchPresenter.detachView();
    }
}
