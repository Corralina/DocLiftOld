package com.company.ellRes.domian;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table
public class Contact {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String telegramId;
    private String phone;
    private String mail;
    @Column(columnDefinition = "TINYINT")
    private Boolean authenticate;

    public Contact(){}
    public Contact(Long id, String telegramId, String phone, String mail, Boolean authenticate){
        this.id = id;
        this.telegramId = telegramId;
        this.phone = phone;
        this.mail = mail;
        this.authenticate = authenticate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(Boolean authenticate) {
        this.authenticate = authenticate;
    }


}
