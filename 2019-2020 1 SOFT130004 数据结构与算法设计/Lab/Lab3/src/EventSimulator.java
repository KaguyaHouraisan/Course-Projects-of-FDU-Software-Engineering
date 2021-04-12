package Lab_3;

import java.util.ArrayList;

public class EventSimulator {
    private int k;
    private ArrayList<Cow> arrivalLine;

    //create an event simulator
    //k determine how many cows can eat grass at the same time
    //arrivalLine is the input stream, sorted by arrival time
    public EventSimulator(int k, ArrayList<Cow> arrivalLine) {
        this.k = k;
        this.arrivalLine = arrivalLine;
    }

    //set the arrival line
    public void setArrivalLine(ArrayList<Cow> arrivalLine) {
        this.arrivalLine = arrivalLine;
    }

    //start simulation
    public void simulate() throws Exception {
        BinaryHeap temp = new BinaryHeap(arrivalLine.size());
        int i;
        for (i = 0; i < k; i++) {
            temp.insert(arrivalLine.get(i).getEating() + arrivalLine.get(i).getArrival());
            System.out.println(arrivalLine.get(i).getArrival() + " o'clock: cow which arrived at " + arrivalLine.get(i).getArrival() + " o'clock starts eating grass and will leave " +
                    "at " + (arrivalLine.get(i).getEating() + arrivalLine.get(i).getArrival()) + " o'clock");
        }

        for (int n = 0; n < 24; n++) {
            for (int t = 0; t < k; t++) {
                int time = temp.deleteMin();
                if (time != n) {
                    temp.insert(time);
                } else {
                    if (i < arrivalLine.size()) {
                        if (arrivalLine.get(i).getArrival() <= n) {
                            temp.insert(arrivalLine.get(i).getEating() + n);
                            System.out.println(n + " o'clock: cow which arrived at " + arrivalLine.get(i).getArrival() + " o'clock starts eating grass and will leave " +
                                    "at " + (arrivalLine.get(i).getEating() + n) + " o'clock");
                            i++;
                        }
                    }
                }
            }
        }
    }
}
