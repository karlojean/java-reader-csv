package services;

import entities.Product;
import exceptions.ReaderException;
import utils.CSVParse;
import utils.FileSystemHandle;

import java.awt.desktop.OpenURIEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServices {

    FileSystemHandle fileSystemHandle = new FileSystemHandle();
    CSVParse csvParse = new CSVParse();

    public  List<Product> loadProductFromFolder(File folder)  {
        File[] files = fileSystemHandle.listFilesFromExtension(folder, ".csv");
        List<Product> productArrays = new ArrayList<>();

        if(files == null) {
            throw new ReaderException("No files found inside the folder.");
        }

        for (File f:  files) {
            try(BufferedReader br = fileSystemHandle.openFile(f)) {
                productArrays.addAll(csvParse.readerProductsInCSV(br));
            } catch (IOException e) {
                throw new ReaderException("File not open: " + e.getMessage());
            }
        }

        return productArrays;
    }

    public void processTotalPrice(List<Product> listProduct, File folder) throws ReaderException {

        File outputFolder = new File(folder + "\\out");
        if(!outputFolder.exists()) {
           boolean isCreatedOutputFolder = outputFolder.mkdir();
            if (!isCreatedOutputFolder) {
                throw new ReaderException("Not possible create output folder");
            }
        }

        File outputFile = new File(outputFolder, "\\out.csv");
        fileSystemHandle.createFile(outputFile);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            csvParse.writeProductWithTotalPriceToCsv(listProduct, bw);
        } catch (IOException e) {
            throw new ReaderException(e.getMessage());
        }

    }

}
