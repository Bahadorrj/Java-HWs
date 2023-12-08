package ir.ac.kntu;

import java.util.ArrayList;

public class Buyer {
    private Profile profile;
    private double wallet;
    private ArrayList<Post> savedBox = new ArrayList<>();
    private ArrayList<Post> history = new ArrayList<>();
    public Buyer(Profile profile, double wallet, ArrayList<Post> savedBox, ArrayList<Post> history) {
        this.profile = profile;
        this.wallet = wallet;
        this.savedBox = savedBox;
        this.history = history;
    }
    public Buyer(Profile profile, int wallet) {
        this.profile = profile;
        this.wallet = wallet;
    }

    public Buyer() {

    }
    public ArrayList<Post> getSavedBox() {
        return savedBox;
    }
    public void setSavedBox(ArrayList<Post> savedBox) {
        this.savedBox = savedBox;
    }
    public ArrayList<Post> getHistory() {
        return history;
    }
    public void setHistory(ArrayList<Post> history) {
        this.history = history;
    }
    public double getWallet() {
        return wallet;
    }
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return profile + " wallet: " + wallet;
    }
}
