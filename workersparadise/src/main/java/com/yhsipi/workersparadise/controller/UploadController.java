package com.yhsipi.workersparadise.controller;

import com.yhsipi.workersparadise.entities.Person;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.repository.PersonRepository;
import com.yhsipi.workersparadise.service.PersonService;
import com.yhsipi.workersparadise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@Controller
public class UploadController {

    // Path for imageuploading
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;

    @GetMapping("/profilepicture")
    public ModelAndView viewUploadImage() {
        // Get logged in user
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findByUsername(authentication.getName());
        Person getPerson = personService.findOne(user.person.getIdPerson()).get();

        System.out.println(getPerson.getImage().toString());

        //byte[] encoded = Base64.getEncoder().encode(getPerson.getImage().getBytes());

        model.addObject("person", personService.findOne(user.person.getIdPerson()).get());
        model.setViewName("upload/profileimageupload");
        return model;
    }


    @PostMapping("/upload/imageupload/new")
    public String uploadFile(@Valid Person p, MultipartFile file) throws IOException {
        ModelAndView model = new ModelAndView();
        Person ps = new Person();
        //FileInputStream inputStream = (FileInputStream) file.getInputStream();

       // System.out.println("Image data " + inputStream.read());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findByUsername(authentication.getName());
        //Person pID = personService.findOne(user.person.getIdPerson()).get();
       // System.out.println("Personid: " + pID);

        p.setIdPerson(user.person.getIdPerson());
        p.setImage(file.getBytes());

        personService.savePerson(p);

        return "redirect:/profilepicture/";
    }
    @PostMapping("/upload/imageupload/delete")
    public String deleteFile() {
        return "";
    }


}
