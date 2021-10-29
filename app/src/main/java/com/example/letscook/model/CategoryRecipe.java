package com.example.letscook.model;

public class CategoryRecipe {
    private String categoryId;
    private String recipeId;

    public CategoryRecipe() {
    }

    public CategoryRecipe(String categoryId, String recipeId) {
        this.categoryId = categoryId;
        this.recipeId = recipeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }
}
