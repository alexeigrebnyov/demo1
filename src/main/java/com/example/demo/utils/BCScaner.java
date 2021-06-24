package com.example.demo.utils;

import com.example.demo.controller.UptakeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BCScaner {

    private  static UptakeController uptakeController;
    @Autowired
    public BCScaner(UptakeController uptakeController) {
        this.uptakeController = uptakeController;
    }
    public static void setCode() {
        Scanner scanner = new Scanner(System.in);
        uptakeController.setCode(scanner.next());
    }
}
