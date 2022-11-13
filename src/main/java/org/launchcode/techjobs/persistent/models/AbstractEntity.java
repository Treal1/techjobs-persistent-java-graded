package org.launchcode.techjobs.persistent.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;


//1- give AbstractEntity the @MappedSuperclassannotation.
@MappedSuperclass
public abstract class AbstractEntity {

    //2- Since all of the subclasses of AbstractEntity will be entities themselves, add the @Idand @GeneratedValue annotations to the field id.
    @Id
    @GeneratedValue
    private int id;

    //3- a user cannot leave this field blank when creating an object and that the name length is a decent length
    @NotBlank(message = "Must enter name")
    @Size(min=3, max=100)
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}