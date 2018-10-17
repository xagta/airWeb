package news.airweb.fr.airwebnews.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.List;

import news.airweb.fr.airwebnews.R;
import news.airweb.fr.airwebnews.models.News;
import news.airweb.fr.airwebnews.models.Response;
import news.airweb.fr.airwebnews.ui.DetailsActivity;

/**
 * Created by xagta on 17/10/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {


    List<News> newsList ;
    Context context ;

    public NewsAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.news_row,parent,false);
        NewsHolder nh = new NewsHolder(v) ;
        return nh ;

    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {


        holder.newsTitle.setText(newsList.get(position).getTitle());
        Glide.with(context).load(newsList.get(position).getPicture()).into(holder.newsImage);

        final News current = newsList.get(position) ;

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("selectedNews", Parcels.wrap(current));

                Intent intent = new Intent(context,DetailsActivity.class) ;
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsHolder extends  RecyclerView.ViewHolder{

        TextView newsTitle ;
        ImageView newsImage;
        CardView cv ;

        public NewsHolder(View itemView) {


            super(itemView);
            newsImage =(ImageView) itemView.findViewById(R.id.newsImg);
            newsTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            cv = (CardView) itemView.findViewById(R.id.cv) ;


        }
    }
}
