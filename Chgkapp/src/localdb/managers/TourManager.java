package localdb.managers;

import android.content.Context;
import android.database.Cursor;

import localdb.adapters.TourAdapter;
import models.entities.Tour;

/**
 * Created by Sergey on 03.02.2015.
 */
public class TourManager
{
    private TourAdapter tourAdapter;

    public TourManager(Context context)
    {
        tourAdapter = new TourAdapter(context);
    }

    public void saveTour(Tour tour)
    {
        tourAdapter.open();

        tourAdapter.insertRecord(tour);

        tourAdapter.close();
    }

    public Tour getTour(int tourId)
    {
        tourAdapter.open();

        Tour tour = new Tour();

        Cursor cur = tourAdapter.getTour(tourId);

        if (cur.moveToFirst())
        {
            tour.setTourId(tourId);
            tour.setParentId(cur.getInt(cur.getColumnIndex("parentid")));
            tour.setTitle(cur.getString(cur.getColumnIndex("title")));
            tour.setNumber(cur.getInt(cur.getColumnIndex("number")));
            tour.setTextId(cur.getString(cur.getColumnIndex("textid")));
            tour.setQuestionsNum(cur.getInt(cur.getColumnIndex("questionsnum")));
            tour.setType(cur.getString(cur.getColumnIndex("type")));
            tour.setFileName(cur.getString(cur.getColumnIndex("filename")));
        }

        tourAdapter.close();

        return tour;
    }
}
