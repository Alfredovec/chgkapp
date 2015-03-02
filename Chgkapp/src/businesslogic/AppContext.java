package businesslogic;

import java.io.IOException;
import java.util.Date;

import helpers.enums.GameType;
import models.entities.Tour;
import models.entities.Tournament;
import models.requests.RandomRequest;

/**
 * Created by Sergey on 30.01.2015.
 */
public class AppContext
{
    public Tour getRandomPackage(GameType type, Date from, Date to, int complexity) throws IOException, ClassNotFoundException
    {
        ContextRandom contextRandomCHGK = new ContextRandom();
        return contextRandomCHGK.get(new RandomRequest(type, from, to, complexity, 5, 25));
    }

    public Tournament getTournamentByTourName(String tourName) throws IOException, ClassNotFoundException
    {
        ContextTournament contextTournamentCHGK = new ContextTournament();
        return contextTournamentCHGK.get(tourName);
    }
}
