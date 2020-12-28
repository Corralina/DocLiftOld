package com.company.ellRes.domian;

import javax.persistence.*;

@Entity
@Table(
        name = "charterAct"
)
public class StatusAct {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Column(columnDefinition = "TINYINT")
    private Boolean finish;
    @Column(columnDefinition = "TINYINT")
    private Boolean revers;
    @Column(columnDefinition = "TINYINT")
    private Boolean process;
    @Column(columnDefinition = "TINYINT")
    private Boolean draft;

    public StatusAct(){}
    public StatusAct(Long id, Boolean finish, Boolean revers, Boolean process, Boolean draft){
        this.id = id;
        this.finish = finish;
        this.revers = revers;
        this.process = process;
        this.draft = draft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Boolean getRevers() {
        return revers;
    }

    public void setRevers(Boolean revers) {
        this.revers = revers;
    }

    public Boolean getProcess() {
        return process;
    }

     public void setProcess(Boolean process) {
        this.process = process;
    }

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }
}
