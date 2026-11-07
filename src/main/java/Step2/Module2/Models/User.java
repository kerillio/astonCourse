package Step2.Module2.Models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    private String name;

    private String email;

    private int age;

    private String created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now().toString();
    }


    public User() {}

    public User(String name, String email, int age, String created_at) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "\n{ User:" +
                "\n\tid: " + this.id +
                "\n\tname: " + this.name +
                "\n\temail: " + this.email +
                "\n\tage: " + this.age +
                "\n\tcreated_at: " + this.created_at + "\n}\n";
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
