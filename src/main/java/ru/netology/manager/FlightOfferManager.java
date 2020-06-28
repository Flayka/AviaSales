package ru.netology.manager;

import ru.netology.domain.FlightOffer;
import ru.netology.repository.FlightOfferRepository;

import java.util.Arrays;

public class FlightOfferManager {
    private FlightOfferRepository repository;

    public FlightOfferManager(FlightOfferRepository repository) {
        this.repository = repository;
    }

    public void add(FlightOffer item) {
        repository.save(item);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public FlightOffer[] findAll(String departure, String arrival) {
        FlightOffer[] result = new FlightOffer[0];
        for (FlightOffer item : repository.findAll()) {
            if (departure.equals(item.getDepartureIATA()) && arrival.equals(item.getArrivalIATA())) {
                FlightOffer[] tmp = new FlightOffer[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }
}
