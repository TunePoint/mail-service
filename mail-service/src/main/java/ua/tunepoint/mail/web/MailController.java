package ua.tunepoint.mail.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tunepoint.mail.model.request.SendMailRequest;
import ua.tunepoint.mail.model.request.SendMailTemplateRequest;
import ua.tunepoint.mail.service.EmailService;
import ua.tunepoint.mail.service.TemplateService;
import ua.tunepoint.web.model.StatusResponse;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final EmailService emailService;
    private final TemplateService templateService;

    @PostMapping("/send")
    public ResponseEntity<StatusResponse> send(@RequestBody SendMailRequest request) {
        emailService.send(request.getRecipientEmail(), request.getSubject(), request.getBody());
        return ResponseEntity.ok(StatusResponse.builder().build());
    }

    @PostMapping("/send-template")
    public ResponseEntity<StatusResponse> send(@RequestBody SendMailTemplateRequest request) {
        String body = templateService.processTemplate(
                request.getTemplate(),
                request.getParams()
        );

        emailService.send(request.getRecipientEmail(), request.getSubject(), body);

        return ResponseEntity.ok(StatusResponse.builder().build());
    }
}
