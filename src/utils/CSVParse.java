package utils;

import entities.Product;
import exceptions.ReaderException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVParse {

    public List<Product> readerProductsInCSV(BufferedReader br) throws IOException {

        List<Product> products = new ArrayList<>();

        br.readLine();
        String line = br.readLine();
        while (line != null) {
            String[] values = line.split(",");
            String name = values[0];
            double price = Double.parseDouble(values[1]);
            int quantity = Integer.parseInt(values[2]);
            Product p = new Product(name, price, quantity);
            products.add(p);
            line = br.readLine();
        }

        return products;
    }

    public void writeProductWithTotalPriceToCsv(List<Product> products, BufferedWriter outputFolder)  {
        try {
            outputFolder.write("name,total");
            outputFolder.newLine();
            for (Product p : products) {
                outputFolder.write(p.getName() + "," + p.totalPrice());
                outputFolder.newLine();
            }
        } catch (IOException e) {
            throw new ReaderException("Error registering total price in output file" + e.getMessage());
        }
    }
}