package com.kardex.core.util;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

public class FileTestUtils {

    private static FileTestUtils instance = null;
    
    private static final String RESOURCE_PATH = "src/test/resources/";

    private FileTestUtils() {}

    public static FileTestUtils getInstance() {
        return instance = Objects.isNull(instance)
                ? new FileTestUtils()
                : instance;
    }

    public File getFile(String fileName) {
    	StringBuilder path = new StringBuilder();
    	path.append(RESOURCE_PATH);
        return Optional.ofNullable(fileName)
                .map(fn -> path.append(fn).toString())
                .map(File::new)
                .orElseThrow(() -> new RuntimeException("File: " + fileName + " could not be found"));
    }


}
