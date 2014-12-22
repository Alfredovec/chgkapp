package models.requests;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class CHGKRandomRequest extends BasicRequest implements Serializable
{
	private static final long serialVersionUID = -2412658681398053698L;

	public CHGKRandomRequest(Date from, Date to, int complexity)
	{
		if (from != null)
			this.setMinDate(from);
		if (to != null)
			this.setMaxDate(to);
		this.setComplexity(complexity);
	}
	
	private int complexity;
	
	private String ToString()
	{
		Random rand = new Random();
		String random = "";
		for (int i = 0; i < 10; i++)
		{
			random += String.valueOf(rand.nextInt(10));
		}
		return "http://db.chgk.info/random/from_1990-01-02/to_2014-12-21/types1/complexity1/" +
				random + "/limit1";
	}

	public int getComplexity() 
	{
		return complexity;
	}

	public void setComplexity(int complexity) 
	{
		this.complexity = complexity;
	}
}
