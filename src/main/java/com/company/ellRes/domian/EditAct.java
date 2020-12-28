package com.company.ellRes.domian;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "editAct"
)
public class EditAct {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private LocalDate date;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user"
    )
    private User autor;

    public EditAct(){}
    public EditAct(Long id, LocalDate date, User autor){
        this.id = id;
        this.date = date;
        this.autor = autor;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }
}
