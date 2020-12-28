package com.company.ellRes.domian;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "act"
)
public class Act{

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String coment;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "author"
    )
    private User author;
    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "status"
    )
    private StatusAct status;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "resolution"
    )
    private Resolution resolution;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "editer"
    )
    private User editer;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "file"
    )
    private DocumentAct documentAct;
    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "agrees"
    )
    private AgreesAct agrees;




    public Act(){}
    public Act(Long id, LocalDate date, LocalTime time, String coment, User author, StatusAct status, Resolution resolution, User editer, DocumentAct documentAct, AgreesAct agrees){
        this.id = id;
        this.date = date;
        this.time = time;
        this.coment = coment;
        this.author = author;
        this.status = status;
        this.resolution = resolution;
        this.editer = editer;
        this.documentAct = documentAct;
        this.agrees = agrees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public StatusAct getStatus() {
        return status;
    }

    public void setStatus(StatusAct status) {
        this.status = status;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public User getEditer() {
        return editer;
    }

    public void setEditer(User editer) {
        this.editer = editer;
    }

    public DocumentAct getDocumentAct() {
        return documentAct;
    }

    public void setDocumentAct(DocumentAct documentAct) {
        this.documentAct = documentAct;
    }

    public AgreesAct getAgrees() {
        return agrees;
    }

    public void setAgrees(AgreesAct agrees) {
        this.agrees = agrees;
    }

}
