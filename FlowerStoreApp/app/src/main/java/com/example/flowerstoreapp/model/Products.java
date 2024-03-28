package com.example.flowerstoreapp.model;
public class Products {
    private int id;
    private String urlImage;
    private String name;
    private String description;
    private double unitPrice;
    private double totalPreviews;

    public double getTotalPreviews() {
        return totalPreviews;
    }

    public void setTotalPreviews(double totalPreviews) {
        this.totalPreviews = totalPreviews;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    private double avgScore;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Products(int id, String name , String description , double unitPrice, String urlImage, double stockQuantity,int categoryId, String createdAt, String updatedAt,double totalPreviews,double avgScore) {
        this.id = id;
        this.urlImage = urlImage;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
        this.categoryId = categoryId;
        this.totalPreviews = totalPreviews;
        this.avgScore = avgScore;
    }

    private double stockQuantity;
    private String createdAt;
    private String updatedAt;
    private Category category;

}
