package org.apache.commons.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class TestFileUtils {
    public static void main(String[] args) {
        try {
            // 创建一个临时文件用于测试
            File tempFile = File.createTempFile("test", ".txt");
            String testContent = "This is a test content.";
            // 写入测试内容
            FileUtils.writeStringToFile(tempFile, testContent, StandardCharsets.UTF_8);

            // 测试 readFileToString 方法
            String content = FileUtils.readFileToString(tempFile, StandardCharsets.UTF_8);
            if (content.equals(testContent)) {
                System.out.println("readFileToString 方法测试通过");
            } else {
                System.out.println("readFileToString 方法测试失败");
            }

            // 测试 fileExists 方法
            boolean exists = FileUtils.getFile(tempFile.getAbsolutePath()).exists();
            if (exists) {
                System.out.println("fileExists 方法测试通过");
            } else {
                System.out.println("fileExists 方法测试失败");
            }


            // 测试 byteCountToDisplaySize 方法
            BigInteger fileSize = BigInteger.valueOf(tempFile.length());
            String displaySize = FileUtils.byteCountToDisplaySize(fileSize);

            // 简单验证显示结果是否包含正确的单位
            boolean isValidDisplay = false;
            if (fileSize.compareTo(BigInteger.valueOf(1024)) < 0) {
                isValidDisplay = displaySize.endsWith(" bytes");
            } else if (fileSize.compareTo(BigInteger.valueOf(1024 * 1024)) < 0) {
                isValidDisplay = displaySize.endsWith(" KB");
            } else if (fileSize.compareTo(BigInteger.valueOf(1024 * 1024 * 1024)) < 0) {
                isValidDisplay = displaySize.endsWith(" MB");
            }
            if (isValidDisplay) {
                System.out.println("byteCountToDisplaySize 方法测试通过");
            } else {
                System.out.println("byteCountToDisplaySize 方法测试失败");
            }

            // 删除临时文件
            tempFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}