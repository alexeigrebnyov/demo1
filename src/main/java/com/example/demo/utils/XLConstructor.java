package com.example.demo.utils;

import com.example.demo.controller.UptakeController;
import com.example.demo.model.Analysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.curs.xylophone.XML2SpreadSheetError;
import ru.curs.xylophone.XML2Spreadsheet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class XLConstructor {

    private static UptakeController uptakeService;

    @Autowired
    public XLConstructor(UptakeController uptakeService) {
        this.uptakeService = uptakeService;
    }

    public static void xml2XLSX() throws IOException, XML2SpreadSheetError {
        FileInputStream data = new FileInputStream("C:/Repo/Report1.xml");
        File out = new File("C:/Repo/report.xlsx");
        FileOutputStream output = new FileOutputStream(out);
        File template = new File("C:/Repo/template.xlsx");
        File desc = new File("C:/Repo/descriptor.xml");
        XML2Spreadsheet.process(data, desc, template, false, output);

    }
    private static void writeDocument(Document document)
            throws TransformerFactoryConfigurationError
    {
        Transformer trf = null;
        DOMSource src = null;
        FileOutputStream fos = null;
        try {
            trf = TransformerFactory.newInstance()
                    .newTransformer();
            src = new DOMSource(document);
            fos = new FileOutputStream("C:/Repo/Report1.xml");

            StreamResult result = new StreamResult(fos);
            trf.transform(src, result);
        } catch (TransformerException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    public static void writeXML() throws IOException {
        DocumentBuilderFactory dbf = null;
        DocumentBuilder db  = null;
        Document doc = null;
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db  = dbf.newDocumentBuilder();
            doc = db.newDocument();

            Element e_root   = doc.createElement("report");
//			e_root.setAttribute("lang", "en");
            Element columnId  = doc.createElement("column");
            columnId.setAttribute("data", "Фио");
            Element columnpatientId = doc.createElement("column");
            columnpatientId.setAttribute("data", "Дата забора");
            Element columnuptakeCod = doc.createElement("column");
            columnuptakeCod.setAttribute("data", "Код забора");
            Element columnnumber = doc.createElement("column");
            columnnumber.setAttribute("data", "ВИЧ");
            Element columnresult = doc.createElement("column");
            columnresult.setAttribute("data", "HBsAg");
            e_root.appendChild(columnId);
            e_root.appendChild(columnpatientId);
            e_root.appendChild(columnuptakeCod);
            e_root.appendChild(columnnumber);
            e_root.appendChild(columnresult);
            doc.appendChild(e_root);
//			if (posts.size() == 0)
//				return;

            List<Analysis> users  = uptakeService.getUptakeByCode();
//            List<String> hivs = users
//                    .stream().map(Analysis::getHiv)
//                    .collect(Collectors.toList());
            int hivIterator = 1;
            int hivCount = (int) users
                    .stream()
                    .filter(e -> e.getKontengent().equals("116 б"))
                    .filter(e ->e.getSex().equals("1"))
                    .count();
            int hbsIterator=1;
            int hbsCount = (int) users
                    .stream()
                    .filter(e -> (e.getKontengent().equals("116 б")&& e.getSex().equals("0")))
                    .count();
            int hcvIterator=1;
//            int hcvCount = (int) users
//                    .stream()
//                    .filter(e -> e.getAtHCV().equals("1"))
//                    .count();
            int ifaIterator = 1;
            int iterator116f = 0;
            int iterator116m = 0;
            for (Analysis hiv : users) {
//                int hivNumber = hivCount - (hivCount-hivIterator);
//                int hbsNumber = hbsCount - (hbsCount-hbsIterator);
//                int hcvNumber = hcvCount - (hcvCount-hcvIterator);
//                int z = hivs.size()-(hivs.size()-i);
                Element item = doc.createElement("item");
                item.setAttribute("name", hiv.getEmc());
                Element id = doc.createElement("data");
                id.setAttribute("value", hiv.getFio());
                Element patientid = doc.createElement("data");
                patientid.setAttribute("value", hiv.getDate_bio());
                Element uptakeCod = doc.createElement("data");
                uptakeCod.setAttribute("value", hiv.getCode());
                Element number = doc.createElement("data");
                if (hiv.getHiv().equals("1")) {
                    number.setAttribute("value", String.valueOf(hivIterator));
                    hivIterator++;
                } else {number.setAttribute("value", "");}
                Element result = doc.createElement("data");
                if (hiv.getHbsAg().equals("1")) {
                    result.setAttribute("value", String.valueOf(hbsIterator));
                    hbsIterator++;
                } else {result.setAttribute("value", "");}
                Element hcv = doc.createElement("data");
                if (hiv.getAtHCV().equals("1")) {
                    hcv.setAttribute("value", String.valueOf(hcvIterator));
                    hcvIterator++;
                } else {hcv.setAttribute("value", "");}
                Element ifa = doc.createElement("data");
                if (hiv.getSyphIFA().equals("1")) {
                    ifa.setAttribute("value", String.valueOf(ifaIterator));
                    ifaIterator++;
                } else {ifa.setAttribute("value", "");}
                Element kont116F = doc.createElement("data");
                if (iterator116f <1) {
                    kont116F.setAttribute("value", String.valueOf(hivCount));
                    iterator116f++;
                }else if (iterator116m<1) {kont116F.setAttribute("value", String.valueOf(hbsCount));
                iterator116m++;
                }


                item.appendChild(id);
                item.appendChild(patientid);
                item.appendChild(uptakeCod);
                item.appendChild(number);
                item.appendChild(result);
                item.appendChild(hcv);
                item.appendChild(ifa);
                item.appendChild(kont116F);

                e_root.appendChild (item);

//                if (hiv.getHiv().equals("1")) {
//                    i++;
//                }
            }
//            Element item1 = doc.createElement("item1");
//            item1.setAttribute("name1", "116 б");
//            Element kont116F = doc.createElement("data1");
//            kont116F.setAttribute("value1", String.valueOf(hivCount));
//            item1.appendChild(kont116F);
//            e_root.appendChild(item1);

//			System.out.println("    форумов : " + forums.size());
//			for (String forum : forums) {
//				Element e = doc.createElement("forum");
//				e.setTextContent(forum);
//				e_forums.appendChild (e);
//			}
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            // Сохраняем Document в XML-файл
            if (doc != null)
                writeDocument(doc);
        }

    }
}
