package Step1.module2;

import java.util.Arrays;

public class Book {

    private int id;
    private String author;
    private String name;
    private int pageCount;
    private int year;

    public Book (String line) {

        Arrays.stream(line.split(",")).forEach(s -> {
//            System.out.println(s);
            String[] data = s.trim().split("=");
            switch (data[0]) {
                case "name": {
                    this.name = data[1];
                }
                break;
                case "author" : {
                    this.author = data[1];
                }
                break;
                case "pageCount" : {
                    this.pageCount = Integer.parseInt(data[1]);
                }
                break;
                case "year" :  {
                    this.year = Integer.parseInt(data[1]);
                }
                break;
            }
        });

    }

    public Book (String name, String author, int pageCount, int year) {
        this.name = name;
        this.author = author;
        this.pageCount = pageCount;
        this.year = year;
    }

    public Book (int id, String name, String author, int pageCount, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pageCount = pageCount;
        this.year = year;
    }

    @Override
    public String toString() {
        return "name: " + this.name + ". author: " + this.author + ". count of pages: " + pageCount + ". made in: " + this.year + " y.";
    }

    public String getName() {
        return this.name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public boolean equals(Book anotherBook) {
       if (anotherBook.name != null && anotherBook.author != null)
           return this.name.equals(anotherBook.name) && this.author.equals(anotherBook.author);
        else
            return false;
    }
}
