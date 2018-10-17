package news.airweb.fr.airwebnews.ui;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import news.airweb.fr.airwebnews.R;
import news.airweb.fr.airwebnews.models.News;

public class DetailsActivity extends AppCompatActivity {

    TextView titleTv, contentTv ;
    ImageView ivImage ;
    News news ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        titleTv = (TextView) findViewById(R.id.tvDetailsTitle);
        contentTv = (TextView) findViewById(R.id.tvDetailsContent);
        ivImage = (ImageView) findViewById(R.id.ivDetailsImage) ;

        if (getIntent().hasExtra("selectedNews")) {
            news = (News) Parcels.unwrap(getIntent().getParcelableExtra("selectedNews"));

            titleTv.setText(news.getTitle());
            contentTv.setText(news.getContent());
            Glide.with(this).load(news.getPicture()).into(ivImage) ;
        }


    }

}
