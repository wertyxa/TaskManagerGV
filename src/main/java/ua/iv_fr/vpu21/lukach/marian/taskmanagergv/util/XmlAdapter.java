package ua.iv_fr.vpu21.lukach.marian.taskmanagergv.util;

import ua.iv_fr.vpu21.lukach.marian.taskmanagergv.models.Tasks;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlAdapter {
    public XmlAdapter() {
    }
    public static Tasks convertXmlToData(File file) throws NullPointerException, IllegalArgumentException{
        try {
            JAXBContext context = JAXBContext.newInstance(Tasks.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Tasks) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void convertDataToXml(Tasks data, String filename){
        try {
            JAXBContext context = JAXBContext.newInstance(Tasks.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(data, new File(filename));
            System.out.println("Converting is successful");
        } catch (JAXBException e) {
            System.out.println("Converting is not successful");
            throw new RuntimeException(e);
        }
    }
}
