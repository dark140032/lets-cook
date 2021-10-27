package com.example.letscook.model;

public class ThemeRecipe {
    private String themeId;
    private String recipeId;

    public ThemeRecipe() {
    }

    public ThemeRecipe(String themeId, String recipeId) {
        this.themeId = themeId;
        this.recipeId = recipeId;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }
}
