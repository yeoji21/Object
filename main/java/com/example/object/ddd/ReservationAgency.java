package com.example.object.ddd;

import com.example.object.movieSystem.Money;

public class ReservationAgency {

    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        return new Reservation(customer, screening, screening.calculateFee(audienceCount), audienceCount);
    }
}
