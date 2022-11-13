package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//In models/data, create a new interface EmployerRepository.
////EmployerRepository should extend CrudRepository.
////EmployerRepository should be annotated with @Repository.
////Repeat the steps above for an interface, SkillRepository.

@Repository
public interface EmployerRepository extends CrudRepository <Employer, Integer> {
}
