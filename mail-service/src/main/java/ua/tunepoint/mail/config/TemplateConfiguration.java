package ua.tunepoint.mail.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Configuration
public class TemplateConfiguration {

    @Bean
    public freemarker.template.Configuration configuration() throws IOException {

        var configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_31);

        configuration.setDirectoryForTemplateLoading(templatesFile());
        configuration.setDefaultEncoding("UTF-8");

        return configuration;
    }

    @SneakyThrows
    private File templatesFile() {
        ClassLoader loader = this.getClass().getClassLoader();
        URL resource = loader.getResource("templates");

        if (resource == null) {
            throw new IllegalStateException();
        }

        return new File(resource.toURI());
    }
}
