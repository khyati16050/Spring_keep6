package com.stackroute.keepnote.model;

import java.util.Date;
import java.util.List;


public class Note {

	/*
	 * This class should have eight fields
	 * (noteId,noteTitle,noteContent,noteStatus,createdAt,
	 * category,reminder,createdBy). This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of createdAt should not be
	 * accepted from the user but should be always initialized with the system date.
	 *
	 */
	private int noteId;
	private String noteTitle;
	private String noteContent;
	private String noteStatus;
	private Date createdAt;
	private Category category;
	private Reminder reminder;
	private String createdBy;


	// getters & setters
	public Note()
	{

	}
	public Note(int noteId,String noteTitle,String noteContent,String noteStatus,Date createdAt,Category category,Reminder reminder,String createdBy)
	{
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
		this.createdAt = createdAt;
		this.category = category;
		this.reminder = reminder;
		this.createdBy = createdBy;
	}
	public int getNoteId() {
		return this.noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return this.noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return this.noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteStatus() {
		return this.noteStatus;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	public Date getNoteCreationDate() {
		return this.createdAt;
	}

	public void setNoteCreationDate(Date noteCreationDate) {
		this.createdAt = noteCreationDate;
	}

	public String getNoteCreatedBy() {
		return this.createdBy;
	}

	public void setNoteCreatedBy(String noteCreatedBy) {
		this.createdBy = noteCreatedBy;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Reminder> getReminders() {
		return null;
	}

	public void setReminders(List<Reminder> reminders) {

	}

}
