package localdb.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import models.entities.Question;
import models.entities.Tour;

/**
 * Created by Sergey on 03.02.2015.
 */

public class TourAdapter
{
    public static final String KEY_TOUR_ID = "tourid";
    public static final String KEY_PARENT_ID = "parentid";
    public static final String KEY_TITLE = "title";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_TEXT_ID = "textid";
    public static final String KEY_QUESTIONS_NUM = "questionsnum";
    public static final String KEY_TYPE = "type";
    public static final String KEY_FILE_NAME= "filename";

    private static final String DATABASE_NAME = "ToursDB";
    private static final String DATABASE_TABLE = "tours";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "create table if not exists tours ("
                    + "tourid integer primary key,"
                    + "parentid integer,"
                    + "title text,"
                    + "number integer,"
                    + "textid text,"
                    + "questionsnum integer,"
                    + "type text,"
                    + "filename text"+ ");";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public TourAdapter(Context context)
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
            db.execSQL("DROP TABLE IF EXISTS tours");
            onCreate(db);
        }
    }

    //---opens the database---
    public TourAdapter open() throws SQLException
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
    public long insertRecord(Tour tour)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TOUR_ID, tour.getTourId());
        initialValues.put(KEY_PARENT_ID, tour.getParentId());
        initialValues.put(KEY_TITLE, tour.getTitle());
        initialValues.put(KEY_NUMBER, tour.getNumber());
        initialValues.put(KEY_TEXT_ID, tour.getTextId());
        initialValues.put(KEY_QUESTIONS_NUM, tour.getQuestionsNum());
        initialValues.put(KEY_TYPE, tour.getType());
        initialValues.put(KEY_FILE_NAME, tour.getFileName());
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteTour(int tourId)
    {
        return db.delete(DATABASE_TABLE, KEY_TOUR_ID + "= ?", new String[] { String.valueOf(tourId) }) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords()
    {
        return db.query(DATABASE_TABLE, new String[] { KEY_TOUR_ID, KEY_PARENT_ID, KEY_TITLE, KEY_NUMBER, KEY_TEXT_ID,
                        KEY_QUESTIONS_NUM, KEY_TYPE, KEY_FILE_NAME },
                null, null, null, null, null);
    }

    //---retrieves a particular record---
    public Cursor getTour(int tourId) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] { KEY_TOUR_ID, KEY_PARENT_ID, KEY_TITLE, KEY_NUMBER, KEY_TEXT_ID,
                        KEY_QUESTIONS_NUM, KEY_TYPE, KEY_FILE_NAME },
                KEY_TOUR_ID + " = ?", new String[] { String.valueOf(tourId) }, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateQuestion(Tour tour)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(KEY_TOUR_ID, tour.getTourId());
        updatedValues.put(KEY_PARENT_ID, tour.getParentId());
        updatedValues.put(KEY_TITLE, tour.getTitle());
        updatedValues.put(KEY_NUMBER, tour.getNumber());
        updatedValues.put(KEY_TEXT_ID, tour.getTextId());
        updatedValues.put(KEY_QUESTIONS_NUM, tour.getQuestionsNum());
        updatedValues.put(KEY_TYPE, tour.getType());
        updatedValues.put(KEY_FILE_NAME, tour.getFileName());
        return db.update(DATABASE_TABLE, updatedValues, KEY_TOUR_ID + "= ?", new String[] { String.valueOf(tour.getTourId()) }) > 0;
    }
}