package com.company.ellRes.domian;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "fileact"
)
public class DocumentAct {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "author"
    )
    private User author;
    private String description;
    private boolean act;


    public DocumentAct(){}
    public DocumentAct(Long id, String name, LocalDate date, LocalTime time, User author, String description, boolean act){
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.author = author;
        this.description = description;
        this.act = act;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAct() {
        return act;
    }

    public void setAct(boolean act) {
        this.act = act;
    }
}
