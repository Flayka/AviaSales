package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.FlightOffer;
import ru.netology.domain.TravelTimeAscComparator;
import ru.netology.repository.FlightOfferRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FlightOfferManagerTest {
    private FlightOfferRepository repository = new FlightOfferRepository();
    FlightOfferManager manager = new FlightOfferManager(repository);
    private FlightOffer MoscowLondon = new FlightOffer(1, 14000, "SVO", "LHR", 243);
    private FlightOffer MoscowParis = new FlightOffer(2, 17000, "SVO", "PAR", 235);
    private FlightOffer MoscowParisSales = new FlightOffer(3, 12750, "SVO", "PAR", 240);
    private FlightOffer MoscowParisBusiness = new FlightOffer(4, 35000, "SVO", "PAR", 237);
    private FlightOffer MoscowPrague = new FlightOffer(5, 13000, "SVO", "PRG", 170);
    private FlightOffer MoscowPragueSales = new FlightOffer(6, 13000, "SVO", "PRG", 155);
    private FlightOffer MoscowBeijing = new FlightOffer(7, 30000, "SVO", "BJS", 430);

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
    public void shouldNotSearchByIATAAndComparatorSort() {
        FlightOffer[] expected = new FlightOffer[]{MoscowPragueSales, MoscowPrague};
        FlightOffer[] actual = manager.findAll("SVO", "PRG", new TravelTimeAscComparator());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIATAAndComparatorSort() {
        FlightOffer[] expected = new FlightOffer[]{};
        FlightOffer[] actual = manager.findAll("BGS", "SVO", new TravelTimeAscComparator());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllAndSort() {
        FlightOffer[] expected = new FlightOffer[]{MoscowParisSales, MoscowPrague, MoscowPragueSales, MoscowLondon, MoscowParis, MoscowBeijing, MoscowParisBusiness};
        FlightOffer[] actual = repository.findAll();
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIATAAndSort() {
        FlightOffer[] expected = new FlightOffer[]{MoscowParisSales, MoscowParis, MoscowParisBusiness};
        FlightOffer[] actual = manager.findAll("SVO", "PAR");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIATAAndSortSamePrice() {
        FlightOffer[] expected = new FlightOffer[]{MoscowPrague, MoscowPragueSales};
        FlightOffer[] actual = manager.findAll("SVO", "PRG");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIATANotExist() {
        FlightOffer[] expected = new FlightOffer[]{};
        FlightOffer[] actual = manager.findAll("BJS", "SVO");

        assertArrayEquals(expected, actual);
    }
}
