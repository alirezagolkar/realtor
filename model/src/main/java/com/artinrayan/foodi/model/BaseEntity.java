package com.artinrayan.foodi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * The ase entity
 */
@Entity
@Table(name = "Base")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreationDate")
    private Date creationDate;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
