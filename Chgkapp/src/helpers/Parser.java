package helpers;


import java.util.ArrayList;

import models.entities.Question;
import models.entities.SvoyakTheme;

/**
 * Created by Sergey on 17.02.2015.
 */
public class Parser
{
    public static ArrayList<Question> parseSvoyakQuestions(SvoyakTheme theme)
    {
        ArrayList<Question> questions = new ArrayList<Question>();

        String themeQuestions = theme.getQuestion();
        String themeAnswers = theme.getAnswer();
        String themeComments = theme.getComments();

        // Questions
        String temp = "";
        int currentNum = 1;
        Question tempQuestion = new Question();
        for (int i = 0; i < themeQuestions.length(); i++)
        {
            int digit = Character.getNumericValue(themeQuestions.charAt(i));
            if (i < themeQuestions.length() - 3 &&
                    digit == currentNum + 1 &&
                    themeQuestions.substring(i+1, i+3).compareTo(". ") == 0)
            {
                tempQuestion = new Question();
                tempQuestion.setQuestion(temp);
                questions.add(tempQuestion);
                temp = "";
                currentNum++;
            }
            temp += themeQuestions.charAt(i);
        }
        tempQuestion = new Question();
        tempQuestion.setQuestion(temp);
        questions.add(tempQuestion);

        // Answers
        temp = "";
        currentNum = 1;
        for (int i = 0; i < themeAnswers.length(); i++)
        {
            int digit = Character.getNumericValue(themeAnswers.charAt(i));
            if (i < themeAnswers.length() - 3 &&
                    digit == currentNum + 1 &&
                    themeAnswers.substring(i+1, i+3).compareTo(". ") == 0)
            {
                tempQuestion = questions.get(currentNum - 1);
                tempQuestion.setAnswer(temp);
                temp = "";
                currentNum = digit;
            }
            temp += themeAnswers.charAt(i);
        }

        tempQuestion = questions.get(questions.size()-1);
        tempQuestion.setAnswer(temp);

        // Comments
        temp = "";
        currentNum = 1;
        for (int i = 0; i < themeComments.length(); i++)
        {
            int digit = Character.getNumericValue(themeComments.charAt(i));
            if (i < themeComments.length() - 3 &&
                    digit > 1 &&
                    themeComments.substring(i+1, i+3).compareTo(". ") == 0)
            {
                tempQuestion = questions.get(currentNum - 1);
                tempQuestion.setComments(temp);
                temp = "";
                currentNum = digit;
            }
            temp += themeComments.charAt(i);
        }
        if (currentNum == questions.size())
        {
            tempQuestion = questions.get(questions.size()-1);
            tempQuestion.setComments(temp);
        }

        return questions;
    }

    public static int parseQuestionsNum(SvoyakTheme theme)
    {
        int lastNum = -1;
        for (int i = 0; i < theme.getAnswer().length() - 3; i++) {
            char[] buf = new char[2];
            theme.getAnswer().getChars(i+1, i+3, buf, 0);
            if (buf[0] == '.' && buf[1] == ' ')
                lastNum = Character.getNumericValue(theme.getAnswer().charAt(i));
        }
        return lastNum;
    }
}
