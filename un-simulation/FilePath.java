// Using Java8, according to the system path String path="E: "; Retrieve all folders and files under the system path, encapsulate the results into a 
import java.io.File;
collection object, and recursively loop to print the results
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Path {
    public static void main(String[] args) {
        List<String> paths = new ArrayList<>();
        for (String path : System.getProperty("java.library.path").split(File.pathSeparator)) {
            paths.addAll(Arrays.asList(path.split(File.pathSeparator)));
        }
        paths.stream().forEach(System.out::println);
    }
}// 注释，代码。
