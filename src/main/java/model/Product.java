package com.coffeepoly.model;

import java.math.BigDecimal;

public class Product {
    private int productId, categoryId, stock;
    private String productName, description, image;
    private BigDecimal price;

    public Product(int productId, int categoryId, String productName, String description,
                   BigDecimal price, String image, int stock) {
        this.productId=productId; this.categoryId=categoryId; this.productName=productName;
        this.description=description; this.price=price; this.image=image; this.stock=stock;
    }
    public int getProductId(){return productId;}
    public int getCategoryId(){return categoryId;}
    public String getProductName(){return productName;}
    public String getDescription(){return description;}
    public BigDecimal getPrice(){return price;}
    public String getImage(){return image;}
    public int getStock(){return stock;}
}
