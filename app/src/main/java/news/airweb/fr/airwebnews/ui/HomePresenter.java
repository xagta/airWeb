package news.airweb.fr.airwebnews.ui;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import news.airweb.fr.airwebnews.models.Response;
import news.airweb.fr.airwebnews.network.NetworkClient;
import news.airweb.fr.airwebnews.network.NetworkInterface;

/**
 * Created by xagta on 17/10/2018.
 */

public class HomePresenter implements HomePresenterInterface {

    private String TAG = "HomePresenter";

    HomeViewInterface hvi;

    public HomePresenter(HomeViewInterface hvi) {
        this.hvi = hvi;
    }

    @Override
    public void getNews() {
        getObservable().subscribeWith(getObserver());
    }


    public Observable<Response> getObservable() {

        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Response> getObserver()
    {
        return new DisposableObserver<Response>() {
            @Override
            public void onNext(Response newsResponse) {
                Log.d(TAG,"OnNext "+newsResponse.getNews().size()) ;
                hvi.displayNews(newsResponse) ;
            }

            @Override
            public void onError(Throwable e) {

                Log.d(TAG,"Error "+ e) ;
                e.printStackTrace();
                hvi.displayError("Error fetching news Data");

            }

            @Override
            public void onComplete() {
                hvi.showToast("news loaded successfully");
            }
        };
    }

}
