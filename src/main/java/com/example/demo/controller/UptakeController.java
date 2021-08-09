package com.example.demo.controller;

import com.example.demo.model.Analysis;
import com.example.demo.model.Contingent;
import com.example.demo.model.User;
import com.example.demo.service.ContService;
import com.example.demo.service.HIVService;
import com.example.demo.service.UptakeService;
import com.example.demo.utils.BCScaner;
import com.example.demo.utils.Test;
import com.example.demo.utils.XLConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.curs.xylophone.XML2SpreadSheetError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/")

public class UptakeController {

    ContService contService;
    UptakeService uptakeService;
//    XLConstructor xlConstructor;
//    BCScaner bcScaner;
    List<Contingent> uptake = new ArrayList<>();
    List<Analysis> uptakeByCode = new ArrayList<>();
//    List<Analysis> distinct = uptakeByCode
//            .stream().distinct().collect(Collectors.toList());
    List<Analysis> chekByCode = new ArrayList<>();
    List<String> chek = new ArrayList<>();

    String code;
    String redirect = "redirect:/code";
    String redirmanual = "redirect:/divrefresh";

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
    public String getScan(@RequestParam(value = "code") String code) {
        setCode(code);
        System.out.println(code);
        return "redirect:/divrefresh";
    }

    @GetMapping(value = "/divrefresh")
    public String redirect(ModelMap modelMap) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Analysis> dist = uptakeByCode
                .stream()
                .distinct()
                .collect(Collectors.toList());
        modelMap.addAttribute("data1", code);
        modelMap.addAttribute("redir1", redirmanual);
        modelMap.addAttribute("selected1", dist);
        modelMap.addAttribute("user", userDetails.getUsername());

//        Test.data.forEach(e -> uptakeByCode.add(contService
//                .getByCode(Integer.parseInt(e.trim()))));
//        System.out.println(Test.data.size());
        return "divRefresh";

    }
