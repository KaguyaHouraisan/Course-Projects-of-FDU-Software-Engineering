package Exercise4;

public class Philosopher implements Runnable {
    private int name;
    private boolean[] forks;
    private final int leftFork;
    private final int rightFork;

    Philosopher(int name, int left, int right, boolean[] forks){
        this.name = name;
        this.leftFork = left;
        this.rightFork = right;
        this.forks = forks;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println("Philosopher " + name + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            int i = 0;
            //修改循环条件（如改为 i < 1）可调整需要模拟的 思考-吃饭-思考 轮数，当前为进行无限轮
            while(true){
                think();
                pickUpForks();
                eat();
                putDownForks();
                i++;
            }
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    //拿起叉子（奇数位置的哲学家先尝试左手边的叉子，偶数位置的哲学家先尝试右手边的叉子）
    private synchronized void pickUpForks() throws InterruptedException {
        if (name % 2 == 1) {  //奇数位置的哲学家先尝试左手边的叉子
            //若左手边的叉子已被占用，等待
            while (forks[leftFork - 1]) {
                Thread.sleep(100);
            }
            forks[leftFork - 1] = true;
            doAction(System.nanoTime() + ": Picked up left fork");

            //若右手边的叉子已被占用，等待
            while (forks[rightFork - 1]) {
                Thread.sleep(100);
            }
            forks[rightFork - 1] = true;
            doAction(System.nanoTime() + ": Picked up right fork - eating");
        } else {  //偶数位置的哲学家先尝试右手边的叉子
            //若右手边的叉子已被占用，等待
            while (forks[rightFork - 1]) {
                Thread.sleep(100);
            }
            forks[rightFork - 1] = true;
            doAction(System.nanoTime() + ": Picked up right fork");

            //若左手边的叉子已被占用，等待
            while (forks[leftFork - 1]) {
                Thread.sleep(100);
            }
            forks[leftFork - 1] = true;
            doAction(System.nanoTime() + ": Picked up left fork - eating");
        }
    }

    //放下叉子（均为先放下左手边的叉子，再放下右手边的叉子）
    private synchronized void putDownForks() throws InterruptedException {
        forks[leftFork - 1] = false;
        doAction(System.nanoTime() + ": Put down left fork");

        forks[rightFork - 1] = false;
        doAction(System.nanoTime() + ": Put down right fork");
    }

    //思考
    private void think() throws InterruptedException {
        doAction(System.nanoTime() + ": Thinking");
    }

    //吃饭
    private void eat() throws InterruptedException {
        Thread.sleep(((int) (Math.random() * 100)));
    }
}
