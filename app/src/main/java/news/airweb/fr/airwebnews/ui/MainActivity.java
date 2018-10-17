package news.airweb.fr.airwebnews.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import news.airweb.fr.airwebnews.R;
import news.airweb.fr.airwebnews.adapters.NewsAdapter;
import news.airweb.fr.airwebnews.models.Response;

public class MainActivity extends AppCompatActivity implements  HomeViewInterface{

    RecyclerView rvNews ;
    private String TAG = "MainActivity" ;
    RecyclerView.Adapter adapter ;
    HomePresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNews = (RecyclerView) findViewById(R.id.rvNews) ;

        setupMVP();
        setupViews();
        getNewsList();
    }


    private void setupMVP() {
        presenter = new HomePresenter(this);
    }

    private void setupViews(){

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvNews.setLayoutManager(llm);
    }

    private void getNewsList() {

        presenter.getNews();

    }

    @Override
    public void showToast(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();

    }

    @Override
    public void displayNews(Response newsResponse) {
        if(newsResponse!=null) {
            Log.d(TAG,newsResponse.getNews().get(1).getTitle());
            adapter = new NewsAdapter(newsResponse.getNews(), MainActivity.this);

            rvNews.setAdapter(adapter);
        }else{
            Log.d(TAG,"News response null");
        }
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }
}
