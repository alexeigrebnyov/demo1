package com.example.demo.utils;

import com.example.demo.model.Contingent;
import com.example.demo.model.HIV;
import com.example.demo.service.ContService;
import com.example.demo.service.HIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInit {
    HIVService hivService;
    ContService contService;

    @Autowired
    public DataInit(HIVService hivService, ContService contService) {
        this.hivService = hivService;
        this.contService = contService;
    }

    public void hivInit() {
        List<HIV> hivs = new ArrayList<>();
        for (int i = 200300; i < 200330; i++) {
            String reult = "положительный" ;
            if ((i%2) !=0) reult = "отрицательный";
            hivService.add(new HIV(String.valueOf(i), String.valueOf(i-200250), reult));
        }
    }

    public void contInit() {
        for (int i = 200300; i<200330; i++) {
            String sex = "женский";
            if ((i%2) != 0) sex = "мужской";
            contService.add(new Contingent((long)(i-200299),i, (i - 200250), "126.1б", sex));
        }
        contService.add(new Contingent(501L, 200330, 3533, "126.1б", "женский" ));
        contService.add(new Contingent(502L, 200331, 3540, "126.1б", "женский" ));
        contService.add(new Contingent(503L, 149377, 100337, "126.1б", "женский" ));
        contService.add(new Contingent(504L, 115514, 4270, "126.1б", "женский" ));
        contService.add(new Contingent(505L, 128823, 100339, "126.1б", "женский" ));
        contService.add(new Contingent(506L, 422925, 4272, "126.1б", "женский" ));
        contService.add(new Contingent(507L, 434968, 4273, "126.1б", "женский" ));
        contService.add(new Contingent(508L, 449844, 4275, "126.1б", "женский" ));
    }
}
