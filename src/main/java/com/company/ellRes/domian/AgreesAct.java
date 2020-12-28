package com.company.ellRes.domian;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "agreesAct"
)
public class AgreesAct {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user"
    )
    private User user;

    private String post;
    private LocalDate date;
    private LocalTime time;
    private boolean sing;

    public AgreesAct(){}
    public AgreesAct(Long id, User user, String post, LocalDate date, LocalTime time, boolean sing){
        this.user = user;
        this.post = post;
        this.date = date;
        this.time = time;
        this.sing = sing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    public boolean isSing() {
        return sing;
    }

    public void setSing(boolean sing) {
        this.sing = sing;
    }


}
