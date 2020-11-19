package ru.itis.rabbitmq.generate;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Component;
import ru.itis.rabbitmq.dto.UserDto;
import java.io.*;
import java.util.UUID;

@Component
public class PdfGeneratorImpl implements PdfGenerator {
    @Override
    public void generatePdfDocs(UserDto user, String templateFileName) {
        try {
            String readyTemplateString = setUserFields(user, getStringifyPageTemplate(templateFileName));
            String pdfPath = "generated/" + UUID.randomUUID() + ".pdf";
            File file = new File(pdfPath);
            file.createNewFile();
            HtmlConverter.convertToPdf(new ByteArrayInputStream(readyTemplateString.getBytes()),
                    new FileOutputStream(file), new ConverterProperties());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    private String getStringifyPageTemplate(String templateName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/templates/" + templateName))) {
            String line;
            StringBuilder pageContent = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                pageContent.append(line);
            }
            return pageContent.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String setUserFields(UserDto user, String template) {
        return template
                .replace("${first_name}", user.getFirst_name())
                .replace("${last_name}", user.getLast_name())
                .replace("${numberOfPassport}", user.getNumberOfPassport())
                .replace("${age}", user.getAge())
                .replace("${date}", user.getDate())
                ;
    }
}
