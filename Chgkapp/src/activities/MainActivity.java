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
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import businesslogic.Context;
import models.entities.Question;
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
                        Intent intent = new Intent(getApplicationContext(), RandomPreferenceActivity.class);
                        startActivity(intent);
                    }
                });
                return view;
            }
        };

        setListAdapter(adapter);

    }
    Tour tour;
    Tournament tournament;

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id)
    {
        tour = new Tour();
        tournament = new Tournament();

        DownloadRandomTask task = new DownloadRandomTask(this);
        task.execute();
    }
    public class DownloadRandomTask extends AsyncTask<Void, Void, Tour>
    {
        ProgressDialog pd;
        Activity activity;

        public DownloadRandomTask(Activity activity)
        {
            pd = new ProgressDialog(activity);
            this.activity = activity;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pd.setTitle("Случайный пакет ЧГК");
            pd.setMessage("Загрузка пакета...");
            pd.show();
        }

        @Override
        protected Tour doInBackground(Void... params)
        {
            Tour tour = new Tour();
            try
            {
                Context context = new Context();

                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
                boolean isCustom = sp.getBoolean("dateSwitcher", false);
                Integer complexity = Integer.parseInt(sp.getString("complexity", "1"));

                String fromStr;
                String toStr;
                if (isCustom)
                {
                    fromStr = sp.getString("dateFrom", "1990.00.01");
                    toStr = sp.getString("dateTo", "2015.00.01");
                }
                else
                {
                    fromStr = "1990.00.01";
                    toStr = "2015.00.01";
                }

                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                Date from = format.parse(fromStr);
                Date to = format.parse(toStr);

                tour = context.getRandomPackageCHGK(from, to, complexity);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return tour;
        }

        @Override
        protected void onPostExecute(Tour result)
        {
            tour = result;

            if (pd.isShowing())
            {
                pd.dismiss();
            }

            if (tour.getQuestionsNum() == 0)
            {
                DownloadRandomTask task = new DownloadRandomTask(activity);
                task.execute();
                return;
            }

//            boolean neinteresno = true;
//            for (Question question : tour.getQuestions())
//            {
//                neinteresno &= question.getPictureAnswer() == null;
//            }
//
//            if (neinteresno)
//            {
//                DownloadRandomTask task = new DownloadRandomTask(activity);
//                task.execute();
//                return;
//            }

            DownloadTournamentRandomTask task2 = new DownloadTournamentRandomTask(activity);
            task2.execute(tour.getFileName());
        }
    }

    public class DownloadTournamentRandomTask extends AsyncTask<String, Void, Tournament>
    {
        ProgressDialog pd;

        public DownloadTournamentRandomTask(Activity activity)
        {
            pd = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pd.setTitle("Случайный пакет ЧГК");
            pd.setMessage("Загрузка информации о турнире...");
            pd.show();
        }

        @Override
        protected Tournament doInBackground(String... params)
        {
            try
            {
                Context context = new Context();

                tournament = context.getTournamentByTourName(params[0]);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return tournament;
        }

        @Override
        protected void onPostExecute(Tournament result)
        {
            tournament = result;

            if (pd.isShowing()) {
                pd.dismiss();
            }

            Intent intent = new Intent(MainActivity.this, mSamples[0].activityClass);

            intent.putExtra("tour", tour);
            intent.putExtra("tournament", tournament);


            startActivity(intent);
        }
    }
}
