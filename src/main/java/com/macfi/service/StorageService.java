package com.macfi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, String directory);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String finlename);

    void deleteAll();
}
