package ru.KhosueAv.ulearn;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartCreator {

    public static void create(String title, String categoryLabel, String valueLabel,
        DefaultCategoryDataset dataset, String savePath) throws IOException {
        JFreeChart chart = ChartFactory.createLineChart(
            title,
            categoryLabel,
            valueLabel,
            dataset
        );

        ChartUtilities.saveChartAsJPEG(
            new File(savePath),
            chart,
            1920,
            1080
        );
    }
}
