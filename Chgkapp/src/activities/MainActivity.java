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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businesslogic.AppContext;
import helpers.Parser;
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

        Parser.parseSvoyakQuestions("1. Восход солнца над старым аванпортом в Гавре произвел неизгладимое впечатление" +
                " не только на ЭТОГО ХУДОЖНИКА, но и на всю мировую живопись. 2. ОН стал 'первым днем для русской кисти'," +
                " по словам Евгения Баратынского. 3. Странная конструкция из человеческих рук и ног, находящаяся в центре" +
                " ЭТОЙ КАРТИНЫ, своей формой напоминает очертание Испании. 4. Во время работы над ЭТОЙ КАРТИНОЙ художник" +
                " использовал следующее описание изображенной героини: 'Персты рук твоих тонкостны, очи твои молниеносны," +
                " и кидаешься ты на врагов аки лев'. 5. В 1844 году ОН совершил путешествие по Большой западной железной дороге." +
                " Путешествовать пришлось не только в хорошую погоду.", "1. [Клод] Моне. 2. \"Последний день Помпеи\". 3." +
                " \"Предчувствие гражданской войны\". 4. \"Боярыня Морозова\". 5. [Уильям] Тёрнер.", "1. Имеется в виду картина" +
                " \"Восход солнца. Впечатление\", положившая начало импрессионизму. 5. Под впечатлением от поездки была написана картина" +
                " \"Дождь, пар и скорость\".", "1. http://ru.wikipedia.org/wiki/Впечатление._Восходящее_солнце 2." +
                " http://ru.wikipedia.org/wiki/Последний_день_Помпеи 3. " +
                "http://ru.wikipedia.org/wiki/Мягкая_конструкция_с_варёными_бобами_(Предчувствие_гражданской_войны) 4. " +
                "http://ru.wikipedia.org/wiki/Боярыня_Морозова 5. http://ru.wikipedia.org/wiki/Дождь,_пар_и_скорость");

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

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id)
    {
        try {
            if (isOnline()) {
                DownloadRandomTask task = new DownloadRandomTask(this);
                task.execute();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Проверьте соединение с сетью", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
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
                AppContext appContext = new AppContext();

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

                tour = appContext.getRandomPackageCHGK(from, to, complexity);
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
            if (pd.isShowing())
            {
                pd.dismiss();
            }

            if (result == null || result.getQuestionsNum() == 0)
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

            DownloadTournamentRandomTask tournamentRandomTask = new DownloadTournamentRandomTask(activity, result);
            tournamentRandomTask.execute();
        }
    }

    public class DownloadTournamentRandomTask extends AsyncTask<Void, Void, Tournament>
    {
        ProgressDialog pd;
        Tour tour;

        public DownloadTournamentRandomTask(Activity activity, Tour tour)
        {
            pd = new ProgressDialog(activity);
            this.tour = tour;
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
        protected Tournament doInBackground(Void... params)
        {
            Tournament tournament = null;
            try {
                AppContext appContext = new AppContext();
                tournament = appContext.getTournamentByTourName(tour.getFileName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tournament;
        }

        @Override
        protected void onPostExecute(Tournament result)
        {
            if (pd.isShowing()) {
                pd.dismiss();
            }

            Intent intent = new Intent(MainActivity.this, mSamples[0].activityClass);

            intent.putExtra("tour", tour);
            intent.putExtra("tournament", result);

//            DataManager dataManager = new DataManager(getApplicationContext());
//            dataManager.SaveTour(tour);
//            Tour newTour = dataManager.LoadTour(tour.getTourId());

            startActivity(intent);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
