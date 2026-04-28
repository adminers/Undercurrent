package com.dpv4.task.handler;

import com.dpv4.task.model.TaskResult;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ResultHandler {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public void handle(TaskResult result) {
        String log = String.format(
            "[%s] 任务: %s, 状态: %s, 耗时: %dms%s",
            result.getEndTime() != null ? result.getEndTime().format(FORMATTER) : "N/A",
            result.getTaskName(),
            result.isSuccess() ? "✅ 成功" : "❌ 失败",
            result.getDurationMs(),
            result.getErrorMessage() != null ? ", 错误: " + result.getErrorMessage() : ""
        );
        
        System.out.println(log);
        
        if (result.getResponse() != null && result.getResponse().getBody() != null) {
            System.out.println("响应内容: " + result.getResponse().getBody().substring(0, Math.min(200, result.getResponse().getBody().length())));
        }
        
        // 这里可以扩展：保存到数据库、发送通知等
        saveToDatabase(result);
    }
    
    private void saveToDatabase(TaskResult result) {
        // 模拟保存到数据库
        // System.out.println("保存结果到数据库: " + result.getTaskId());
    }
}