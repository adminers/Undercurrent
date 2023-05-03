//   Obtain system memory, encapsulate it in an object, and convert it into JSON print to the console 
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class MemoryUsage {
    public static void main(String[] args) {
        ThreadMXBean threadMX = ManagementFactory.getThreadMXBean();
        // Can be used to stop the program, for example
        threadMX.setThreadCpuTimeEnabled(true);
        System.out.println("Memory Usage for the JVM");
        System.out.println("=========================");
        try {
            for (int i = 0; i < 10; i++) {
                long start = threadMX.getCurrentThreadCpuTime();
                Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                long end = threadMX.getCurrentThreadCpuTime();
                System.out.printf("%d:%f%n", i, TimeUnit.MILLISECONDS.toSeconds(end - start));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}