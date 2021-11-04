package com.example.letscook.model;

import java.io.Serializable;

public class Theme implements Serializable {
    private String themeId;
    private String themeName;
    private String themeImage;

    public Theme() {
    }

    public Theme(String themeId, String themeName, String themeImage) {
        this.themeId = themeId;
        this.themeName = themeName;
        this.themeImage = themeImage;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeImage() {
        return themeImage;
    }

    public void setThemeImage(String themeImage) {
        this.themeImage = themeImage;
    }
}
