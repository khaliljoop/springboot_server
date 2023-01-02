package sn.ssi.ersen.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sn.ssi.ersen.dao.UtilisateurRepository;

// Annotation
@RestController
// Class
@CrossOrigin("*")
public class EmailController {

    private final EmailService emailService;
    private final UtilisateurRepository utilisateurRepository;

    public EmailController(EmailService emailService, UtilisateurRepository utilisateurRepository) {
        this.emailService = emailService;
        this.utilisateurRepository = utilisateurRepository;
    }

    // Sending a simple Email
    @PostMapping("/sendMail")
    public Integer sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        return emailService.sendMailWithAttachment(details);
    }
}

