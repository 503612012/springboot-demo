package com.oven.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileUtils {

    private final Path path = Paths.get("/Users/oven/Desktop/");

    public void upload(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                System.out.println("文件为空！");
                return;
            }
            Files.copy(file.getInputStream(), path.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (IOException e) {
            log.error("上传文件异常：", e);
        }
    }

    public Stream<Path> list() {
        try {
            return Files.walk(this.path, 1)
                    .filter(path -> !path.equals(this.path))
                    .map(this.path::relativize);
        } catch (IOException e) {
            log.error("获取文件列表异常：", e);
        }
        return null;
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = path.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                System.out.println("读取文件失败！");
            }
        } catch (MalformedURLException e) {
            log.error("读取文件异常：", e);
        }
        return null;
    }

}
