package com.company.ellRes.domian;


import javax.persistence.*;

@Entity
@Table(
        name = "checkAct"
)
public class CheckAct {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    private Integer count;
    @Column(columnDefinition = "TINYINT")
    private Boolean telegram;


    public CheckAct(){}
    public CheckAct(Long id, Integer count, Boolean telegram){
        this.id = id;
        this.count = count;
        this.telegram = telegram;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getTelegram() {
        return telegram;
    }

    public void setTelegram(Boolean telegram) {
        this.telegram = telegram;
    }
}
