package ir.ac.kntu;

import java.util.Comparator;

public class PostPriceComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        if (o1.getPrice() > o2.getPrice()) {
            return 1;
        }
        if (o1.getPrice() == o2.getPrice()) {
            return 0;
        }
        return -1;
    }
}
