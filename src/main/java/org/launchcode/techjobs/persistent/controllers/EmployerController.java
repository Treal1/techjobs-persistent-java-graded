package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

// 1- Add a private field of EmployerRepository type called employerRepository toEmployerController. Give this field an @Autowired annotation
    // This injects EmployerRepository and gives access to the data inside of the table
    @Autowired
    private EmployerRepository employerRepository;

    /// The following code displays our add employer form
    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    // The following code processes our add employer form and creates and saves a new employer
    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }

        return "redirect:";
    }

    // The following code processes our add employer form and creates and saves a new employer
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
//3- Replace this using the .findById() method with the right argument to look for the given employer object from the data layer.
        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }

    //2- Add an index method that responds at /employers with a list of all employers in the database. This method should use the template employers/index. To figure out the name of the model attribute you should use to pass employers into the view, review this template.
    // This displays all employers
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }
}
