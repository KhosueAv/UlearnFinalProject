package ru.KhosueAv.ulearn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {

    private static List<Earthquake> Earthquakes;
    private static final List<Double> westVirginiaMagnitudes = new ArrayList<>();
    private static SQLiteHandler sql;

    public static void main(String[] main) throws IOException, SQLException {
        // заполняем лист с данными с помощью метода readToObj
        Earthquakes = new CSVHandler().readToObj();
        // создаем эксемляр класса обработчика SQL
        sql = new SQLiteHandler();
        // идём по листу с классами Earthquake
        for (var i : Earthquakes) {
            // добавляем объект в БД
            sql.add(i);
            if (i.getState().contains("West Virginia")) {
                // для второго задания сохраним магнитуды из штата West Virginia
                westVirginiaMagnitudes.add(i.getMagnitude());
            }
        }

        // выводим данные из БД в консоль
        var rs = sql.executeQuery("select * from earthquakes");
        var col = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            for (var i = 1; i < col; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }

        Task1();
        Task2();
        Task3();
    }

    // Постройте график по среднему количеству землетрясений для каждого года
    private static void Task1() throws IOException {
        var dataset = new DefaultCategoryDataset();
        var earthquakesByYear = new HashMap<Integer, Integer>();

        // собираем данные о количестве землетрясений
        for (var i : Earthquakes) {
            var year = i.getDate().toLocalDate().getYear();
            if (earthquakesByYear.containsKey(year)) {
                earthquakesByYear.put(year, earthquakesByYear.get(year) + 1);
            } else {
                earthquakesByYear.put(year, 1);
            }
        }

        // сортируем hashmap earthquakesByYear по ключу, чтобы иметь порядок вида
        // 1999 2000 2001 2002...
        var map = earthquakesByYear.entrySet()
            .stream().sorted(Entry.comparingByKey()).collect(
                Collectors.toMap(Entry::getKey, Entry::getValue,
                    (e1, e2) -> e1, LinkedHashMap::new));

        // заполняем датасет для графика
        for (var key : map.keySet()) {
            dataset.addValue(map.get(key), "year", key);
        }

        // создаём и сохраняем график
        ChartCreator.create("График среднего количества землетрясений",
            "Год",
            "Количество землетрясений",
            dataset,
            "src\\main\\java\\ru\\urfu\\ulearn\\chart.jpg");

        System.out.println("\nTask1 - График был успешно создан");
    }

    private static void Task2() {
        System.out.println("Task2 - Средняя магнитуда для штата: "
            + westVirginiaMagnitudes.stream().mapToDouble(a -> a).average().getAsDouble()
        );
    }

    private static void Task3() throws SQLException {
        var result = """ 
            SELECT state, depth FROM earthquakes
            WHERE strftime('%Y', date(registration_date)) = '2013'
            ORDER BY depth DESC
            """;

        var rs = sql.executeQuery(result);
        System.out.println(String.format(
            "Task3 - Самое глубокое землятресение произошлов в штате - %s, его глубина ставило - %s",
            rs.getString(1), rs.getString(2))
        );
    }
}
