package utils;

import exceptions.ReaderException;

import java.io.*;

public class FileSystemHandle {

    public File[] listFilesFromExtension(File folder, String extension) {
        return folder.listFiles((dir, name) -> name.endsWith(extension));
    }

    public BufferedReader openFile(File file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }

    public File createFile(File file) throws ReaderException {
        try {
            System.out.println(file);
            boolean isFileCreated = file.createNewFile();
            if (!isFileCreated) {
                throw new ReaderException("File not created");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return file;
    }
}
