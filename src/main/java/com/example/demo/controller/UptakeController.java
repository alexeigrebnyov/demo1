package com.example.demo.controller;

import com.example.demo.model.Analysis;
import com.example.demo.model.Contingent;
import com.example.demo.service.ContService;
import com.example.demo.service.HIVService;
import com.example.demo.service.UptakeService;
import com.example.demo.utils.BCScaner;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/")

public class UptakeController {

    ContService contService;
    UptakeService uptakeService;
//    BCScaner bcScaner;
    List<Contingent> uptake = new ArrayList<>();
    List<Analysis> uptakeByCode = new ArrayList<>();
    List<Analysis> chekByCode = new ArrayList<>();
    List<String> chek = new ArrayList<>();

    String code;

    @Autowired
    public UptakeController(ContService contService,
                            UptakeService uptakeService
//                            BCScaner bcScaner
    ) {

        this.contService = contService;
        this.uptakeService = uptakeService;
//        this.bcScaner = bcScaner;
//        uptake  = contService.getAll();
    }


//    @GetMapping(value = {"/"})
//    public String getAl(ModelMap modelMap){
//           modelMap.addAttribute("contingents", contService.getAll());
//            return  "contingent";
//    }
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

//    Test.getScan();
        List<Analysis> dist = uptakeByCode
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
    @GetMapping(value = "/chek")
    public String getCheked (ModelMap model) throws SQLException {
        chekAnalysis();
        List<Analysis> dist = chekByCode
                .stream()
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("chekedAnalysis", dist);
        return "chek";

    }
    @GetMapping(value = "/refresh")
    public String refresh(ModelMap model) {
    BCScaner.setCode();
    model.addAttribute("data1", code);
    return "divRefresh";

    }
    @PostMapping(value = "/write")
    public String write() throws IOException {
        List<Analysis> dist = uptakeByCode
                .stream()
                .distinct()
                .collect(Collectors.toList());
        try(FileOutputStream fos=new FileOutputStream("C:/Repo/HIVList.txt", true);) {
            for (Analysis data:
                    dist) {
                if (data.getHiv().equals("1")) {
                    String item = data.getEmc()+ System.lineSeparator();
                    fos.write(item.getBytes());
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        uptakeByCode.clear();
        return "redirect:/code";


    }

    @PostMapping(value = "/code")
    public String updateUser(ModelMap model, @RequestParam(value = "codeInt") String codeInt) throws SQLException {
        Analysis analysis = new Analysis();
        List<Object[]> data = new ArrayList<>();
        data = uptakeService.getData(" ",codeInt);
//        try {
//            Object[] data1 = data
//                    .stream()
//                    .findAny().get();
//
//
//        } catch (Exception ignored) {}

        if (data.size() != 0) {
            for (Object[] data1 : data) {
//            System.out.println(data1[0] + " " + data1[1] + " " + data1[2] + " " + data1[3] + " " + data1[4] + " "
//                    + data1[5] + data1[6] + " " + data1[7] + " " + data1[8] + " " + data1[9]
//                    + "" + data1[10]
//            );
                try {
                    analysis.setEmc(data1[0].toString());
                    analysis.setFio(data1[1].toString());


                    analysis.setMain_org_id(data1[5].toString());
                    analysis.setLabel(data1[6].toString());
                    analysis.setPatdirect_id(data1[7].toString());
                    analysis.setDate_bio(data1[8].toString());
                    analysis.setCode(data1[10].toString());


                    if (data1[9].toString().equals("А/т к ВИЧ 1,2 +А/г")) {
                        analysis.setHiv("1");
                        if (data1[4] != null) {
                            analysis.setResultHiv(data1[4].toString());
                        }
                    } else { if (analysis.getHiv()==null)
                        analysis.setHiv("");
                    }
                    if (data1[9].toString().equals("HBsAg")) {
                        analysis.setHbsAg("1");
                        if (data1[4] != null) {
                            analysis.setResultHbsAg(data1[4].toString());
                        }
                    } else { if (analysis.getHbsAg() == null)
                        analysis.setHbsAg("");
                    }
                    if (data1[9].toString().equals("Ат .к. HCV")) {
                        analysis.setAtHCV("1");
                        if (data1[4] != null) {
                            analysis.setResultatHCV(data1[4].toString());
                        }
                    } else { if (analysis.getAtHCV() == null)
                        analysis.setAtHCV("");
                    }
                    if (data1[9].toString().equals("Сифилис МРП")) {
                        analysis.setSyphMRP("1");
                        if (data1[4] != null) {
                            analysis.setResultMRP(data1[4].toString());
                        }
                    } else { if (analysis.getSyphMRP() == null)
                        analysis.setSyphMRP("");
                    }
                    if (data1[9].toString().equals("Syphilis ИФА")) {
                        analysis.setSyphIFA("1");
                        if (data1[4] != null) {
                            analysis.setResultSyphIfa(data1[4].toString());
                        }
                    } else { if (analysis.getSyphIFA() == null)
                        analysis.setSyphIFA("");
                    }


                } catch (Exception ignored) {
                }

                try {
                    analysis.setKontengent(data1[2].toString());
                } catch (Exception ex) {
                }

                try {
                    analysis.setMotconsu_resp_id(data1[3].toString());

                } catch (Exception ex) {
                }

//            try {
//                if (data1[9].toString().equals("А/т к ВИЧ 1,2 +А/г"))
//                analysis.setResultHiv(data1[4].toString());
//
//            } catch (Exception ex) {}
//
            }
        uptakeByCode.add(analysis);
        chek.add(analysis.getCode());
        }


//           uptakeByCode.add(contService.getByCode(Integer.parseInt(codeInt.trim())));
//        model.addAttribute("selected", uptakeByCode);
       code = null;
        return "redirect:/code";
    }
//    @PostMapping(value = "/chek")
    public String chekAnalysis () throws SQLException {

        chek.add("3856");
        List<Object[]> data = new ArrayList<>();
        for (String upt: chek) {

            Analysis analysis = new Analysis();
        data = uptakeService.getData(" ",upt);
//		System.out.println(data.size());
//        try {
//            Object[] data1 = data
//                    .stream()
//                    .findAny().get();
//
//
//        } catch (Exception ignored) {}


        for (Object[] data1 : data) {

//            System.out.println(data1[0] + " " + data1[1] + " " + data1[2] + " " + data1[3] + " " + data1[4] + " "
//                    + data1[5] + data1[6] + " " + data1[7] + " " + data1[8] + " " + data1[9]
//                    + "" + data1[10]
//            );
            try {
                analysis.setEmc(data1[0].toString());
                analysis.setFio(data1[1].toString());


                analysis.setMain_org_id(data1[5].toString());
                analysis.setLabel(data1[6].toString());
                analysis.setPatdirect_id(data1[7].toString());
                analysis.setDate_bio(data1[8].toString());
                analysis.setCode(data1[10].toString());




                if (data1[9].toString().equals("А/т к ВИЧ 1,2 +А/г")) {
                    analysis.setHiv("1");
                    if (data1[4] != null) {analysis.setResultHiv(data1[4].toString());}
                }  else { if (analysis.getHiv() == null)
                    analysis.setHiv("");}

                if (data1[9].toString().equals("HBsAg")){
                    analysis.setHbsAg("1");
                    if (data1[4] != null) {analysis.setResultHbsAg(data1[4].toString());}
                } else { if (analysis.getHbsAg() == null)
                    analysis.setHbsAg("");}

                if (data1[9].toString().equals("Ат .к. HCV")){
                    analysis.setAtHCV("1");
                    if (data1[4] != null) {analysis.setResultatHCV(data1[4].toString());}

                } else { if (analysis.getAtHCV() == null)
                    analysis.setAtHCV("");}

                if (data1[9].toString().equals("Сифилис МРП")) {
                    analysis.setSyphMRP("1");
                    if (data1[4] != null) {analysis.setResultMRP(data1[4].toString());}
                } else { if (analysis.getSyphMRP() == null)
                    analysis.setSyphMRP("");}
                if (data1[9].toString().equals("Syphilis ИФА")) {
                    analysis.setSyphIFA("1");
                    if (data1[4] != null) {analysis.setResultSyphIfa(data1[4].toString());}
                } else { if (analysis.getSyphIFA() == null)
                    analysis.setSyphIFA("");}


            } catch (Exception ignored) {}

            try {
                analysis.setKontengent(data1[2].toString());
            } catch (Exception ex) {}

            try {
                analysis.setMotconsu_resp_id(data1[3].toString());

            } catch (Exception ex) {}

//            try {
//                if (data1[9].toString().equals("А/т к ВИЧ 1,2 +А/г"))
//                analysis.setResultHiv(data1[4].toString());
//
//            } catch (Exception ex) {}

//
        }
//        System.out.println(analysis);
//        System.out.println(data.size());

            chekByCode.add(analysis);

        }

//           uptakeByCode.add(contService.getByCode(Integer.parseInt(codeInt.trim())));
//        model.addAttribute("selected", uptakeByCode);
        return "redirect:/chek";
    }


    public void setCode(String code) {
        this.code = code;
    }

    //    public  String getByCode();
//
}
