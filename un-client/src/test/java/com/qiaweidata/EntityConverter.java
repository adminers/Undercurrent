package com.qiaweidata;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityConverter {

    public static void main(String[] args) throws IOException {

        String targetPath = "C:\\Users\\Administrator\\Downloads\\tfile\\";
        Path dirPath = Paths.get("G:\\work\\wrenchData\\wrenchData-core\\src\\main\\java\\com\\wrenchdata\\web\\system\\entity"); // 替换为你的路径

        // 获取所有文件（不包括子目录）
        try (Stream<Path> list = Files.list(dirPath)) {
            List<Path> files = list
                    .filter(Files::isRegularFile) // 只保留文件
                    .collect(Collectors.toList());
            for (Path file : files) {
                FileLineToMap fileLineToMap = new FileLineToMap();
                fileLineToMap.converterStart(file.toString(), targetPath + file.getFileName());
            }
        }

    }

    public static void convertEntityClass(String sourcePath, String targetPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(sourcePath));
        StringBuilder result = new StringBuilder();
        
        // 1. 处理包声明和导入
        processPackageAndImports(lines, result);
        
        // 2. 处理类注释
        processClassComment(lines, result);
        
        // 3. 处理类声明
        processClassDeclaration(lines, result);
        
        // 4. 处理常量字段
        processConstantFields(lines, result);
        
        // 5. 处理实体字段
        processEntityFields(lines, result);
        
        // 写入目标文件
        Files.write(Paths.get(targetPath), result.toString().getBytes());
    }

    private static void processPackageAndImports(List<String> lines, StringBuilder result) {
        lines.stream()
            .filter(line -> line.startsWith("package") || line.startsWith("import"))
            .forEach(line -> {
                // 替换 javax.persistence 为 jakarta.persistence
                if (line.contains("javax.persistence")) {
                    line = line.replace("javax.persistence", "jakarta.persistence");
                }
                result.append(line).append("\n");
            });
        
        // 添加必要的导入
        result.append("import lombok.Getter;\n")
              .append("import lombok.Setter;\n")
              .append("import java.io.Serial;\n\n");
    }

    private static void processClassComment(List<String> lines, StringBuilder result) {
        boolean inComment = false;
        for (String line : lines) {
            if (line.trim().startsWith("/**")) {
                inComment = true;
                result.append(line).append("\n");
            } else if (inComment && line.trim().startsWith("*/")) {
                result.append(line).append("\n");
                break;
            } else if (inComment) {
                result.append(line).append("\n");
            }
        }
    }

    private static void processClassDeclaration(List<String> lines, StringBuilder result) {
        String classDeclaration = lines.stream()
            .filter(line -> line.contains("public class") || line.contains("extends"))
            .findFirst()
            .orElse("");
        
        // 替换 IdEntity 为 MarkAndIdEntity
        classDeclaration = classDeclaration.replace("IdEntity", "MarkAndIdEntity");
        
        // 添加注解
        result.append("@Getter\n@Setter\n")
              .append("@Entity\n");
        
        // 保留原 Table 注解
        lines.stream()
            .filter(line -> line.contains("@Table"))
            .findFirst()
            .ifPresent(result::append);
        
        // 处理 DynamicInsert/Update
        result.append("@DynamicInsert()\n")
              .append("@DynamicUpdate()\n")
              .append(classDeclaration)
              .append(" {\n\n");
        
        // 添加 serialVersionUID
        result.append("\t@Serial\n")
              .append("\tprivate static final long serialVersionUID = 1L;\n\n");
    }

    private static void processConstantFields(List<String> lines, StringBuilder result) {
        Pattern constantPattern = Pattern.compile("public static final String (\\w+) =");
        
        lines.stream()
            .filter(line -> constantPattern.matcher(line).find())
            .forEach(line -> {
                result.append("\t").append(line.trim()).append("\n");
            });
        
        if (result.indexOf("TASKTYPE1") > 0) {
            result.append("\n");
        }
    }

    private static void processEntityFields(List<String> lines, StringBuilder result) {
        // 匹配字段声明和对应的 getter 方法上的注解
        Map<String, String> fieldAnnotations = new LinkedHashMap<>();
        String currentField = null;
        
        // 第一遍：收集字段和对应的注解
        for (String line : lines) {
            // 匹配字段声明
            if (line.trim().matches("private\\s+\\w+\\s+\\w+;")) {
                currentField = line.trim().split("\\s+")[2].replace(";", "");
            }
            // 匹配 getter 方法上的 Column 注解
            else if (line.trim().startsWith("@Column") && currentField != null) {
                fieldAnnotations.put(currentField, line.trim());
                currentField = null;
            }
        }
        
        // 第二遍：处理原始字段
        Set<String> skipFields = Set.of("createUser", "createTime", "updateUser", "updateTime");
        
        for (String line : lines) {
            if (line.trim().matches("private\\s+\\w+\\s+\\w+;")) {
                String fieldName = line.trim().split("\\s+")[2].replace(";", "");
                
                if (!skipFields.contains(fieldName)) {
                    // 添加字段注解
                    if (fieldAnnotations.containsKey(fieldName)) {
                        result.append("\t").append(fieldAnnotations.get(fieldName)).append("\n");
                    }
                    // 添加字段声明
                    result.append("\t").append(line.trim()).append("\n\n");
                }
            }
        }
    }
}