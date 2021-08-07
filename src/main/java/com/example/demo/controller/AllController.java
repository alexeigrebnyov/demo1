package com.example.demo.controller;

import com.example.demo.model.HIV;
import com.example.demo.service.HIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@RequestMapping("/")

public class AllController {

    HIVService hivService;

    @Autowired
    public AllController(HIVService hivService) {
        this.hivService = hivService;
    }



    @GetMapping(value = {"/", "/hello"})
    public String getAl(ModelMap modelMap){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.addAttribute("user", userDetails);
            return  "hello";
    }



}
