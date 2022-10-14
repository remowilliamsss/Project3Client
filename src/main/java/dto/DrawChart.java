package dto;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DrawChart {
    public static void main(String[] args) {
        List<MeasurementDTO> measurements = getMeasurementsFromServer();
        drawChart(measurements);
    }

    private static List<MeasurementDTO> getMeasurementsFromServer() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/measurements";

        MeasurementsResponse response = restTemplate.getForObject(url, MeasurementsResponse.class);

        if (response == null || response.getMeasurements() == null)
            return Collections.emptyList();

        return response.getMeasurements();
    }

    private static void drawChart(List<MeasurementDTO> measurements) {
        List<Double> values = measurements.stream().map(MeasurementDTO::getValue).collect(Collectors.toList());

        double[] xData = IntStream.range(0, values.size()).asDoubleStream().toArray();
        double[] yData = values.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "temperature",
                xData, yData);

        new SwingWrapper(chart).displayChart();
    }
}
