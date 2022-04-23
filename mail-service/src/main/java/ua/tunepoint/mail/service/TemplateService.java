package ua.tunepoint.mail.service;

import freemarker.template.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ua.tunepoint.web.exception.ServerException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final Configuration configuration;

    public String processTemplate(String templateName, Map<String, String> model) {
        try {
            var template = configuration.getTemplate(templateName + ".html");
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception ex) {
            throw new ServerException(ex.getMessage());
        }
    }
}
