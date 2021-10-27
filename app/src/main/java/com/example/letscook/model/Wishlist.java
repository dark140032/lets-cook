package com.example.letscook.model;

public class Wishlist {
    private String WishlistId;
    private String userId;
    private String recipeID;

    public Wishlist() {
    }

    public Wishlist(String wishlistId, String userId, String recipeID) {
        WishlistId = wishlistId;
        this.userId = userId;
        this.recipeID = recipeID;
    }

    public String getWishlistId() {
        return WishlistId;
    }

    public void setWishlistId(String wishlistId) {
        WishlistId = wishlistId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }
}
