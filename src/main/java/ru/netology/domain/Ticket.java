package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Comparable {
    private int id;
    private int price;
    private String departureIATA;
    private String arrivalIATA;
    private int travelTime;

    @Override
    public int compareTo(Object o) {
        Ticket p = (Ticket) o;
        return price - p.price;
    }
}
