package ru.KhosueAv.ulearn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {

    public List<Earthquake> readToObj() {
        String path = "src\\main\\java\\ru\\urfu\\ulearn\\Землетрясения.csv";
        List<Earthquake> result = new ArrayList<>();

        try (var br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                // вызываем метод который вернет нам массив из 5 элементов
                var earthquakeData = splitForEarthquakeData(line);
                // создаём объект заполняя его данными
                result.add(new Earthquake(earthquakeData));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String[] splitForEarthquakeData(String line) {
        if (line.contains("\"")) {
            String[] splitedData = line.split("\"");
            var result = new ArrayList<String>();

            Arrays.stream(splitedData[0].split(","))
                .forEach(s -> result.add(s));

            result.add(splitedData[1]);

            Arrays.stream(splitedData[2].split(","))
                .filter(s -> s != "").forEach(s -> result.add(s));

            return result.toArray(new String[6]);
        }
        return line.split(",");
    }
}

