package localdb.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import models.entities.Question;

/**
 * Created by Sergey on 03.02.2015.
 */

public class QuestionAdapter
{
    public static final String KEY_QUESTION_ID = "questionid";
    public static final String KEY_PARENT_ID = "parentid";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_TYPE = "type";
    public static final String KEY_TYPE_NUM = "typenum";
    public static final String KEY_TEXT_ID = "textid";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_ANSWER = "answer";
    public static final String KEY_PASS_CRITERIA = "passcriteria";
    public static final String KEY_AUTHORS = "authors";
    public static final String KEY_SOURCES = "sources";
    public static final String KEY_COMMENTS = "comments";
    public static final String KEY_COMPLEXITY = "complexity";
    public static final String KEY_MATERIAL = "material";
    public static final String KEY_PICTURE_QUESTION = "picturequestion";
    public static final String KEY_PICTURE_ANSWER = "pictureanswer";

    private static final String DATABASE_NAME = "QuestionsDB";
    private static final String DATABASE_TABLE = "questions";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "create table if not exists questions ("
                    + "questionid integer primary key,"
                    + "parentid integer,"
                    + "number integer,"
                    + "type text,"
                    + "typenum integer,"
                    + "textid integer,"
                    + "question text,"
                    + "answer text,"
                    + "passcriteria text,"
                    + "authors text,"
                    + "sources text,"
                    + "comments text,"
                    + "complexity integer,"
                    + "material text,"
                    + "picturequestion blob,"
                    + "pictureanswer blob"+ ");";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public QuestionAdapter(Context context)
    {
        this.context = context;
        DBHelper = new DatabaseHelper(this.context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS questions");
            onCreate(db);
        }
    }

    //---opens the database---
    public QuestionAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a record into the database---
    public long insertRecord(Question question)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_QUESTION_ID, question.getQuestionId());
        initialValues.put(KEY_PARENT_ID, question.getParentId());
        initialValues.put(KEY_NUMBER, question.getNumber());
        initialValues.put(KEY_TYPE, question.getType());
        initialValues.put(KEY_TYPE_NUM, question.getTypeNum());
        initialValues.put(KEY_TEXT_ID, question.getTextId());
        initialValues.put(KEY_QUESTION, question.getQuestion());
        initialValues.put(KEY_ANSWER, question.getAnswer());
        initialValues.put(KEY_PASS_CRITERIA, question.getPassCriteria());
        initialValues.put(KEY_AUTHORS, question.getAuthors());
        initialValues.put(KEY_SOURCES, question.getSources());
        initialValues.put(KEY_COMMENTS, question.getComments());
        initialValues.put(KEY_COMPLEXITY, question.getComplexity());
        initialValues.put(KEY_MATERIAL, question.getMaterial());
        initialValues.put(KEY_PICTURE_QUESTION, question.getPictureQuestion());
        initialValues.put(KEY_PICTURE_ANSWER, question.getPictureAnswer());
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteQuestionsOfTour(int tourId)
    {
        return db.delete(DATABASE_TABLE, KEY_PARENT_ID + "= ?", new String[] { String.valueOf(tourId) }) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords()
    {
        return db.query(DATABASE_TABLE, new String[] { KEY_QUESTION_ID, KEY_PARENT_ID, KEY_NUMBER, KEY_TYPE, KEY_TYPE_NUM,
                KEY_TEXT_ID, KEY_QUESTION, KEY_ANSWER, KEY_PASS_CRITERIA, KEY_AUTHORS, KEY_SOURCES, KEY_COMMENTS,
                KEY_COMPLEXITY, KEY_MATERIAL, KEY_PICTURE_QUESTION, KEY_PICTURE_ANSWER },
                null, null, null, null, null);
    }

    //---retrieves a particular record---
    public Cursor getQuestionsByTour(int tourId) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] { KEY_QUESTION_ID, KEY_PARENT_ID, KEY_NUMBER, KEY_TYPE,
                        KEY_TYPE_NUM, KEY_TEXT_ID, KEY_QUESTION, KEY_ANSWER, KEY_PASS_CRITERIA, KEY_AUTHORS, KEY_SOURCES,
                        KEY_COMMENTS, KEY_COMPLEXITY, KEY_MATERIAL, KEY_PICTURE_QUESTION, KEY_PICTURE_ANSWER },
                KEY_PARENT_ID + " = ?", new String[] { String.valueOf(tourId) }, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateQuestion(Question question)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(KEY_PARENT_ID, question.getParentId());
        updatedValues.put(KEY_NUMBER, question.getNumber());
        updatedValues.put(KEY_TYPE, question.getType());
        updatedValues.put(KEY_TYPE_NUM, question.getTypeNum());
        updatedValues.put(KEY_TEXT_ID, question.getTextId());
        updatedValues.put(KEY_QUESTION, question.getQuestion());
        updatedValues.put(KEY_ANSWER, question.getAnswer());
        updatedValues.put(KEY_PASS_CRITERIA, question.getPassCriteria());
        updatedValues.put(KEY_AUTHORS, question.getAuthors());
        updatedValues.put(KEY_SOURCES, question.getSources());
        updatedValues.put(KEY_COMMENTS, question.getComments());
        updatedValues.put(KEY_COMPLEXITY, question.getComplexity());
        updatedValues.put(KEY_MATERIAL, question.getMaterial());
        updatedValues.put(KEY_PICTURE_QUESTION, question.getPictureQuestion());
        updatedValues.put(KEY_PICTURE_ANSWER, question.getPictureAnswer());
        return db.update(DATABASE_TABLE, updatedValues, KEY_QUESTION_ID + "= ?", new String[] { String.valueOf(question.getQuestionId()) }) > 0;
    }
}