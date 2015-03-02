package models.requests;

import java.io.Serializable;
import java.util.Date;

import helpers.enums.GameType;

public abstract class BasicRequest implements Serializable 
{
	private static final long serialVersionUID = 8029541479287789398L;

    private GameType type;

	private Date minDate;
	
	private Date maxDate;

    public String toString()
    {
        return type.toString() + "Request";
    }

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

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }
}
