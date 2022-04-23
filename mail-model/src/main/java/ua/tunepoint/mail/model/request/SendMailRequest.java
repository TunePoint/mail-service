package ua.tunepoint.mail.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SendMailRequest {

    @Email
    @NotNull
    private String recipientEmail;

    @NotNull
    @NotBlank
    private String subject;

    @NotNull
    @NotBlank
    private String body;
}
