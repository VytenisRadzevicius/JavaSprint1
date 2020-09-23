package com.sprint1.app;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int items;
        String name;

        Scanner myScanner = new Scanner(System.in);

        // create file
        try {
            new File("items.csv");
        } catch (Exception e) {
            System.out.println("Klaida apdorojant failus (" + e + ")");
        }

        while(true) {
            System.out.println("Kiek prekių norėsite pridėti? (tarp 0 ir 100): ");

            // check item number input
            try {
                if(myScanner.hasNextInt()) {
                    items = myScanner.nextInt();
                    myScanner.nextLine();
                }
                else {
                    myScanner.nextLine();
                    throw new Exception("Klaida! Įveskite taisyklingus duomenis.");
                }

                // check item number
                if(items > 0 && items < 100) {

                    // enter items
                    for (int i = 0; i < items; i++) {
                        System.out.println("Įveskite " + (i + 1) + " prekės pavadinimą: ");
                        name = myScanner.nextLine();

                        // check the price and format it
                        while (true) {
                            System.out.println("Įveskite \"" + name + "\" prekės kainą: ");
                            if (myScanner.hasNextDouble()) {
                                Item newItem = new Item(name, myScanner.nextDouble());
                                myScanner.nextLine();

                                // save data to file
                                try {
                                    FileWriter myWriter = new FileWriter("items.csv", true);
                                    myWriter.write(newItem + "\n");
                                    myWriter.close();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Klaida apdorojant failus (" + e + ")");
                                }
                            } else { // invalid price
                                myScanner.nextLine();
                                System.out.println("Įveskite taisyklinga kainą.");
                            }
                        }
                    }

                    // return full list and exit
                    System.out.println(items + " prekės pridėtos sekmingai!");
                    System.out.println("Visas prekių sąrašas: ");
                    try {
                        File myFile = new File("items.csv");
                        Scanner myReader = new Scanner(myFile);

                        int counter = 0;
                        while (myReader.hasNextLine()) {
                            counter++;
                            String data = myReader.nextLine();
                            System.out.println(counter + ". " + data);
                        }
                        myReader.close();
                    } catch (Exception e) {
                        System.out.println("Klaida apdorojant failus (" + e + ")");
                    }

                    myScanner.close();
                    break;
                } else { // invalid number of items
                    throw new Exception("Klaida! Turi buti tarp 0 ir 100.");
                }
            }

            // error handling for items
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        } // while
    } // main
} // Main

class Item {
    String name;
    String price;

    public Item(String name, double price) {
        this.name = name;
        DecimalFormat df = new DecimalFormat("######0.00");
        this.price = df.format(price);
    }
    @Override
    public String toString() {
        return this.name + " " + this.price;
    }
}