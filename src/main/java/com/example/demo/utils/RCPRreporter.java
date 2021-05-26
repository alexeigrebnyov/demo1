package com.example.demo.utils;

import com.example.demo.model.Contingent;
import com.example.demo.model.UptakeReport;
import com.example.demo.service.ContService;
import com.example.demo.service.UptakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class RCPRreporter {

    private static ContService contService;
    private static UptakeService uptakeService;

    @Autowired
    public RCPRreporter(ContService contService, UptakeService uptakeService) {
        this.contService = contService;
        this.uptakeService = uptakeService;

    }


    private static Contingent parseResult(String line, Long id) throws ParseException {

//	String[] strings = new String[2];
        Scanner scanner = new Scanner(line);
//		DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        int emc = 0;
        int code = 0;
        int refer = 0;
//		double cont = 0.0;
        String sex = null;
        emc = scanner.nextInt();
//		Date date = formater.parse(scanner.next());
        code = scanner.nextInt();

        if (scanner.hasNextInt()) {
            refer = scanner.nextInt();}
//		if (scanner.hasNextDouble()) {
//			cont = scanner.nextDouble();}
        String contingent = scanner.next();
        if (contingent.equals("мужской") || contingent.equals("женский")) {
            sex = contingent;
            contingent = "126.1б";
        }
        if (scanner.hasNext()) {
            String app = scanner.next();
            if (app.equals("а")|| app.equals("б")) {
                contingent += app;
            }
            if ((scanner.hasNext())) {
                sex = scanner.next();
            } else {
                sex = app;
            }
        }


        Contingent contingent1 =  new Contingent(id, emc, code, contingent, sex);
        contingent1.setRefer(refer);
//		return new UptakeReport(date, refer, contService.getByEMC(emc, code));
        return  contingent1;



    }
    private static UptakeReport parseWrResult(String line) throws ParseException {

        Scanner scanner = new Scanner(line);
        DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        Integer emc = scanner.nextInt();
        Contingent contingent = null;
        try {contingent = contService.getByEMC(emc);
        }
        catch (Exception ignored) {
        }

        Date date = formater.parse(scanner.next());

        return new UptakeReport(emc, date, contingent);




    }

    public static void addContingent(String path) throws IOException, ParseException {



        List<Contingent> conts = new ArrayList<>();


        Scanner scanner = new Scanner(Path.of(path));
        int i = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Contingent contingent = parseResult(line, (long) i);
            if (contingent.getRefer() == 1) {

                conts.add(contingent);
                i++;
            }


        }
        conts.forEach(e -> contService.add(e));
    }

    public static void addUptakeReport(String path) throws IOException, ParseException {

        List<UptakeReport> reps = new ArrayList<>();
        List<UptakeReport> freps = new ArrayList<>();

        Scanner scanner1 = new Scanner(Path.of(path));
        while (scanner1.hasNextLine()) {
            String line = scanner1.nextLine();

            UptakeReport uptakeReport = parseWrResult(line);

            reps.add(uptakeReport);
            freps = reps
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());

        }

//		reps.forEach(System.out::println);
        freps.forEach(e -> uptakeService.add(e));

    }
    //		reps.forEach(System.out::println);
    public static void generate() throws ParseException {

        List<UptakeReport> filterreps = new ArrayList<>();
        List<UptakeReport> contsoff = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        long sum = 0L;
        for (int amount = 0; amount < 30; amount++) {
            Date date = format.parse("26.04.2021");
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(date);
            cal.add(GregorianCalendar.DATE, amount);
            Date newDate = cal.getTime();
            filterreps = uptakeService.getByDate(newDate).stream().filter(e -> e.getContingent() != null)
                    .collect(Collectors.toList());

            long cont116M = 0L;
            long cont116F = 0L;
            long cont109a = 0L;
            long cont109b = 0L;
            long cont1262a = 0L;
            long cont108b = 0L;

            String infoM = null;
            String infoF = null;
            String info109A = null;
            String info109B = null;
            String info1262A = null;
            String info108B = null;
            try {
                cont116M = filterreps.stream().filter(e -> e.getContingent().getName().equals("126.1.б"))
                        .filter(e -> e.getContingent().getSex().equals("мужской"))
                        .count();
                infoM = "M - " + cont116M;

            } catch (Exception ex) {
                infoM = "M - " + cont116M;


            }
            try {
                cont116F = filterreps.stream().filter(e -> e.getContingent().getName().equals("126.1.б"))
                        .filter(e -> e.getContingent().getSex().equals("женский"))
                        .count();
                infoF = "F - " + cont116F;

            } catch (Exception ex) {
                infoF = "F - " + cont116F;


            }
            try {
                cont109a = filterreps.stream().filter(e -> e.getContingent().getName().equals("109.а"))
                        .count();
                info109A = "109.а - " + cont109a;

            } catch (Exception ex) {
                info109A = "109.а - " + cont109a;


            }
            try {
                cont109b = filterreps.stream().filter(e -> e.getContingent().getName().equals("109.б"))
                        .count();
                info109B = "109.б - " + cont109b;

            } catch (Exception ex) {
                info109B = "109.б - " + cont109b;


            }
            try {
                cont1262a = filterreps.stream().filter(e -> e.getContingent().getName().equals("126.2.а"))
                        .count();
                info1262A = "110 - " + cont1262a;

            } catch (Exception ex) {
                info1262A = "110 - " + cont1262a;


            }
            try {
                cont108b = filterreps.stream().filter(e -> e.getContingent().getName().equals("108.б"))
                        .count();
                info108B = "108.б - " + cont108b;

            } catch (Exception ex) {
                info108B = "108.б - " + cont108b;


            }
            long total = cont116M + cont116F + cont109a + cont109b + cont108b;
            System.out.println(newDate);
            System.out.println(total);
            System.out.println(infoM);
            System.out.println(infoF);
            System.out.println(info109A);
            System.out.println(info109B);
            System.out.println(info1262A);
            System.out.println(info108B);
            sum += total;

        }
    }

}
