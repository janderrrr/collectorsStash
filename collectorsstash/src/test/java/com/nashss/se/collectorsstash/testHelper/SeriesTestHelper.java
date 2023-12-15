package com.nashss.se.collectorsstash.testHelper;

import com.nashss.se.collectorsstash.dynamodb.models.Series;

import java.util.ArrayList;
import java.util.List;

public class SeriesTestHelper {

    public SeriesTestHelper() {}

    public static Series generateSeries(int sequenceNum) {
        Series series = new Series();
        series.setCustomerId("jojo@gmail.com");
        series.setSeriesId("x12345" + sequenceNum);
        series.setVolumeNumber("volume" + sequenceNum);
        series.setTitle("title" + sequenceNum);

        return series;
    }

    public static List<Series> generateSeriesList(int numOfSeries) {
        List<Series> seriesList = new ArrayList<>();

        for(int i = 0; i < numOfSeries; i++) {
            seriesList.add(generateSeries(i));
        }
        return seriesList;
    }
}
