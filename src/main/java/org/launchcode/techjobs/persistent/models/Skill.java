package org.launchcode.techjobs.persistent.models;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

// 4- added @Entity and a no-arg constructor required for Hibernate to create an object.
@Entity
public class Skill extends AbstractEntity {
    //5- Add the field for location with validation that ensures it is not empty and has a reasonable length. In addition, add public accessor methods to Employer.
    @NotBlank
    @Size(min = 3, max = 500)
    private String description;

    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();

    public Skill() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}