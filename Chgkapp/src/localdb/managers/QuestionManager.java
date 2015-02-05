package localdb.managers;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import localdb.adapters.QuestionAdapter;
import models.entities.Question;

/**
 * Created by Sergey on 03.02.2015.
 */
public class QuestionManager
{
    private QuestionAdapter questionAdapter;

    public QuestionManager(Context context)
    {
        questionAdapter = new QuestionAdapter(context);
    }
    public void saveQuestion(Question question)
    {
        questionAdapter.open();

        questionAdapter.insertRecord(question);

        questionAdapter.close();
    }

    public ArrayList<Question> getQuestions(int tourId)
    {
        questionAdapter.open();

        ArrayList<Question> questions = new ArrayList<Question>();


        Cursor cur = questionAdapter.getQuestionsByTour(tourId);

        if (cur.moveToFirst())
        {
            do {
                Question question = new Question();

                question.setQuestionId(cur.getInt(cur.getColumnIndex("questionid")));
                question.setParentId(cur.getInt(cur.getColumnIndex("parentid")));
                question.setNumber(cur.getInt(cur.getColumnIndex("number")));
                question.setType(cur.getString(cur.getColumnIndex("type")));
                question.setTypeNum(cur.getInt(cur.getColumnIndex("typenum")));
                question.setTextId(cur.getString(cur.getColumnIndex("textid")));
                question.setQuestion(cur.getString(cur.getColumnIndex("question")));
                question.setAnswer(cur.getString(cur.getColumnIndex("answer")));
                question.setPassCriteria(cur.getString(cur.getColumnIndex("passcriteria")));
                question.setAuthors(cur.getString(cur.getColumnIndex("authors")));
                question.setSources(cur.getString(cur.getColumnIndex("sources")));
                question.setComments(cur.getString(cur.getColumnIndex("comments")));
                question.setComplexity(cur.getInt(cur.getColumnIndex("complexity")));
                question.setMaterial(cur.getString(cur.getColumnIndex("material")));
                question.setPictureQuestion(cur.getBlob(cur.getColumnIndex("picturequestion")));
                question.setPictureAnswer(cur.getBlob(cur.getColumnIndex("pictureanswer")));

                questions.add(question);
            }
            while (cur.moveToNext());
        }
        else
        {
            questionAdapter.close();
            return null;
        }

        questionAdapter.close();

        return questions;
    }
}
