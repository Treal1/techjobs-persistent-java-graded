package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import java.util.ArrayList;
import java.util.List;

// 4- added @Entity and a no-arg constructor required for Hibernate to create an object.
@Entity
public class Employer extends AbstractEntity {
    //5- Add the field for location with validation that ensures it is not empty and has a reasonable length. In addition, add public accessor methods to Employer.
    @NotBlank(message = "Location is required!")
    @Size(min=3, max=100)
    private String location;

    //Add a jobs Field to Employer
    @OneToMany
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();

    public Employer(){}
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
