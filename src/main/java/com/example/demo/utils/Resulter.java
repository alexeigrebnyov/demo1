package com.example.demo.utils;

import com.example.demo.model.HIV;
import com.example.demo.service.HIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Resulter {

    private static HIVService hivService;

    @Autowired
    public Resulter(HIVService hivService) {
        this.hivService = hivService;
    }

    private static HIV parseResult(String line, int i) {

		Scanner scanner = new Scanner(line);
		String name = scanner.next();
		double value = scanner.nextDouble();
		String lastName ;
		if (value >= 130) {
			lastName = "положительный";
		}
		else lastName = "отрицательный";


		return new HIV(name, String.valueOf(i), lastName );



	}
	public static void addHiv(String path) throws IOException {

        List<HIV> hivs = new ArrayList<>();

        Scanner scanner1 = new Scanner(Path.of(path));
        int i = 1;
        while (scanner1.hasNext()) {
            i++;
            String line = scanner1.nextLine();

            hivs.add(parseResult(line, i));
        }
        hivs.stream().forEach(System.out::println);
        System.out.println();
        hivs.stream().forEach(e -> hivService.add(e));
        hivService.allHivs().forEach(System.out::println);
    }
//		writeXML();
//		xml2XLSX();
}
