package com.sprint1.app;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int items;
        String name;
        String price;

        Scanner myScanner = new Scanner(System.in);

        // create file
        try {
            new File("items.csv");
        } catch (Exception e) {
            System.out.println("Klaida apdorojant failus (" + e + ")");
        }

        while(true) {
            System.out.println("Kiek prekiu noresite prideti? (tarp 0 ir 100): ");

            // check item number input
            try {
                if(myScanner.hasNextInt()) {
                    items = myScanner.nextInt();
                    myScanner.nextLine();
                }
                else {
                    myScanner.nextLine();
                    throw new Exception("Klaida! Iveskite taisyklingus duomenis.");
                }

                // check item number
                if(items > 0 && items < 100) {

                    // enter items
                    for (int i = 0; i < items; i++) {
                        System.out.println("Iveskite " + (i + 1) + " prekes pavadinima: ");
                        name = myScanner.nextLine();

                        // check the price and format it
                        while (true) {
                            System.out.println("Iveskite \"" + name + "\" prekes kaina: ");
                            if (myScanner.hasNextDouble()) {
                                DecimalFormat df = new DecimalFormat("######0.00");
                                price = df.format(myScanner.nextDouble());

                                myScanner.nextLine();

                                // save data to file
                                try {
                                    FileWriter myWriter = new FileWriter("items.csv", true);
                                    myWriter.write(name + " " + price + "\n");
                                    myWriter.close();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Klaida apdorojant failus (" + e + ")");
                                }
                            } else { // invalid price
                                myScanner.nextLine();
                                System.out.println("Iveskite taisiklynga kaina.");
                            }
                        }
                    }

                    // return full list and exit
                    System.out.println(items + " prekes pridetos sekmingai!");
                    System.out.println("Visas prekiu sarasas: ");
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