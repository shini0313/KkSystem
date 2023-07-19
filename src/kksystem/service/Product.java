package kksystem.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shini
 */
public class Product {

    private int productId;

    private String productName;

    private float price;

    private int quantity;

    private int totalAmount;

    private int purchases;

    public int getProductId() {
        return productId;
    }

    public void setProductid(int productId) {
        this.productId = productId;
    }

    public String getProductname() {
        return productName;
    }

    public void setProductname(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    

}
