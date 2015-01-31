/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package activities;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import businesslogic.Context;
import models.entities.Tour;
import models.entities.Tournament;
import ru.chgkapp.R;

/**
 * The launchpad activity for this sample project. This activity launches other activities that
 * demonstrate implementations of common animations.
 */
public class MainActivity extends ListActivity {

    /**
     * This class describes an individual sample (the sample title, and the activity class that
     * demonstrates this sample).
     */
    private class Sample {
        private CharSequence title;
        private Class<? extends Activity> activityClass;

        public Sample(int titleResId, Class<? extends Activity> activityClass) {
            this.activityClass = activityClass;
            this.title = getResources().getString(titleResId);
        }

        @Override
        public String toString() {
            return title.toString();
        }
    }

    /**
     * The collection of all samples in the app. This gets instantiated in {@link
     * #onCreate(android.os.Bundle)} because the {@link activities.MainActivity.Sample} constructor needs access to {@link
     * android.content.res.Resources}.
     */
    private static Sample[] mSamples;
    ProgressDialog progressDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the list of samples.
        mSamples = new Sample[]
        {
                new Sample(R.string.title_game, GameActivity.class),
                new Sample(R.string.title_game, GameActivity.class),
                new Sample(R.string.title_game, GameActivity.class),
                new Sample(R.string.title_game, GameActivity.class),
                new Sample(R.string.title_game, GameActivity.class),
                new Sample(R.string.title_game, GameActivity.class)
        };

        ArrayAdapter<Sample> adapter = new ArrayAdapter<Sample>(this,
                R.layout.list_item_menu,
                android.R.id.text1,
                mSamples)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                ImageButton ib = (ImageButton) view.findViewById(R.id.delete_button);
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "azaza", Toast.LENGTH_SHORT).show();
                    }
                });
                return view;
            }
        };

        setListAdapter(adapter);

    }

    Intent intent;

    private void azaza()
    {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    int a = 4;
                    while(a == 2)
                        sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id)
    {
        intent = new Intent();
        intent = new Intent(MainActivity.this, mSamples[position].activityClass);

        Date from = new Date();
        from.setDate(10);
        from.setMonth(10);
        from.setYear(2000);

        Date to = new Date();
        to.setDate(10);
        to.setMonth(10);
        to.setYear(2010);

        Context context = new Context();
        Tour tour = new Tour();
        for (int i = 0; i < 10 && tour.getQuestionsNum() == 0; i++)
            tour = context.getRandomPackageCHGK(from, to, 1);

        Tournament tournament = new Tournament();
        for (int i = 0; i < 10 && tournament.getQuestionsNum() == 0; i++)
            tournament = context.getTournamentByTourName(tour.getFileName());

        intent.putExtra("tour", tour);
        intent.putExtra("tournament", tournament);

        startActivity(intent);
    }
}
