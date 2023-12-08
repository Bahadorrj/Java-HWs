package ir.ac.kntu;

import java.util.ArrayList;

public class Seller {
    private Profile profile;
    private double wallet;
    private ArrayList<Post> history = new ArrayList<>();
    private ArrayList<Post> availableAds = new ArrayList<>();

    public Seller(Profile profile, double wallet) {
        this.profile = profile;
        this.wallet = wallet;
    }

    public Seller() {

    }
    public ArrayList<Post> getHistory() {
        return history;
    }
    public void setHistory(ArrayList<Post> history) {
        this.history = history;
    }
    public ArrayList<Post> getAvailableAds() {
        return availableAds;
    }
    public void setAvailableAds(ArrayList<Post> availableAds) {
        this.availableAds = availableAds;
    }
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public double getWallet() {
        return wallet;
    }
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
}
