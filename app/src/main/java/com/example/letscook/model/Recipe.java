package com.example.letscook.model;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String recipeId;
    private String recipeName;
    private String recipeDes;
    private String recipeMaterial;
    private String recipeMaking;
    private String recipeAvatar;

    public Recipe() {
    }

    public Recipe(String recipeId, String recipeName, String recipeDes, String recipeMaterial, String recipeMaking, String recipeAvatar) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeDes = recipeDes;
        this.recipeMaterial = recipeMaterial;
        this.recipeMaking = recipeMaking;
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

    public String getRecipeDes() {
        return recipeDes;
    }

    public void setRecipeDes(String recipeDes) {
        this.recipeDes = recipeDes;
    }

    public String getRecipeMaterial() {
        return recipeMaterial;
    }

    public void setRecipeMaterial(String recipeMaterial) {
        this.recipeMaterial = recipeMaterial;
    }

    public String getRecipeMaking() {
        return recipeMaking;
    }

    public void setRecipeMaking(String recipeMaking) {
        this.recipeMaking = recipeMaking;
    }

    public String getRecipeAvatar() {
        return recipeAvatar;
    }

    public void setRecipeAvatar(String recipeAvatar) {
        this.recipeAvatar = recipeAvatar;
    }
}
