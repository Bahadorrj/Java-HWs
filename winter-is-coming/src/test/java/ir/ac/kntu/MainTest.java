package ir.ac.kntu;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void getSeasonTest1() {
        ArrayList<Integer> months = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        ArrayList<String> expectedSeasons =
                new ArrayList<>(Arrays.asList("SPRING", "SPRING", "SPRING", "SUMMER",
                        "SUMMER", "SUMMER", "FALL", "FALL", "FALL", "WINTER", "WINTER", "WINTER"));
        ArrayList<String> actualSeasons = Main.getSeasons(months);

        assertEquals(expectedSeasons, actualSeasons);
    }

    @Test
    public void getSeasonTest2() {
        ArrayList<Integer> months = new ArrayList<>(Arrays.asList(1, 4, 7, 10));
        ArrayList<String> expectedSeasons =
                new ArrayList<>(Arrays.asList("SPRING", "SUMMER", "FALL", "WINTER"));
        ArrayList<String> actualSeasons = Main.getSeasons(months);

        assertEquals(expectedSeasons, actualSeasons);
    }

    @Test
    public void getSeasonTest3() {
        ArrayList<Integer> months = new ArrayList<>(Arrays.asList(3, 6, 9, 12));
        ArrayList<String> expectedSeasons =
                new ArrayList<>(Arrays.asList("SPRING", "SUMMER", "FALL", "WINTER"));
        ArrayList<String> actualSeasons = Main.getSeasons(months);

        assertEquals(expectedSeasons, actualSeasons);
    }

    @Test
    public void getSeasonTest4() {
        ArrayList<Integer> months = new ArrayList<>(Arrays.asList(12, 9, 6, 3));
        ArrayList<String> expectedSeasons =
                new ArrayList<>(Arrays.asList("WINTER", "FALL", "SUMMER", "SPRING"));
        ArrayList<String> actualSeasons = Main.getSeasons(months);

        assertEquals(expectedSeasons, actualSeasons);
    }

    @Test
    public void getMonthsOfSpringTest() {
        ArrayList<Integer> expectedMonths = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> actualMonth = Main.getMonths(Season.SPRING);

        assertEquals(expectedMonths, actualMonth);
    }

    @Test
    public void getMonthsOfSummerTest() {
        ArrayList<Integer> expectedMonths = new ArrayList<>(Arrays.asList(4, 5, 6));
        ArrayList<Integer> actualMonth = Main.getMonths(Season.SUMMER);

        assertEquals(expectedMonths, actualMonth);
    }

    @Test
    public void getMonthsOfFallTest() {
        ArrayList<Integer> expectedMonths = new ArrayList<>(Arrays.asList(7, 8, 9));
        ArrayList<Integer> actualMonth = Main.getMonths(Season.FALL);

        assertEquals(expectedMonths, actualMonth);
    }

    @Test
    public void getMonthsOfWinterTest() {
        ArrayList<Integer> expectedMonths = new ArrayList<>(Arrays.asList(10, 11, 12));
        ArrayList<Integer> actualMonth = Main.getMonths(Season.WINTER);

        assertEquals(expectedMonths, actualMonth);
    }
}