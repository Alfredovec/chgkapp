package helpers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import activities.GameActivityCHGK;
import businesslogic.AppContext;
import helpers.enums.GameType;
import models.entities.Tour;

/**
 * Created by Sergey on 20.02.2015.
 */

public class DownloadRandomCHGKTask extends AsyncTask<Void, Void, Tour>
{
    ProgressDialog pd;
    Activity activity;
    Class<GameActivityCHGK> targetClass;

    public DownloadRandomCHGKTask(Activity activity, Class<GameActivityCHGK> targetClass)
    {
        pd = new ProgressDialog(activity);
        this.activity = activity;
        this.targetClass = targetClass;
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

            tour = appContext.getRandomPackage(GameType.CHGK, from, to, complexity);
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
            DownloadRandomCHGKTask task = new DownloadRandomCHGKTask(activity, targetClass);
            task.execute();
            return;
        }

        DownloadTournamentInfoTask tournamentRandomTask = new DownloadTournamentInfoTask(activity, targetClass, result);
        tournamentRandomTask.execute();
    }
}
