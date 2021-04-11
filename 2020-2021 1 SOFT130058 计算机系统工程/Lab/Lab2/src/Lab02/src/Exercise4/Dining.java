package Exercise4;

public class Dining {
    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        boolean[] forks = new boolean[philosophers.length];
        Thread[] threads = new Thread[philosophers.length];

        // initialize fork object
        for (int i = 0; i < forks.length; i++) {
            forks[i] = false;
        }

        // initialize philosopher object
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i + 1, (i + 1) % 5 + 1, i % 5 + 1, forks);
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }
    }
}