//    @GetMapping(value = "/code/codeInt")
//    public String updateInput(ModelMap modelMap) {
//        modelMap.addAttribute("data1", code);
//        return "byCode";
//
//    }
    @GetMapping(value = "/code")
    public String updateUser1(ModelMap model)  {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    Test.getScan();
        List<Analysis> dist = uptakeByCode
                .stream()
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("data1", code);
        model.addAttribute("selected", dist);
        model.addAttribute("redir", redirect);
        model.addAttribute("user", userDetails.getUsername());
        return "byCode";
    }
    @GetMapping(value = "/chek")
    public String getCheked (ModelMap model) throws SQLException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        chekAnalysis();
        List<Analysis> dist = uptakeByCode
                .stream()
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("chekedAnalysis", dist);
        model.addAttribute("user", userDetails.getUsername());
        return "chek";

    }
    @GetMapping(value = "/admin")
    public String adminPage(ModelMap modelMap) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> users = uptakeService.getAllUsers();
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("user", userDetails.getUsername());

        return "admin";
    }
    @PostMapping(value = "/saveuser")
    public String saveUser(@RequestParam(value = "name") String name,
                           @RequestParam(value = "pass") String password,
                           @RequestParam(value = "role") String role) {
        uptakeService.saveUser(name, password, role);
        return "redirect:/admin";
    }
    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam(value = "id") Long id){
        uptakeService.removeUserById(id);
        return "redirect:/admin";
    }
    @PostMapping(value = "/refresh")
    public String refresh() throws SQLException {

        if (uptakeByCode.size()!=0) {uptakeByCode.clear();}
        return "redirect:/code";
    }
    @PostMapping(value = "/write")
    public String write() throws IOException, XML2SpreadSheetError {
        List<Analysis> dist = uptakeByCode
                .stream()
                .distinct()
                .collect(Collectors.toList());

        deleteAllFilesFolder("C:/Lists");
        try(
                FileOutputStream fos=new FileOutputStream("C:/Lists/HIVList.txt", true);
                FileOutputStream fosB=new FileOutputStream("C:/Lists/HBsList.txt", true);
                FileOutputStream fosC=new FileOutputStream("C:/Lists/HCVList.txt", true);
                FileOutputStream fosSyf=new FileOutputStream("C:/Lists/SyfList.txt", true);
                FileOutputStream fosMRP=new FileOutputStream("C:/Lists/MRPList.txt", true);
                ) {
            for (Analysis data:
                    dist) {
                if (data.getHiv().equals("1")) {
                    String item = data.getEmc()+ System.lineSeparator();
                    fos.write(item.getBytes());
                }
                if (data.getHbsAg().equals("1")) {
                    String item = data.getEmc()+ System.lineSeparator();

                    fosB.write(item.getBytes());
                }
                if (data.getAtHCV().equals("1")) {
                    String item = data.getEmc()+ System.lineSeparator();
                    fosC.write(item.getBytes());
                }
                if (data.getSyphIFA().equals("1")) {
                    String item = data.getEmc()+ System.lineSeparator();
                    fosSyf.write(item.getBytes());
                }
                if (data.getSyphMRP().equals("1")) {
                    String item = data.getEmc()+" "+ "-"+ System.lineSeparator();
                    fosMRP.write(item.getBytes());
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        XLConstructor.writeXML();
        XLConstructor.xml2XLSX();

        return "redirect:/code";


    }

    @PostMapping(value = "/code")
    public String updateUser(ModelMap model, @RequestParam(value = "codeInt") String codeInt,
                             @RequestParam(value ="redir") String redir) throws SQLException {
//        chek.add(codeInt);

            Analysis analysis = new Analysis();
            List<Object[]> data = new ArrayList<>();
            data = uptakeService.getData("and PATDIREC.QUANTITY_DONE=0\n", codeInt);
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
                        analysis.setSex(data1[11].toString());


                        if (data1[9].toString().equals("А/т к ВИЧ 1,2 +А/г")) {
                            analysis.setHiv("1");
                            if (data1[4] != null) {
                                analysis.setResultHiv(data1[4].toString());
                            }
                        } else {
                            if (analysis.getHiv() == null)
                                analysis.setHiv("");
                        }
                        if (data1[9].toString().equals("HBsAg")) {
                            analysis.setHbsAg("1");
                            if (data1[4] != null) {
                                analysis.setResultHbsAg(data1[4].toString());
                            }
                        } else {
                            if (analysis.getHbsAg() == null)
                                analysis.setHbsAg("");
                        }
                        if (data1[9].toString().equals("Ат .к. HCV")) {
                            analysis.setAtHCV("1");
                            if (data1[4] != null) {
                                analysis.setResultatHCV(data1[4].toString());
                            }
                        } else {
                            if (analysis.getAtHCV() == null)
                                analysis.setAtHCV("");
                        }
                        if (data1[9].toString().equals("Сифилис МРП")) {
                            analysis.setSyphMRP("1");
                            if (data1[4] != null) {
                                analysis.setResultMRP(data1[4].toString());
                            }
                        } else {
                            if (analysis.getSyphMRP() == null)
                                analysis.setSyphMRP("");
                        }
                        if (data1[9].toString().equals("Syphilis ИФА")) {
                            analysis.setSyphIFA("1");
                            if (data1[4] != null) {
                                analysis.setResultSyphIfa(data1[4].toString());
                            }
                        } else {
                            if (analysis.getSyphIFA() == null)
                                analysis.setSyphIFA("");
                        }


                    } catch (Exception ignored) {
                    }

                    try {
                        if (data1[2] != null)
                        analysis.setKontengent(data1[2].toString());
                        else {analysis.setKontengent("");}
                    } catch (Exception ex) {
                        analysis.setKontengent("");
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
//                uptakeByCode.removeIf(n -> (n.getEmc().equals(analysis.getEmc())&&n.getCode().equals(analysis.getCode())));
                uptakeByCode.add(analysis);
//                chekByCode.add(analysis);
//        chek.add(analysis.getCode());

            }


//           uptakeByCode.add(contService.getByCode(Integer.parseInt(codeInt.trim())));
//        model.addAttribute("selected", uptakeByCode);
       code = null;
        return redir;
    }
//    @PostMapping(value = "/chek")
    public String chekAnalysis () throws SQLException {
//        List<Analysis> dist = uptakeByCode
//                .stream()
//                .distinct()
//                .collect(Collectors.toList());
//        chek.add("3856");
        List<Object[]> data = new ArrayList<>();
        for (Analysis upt:uptakeByCode) {

        data = uptakeService.chek(" ",upt.getCode());
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
                if (data1[1].toString().equals("А/т к ВИЧ 1,2 +А/г")) {
                    upt.setHiv("1");
                    if (data1[0] != null ) {upt.setResultHiv(data1[0].toString());}
                }
                if (data1[1].toString().equals("HBsAg")){
                    upt.setHbsAg("1");
                    if (data1[0] != null) {upt.setResultHbsAg(data1[0].toString());}
                }

                if (data1[1].toString().equals("Ат .к. HCV")){
                    upt.setAtHCV("1");
                    if (data1[0] != null) {upt.setResultatHCV(data1[0].toString());}

                }

                if (data1[1].toString().equals("Сифилис МРП")) {
                    upt.setSyphMRP("1");
                    if (data1[0] != null) {upt.setResultMRP(data1[0].toString());}
                    else {upt.setResultMRP("");}
                }
                if (data1[1].toString().equals("Syphilis ИФА")) {
                    upt.setSyphIFA("1");
                    if (data1[0] != null) {upt.setResultSyphIfa(data1[0].toString());}
                    else {upt.setResultSyphIfa("");}
                }
//                System.out.println(Arrays.toString(data1));

            } catch (Exception ignored) {}

//                analysis.setResultHiv(data1[4].toString());
//
//            } catch (Exception ex) {}

//
        }
//        System.out.println(analysis);
//        System.out.println(data.size());

//            chekByCode.add(upt);
//            System.out.println(upt.chekHiv());
//            System.out.println(upt.chekatHCV());
//            System.out.println(upt.chekHbs());
//            System.out.println(upt.chekSyphIfa());
//            System.out.println(upt.chek());
//            System.out.println(upt.totalChek());
//            System.out.println(upt);

        }

//           uptakeByCode.add(contService.getByCode(Integer.parseInt(codeInt.trim())));
//        model.addAttribute("selected", uptakeByCode);
        return "redirect:/chek";
    }


    public void setCode(String code) {
        this.code = code;
    }

    public List<Analysis> getUptakeByCode() {
        return uptakeByCode
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static void deleteAllFilesFolder(String path) {
        for (File myFile : Objects.requireNonNull(new File(path).listFiles()))
            if (myFile.isFile()) myFile.delete();
    }
}
