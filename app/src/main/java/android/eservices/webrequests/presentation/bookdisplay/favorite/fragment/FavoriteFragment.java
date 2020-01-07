package android.eservices.webrequests.presentation.bookdisplay.favorite.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjection1;
import android.eservices.webrequests.presentation.bookdisplay.favorite.NewsFavoriteContract;
import android.eservices.webrequests.presentation.bookdisplay.favorite.NewsFavoritePresenter;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailAdapter;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailActionInterface;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailViewModel;
import android.eservices.webrequests.presentation.bookdisplay.favorite.mapper.NewsEntityToDetailViewModelMapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//=====================================================================deja modifier ================> News
public class FavoriteFragment extends Fragment implements NewsFavoriteContract.View, NewsDetailActionInterface {

    public static final String TAB_NAME = "Favorites";
    private View rootView;
    NewsFavoriteContract.Presenter newsFavoritePresenter;
    private RecyclerView recyclerView;
    private NewsDetailAdapter newsAdapter;

    private FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();

        newsFavoritePresenter = new NewsFavoritePresenter(FakeDependencyInjection1.getNewsDisplayRepository(), new NewsEntityToDetailViewModelMapper());
        newsFavoritePresenter.attachView(this);
        newsFavoritePresenter.getFavorites();
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        newsAdapter = new NewsDetailAdapter(this);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayFavorites(List<NewsDetailViewModel> newsDetailViewModelList) {
        newsAdapter.bindViewModels(newsDetailViewModelList);

    }

    @Override
    public void onRemoveFavorite(String bookId) {
        newsFavoritePresenter.removeNewsFromFavorites(bookId);
        System.out.println("Remove book " + bookId);
    }

    @Override
    public void onNewsRemoved() {
        //Do nothing yet
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        newsFavoritePresenter.detachView();
    }
}
