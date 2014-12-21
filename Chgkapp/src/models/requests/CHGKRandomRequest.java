package models.requests;

import java.io.Serializable;
import java.util.Date;

public class CHGKRandomRequest extends BasicRequest implements Serializable
{
	public CHGKRandomRequest(Date from, Date to, int complexity)
	{
		if (from != null)
			this.setMinDate(from);
		if (to != null)
			this.setMaxDate(to);
		this.setComplexity(complexity);
	}
	
	private int complexity;

	public int getComplexity() 
	{
		return complexity;
	}

	public void setComplexity(int complexity) 
	{
		this.complexity = complexity;
	}
}
