// Using Java8, according to the system path String path="E: "; According to the system path provided by path, use NIO to obtain all folders and files under the path folder, encapsulate the results into a collection object, and recursively loop to print the results
. Note that the path is case-insensitive on Windows.
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListFiles {
    public static void main(String[] args) {
        List<String> paths = Arrays.asList("E:", "E:\\MyData", "E:\\MyData\\Subdir", "E:\\MyData\\Subdir\\file5");
        Collections.list(Paths.get(paths.toArray(new String[0]))).forEach(System.out::println);
    }
}