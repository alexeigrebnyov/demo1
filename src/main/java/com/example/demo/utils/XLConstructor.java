package com.example.demo.utils;

import com.example.demo.model.HIV;
import com.example.demo.service.HIVService;
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


@Component
public class XLConstructor {

    private static HIVService hivService;

    @Autowired
    public XLConstructor(HIVService hivService) {
        this.hivService = hivService;
    }

    public static void xml2XLSX() throws IOException, XML2SpreadSheetError {
        FileInputStream data = new FileInputStream("C:/Users/alexei/Downloads/Report1.xml");
        File out = new File(".","report.xlsx");
        FileOutputStream output = new FileOutputStream(out);
        File template = new File("C:/Users/alexei/Downloads/template.xlsx");
        File desc = new File("C:/Users/alexei/Downloads/descriptor.xml");
        XML2Spreadsheet.process(data, desc, template, false, output);

    }
    private static void writeDocument(Document document, String path)
            throws TransformerFactoryConfigurationError
    {
        Transformer trf = null;
        DOMSource src = null;
        FileOutputStream fos = null;
        try {
            trf = TransformerFactory.newInstance()
                    .newTransformer();
            src = new DOMSource(document);
            fos = new FileOutputStream(path);

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
            columnId.setAttribute("data", "id");
            Element columnpatientId = doc.createElement("column");
            columnpatientId.setAttribute("data", "patientId");
            Element columnuptakeCod = doc.createElement("column");
            columnuptakeCod.setAttribute("data", "uptakeCod");
            Element columnresult = doc.createElement("column");
            columnresult.setAttribute("data", "result");
            e_root.appendChild(columnId);
            e_root.appendChild(columnpatientId);
            e_root.appendChild(columnuptakeCod);
            e_root.appendChild(columnresult);
            doc.appendChild(e_root);
//			if (posts.size() == 0)
//				return;

            List<HIV> users  = hivService.allHivs();

            for (HIV hiv : users) {
                Element item = doc.createElement("item");
                item.setAttribute("name", hiv.getPatientId());
                Element id = doc.createElement("data");
                id.setAttribute("value", hiv.getId().toString());
                Element patientid = doc.createElement("data");
                patientid.setAttribute("value", hiv.getPatientId());
                Element uptakeCod = doc.createElement("data");
                uptakeCod.setAttribute("value", hiv.getUptakeCod());
                Element result = doc.createElement("data");
                result.setAttribute("value", hiv.getResult());
                item.appendChild(id);
                item.appendChild(patientid);
                item.appendChild(uptakeCod);
                item.appendChild(result);
                e_root.appendChild (item);
            }
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
                writeDocument(doc, "C:/Users/alexei/Downloads/Report1.xml");
        }

    }
}
