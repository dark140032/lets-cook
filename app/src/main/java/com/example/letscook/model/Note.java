package com.example.letscook.model;

import java.io.Serializable;

public class Note implements Serializable {
    private String noteId;
    private String noteName;
    private String noteContent;
    private String frstRegistPttm;
    private String userId;

    public Note() {
    }

    public Note(String noteId, String noteName, String noteContent, String frstRegistPttm, String userId) {
        this.noteId = noteId;
        this.noteName = noteName;
        this.noteContent = noteContent;
        this.frstRegistPttm = frstRegistPttm;
        this.userId = userId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getFrstRegistPttm() {
        return frstRegistPttm;
    }

    public void setFrstRegistPttm(String frstRegistPttm) {
        this.frstRegistPttm = frstRegistPttm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
