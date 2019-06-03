package com.san.agster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class DetailActivity extends Activity {
    private static final String TAG = "MovieDetail goes: ";



    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Intent intent = getIntent();
        String[] movieInfo = intent.getStringArrayExtra(ZombieViewActivity.RESULT);
        setContentView(R.layout.movie_detail_description);
        TextView title = (TextView)findViewById(R.id.title);
        TextView year = (TextView)findViewById(R.id.year);
        TextView director = (TextView)findViewById(R.id.director);
        TextView description = (TextView)findViewById(R.id.description);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        Picasso.get().load(movieInfo[3]).into(imageView);
        title.setText(movieInfo[0]);
        year.setText(movieInfo[1]);
        director.setText(movieInfo[2]);
        description.setText(movieInfo[4]);

    }

}
