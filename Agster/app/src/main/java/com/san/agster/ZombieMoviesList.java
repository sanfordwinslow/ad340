package com.san.agster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZombieMoviesList extends AppCompatActivity {
    TextView textView;
    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zombie_movie_list);
        TextView textView = (TextView) findViewById(R.id.textView);
        /*String[] movies = new String[] {
        "What would kids in the 1980s do if the apocalypse blew through the world without them noticing? Hang out at the mall, but of course. That’s the set-up for this very funny, quite dated horror-comedy, which begins when a quartet of adolescents lock themselves inside a projection booth at the mall’s multiplex. This somehow allows them to live through an extinction level event of some sort, which has also left roaming bands of murderous mutants. Catherine Mary Stewart of the equally inexplicable Weekend at Bernie’s leads the film, but it’s a movie of mood more than substance ultimately. Does the wealth-fueled naiveté of the average white teenager survive in a vacuum? Does it go away when they are being hunted for sustenance? It’s an interesting to watch on these terms and when the zombies show up, director Thom Eberhardt adds menace and a tight feel for suspense to the action sequences. And if we’re being honest, it belongs on this list for its soundtrack alone. The rest of this is just whip cream and cherries. – Chris Cabin",
        "With so many zombie movies over the years, eventually you’re going to run out of ways to freshen up the sub-genre. Enter Wirkola’s decidedly skewed take on zombies in this horror-comedy with plenty of guts. Sure, zombies are great movie monsters, but if you have Nazi zombies, well you’ve just doubled-down on the level of villainy (and pun-worthiness) in your picture! \nThis splatter-fest puts a Nordic spin on the traditional zombie by adding in elements of the Draugr, an undead creature from Scandinavian folklore that fiercely protects its treasure horde. In the case of Dead Snow, these draugr happen to be former SS soldiers who terrorized a Norwegian town and looted their belongings, only to be done in or chased into the freezing mountains by the villagers themselves. Dead Snow gets originality points for this, for sure. It’s also a very funny, gory, and satisfyingly violent movie with elements of Evil Dead and “teen sex/slasher” flicks scattered throughout. And if you like it, there’s more where that came from in the sequel, Dead Snow: Red vs Dead. – Dave Trumbore"};
*/
        /*ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < movies.length; i++) {
            list.add(movies[i]);
        }*/

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //        android.R.layout.simple_list_item_1, movies);

        //textView.setAdapter(adapter);


    }


}

