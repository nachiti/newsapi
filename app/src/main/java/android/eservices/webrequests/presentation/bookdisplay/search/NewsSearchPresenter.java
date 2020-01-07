package android.eservices.webrequests.presentation.bookdisplay.search;
 import android.eservices.webrequests.data.api.model.NewsSearchResponse;
        import android.eservices.webrequests.data.repository.bookdisplay.NewsDisplayRepository;
        import android.eservices.webrequests.presentation.bookdisplay.search.mapper.NewsToViewModelMapper;

        import io.reactivex.android.schedulers.AndroidSchedulers;
        import io.reactivex.disposables.CompositeDisposable;
        import io.reactivex.observers.DisposableCompletableObserver;
        import io.reactivex.observers.DisposableSingleObserver;
        import io.reactivex.schedulers.Schedulers;

public class NewsSearchPresenter implements NewsSearchContract.Presenter {

    private NewsDisplayRepository newsDisplayRepository;
    private NewsSearchContract.View view;
    private CompositeDisposable compositeDisposable;
    private NewsToViewModelMapper newsToViewModelMapper;

    public NewsSearchPresenter(NewsDisplayRepository newsDisplayRepository, NewsToViewModelMapper newsToViewModelMapper) {
        this.newsDisplayRepository = newsDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.newsToViewModelMapper = newsToViewModelMapper;
    }
    // il attahce a la vue : chaque fois que on tape a apres ab apres aba apres abab => ici il va prendere le derniere abab et annuler les autres
    @Override
    public void searchNews(String keywords) {                                          //4
        compositeDisposable.clear();
        compositeDisposable.add(newsDisplayRepository.getNewsSearchResponse(keywords) // recupere les donnes a partire de data repository et a chaque fois on ajout un disposible
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<NewsSearchResponse>() { // single de BookSearchResponse

                    @Override
                    public void onSuccess(NewsSearchResponse newsSearchResponse) {
                        // work with the resulting todos
                        view.displayNews(newsToViewModelMapper.map(newsSearchResponse.getNewsList()));// afficher les livres
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        System.out.println(e.toString());
                    }
                }));

    }

    @Override
    public void addNewsToFavorite(String bookId) {
        compositeDisposable.add(newsDisplayRepository.addNewsToFavorites(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onNewsAddedToFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

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
                        view.onNewsRemovedFromFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void attachView(NewsSearchContract.View view) {
        this.view = view;
    }//

    @Override
    public void cancelSubscription() {
        compositeDisposable.clear();
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }
}
