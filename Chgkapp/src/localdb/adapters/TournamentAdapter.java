package localdb.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import models.entities.Question;
import models.entities.Tour;
import models.entities.Tournament;

/**
 * Created by Sergey on 03.02.2015.
 */

public class TournamentAdapter
{
    public static final String KEY_TOURNAMENT_ID = "tournamentid";
    public static final String KEY_PARENT_ID = "parentid";
    public static final String KEY_TITLE = "title";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_TEXT_ID = "textid";
    public static final String KEY_QUESTIONS_NUM = "questionsnum";
    public static final String KEY_TYPE = "type";
    public static final String KEY_INFO = "info";
    public static final String KEY_URL = "url";
    public static final String KEY_FILE_NAME= "filename";
    public static final String KEY_EDITORS = "editors";
    public static final String KEY_LAST_UPDATED = "lastupdated";
    public static final String KEY_PLAYES_AT = "playedat";
    public static final String KEY_CREATED_AT = "createdat";
    public static final String KEY_TOURS_NUM = "toursnum";

    private static final String DATABASE_NAME = "TournamentsDB";
    private static final String DATABASE_TABLE = "tournaments";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "create table if not exists tournaments ("
                    + "tournamentid integer primary key,"
                    + "parentid integer,"
                    + "title text,"
                    + "number integer,"
                    + "textid integer,"
                    + "questionsnum integer,"
                    + "type text,"
                    + "info text,"
                    + "url text,"
                    + "filename text"
                    + "editors text"
                    + "lastupdated text"
                    + "playedat text"
                    + "createdat text"
                    + "toursnum integer"+ ");";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public TournamentAdapter(Context context)
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
            db.execSQL("DROP TABLE IF EXISTS tournaments");
            onCreate(db);
        }
    }

    //---opens the database---
    public TournamentAdapter open() throws SQLException
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
    public long insertRecord(Tournament tournament)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TOURNAMENT_ID, tournament.getTournamentId());
        initialValues.put(KEY_PARENT_ID, tournament.getParentId());
        initialValues.put(KEY_TITLE, tournament.getTitle());
        initialValues.put(KEY_NUMBER, tournament.getNumber());
        initialValues.put(KEY_TEXT_ID, tournament.getTextId());
        initialValues.put(KEY_QUESTIONS_NUM, tournament.getQuestionsNum());
        initialValues.put(KEY_TYPE, tournament.getType());
        initialValues.put(KEY_INFO, tournament.getInfo());
        initialValues.put(KEY_URL, tournament.getURL());
        initialValues.put(KEY_FILE_NAME, tournament.getFileName());
        initialValues.put(KEY_EDITORS, tournament.getEditors());
        initialValues.put(KEY_LAST_UPDATED, tournament.getLastUpdated().toString());
        initialValues.put(KEY_PLAYES_AT, tournament.getPlayedAt().toString());
        initialValues.put(KEY_CREATED_AT, tournament.getCreatedAt().toString());
        initialValues.put(KEY_TOURS_NUM, tournament.getToursNum());
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteTournament(String title)
    {
        return db.delete(DATABASE_TABLE, KEY_TITLE + "= ?", new String[] { title }) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords()
    {
        return db.query(DATABASE_TABLE, new String[] { KEY_TOURNAMENT_ID, KEY_PARENT_ID, KEY_TITLE, KEY_NUMBER, KEY_TEXT_ID,
                        KEY_QUESTIONS_NUM, KEY_TYPE, KEY_INFO, KEY_URL, KEY_FILE_NAME, KEY_EDITORS, KEY_LAST_UPDATED,
                KEY_PLAYES_AT, KEY_CREATED_AT, KEY_TOURS_NUM },
                null, null, null, null, null);
    }

    //---retrieves a particular record---
    public Cursor getTournament(String title) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] { KEY_TOURNAMENT_ID, KEY_PARENT_ID, KEY_TITLE, KEY_NUMBER,
                        KEY_TEXT_ID, KEY_QUESTIONS_NUM, KEY_TYPE, KEY_INFO, KEY_URL, KEY_FILE_NAME, KEY_EDITORS, KEY_LAST_UPDATED,
                        KEY_PLAYES_AT, KEY_CREATED_AT, KEY_TOURS_NUM },
                KEY_TITLE + " = ?", new String[] { title }, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateQuestion(Tournament tournament)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(KEY_PARENT_ID, tournament.getParentId());
        updatedValues.put(KEY_TITLE, tournament.getTitle());
        updatedValues.put(KEY_NUMBER, tournament.getNumber());
        updatedValues.put(KEY_TEXT_ID, tournament.getTextId());
        updatedValues.put(KEY_QUESTIONS_NUM, tournament.getQuestionsNum());
        updatedValues.put(KEY_TYPE, tournament.getType());
        updatedValues.put(KEY_INFO, tournament.getInfo());
        updatedValues.put(KEY_URL, tournament.getURL());
        updatedValues.put(KEY_FILE_NAME, tournament.getFileName());
        updatedValues.put(KEY_EDITORS, tournament.getEditors());
        updatedValues.put(KEY_LAST_UPDATED, tournament.getLastUpdated().toString());
        updatedValues.put(KEY_PLAYES_AT, tournament.getPlayedAt().toString());
        updatedValues.put(KEY_CREATED_AT, tournament.getCreatedAt().toString());
        updatedValues.put(KEY_TOURS_NUM, tournament.getToursNum());
        return db.update(DATABASE_TABLE, updatedValues, KEY_TOURNAMENT_ID + "= ?", new String[] { String.valueOf(tournament.getTournamentId()) }) > 0;
    }
}