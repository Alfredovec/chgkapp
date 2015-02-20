package models.requests;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import helpers.enums.GameType;

public class RandomRequest extends BasicRequest implements Serializable
{
	private static final long serialVersionUID = -2412658681398053698L;

	public RandomRequest(GameType type, Date from, Date to, int complexity, int minQuestions, int maxQuestions)
	{
		if (from != null)
			this.setMinDate(from);
		if (to != null)
			this.setMaxDate(to);
		this.setComplexity(complexity);
        this.setMinQuestions(minQuestions);
        this.setMaxQuestions(maxQuestions);
        this.setType(type);
	}
	public RandomRequest()
	{
	}

	private int complexity;

    private int minQuestions;

    private int maxQuestions;

	public int getComplexity() 
	{
		return complexity;
	}

	public void setComplexity(int complexity) 
	{
		this.complexity = complexity;
	}

    public int getMinQuestions() {
        return minQuestions;
    }

    public void setMinQuestions(int minQuestions) {
        this.minQuestions = minQuestions;
    }

    public int getMaxQuestions() {
        return maxQuestions;
    }

    public void setMaxQuestions(int maxQuestions) {
        this.maxQuestions = maxQuestions;
    }
}
