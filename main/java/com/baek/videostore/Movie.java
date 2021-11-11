package com.baek.videostore;

public abstract class Movie {
    public static final int CHILDRENS = 2;
    public static final int NEW_RELEASE = 1;
    public static final int REGULAR = 0;

    private String title;
    private int pricecode;

    public Movie(String title, int pricecode) {
        this.title = title;
        this.pricecode = pricecode;
    }

    public void setPricecode(int pricecode) {
        this.pricecode = pricecode;
    }

    public String getTitle() {
        return title;
    }

    public int getPricecode() {
        return pricecode;
    }

    abstract double determineAmount(int daysRented);

    abstract int determineFrequentRentalPoint(int daysRented);
}
