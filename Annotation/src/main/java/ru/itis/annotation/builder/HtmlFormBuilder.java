package ru.itis.annotation.builder;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@SupportedAnnotationTypes(value = {"ru.itis.annotation.html.HtmlForm"})
public class HtmlFormBuilder {
    private final static String templateFileName = "template.ftlh";
    private final static String templatePath = "C:\\Users\\Айнурка\\Desktop\\JavaLab\\Annotation\\src\\main\\resources";
    private Configuration configuration;

    public HtmlFormBuilder() {
        configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        try {
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void buildHtmlForm(String filePath, Map<String, Object> params) {
        try {
            Template template = configuration.getTemplate(templateFileName);
            File file = new File(filePath);
            Writer writer = new FileWriter(file);

            template.process(params, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
