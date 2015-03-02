package models.entities;
 
import java.io.Serializable;
 
public class Question implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int questionId;

    private int parentId;

    private int number;

    private String type;

    private int typeNum;

    private String textId;

    private String question;

    private String answer;

    private String passCriteria;

    private String authors;

    private String sources;

    private String comments;

    private int complexity;

    private String material;

    private byte[] pictureQuestion;

    private byte[] pictureAnswer;

    public int getQuestionId() {
            return questionId;
    }

    public void setQuestionId(int questionId) {
            this.questionId = questionId;
    }

    public int getParentId() {
            return parentId;
    }

    public void setParentId(int parentId) {
            this.parentId = parentId;
    }

    public int getNumber() {
            return number;
    }

    public void setNumber(int number) {
            this.number = number;
    }

    public String getType() {
            return type;
    }

    public void setType(String type) {
            this.type = type;
    }

    public int getTypeNum() {
            return typeNum;
    }

    public void setTypeNum(int typeNum) {
            this.typeNum = typeNum;
    }

    public String getTextId() {
            return textId;
    }

    public void setTextId(String textId) {
            this.textId = textId;
    }

    public String getQuestion() {
            return question;
    }

    public void setQuestion(String question) {
            this.question = question;
    }

    public String getAnswer() {
            return answer;
    }

    public void setAnswer(String answer) {
            this.answer = answer;
    }

    public String getPassCriteria() {
            return passCriteria;
    }

    public void setPassCriteria(String passCriteria) {
            this.passCriteria = passCriteria;
    }

    public String getAuthors() {
            return authors;
    }

    public void setAuthors(String authors) {
            this.authors = authors;
    }

    public String getSources() {
            return sources != null? sources : "";
    }

    public void setSources(String sources) {
            this.sources = sources;
    }

    public String getComments() {
            return comments;
    }

    public void setComments(String comments) {
            this.comments = comments;
    }

    public int getComplexity() {
            return complexity;
    }

    public void setComplexity(int complexity) {
            this.complexity = complexity;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public byte[] getPictureQuestion() {
        return pictureQuestion;
    }

    public void setPictureQuestion(byte[] pictureQuestion) {
        this.pictureQuestion = pictureQuestion;
    }

    public byte[] getPictureAnswer() {
        return pictureAnswer;
    }

    public void setPictureAnswer(byte[] pictureAnswer) {
        this.pictureAnswer = pictureAnswer;
    }
}