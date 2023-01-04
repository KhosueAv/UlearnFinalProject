# UlearnFinalProject 
  **Итоговый проект по курсу Ulearn Java - Вариант 5**
  
  Для выполнения задач нам требуется скачать файл с данными формата csv.
![image](https://user-images.githubusercontent.com/121817358/210408349-c1be6777-dc3d-4325-bbc9-6ff696d0ad0d.png)
  
  На основе данных в csv, создаём модель для данных. Поля данной модели являются приватными(Инкапсуляция)
![image](https://user-images.githubusercontent.com/121817358/210408858-5edd1c74-bebc-4b55-a65e-51fb1421cb2e.png)
  Для получения данных из модели создадим getter'ы
  ![image](https://user-images.githubusercontent.com/121817358/210409178-45d08e02-0232-459c-9322-51050a5a7e3e.png)

<br /><br />
  Создадим класс CSVHandler в котором опишем логику чтения и преобразование данных из csv в ArrayList<Earthquake>. Сразу же объявляем метод readToObj который будет возвращать List<Earthquake>
![image](https://user-images.githubusercontent.com/121817358/210409636-b13df9f9-5aa9-41aa-9b0e-a495942be06c.png)

  Описываем логику метода readToObj, там же был создан в метод splitForEarthquakeData получающий на вход строку, возвращающий String[] (элементы строки разделённые ',')
![image](https://user-images.githubusercontent.com/121817358/210409955-b805edac-6bae-4ab9-826b-b2a851217818.png)

  Описываем логику метода splitForEarthquakeData, т.к строка из сsv файла, может содержать запятые которые не являются разделителями между строками, мы не можем использовать просто split(",").
  ![image](https://user-images.githubusercontent.com/121817358/210410522-019f6ab4-8f70-49cb-b530-428c738246f4.png)
  
<br /><br />
  Создадим класс SQLHandler который будет хранить логику операций с БД. Создаём сигнатуры методов addTable, add, executeQuery.
  addTable - будет создавать таблицу в БД, если данной таблицы не существует.
  add - будет записывать данные в БД, принимая в качестве аргумента класс Earthquake.
  executeQuery - будет выполнять запрос и возвращать результат в виде ResultSet
![image](https://user-images.githubusercontent.com/121817358/210411453-c6f37fad-b27a-444c-a8a8-32a076a44d4a.png)

  Логика метода addTable, переменная createTableSqlQuery - хранит sql запрос, метод stmt.execute выполняет операцию
  ![image](https://user-images.githubusercontent.com/121817358/210411729-c15b3830-a116-4a29-b396-96f2f210c3b6.png)
  Логика метода add, переменная addSqlQuery - хранит sql запрос, с помощью conn.prepareStatement заполняем запрос данными и выполняем его
  ![image](https://user-images.githubusercontent.com/121817358/210411825-6dab94a6-95fa-4563-be57-aa63ce89f736.png)
  Логика метода executeQuery - в stmt.executeQuery передаем аргумент
  ![image](https://user-images.githubusercontent.com/121817358/210412177-bb0065ef-1ce1-48c2-a45b-1650b3451c27.png)
  
  В классе Main в методе main(главном выполняемом) запустим парсинг csv, создание БД, заполнения БД
![image](https://user-images.githubusercontent.com/121817358/210416622-a797634d-82e9-4610-978e-f8eabb8d9b51.png)

  Выводим данные из БД
  ![image](https://user-images.githubusercontent.com/121817358/210483397-0ac3f51f-3e93-4fc8-ae4c-dced9e47d2bc.png)

  
  <br /><br />
  **Задание 1 - Постройте график по среднему количеству землетрясений для каждого года**
  P.S так как весь метод не 
  Создаём класс ChartCreate который будет иметь статический метод для создания и сохранения графика
  ![image](https://user-images.githubusercontent.com/121817358/210414928-f60de9c9-966a-4ea6-9aeb-8df5f7575c82.png)
  Создадим метод Task1 который будет решать первое задание
![image](https://user-images.githubusercontent.com/121817358/210415576-99d0a7fa-588a-4f42-89c9-d761c2cde28c.png)
![image](https://user-images.githubusercontent.com/121817358/210415601-f75017d5-419c-450c-a89a-a9782e529067.png)

<br /><br />
  **Задание 2 - Выведите в консоль среднюю магнитуду для штата "West Virginia"**
  Используем ранее заполенный лист с магнитудами из штата West Virginia, и с помощью Stream API находим среднее.
  ![image](https://user-images.githubusercontent.com/121817358/210413913-0560d86d-af93-45fa-98a4-7699589a8d27.png)

<br /><br />  
  **Задание 3 - Выведите в консоль название штата, в котором произошло самое глубокое землетрясение в 2013 году.**
  Для решения данной задачи будем использовать sql запрос (выберим строки только с registration_date = 2013) сделаем сортировку depth от большего к меньшему с помощью order by depth DESC  который передадим в executeQuery.
![image](https://user-images.githubusercontent.com/121817358/210413836-311617c4-2a4f-4122-93fb-5360339103aa.png)

<br /><br />
В методе main класса Main вызываем решение всех данных нам задач
![image](https://user-images.githubusercontent.com/121817358/210416804-a1644c47-3e42-4b11-a6b3-78e01f76e0b1.png)

  

