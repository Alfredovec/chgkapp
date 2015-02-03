package models.entities;
 
import java.io.Serializable;
import java.util.ArrayList;
 
public class Tour implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int tourId;

    private int parentId;

    private String title;

    private int number;

    private String textId;

    private int questionsNum;

    private String type;

    private String fileName;

    private ArrayList<Question> questions;

    public Tour() {
            questions = new ArrayList<Question>();
    }

    public int getTourId() {
            return tourId;
    }

    public void setTourId(int tourId) {
            this.tourId = tourId;
    }

    public int getParentId() {
            return parentId;
    }

    public void setParentId(int parentId) {
            this.parentId = parentId;
    }

    public String getTitle() {
            return title;
    }

    public void setTitle(String title) {
            this.title = title;
    }

    public int getNumber() {
            return number;
    }

    public void setNumber(int number) {
            this.number = number;
    }

    public String getTextId() {
            return textId;
    }

    public void setTextId(String textId) {
            this.textId = textId;
    }

    public int getQuestionsNum() {
            return questionsNum;
    }

    public void setQuestionsNum(int questionsNum) {
            this.questionsNum = questionsNum;
    }

    public String getType() {
            return type;
    }

    public void setType(String type) {
            this.type = type;
    }

    public String getFileName() {
            return fileName;
    }

    public void setFileName(String fileName) {
            this.fileName = fileName;
    }

    public ArrayList<Question> getQuestions() {
            return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
            this.questions = questions;
    }

    public void addQ(Question q) {
            questions.add(q);
    }
}
