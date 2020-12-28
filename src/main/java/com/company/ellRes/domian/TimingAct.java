package com.company.ellRes.domian;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "timingAct"
)
public class TimingAct {

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

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "act"
    )
    private Act act;
    private String post;
    private Integer status;
    private LocalDate date;
    private boolean sing;



    public TimingAct(){}
    public TimingAct(Long id, User user, Act act, String post, Integer status, LocalDate date, boolean sing){
        this.id = id;
        this.user = user;
        this.act = act;
        this.post = post;
        this.status = status;
        this.date = date;
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

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isSing() {
        return sing;
    }

    public void setSing(boolean sing) {
        this.sing = sing;
    }
}
