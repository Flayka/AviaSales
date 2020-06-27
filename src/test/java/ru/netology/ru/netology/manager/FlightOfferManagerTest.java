package ru.netology.ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.FlightOffer;
import ru.netology.repository.FlightOfferRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FlightOfferManagerTest {
    private FlightOfferRepository repository = new FlightOfferRepository();
    FlightOfferManager manager = new FlightOfferManager(repository);
    FlightOffer MoscowLondon = new FlightOffer(1, 14000, "SVO", "LHR", 240);
    FlightOffer MoscowParis = new FlightOffer(2, 17000, "SVO", "PAR", 235);
    FlightOffer MoscowParisSales = new FlightOffer(3, 12750, "SVO", "PAR", 240);
    FlightOffer MoscowParisBusiness = new FlightOffer(4, 35000, "SVO", "PAR", 235);
    FlightOffer MoscowPrague = new FlightOffer(5, 13000, "SVO", "PRG", 155);
    FlightOffer MoscowPragueSales = new FlightOffer(6, 13000, "SVO", "PRG", 155);
    FlightOffer MoscowBeijing = new FlightOffer(7, 30000, "SVO", "BJS", 430);

    @BeforeEach
    public void setUp() {
        manager.add(MoscowLondon);
        manager.add(MoscowParis);
        manager.add(MoscowParisSales);
        manager.add(MoscowParisBusiness);
        manager.add(MoscowPrague);
        manager.add(MoscowPragueSales);
        manager.add(MoscowBeijing);
    }

    @Test
    public void shouldSearchByIATA() {
        String addDeparture = "SVO";
        String addArrival = "LHR";

        FlightOffer[] actual = manager.findAll(addDeparture, addArrival);
        FlightOffer[] expected = new FlightOffer[]{MoscowLondon};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIATAAndSort() {
        String addDeparture = "SVO";
        String addArrival = "PAR";

        FlightOffer[] actual = manager.findAll(addDeparture, addArrival);
        FlightOffer[] expected = new FlightOffer[]{MoscowParisSales, MoscowParis, MoscowParisBusiness};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIATAAndSortSamePrice() {
        String addDeparture = "SVO";
        String addArrival = "PRG";

        FlightOffer[] actual = manager.findAll(addDeparture, addArrival);
        FlightOffer[] expected = new FlightOffer[]{MoscowPrague, MoscowPragueSales};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIATANotExist() {
        String addDeparture = "BJS";
        String addArrival = "SVO";

        FlightOffer[] actual = manager.findAll(addDeparture, addArrival);
        FlightOffer[] expected = new FlightOffer[]{};

        assertArrayEquals(expected, actual);
    }
}
