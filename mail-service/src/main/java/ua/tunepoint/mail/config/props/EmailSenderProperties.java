package ua.tunepoint.mail.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mail.sender")
public class EmailSenderProperties {

    private String address;
    private String password;
}
