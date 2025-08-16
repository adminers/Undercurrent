package com.qiaweidata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLineToMap {

    private List<String> lines = new ArrayList<>();
    private Map<String, Integer> lineIndexs = new LinkedHashMap<>();
    private Map<String, String> attrMap = new LinkedHashMap<>();
    private List<String> columnAnnotations = new ArrayList<>();
    private List<String> columns = new ArrayList<>();
    private Map<Integer, String> addIndex = new LinkedHashMap<>();

    public void converterStart(String filePath, String targetPath) {
        try {

            Map<String, String> lineMap = parseFileToMap(filePath);


            lines.add(lineIndexs.get("@Entity"), "@Getter\n@Setter");

            StringBuilder result = new StringBuilder();
            for (String line : lines) {
                if (line.indexOf("javax.persistence.*;") != -1) {
                    line = "import jakarta.persistence.Column;\n" + "import jakarta.persistence.Entity;\n" + "import jakarta.persistence.Table;\n"
                            +
                            "import lombok.Getter;\n" +
                            "import lombok.Setter;";
                }
                if (line.indexOf("javax.persistence.Column;") != -1) {
                    line = "import jakarta.persistence.Column;";
                }
                if (line.indexOf("javax.persistence.Entity;") != -1) {
                    line = "import jakarta.persistence.Entity;";
                }
                if (line.indexOf("javax.persistence.Table;") != -1) {
                    line = "import jakarta.persistence.Table;"
                    + "\n" +
                            "import lombok.Getter;\n" +
                            "import lombok.Setter;";
                }
                if (line.indexOf("com.wrenchdata.core.common.entity.IdEntity;") != -1) {
                    line = "import vip.xiaonuo.common.pojo.MarkAndIdEntity;";
                }
                if (line.indexOf("extends IdEntity") != -1) {
                    line = line.replace("extends IdEntity", "extends MarkAndIdEntity");
                }
                result.append(line).append("\n");
            }
            result.append("}");

            // 写入目标文件
            Files.write(Paths.get(targetPath), result.toString().getBytes());

            // 打印结果
//            lineMap.forEach((key, value) -> System.out.println("" + value ));
//            lines.forEach(l -> System.out.println(l));
//            columnAnnotations.forEach(l -> System.out.println(l));

        } catch (IOException e) {
            System.err.println("处理文件时出错: " + e.getMessage());
        }
    }

    public AtomicInteger integer = new AtomicInteger(0);

    /**
     * 将文件按行解析为Map，key和value都是每行的内容
     * @param filePath 文件路径
     * @return 行内容为key和value的Map
     * @throws IOException 如果文件读取失败
     */
    public Map<String, String> parseFileToMap(String filePath) throws IOException {
        Map<String, String> lineMap = new LinkedHashMap<>(); // 使用LinkedHashMap保持插入顺序

        int maxAttrLine = -1;
        AtomicInteger index = new AtomicInteger(0);
        try (Stream<String> lines1 = Files.lines(Paths.get(filePath))) {
            lines1.forEach(line -> {
                String key = line;
                if (lineMap.containsKey(key)) {
                    key = key + integer.getAndIncrement();
                }
                lineMap.put(key, line);
                lines.add(line);
                lineIndexs.put(key, index.getAndIncrement());
            });
        }
        for (int i = lines.size() - 1; i >= 0; i--) {
            String lineStr = lines.get(i);
            boolean columnTrue = ColumnAnnotationFinder.containsColumnAnnotation(lineStr);
            if (columnTrue) {
                columnAnnotations.add(lineStr);

                // 寻找下两行
                String retrunStr = lines.get(i + 2);
                String attrName = FieldMatcher.extractVariableName(retrunStr);
                attrMap.put(attrName, lineStr);
                columns.add(attrName);
            }

            // 匹配字段
            for (String column : columns) {
                boolean fieldDeclarationMatch = FieldMatcher.isFieldDeclarationMatch(lineStr, column);
                if (fieldDeclarationMatch) {
                    if (maxAttrLine == -1) {
                        maxAttrLine = i;
                    }
                    addIndex.put(i, attrMap.get(column));
                    break;
                }
            }
        }
        if (maxAttrLine != -1) {

            // 移除后面所有行
            lines.subList(maxAttrLine + 1, lines.size()).clear();
        }
        addIndex.forEach((k, v) -> {lines.add(k, v);});
        return lineMap;
    }
}