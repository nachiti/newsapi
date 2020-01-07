package android.eservices.webrequests.presentation.bookdisplay.favorite;

        import android.eservices.webrequests.data.entity.NewsEntity;
        import android.eservices.webrequests.data.repository.bookdisplay.NewsDisplayRepository;
        import android.eservices.webrequests.presentation.bookdisplay.favorite.mapper.NewsEntityToDetailViewModelMapper;

        import java.util.List;

        import io.reactivex.android.schedulers.AndroidSchedulers;
        import io.reactivex.disposables.CompositeDisposable;
        import io.reactivex.observers.DisposableCompletableObserver;
        import io.reactivex.schedulers.Schedulers;
        import io.reactivex.subscribers.ResourceSubscriber;

public class NewsFavoritePresenter implements NewsFavoriteContract.Presenter {

    private NewsDisplayRepository newsDisplayRepository;
    private NewsFavoriteContract.View view;
    private CompositeDisposable compositeDisposable;
    private NewsEntityToDetailViewModelMapper newsEntityToDetailViewModelMapper;

    public NewsFavoritePresenter(NewsDisplayRepository newsDisplayRepository, NewsEntityToDetailViewModelMapper newsEntityToDetailViewModelMapper) {
        this.newsDisplayRepository = newsDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.newsEntityToDetailViewModelMapper = newsEntityToDetailViewModelMapper;
    }

    @Override
    public void attachView(NewsFavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getFavorites() {
        compositeDisposable.add(newsDisplayRepository.getFavoriteNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<NewsEntity>>() {

                    @Override
                    public void onNext(List<NewsEntity> newsEntityList) {
                        view.displayFavorites(newsEntityToDetailViewModelMapper.map(newsEntityList));
                        System.out.println("BIND FAVORITES");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                    }
                }));

    }

    @Override
    public void removeNewsFromFavorites(String bookId) {
        compositeDisposable.add(newsDisplayRepository.removeNewsFromFavorites(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onNewsRemoved();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }
}
