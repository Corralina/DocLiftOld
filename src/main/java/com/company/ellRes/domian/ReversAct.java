package com.company.ellRes.domian;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "comeAct"
)
public class ReversAct {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "act"
    )
    private Act act;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user"
    )
    private User user;
    private String coment;
    private LocalDate date;
    private LocalDate finishDate;
    @Column(columnDefinition = "TINYINT")
    private Boolean finish;


    public ReversAct(){}
    public ReversAct(Long id, Act act, User user, String coment, LocalDate date, LocalDate finishDate, Boolean finish){
        this.id = id;
        this.act = act;
        this.user = user;
        this.coment = coment;
        this.date = date;
        this.finishDate = finishDate;
        this.finish = finish;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getRev() {
        return finish;
    }

    public void setRev(Boolean rev) {
        this.finish = rev;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
