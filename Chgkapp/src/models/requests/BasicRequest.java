package models.requests;

import java.io.Serializable;
import java.util.Date;

public abstract class BasicRequest implements Serializable 
{
	private Date minDate;
	
	private Date maxDate;

	public Date getMinDate() 
	{
		return minDate;
	}

	public void setMinDate(Date minDate) 
	{
		this.minDate = minDate;
	}

	public Date getMaxDate() 
	{
		return maxDate;
	}

	public void setMaxDate(Date maxDate) 
	{
		this.maxDate = maxDate;
	}
}