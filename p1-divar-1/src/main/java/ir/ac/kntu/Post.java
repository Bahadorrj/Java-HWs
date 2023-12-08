package ir.ac.kntu;

import java.util.Comparator;

public class Post {
    private double price;
    private String name;
    private  Seller seller;
    private String category;
    private int approved;
    public Post(String name, double price, Seller seller, String category, int approved) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.category = category;
        this.approved = approved;
        seller.getAvailableAds().add(this);
    }
    public Post() {

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Seller getSeller() {
        return seller;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int isApproved() {
        return approved;
    }
    public void setApproved(int approved) {
        this.approved = approved;
    }
    @Override
    public String toString() {
        String s =  "(name: " + name + ", price: " + price + ", category: " + category + ", approved: ";
        if (approved == 1) {
            s += "approved)";
        }
        else if (approved == 0) {
            s += "pending)";
        }
        else if (approved == -1) {
            s += "disapproved)";
        }
        return s;
    }
}

