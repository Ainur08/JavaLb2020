package ru.itis.annotation;

import ru.itis.annotation.builder.HtmlFormBuilder;
import ru.itis.annotation.html.HtmlForm;
import ru.itis.annotation.html.HtmlInput;
import ru.itis.annotation.models.Input;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.*;

@SupportedAnnotationTypes(value = {"ru.itis.annotation.html.HtmlForm", "ru.itis.annotation.html.HtmlInput"})
public class HtmlFormProcessor extends AbstractProcessor {
    private HtmlFormBuilder builder;

    public HtmlFormProcessor() {
        this.builder = new HtmlFormBuilder();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(ru.itis.annotation.html.HtmlForm.class);
        annotatedElements.forEach(element -> {
            String path = HtmlFormProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                    + element.getSimpleName()
                    + ".html";

            builder.buildHtmlForm(path, getElementAnnotation(element));
        });
        return true;
    }


    public Map<String, Object> getElementAnnotation(Element element) {
        Map<String, Object> params = new HashMap<>();
        HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);

        params.put("method", htmlForm.method());
        params.put("action", htmlForm.action());

        List<Input> inputs = new ArrayList<>();
        element.getEnclosedElements()
                .stream()
                .filter(field -> field.getAnnotation(HtmlInput.class) != null)
                .forEach(field -> inputs.add(getElementsInput(field)));

        params.put("inputs", inputs);
        return params;
    }

    public Input getElementsInput(Element element) {
        HtmlInput htmlInput = element.getAnnotation(HtmlInput.class);
        return new Input(
                htmlInput.placeholder(),
                htmlInput.name(),
                htmlInput.type()
        );
    }
}
