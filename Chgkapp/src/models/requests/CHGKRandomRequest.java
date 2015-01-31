package models.requests;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class CHGKRandomRequest extends BasicRequest implements Serializable
{
	private static final long serialVersionUID = -2412658681398053698L;

//	public CHGKRandomRequest(Date from, Date to, int complexity, int minQuestions, int MaxQuestions)
//	{
//		if (from != null)
//			this.setMinDate(from);
//		if (to != null)
//			this.setMaxDate(to);
//		this.setComplexity(complexity);
//        this.setMinQuestions(minQuestions);
//        this.setMaxQuestions(maxQuestions);
//	}
    public CHGKRandomRequest(Date from, Date to, int complexity)
    {
        if (from != null)
            this.setMinDate(from);
        if (to != null)
            this.setMaxDate(to);
        this.setComplexity(complexity);
    }
	public CHGKRandomRequest() 
	{
	}

	private int complexity;

//    private int minQuestions;
//
//    private int maxQuestions;
	
	@Override
	public String toString()
	{
		Random rand = new Random();
		String random = "";
		for (int i = 0; i < 10; i++)
		{
			random += String.valueOf(rand.nextInt(10));
		}
		return "http://db.chgk.info/random/from_2000-10-6/to_2010-10-4/types1/complexity1/" +
				"9999999999" + "/limit1";
	}

	public int getComplexity() 
	{
		return complexity;
	}

	public void setComplexity(int complexity) 
	{
		this.complexity = complexity;
	}

//    public int getMinQuestions() {
//        return minQuestions;
//    }
//
//    public void setMinQuestions(int minQuestions) {
//        this.minQuestions = minQuestions;
//    }
//
//    public int getMaxQuestions() {
//        return maxQuestions;
//    }
//
//    public void setMaxQuestions(int maxQuestions) {
//        this.maxQuestions = maxQuestions;
//    }
}
