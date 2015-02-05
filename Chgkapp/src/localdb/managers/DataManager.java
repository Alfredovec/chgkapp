package localdb.managers;

import android.content.Context;

import models.entities.Question;
import models.entities.Tour;

/**
 * Created by Sergey on 03.02.2015.
 */

public class DataManager
{
    private Context context;

    public DataManager(Context context)
    {
        this.context = context;
    }

    public void SaveTour(Tour tour)
    {
        TourManager tourManager = new TourManager(context);
        tourManager.saveTour(tour);

        QuestionManager questionManager = new QuestionManager(context);
        for (Question question : tour.getQuestions())
        {
            questionManager.saveQuestion(question);
        }
    }

    public Tour LoadTour(int tourId)
    {
        TourManager tourManager = new TourManager(context);
        Tour tour = tourManager.getTour(tourId);

        QuestionManager questionManager = new QuestionManager(context);
        tour.setQuestions(questionManager.getQuestions(tourId));

        return tour;
    }
}
