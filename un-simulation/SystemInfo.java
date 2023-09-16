//   Obtain system memory, create SystemInfo objects, and encapsulate the obtained information in SystemInfo
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.text.NumberFormat;

public class SystemInfo {
    public static void main(String[] args) {
        ThreadMXBean threadMX = ManagementFactory.getThreadMXBean();
        // is the thread CPU time supported?
        if (threadMX.isCurrentThreadCpuTimeSupported()) {
            // get the total CPU time for the current thread
            long tid = threadMX.getCurrentThreadCpuTime();
            System.out.println("Current thread CPU time (in nanos): " + tid);
        } else {
            System.out.println("Thread CPU time measurement not supported");
        }
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println("System load average: " + nf.format(loadAverage(5)));
    }
    private static double loadAverage(int skip) {
        long[] la = new long[3];
        System.gc();
        System.runFinalization();
        System.gc();
        System.gc();
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            // do something
        }
        long end = System.nanoTime();
        long totalTime = end - start;
        la[0] = la[1] = la[2] = 0;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return 0;
        }
        System.gc();
        System.runFinalization();
        System.gc();
        System.gc();
        long start2 = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            // do something
        }
        long end2 = System.nanoTime();
        long totalTime2 = end2 - start2;
        return (end - start) / 1000000000.0 + (end2 - start2) / 1000000000.0 / 10.0 / 10.0 / totalTime / totalTime2;
    }
}

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(getName());
                }
            }).start();
        }
    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println(getName());
    }
}

public class Test {
    public static void main(String[] args) {
        new Thread(new MyRunnable(), "A").start();
        new Thread(new MyRunnable(), "B").start();
        new Thread(new MyRunnable(), "C").start();
    }
}