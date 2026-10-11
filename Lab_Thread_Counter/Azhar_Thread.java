import java.util.concurrent.atomic.AtomicLong;

public class Azhar_Thread implements Runnable {

    static AtomicLong safeCounter = new AtomicLong(0);
    static long unsafeCounter = 0;

    long instanceCounter = 0;
    int increments;
    boolean threadSafe;

    Azhar_Thread(int increments, boolean threadSafe) {
        this.increments = increments;
        this.threadSafe = threadSafe;
    }

    @Override
    public void run() {
        for (int i = 0; i < increments; i++) {
            instanceCounter++;

            if (threadSafe) {
                safeCounter.incrementAndGet();
            } else {
                unsafeCounter++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.out.println(
                "Usage: java Azhar_Thread <threads> <increments> <true/false>");
            return;
        }

        int numberOfThreads;
        int increments;
        boolean threadSafe;

        try {
            numberOfThreads = Integer.parseInt(args[0]);
            increments = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Thread count and increments must be integers.");
            return;
        }

        if (numberOfThreads <= 0 || increments < 0
                || (!args[2].equals("true") && !args[2].equals("false"))) {
            System.out.println("Invalid input.");
            return;
        }

        threadSafe = Boolean.parseBoolean(args[2]);

        safeCounter.set(0);
        unsafeCounter = 0;

        Azhar_Thread[] tasks = new Azhar_Thread[numberOfThreads];
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            tasks[i] = new Azhar_Thread(increments, threadSafe);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        long nonStaticTotal = 0;
        for (int i = 0; i < numberOfThreads; i++) {
            nonStaticTotal += tasks[i].instanceCounter;
        }

        long staticTotal = threadSafe ? safeCounter.get() : unsafeCounter;
        long expectedCount = (long) numberOfThreads * increments;
        long difference = Math.abs(staticTotal - nonStaticTotal);

        double percentageDifference;
        if (nonStaticTotal != 0) {
            percentageDifference = (double) difference / nonStaticTotal * 100.0;
        } else if (staticTotal == 0) {
            percentageDifference = 0.0;
        } else {
            percentageDifference = Double.NaN;
        }

        System.out.println("Mode: " + (threadSafe ? "THREAD-SAFE" : "UNSYNCHRONIZED"));
        System.out.println("Threads: " + numberOfThreads);
        System.out.println("Increments per thread: " + increments);
        System.out.println("Expected count: " + expectedCount);
        System.out.println("Static count: " + staticTotal);
        System.out.println("Non-static total: " + nonStaticTotal);
        System.out.println("Absolute difference: " + difference);

        if (Double.isNaN(percentageDifference)) {
            System.out.println("Percentage difference: Undefined");
        } else {
            System.out.printf("Percentage difference: %.4f%%%n", percentageDifference);
        }
    }
}
