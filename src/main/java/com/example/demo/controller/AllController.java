package com.example.demo.controller;

import com.example.demo.model.HIV;
import com.example.demo.service.HIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

    @GetMapping(value = {"/result"})
    public String getAl(ModelMap modelMap){
           modelMap.addAttribute("hivs", hivService.allHivs());
            return  "hello";
    }



}
