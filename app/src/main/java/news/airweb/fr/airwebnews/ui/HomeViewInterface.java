package news.airweb.fr.airwebnews.ui;

import news.airweb.fr.airwebnews.models.Response;

/**
 * Created by xagta on 17/10/2018.
 */

public interface HomeViewInterface {

    void showToast(String s);
    void displayNews(Response newsResponse);
    void displayError(String s);
}
