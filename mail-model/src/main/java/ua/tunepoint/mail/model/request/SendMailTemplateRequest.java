package ua.tunepoint.mail.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMailTemplateRequest {

    @Email
    private String recipientEmail;

    @NotBlank
    private String template;

    @NotBlank
    private String subject;

    private Map<String, String> params;
}
