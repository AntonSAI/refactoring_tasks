package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidDirectoryException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileManager {

    private static final String[] IMAGE_TYPE = {"jpg", "png"};
    private static final String[] FILE_TYPE = {"pdf", "doc"};

    private String basePath = PropertyUtil.loadProperty("basePath");

    public File retrieveFile(String fileName) {
        validateFileType(fileName);
        final String directoryPath = basePath + File.separator;
        return Paths.get(directoryPath, fileName).toFile();
    }

    public List<String> getImages() {
        return getFiles(basePath, IMAGE_TYPE);
    }

    public List<String> getDocumentFile() {
        return getFiles(basePath, FILE_TYPE);
    }

    private void validateFileType(String fileName) {
        if (isInvalidFileType(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private boolean isInvalidFileType(String fileName) {
        return isInvalidImage(fileName) && isInvalidDocument(fileName);
    }

    private boolean isInvalidImage(String fileName) {
        FileExtension imageExtension = new FileExtension(IMAGE_TYPE);
        return !imageExtension.test(fileName);
    }

    private boolean isInvalidDocument(String fileName) {
        FileExtension documentExtension = new FileExtension(FILE_TYPE);
        return !documentExtension.test(fileName);
    }

    private List<String> getFiles(String directoryPath, String[] extensions) {
        final FileExtension fileExtension = new FileExtension(extensions);
        return Arrays.asList(getDirectory(directoryPath).list(getFilenameFilterByPredicate(fileExtension)));
    }

    private FilenameFilter getFilenameFilterByPredicate(final FileExtension fileExtension) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                return fileExtension.test(name);
            }
        };
    }

    private File getDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        validateDirectory(directory);
        return directory;
    }

    private void validateDirectory(File directoryInstance) {
        if (isNotDirectory(directoryInstance)) {
            throw new InvalidDirectoryException("Invalid directory found: " + directoryInstance.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File directory) {
        return !directory.isDirectory();
    }

}