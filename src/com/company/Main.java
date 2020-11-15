package com.company;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // open file and read data
        FileReader fr= new FileReader("B2BTUR01_07_2019.txt");
        Scanner scan = new Scanner(fr);
        ArrayList<MeteoSingleDay> meteoData = new ArrayList<>();

        // read the line from the text-file
        // and make array that matches for the one day
        while (scan.hasNextLine()) {
            String[] bufArray = scan.nextLine().split(",");

            meteoData.add(new MeteoSingleDay(LocalDate.of(Integer.parseInt(bufArray[0]),
                                             Integer.parseInt(bufArray[1]),
                                             Integer.parseInt(bufArray[2])),
                                             Double.parseDouble(bufArray[3]),
                                             Double.parseDouble(bufArray[4]),
                                             Double.parseDouble(bufArray[5]),
                                             Double.parseDouble(bufArray[6]),
                                             Double.parseDouble(bufArray[7]),
                                             Integer.parseInt(bufArray[8]),
                                             Double.parseDouble(bufArray[9])));
        }
        fr.close();

        // user greetings:
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Welcome to the application for Meteorological Data Analysis.");
        System.out.println("--------------------------------------------------------------------------------");

        // total number of daily records
        System.out.println("We have "+ meteoData.size() + " meteorological daily records to analyze");

        // Average temperature for the reporting period :
        double averageTemperatureTotal = 0;
        for (MeteoSingleDay meteoDataTmp: meteoData) {
            averageTemperatureTotal += meteoDataTmp.getAverage_temperature();
        }
        System.out.println(String.format("Average temperature for the reporting period: %.1f",
                averageTemperatureTotal/meteoData.size()) + "°C");

        // Maximum temperature for the reporting period:
        int indexMaxTemperature = 0;
        for (MeteoSingleDay meteoDataTmp: meteoData) {
            if (meteoDataTmp.getMax_temperature() > meteoData.get(indexMaxTemperature).getMax_temperature())
                indexMaxTemperature = meteoData.indexOf(meteoDataTmp);
        }
        System.out.println("Maximum temperature for the reporting period: " +
                        meteoData.get(indexMaxTemperature).getDate() +
                        String.format(" was %.1f", +
                        meteoData.get(indexMaxTemperature).getMax_temperature()) + "°C");

        // Minimum temperature for the reporting period:
        int indexMinTemperature = 0;
        for (MeteoSingleDay meteoDataTmp: meteoData) {
            if (meteoDataTmp.getMin_temperature() < meteoData.get(indexMinTemperature).getMin_temperature())
                indexMinTemperature = meteoData.indexOf(meteoDataTmp);
        }
        System.out.println("Minimum temperature for the reporting period: " +
                meteoData.get(indexMinTemperature).getDate() +
                String.format(" was %.1f", +
                        meteoData.get(indexMinTemperature).getMin_temperature()) + "°C");

        //Number of windy days:
        int counterOfWindyDays = 0;
        for (MeteoSingleDay meteoDataTmp: meteoData) {
            if (meteoDataTmp.getWindSpeed() >= 4.2 )
                counterOfWindyDays++;
        }
        System.out.println("Number of windy days: " + counterOfWindyDays);

        //Number of calm days:
        int counterOfCalmDays = 0;
        for (MeteoSingleDay meteoDataTmp: meteoData) {
            if (meteoDataTmp.getWindSpeed() <= 1.8 )
                counterOfCalmDays++;
        }
        System.out.println("Number of calm days: " + counterOfCalmDays);

        //Precipitation summary in month decades:
        double firstDecade = 0, secondDecade = 0, thirdDecade = 0;
        for (MeteoSingleDay meteoDataTmp: meteoData) {
            if (meteoData.indexOf(meteoDataTmp) < 10 ) {
                firstDecade += meteoDataTmp.getTotalPrecipitation();}
                else if (meteoData.indexOf(meteoDataTmp) >= 10 && meteoData.indexOf(meteoDataTmp) < 20) {
                secondDecade += meteoDataTmp.getTotalPrecipitation();}
                else { thirdDecade += meteoDataTmp.getTotalPrecipitation(); }
            }
        System.out.println("Precipitation summary in month decades: " +
                String.format("%.1f", firstDecade) + "mm - " +
                String.format("%.1f", secondDecade) + "mm - " +
                String.format("%.1f", thirdDecade)  + "mm");

        //graph of daily temperature distribution:
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("   | 00--------10--------20--------30--------40");
        for (MeteoSingleDay meteoDataTmp: meteoData) {
            System.out.print(String.format("%02d", meteoData.indexOf(meteoDataTmp) + 1)+ " | ");
            for (int i = 0; i < 40; i++) {
            if (i >= meteoDataTmp.getMin_temperature() && i <= meteoDataTmp.getMax_temperature()) {
                System.out.print("*");}
            else {System.out.print(" ");}
            }
            System.out.println();
        }
    }
}
