package com.san.agster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ZombieMovies extends AppCompatActivity {
    ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zombie_movies);
        ListView listView = (ListView) findViewById(R.id.ListView);
        String[] values = new String[]{"Night of the Comet - 1984", "Dead Snow - 2009", "Cemetery Man - 2004",
                "28 Weeks Later - 2007", "Night of the Creeps - 1986", "ParaNorman - 2012", "Zombieland - 2009",
                "Planet Terror - 2007", "Train to Busan - 2016", "The Beyond - 1981", "Day of the Dead - 1985",
                "The Serpent & the Rainbow - 1988", "Re-Animator - 1985", "Zombi 2 - 1971", "Dawn of the Dead - 2004",
                "Return of the Living Dead - 1985", "Dead Alive - 1992", "28 Days Later - 2002",
                "Night of the Living Dead - 1968", "Shawn of the Dead - 2004", "Dawn of the Dead - 1978"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String values = String.valueOf(parent.getItemAtPosition(position));
                //Intent intent = new Intent(view.getContext(), ZombieMoviesList.class);
                //startActivity(intent);

                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), ZombieMoviesList.class);

                    startActivityForResult(intent, 0);
                }

                if (position == 1) {
                    Intent intent = new Intent(view.getContext(), ZombieMoviesList.class);

                    startActivityForResult(intent, 1);
                }

            }

            ;

        });
    }
}



