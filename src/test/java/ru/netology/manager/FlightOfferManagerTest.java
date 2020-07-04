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
    private FlightOffer MoscowParisSale = new FlightOffer(3, 12750, "SVO", "PAR", 240);
    private FlightOffer MoscowParisPremium = new FlightOffer(4, 35000, "SVO", "PAR", 237);
    private FlightOffer MoscowPrague = new FlightOffer(5, 13000, "SVO", "PRG", 170);
    private FlightOffer MoscowPragueSale = new FlightOffer(6, 13000, "SVO", "PRG", 155);
    private FlightOffer MoscowBeijing = new FlightOffer(7, 30000, "SVO", "BJS", 430);

    @BeforeEach
    public void setUp() {
        manager.add(MoscowLondon);
        manager.add(MoscowParis);
        manager.add(MoscowParisSale);
        manager.add(MoscowParisPremium);
        manager.add(MoscowPrague);
        manager.add(MoscowPragueSale);
        manager.add(MoscowBeijing);
    }

    @Test
    public void shouldNotSearchByIATAAndComparatorSort() {
        FlightOffer[] expected = new FlightOffer[]{MoscowPragueSale, MoscowPrague};
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
        FlightOffer[] expected = new FlightOffer[]{MoscowParisSale, MoscowPrague, MoscowPragueSale, MoscowLondon, MoscowParis, MoscowBeijing, MoscowParisPremium};
        FlightOffer[] actual = repository.findAll();
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIATAAndSort() {
        FlightOffer[] expected = new FlightOffer[]{MoscowParisSale, MoscowParis, MoscowParisPremium};
        FlightOffer[] actual = manager.findAll("SVO", "PAR");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIATAAndSortSamePrice() {
        FlightOffer[] expected = new FlightOffer[]{MoscowPrague, MoscowPragueSale};
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
