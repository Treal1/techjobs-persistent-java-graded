package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    //allows us to access data inside EmployerRepository
    @Autowired
    private EmployerRepository employerRepository;

    //allows us to access data inside JobRepository
    @Autowired
    private JobRepository jobRepository;

    //allows us to access data inside SkillRepository
    @Autowired
    private SkillRepository skillRepository;

    //displays list of jobs on homepage

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        //added
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    //add jobs form

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        //added code VVV
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("employers", employerRepository.findAll());

        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        return "add";
    }
//creates new job from the add-jobs form input
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        //added below code VVV
        Optional optEmployer = employerRepository.findById(employerId);
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        if (errors.hasErrors() || !optEmployer.isPresent()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        Employer employer = (Employer) optEmployer.get();
        newJob.setEmployer(employer);
        jobRepository.save(newJob);
        return"redirect:";


    }

    //the above code allows the job to show with id
    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        return "view";
    }


}
