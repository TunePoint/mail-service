package ua.tunepoint.mail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.tunepoint.mail.model.request.SendMailRequest;
import ua.tunepoint.mail.model.request.SendMailTemplateRequest;
import ua.tunepoint.web.model.StatusResponse;

@RequestMapping("/mail")
public interface MailEndpoint {

    @PostMapping("/send")
    ResponseEntity<StatusResponse> sendMail(@RequestBody SendMailRequest request);

    @PostMapping("/send-template")
    ResponseEntity<StatusResponse> sendMailTemplate(@RequestBody SendMailTemplateRequest request);
}
