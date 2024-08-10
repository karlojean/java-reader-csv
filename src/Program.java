import entities.Product;
import services.ProductServices;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    static ProductServices productServices = new ProductServices();

    public static void main(String[] args) {
        File folder = new File("c:\\ProductsList");
        List<Product> productList = productServices.loadProductFromFolder(folder);
        productServices.processTotalPrice(productList, folder);
    }
}