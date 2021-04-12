package Lab_3;

import java.util.ArrayList;

public class TestCase {
    public static void main(String args[]) throws Exception {
        ArrayList<Cow> arrivalLine = new ArrayList<Cow>();
        arrivalLine.add(new Cow(0, 8));
        arrivalLine.add(new Cow(1, 5));
        arrivalLine.add(new Cow(2, 3));
        arrivalLine.add(new Cow(4, 1));
        arrivalLine.add(new Cow(4, 5));
        arrivalLine.add(new Cow(4, 4));
        arrivalLine.add(new Cow(5, 4));
        arrivalLine.add(new Cow(6, 1));
        arrivalLine.add(new Cow(7, 3));
        EventSimulator es = new EventSimulator(3, arrivalLine);
        es.simulate();
    }
}
