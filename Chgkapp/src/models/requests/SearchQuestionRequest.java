package models.requests;

import java.io.Serializable;
import java.util.ArrayList;

import helpers.enums.GameType;
import helpers.enums.QuestionField;
import helpers.enums.RequestWords;

/**
 * Created by Sergey on 09.02.2015.
 */
public class SearchQuestionRequest extends BasicRequest implements Serializable
{
    private GameType type;

    private ArrayList<QuestionField> fields;

    private String textRequest;

    private RequestWords words;

    public SearchQuestionRequest(GameType type, ArrayList<QuestionField> fields, String textRequest, RequestWords words)
    {
        this.setType(type);
        this.setFields(fields);
        this.setTextRequest(textRequest);
        this.setWords(words);
    }

    @Override
    public GameType getType() {
        return type;
    }

    @Override
    public void setType(GameType type) {
        this.type = type;
    }

    public ArrayList<QuestionField> getFields() {
        return fields;
    }

    public void setFields(ArrayList<QuestionField> fields) {
        this.fields = fields;
    }

    public String getTextRequest() {
        return textRequest;
    }

    public void setTextRequest(String textRequest) {
        this.textRequest = textRequest;
    }

    public RequestWords getWords() {
        return words;
    }

    public void setWords(RequestWords words) {
        this.words = words;
    }
}
