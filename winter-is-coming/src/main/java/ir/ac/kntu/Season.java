package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Season enum implementation
 *
 * @author sina rostami
 */
public enum Season {
    // todo: define each Season and call its constructor.
    SPRING(Arrays.asList(1, 2, 3)),
    SUMMER(Arrays.asList(4, 5, 6)),
    FALL(Arrays.asList(7, 8, 9)),
    WINTER(Arrays.asList(10, 11, 12));

    private ArrayList<Integer> months;

    Season(List<Integer> months) {
        this.months = new ArrayList<Integer>(months);
    }

    public ArrayList<Integer> getMonths() {
        return this.months;
    }
    // todo: define attributes of The Season enum.

    // todo: define constructor for the Season enum.

    // todo: define helper methods for the Season enum.
}
