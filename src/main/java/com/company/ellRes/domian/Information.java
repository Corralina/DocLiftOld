package com.company.ellRes.domian;


import javax.persistence.*;

@Entity
@Table(
        name = "info"
)
public class Information {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "person"
    )
    private Individual individual;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "contact"
    )
    private Contact contact;


    public Information(){}

    public Information(Long id, Individual individual, Contact contact){
        this.id = id;
        this.individual = individual;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
