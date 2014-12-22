package businesslogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import models.entities.Question;
import models.requests.CHGKRandomRequest;

public class Context implements Serializable
{
	private static final long serialVersionUID = 3474846514992754866L;

	public ArrayList<Question> getRandomPackageCHGK(Date from, Date to, int complexity)
	{
		CHGKRandomRequest request = new CHGKRandomRequest(from, to, complexity);
		
		// Place for server request
		
		return new ArrayList<Question>();
	}
}
