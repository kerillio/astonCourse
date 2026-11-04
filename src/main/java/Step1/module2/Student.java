package Step1.module2;

import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Student {

    private String name;
    private int age;
    private int course;
    private ArrayList<Book> bookList = new ArrayList<>();

    public Student (String line) {
        List<String> lines = Arrays.asList(line.split(","));
        lines.forEach(l -> {
            String[] x = l.trim().split("=");
            switch (x[0]) {
                case "name" : {
                    this.name = x[1];
                }
                    break;
                case "age" : {
                    this.age = Integer.parseInt(x[1]);
                }
                    break;
                case "course" : {
                    this.course = Integer.parseInt(x[1]);
                }
                    break;
            }
        });
    }

    public Student (String name, int age, int course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public Student (String name, int age, int course, List<Book> bookList) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.bookList.addAll(0, bookList);
    }


    @Override
    public String toString() {

        String studentInfo = "Имя: " + this.name + ", возраст: " + this.age + ". Является студентом " + this.course + " курса.";
        if (bookString() != null) return studentInfo + bookString();
            else return studentInfo;
    }

    public String toString(boolean addBookList) {

        String studentInfo = "Имя: " + this.name + ", возраст: " + this.age + ". Является студентом " + this.course + ".";
        if (bookString() != null && addBookList) return studentInfo + "\n" + bookString();
        else return studentInfo;
    }

    public String bookString () {
        StringBuilder bookString = new StringBuilder("Список книг:\n");
        if (bookList.size() > 0) {
            for (int i = 0; i < bookList.size(); i++) {
                bookString.append((i + 1) + ". " + bookList.get(i) + "\n");
            }
        }
        return bookString.toString();

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getCourse() {
        return course;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(byte age) {
        this.age = age;
    }



    public void setBookList(List<Book> bookList) {
        this.bookList.containsAll(bookList);
    }

    public void setBookListStream(String line) {

    }

    public void setCourse(byte course) {
        this.course = course;
    }

    public void addBook(Book book) {
        try {
            if (this.bookList != null) this.bookList.add(book);
            else this.bookList.addFirst(book);
        } catch (NullPointerException e) {

        }
    }

    public void removeBook (Book book) {
        this.bookList.remove(book);
    }

}
