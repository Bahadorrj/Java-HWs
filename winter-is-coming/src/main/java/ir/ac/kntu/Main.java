package ir.ac.kntu;

import java.util.ArrayList;

/**
 * Season functions implementation
 *
 * @author sina rostami
 */
public class Main {

    public static ArrayList<String> getSeasons(ArrayList<Integer> months) {
        // todo: return an arrayList containing the names of the seasons of the given
        // months.
        // todo: the returned arrayList should correspond to the given month so watch
        // the order!
        ArrayList<String> out = new ArrayList<>();
        for (Integer month : months) {
            for (Season s : Season.values()) {
                if (s.getMonths().contains(month)) {
                    out.add(s.toString());
                    break;
                }
            }
        }
        return out;
    }

    public static ArrayList<Integer> getMonths(Season season) {
        // todo: return an arrayList containing the months of the given season.
        return season.getMonths();
    }

    public static void main(String[] args) {
        // todo: Test Your code :)

    }
}