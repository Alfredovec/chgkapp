package businesslogic;

import java.util.Date;

import models.entities.Tour;
import models.entities.Tournament;

/**
 * Created by Sergey on 30.01.2015.
 */
public class Context
{
    public Tour getRandomPackageCHGK(Date from, Date to, int complexity)
    {
        ContextRandomCHGK contextRandomCHGK = new ContextRandomCHGK();
        return contextRandomCHGK.get(from, to, complexity);
    }

    public Tournament getTournamentByTourName(String tourName)
    {
        ContextTournamentCHGK contextTournamentCHGK = new ContextTournamentCHGK();
        return contextTournamentCHGK.get(tourName);
    }
}
