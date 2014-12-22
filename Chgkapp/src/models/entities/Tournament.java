package models.entities;

import java.util.Date;

public class Tournament 
{
	private int id;
	
	private int parentId;
	
	private String title;
	
	private int number;
	
	private String textId;
	
	private int questionsNum;
	
	private int type;
	
	private String info;
	
	private String fileName;
	
	private String editors;
	
	private Date lastUndated;
	
	private Date playedAt;
	
	private Date createdAt;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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

	public Date getLastUndated() {
		return lastUndated;
	}

	public void setLastUndated(Date lastUndated) {
		this.lastUndated = lastUndated;
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
}
