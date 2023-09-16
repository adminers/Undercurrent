// Using Java8, according to the system path String path="E: "; According to the system path provided by path, use NIO to obtain all folders and files under the path folder, encapsulate the results into a collection object, and recursively loop to print the results
. Note that the path is case-insensitive on Windows.
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllFiles {
    public static void main(String[] args) {
        List<String> paths = Arrays.asList("E:", "E:\\My_CodeGeeX", "E:\\CodeGeeX", "c:\\codegeex", "c:\\codegeex\\foo", "c:\\codegeex\\bar");
        paths.stream().map(Paths::get).map(Path::normalize).map(p -> p.toString()).forEach(System.out::println);
    }
}