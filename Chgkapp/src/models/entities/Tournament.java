package models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Tournament implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private int parentId;
	
	private String title;
	
	private int number;
	
	private String textId;
	
	private int questionsNum;
	
	private String type;
	
	private String info;
	
	private String URL;
	
	private String fileName;
	
	private String editors;
	
	private Date lastUpdated;
	
	private Date playedAt;
	
	private Date createdAt;

	private ArrayList<Tour> tours;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEditors() {
		return editors;
	}

	public void setEditors(String editors) {
		this.editors = editors;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUndated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getPlayedAt() {
		return playedAt;
	}

	public void setPlayedAt(Date playedAt) {
		this.playedAt = playedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public ArrayList<Tour> getTours() {
		return tours;
	}

	public void setTours(ArrayList<Tour> tours) {
		this.tours = tours;
	}
}
