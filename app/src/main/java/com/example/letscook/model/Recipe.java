package com.example.letscook.model;

public class Recipe {
    private String recipeId;
    private String recipeName;
    private String recipeDetail;
    private String recipeAvatar;

    public Recipe() {
    }

    public Recipe(String recipeId, String recipeName, String recipeDetail, String recipeAvatar) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeDetail = recipeDetail;
        this.recipeAvatar = recipeAvatar;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDetail() {
        return recipeDetail;
    }

    public void setRecipeDetail(String recipeDetail) {
        this.recipeDetail = recipeDetail;
    }

    public String getRecipeAvatar() {
        return recipeAvatar;
    }

    public void setRecipeAvatar(String recipeAvatar) {
        this.recipeAvatar = recipeAvatar;
    }
}
