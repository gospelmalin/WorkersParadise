package com.yhsipi.workersparadise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class UploadController {

    // Path for imageuploading

    @GetMapping("/profilepicture")
    public ModelAndView viewUploadImage() {
        ModelAndView model = new ModelAndView();

        model.setViewName("upload/profileimageupload");
        return model;
    }


    @PostMapping("/upload/imageupload/new")
    public String uploadFile( MultipartFile file) throws IOException {

        FileInputStream inputStream = (FileInputStream) file.getInputStream();

        //you can use inputStream object which currently has your "file" data
        System.out.println("Image data " + inputStream.read());
        // you can process this to fetch your data.
        return  "file uploaded successfully ";
    }
    @PostMapping("/upload/imageupload/delete")
    public String deleteFile() {
        return "";
    }


}
