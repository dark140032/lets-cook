package com.example.letscook.ui.notes;

import java.io.Serializable;

public class Note implements Serializable {
    String title;
    String datelast;
    String content;

    public Note() {
    }

    public Note(String title, String datelast, String content) {
        this.title = title;
        this.datelast = datelast;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatelast() {
        return datelast;
    }

    public void setDatelast(String datelast) {
        this.datelast = datelast;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
