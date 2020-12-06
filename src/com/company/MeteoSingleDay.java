package com.company;

import java.time.LocalDate;

public class MeteoSingleDay {
    LocalDate date;
    final double averageTemperature;
    final double maxTemperature;
    final double minTemperature;
    final double pressure;
    final double windSpeed;
    final int humidity;
    final double totalPrecipitation;

    public MeteoSingleDay(LocalDate date,
                          double averageTemperature,
                          double maxTemperature,
                          double minTemperature,
                          double pressure,
                          double windSpeed,
                          int humidity,
                          double totalPrecipitation) {
        this.date = date;
        this.averageTemperature = averageTemperature;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.totalPrecipitation = totalPrecipitation;
    }


    public LocalDate getDate() {
        return date;
    }

    public double getAverage_temperature() {
        return averageTemperature;
    }

    public double getMax_temperature() {
        return maxTemperature;
    }

    public double getMin_temperature() {
        return minTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getTotalPrecipitation() {
        return totalPrecipitation;
    }

}
