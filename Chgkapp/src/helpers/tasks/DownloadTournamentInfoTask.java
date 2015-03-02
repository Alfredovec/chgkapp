package helpers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import activities.GameActivityCHGK;
import activities.GameActivitySI;
import activities.MainActivity;
import businesslogic.AppContext;
import models.entities.Tour;
import models.entities.Tournament;

/**
 * Created by Sergey on 20.02.2015.
 */
public class DownloadTournamentInfoTask extends AsyncTask<Void, Void, Tournament>
{
    private ProgressDialog pd;
    private Tour tour;
    private Class<?> targetClass;
    private Activity activity;

    public DownloadTournamentInfoTask(Activity activity, Class<?> targetClass, Tour tour)
    {
        pd = new ProgressDialog(activity);
        this.tour = tour;
        this.activity = activity;
        this.targetClass = targetClass;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        pd.setTitle("Случайный турнир");
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

        Intent intent = new Intent(activity.getApplicationContext(), targetClass);

        intent.putExtra("tour", tour);
        intent.putExtra("tournament", result);

        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
