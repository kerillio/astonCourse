package Step1.module2;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static void main (String[] args) throws Exception {

        //Лист "библиотека", содержащая все книги из инпута (для удобства заполнения)
        List<Book> library = new ArrayList<>();

        //Лист "студенты", содержащий всех студентов из инпута
        List<Student> students = new ArrayList<>();

        //чтение файла с данными о студентах и книгах
        String filePath = "src\\Step1\\module2\\data.txt";
        try {
            Files.lines(Path.of(filePath)).filter(s -> s.length() > 1).forEach(line -> {
                switch (line.split(" : ")[0]) {
                    case "student" : students.add(new Student(line.split(" : ")[1]));
                        break;
                    case "book" : library.add(new Book(line.split(" : ")[1]));
                        break;
                }
            });
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


        //заполнение списка книг рандомными из библиотеки, исключая дублирование книги у каждого
        students.stream().forEach(s -> {
            while (s.getBookList().size() < 5) {
                Book b = library.get((int) Math.floor(Math.random() * 100)%library.size());
                    if (s.getBookList().contains(b)); else s.addBook(b);
            }
        });


        //Пункт 4. все подпункты одним стримом
        students.stream()
                //вывод каждого студента, пока без списка книг
                .peek(s -> System.out.println(s.toString(false)))
                //вывод отдельно списка книг каждого студента
                .peek(s -> {
                    System.out.println("Список для: " + s.getName());
                    System.out.println(s.bookString() + "\n");
                })
                //запись всех книг, взятых студентами, в один список
                .flatMap(s -> s.getBookList().stream())

                //сортировка списка по количеству страниц
                .sorted(Comparator.comparingInt(b -> b.getPageCount()))

                //исклюение дублей
                .distinct()

                //фильтр по категории "выпущена после 2000 года"
                .filter(b -> b.getYear() > 2000)

                //ограничение потока тремя элементами
                .limit(3)

                //вывод года
                .peek(b -> System.out.println(b.getYear()))

                //возвращаем коротким замыканием optional
                .findAny()
                
                //если значение не null, возвращаем его
                .ifPresentOrElse(
                    b -> System.out.println("Книга найдена, год выпуска: " + b.getYear()),
                    () -> System.out.println("Книга не найдена :(")
                );

    }

}
