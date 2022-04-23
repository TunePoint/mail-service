package ua.tunepoint.mail.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mail.server")
public class EmailServerProperties {

    private String host;
    private Integer port;
}
