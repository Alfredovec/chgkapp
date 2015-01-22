package models.entities;
 
import android.graphics.Picture;

import java.io.Serializable;
 
public class Question implements Serializable
{
        private static final long serialVersionUID = 1L;
 
        private int questionId;
       
        private int parentNumber;
       
        private int number;
       
        private String type;
       
        private int typenum;
       
        private String textId;
       
        private String question;
       
        private String answer;
       
        private String passCriteria;
       
        private String authors;
       
        private String sources;
       
        private String comments;
       
        private int complexity;

        private String material;

        private Picture picture;
 
        public int getQuestionId() {
                return questionId;
        }
 
        public void setQuestionId(int questionId) {
                this.questionId = questionId;
        }
 
        public int getParentNumber() {
                return parentNumber;
        }
 
        public void setParentNumber(int parentNumber) {
                this.parentNumber = parentNumber;
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
 
        public int getTypenum() {
                return typenum;
        }
 
        public void setTypenum(int typenum) {
                this.typenum = typenum;
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
                return sources;
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

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}