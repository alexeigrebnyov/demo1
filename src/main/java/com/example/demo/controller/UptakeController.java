package com.example.demo.controller;

import com.example.demo.model.Contingent;
import com.example.demo.service.ContService;
import com.example.demo.service.HIVService;
import com.example.demo.utils.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/")

public class UptakeController {

    ContService contService;
    List<Contingent> uptake = new ArrayList<>();
    List<Contingent> uptakeByCode = new ArrayList<>();
    String code;

    @Autowired
    public UptakeController(ContService contService) {

        this.contService = contService;
//        uptake  = contService.getAll();
    }


    @GetMapping(value = {"/"})
    public String getAl(ModelMap modelMap){
           modelMap.addAttribute("contingents", contService.getAll());
            return  "contingent";
    }
    @PostMapping(value = "/scan")
    public String getScan() {
        return "redirect:/code";
    }

    @PostMapping(value = "/code/init")
    public String redirect(ModelMap modelMap) {

//        Test.data.forEach(e -> uptakeByCode.add(contService
//                .getByCode(Integer.parseInt(e.trim()))));
//        System.out.println(Test.data.size());
        return "redirect:/code";

    }
    @GetMapping(value = "/code")
    public String updateUser1(ModelMap model) {

    Test.getScan();
    List<Contingent> dist = uptakeByCode
                .stream()
                .distinct()
                .collect(Collectors.toList());
//        if (Test.data != null) {
//            Scanner scanner = new Scanner(Test.data);
//            while (scanner.hasNextInt()) {
//                uptakeByCode.add(contService.getByCode(scanner.nextInt()));
//            }
//            uptakeByCode.add(contService.getByCode(Integer.parseInt(Test.data.trim())));
//            model.addAttribute("code", Test.data);
//        }
        model.addAttribute("data1", code);
        model.addAttribute("selected", dist);
//        System.out.println(code);
//        dist.forEach(System.out::println);
        return "byCode";
    }
    @GetMapping(value = "/refresh")
    public String refresh(ModelMap model) {
    model.addAttribute("data1", code);
    return "divRefresh";

    }

    @PostMapping(value = "/code")
    public String updateUser(ModelMap model, @RequestParam(value = "codeInt") String codeInt) {
       try {

           uptakeByCode.add(contService.getByCode(Integer.parseInt(codeInt.trim())));
       }
       catch (Exception ignored) {

       }
        model.addAttribute("selected", uptakeByCode);
       code = null;
        return "redirect:/code";
    }

    public void setCode(String code) {
        this.code = code;
    }

    //    public  String getByCode();
//
}
