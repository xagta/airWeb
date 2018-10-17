package news.airweb.fr.airwebnews.network;

import io.reactivex.Observable;
import news.airweb.fr.airwebnews.models.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xagta on 17/10/2018.
 */

public interface NetworkInterface {

    @GET("psg/psg.json")
    Observable<Response> getNews() ;
}
