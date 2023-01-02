package sn.ssi.ersen.Email;

// Service implementation class


// Importing required classes
import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sn.ssi.ersen.dao.UtilisateurRepository;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;
    @Autowired private UtilisateurRepository utilisateurRepository;

    @Value("${spring.mail.username}") private String sender;

    // Method 1
    // To send a simple email
    public Integer sendSimpleMail(EmailDetails details)
    {
        int res;
        Integer nb =  utilisateurRepository.getNbByEmail(details.getRecipient());
        if (nb==0)
            res=0;
        else{
            // Try block to check for exceptions
            try {
                // Creating a simple mail message
                SimpleMailMessage mailMessage = new SimpleMailMessage();

                // Setting up necessary details
                mailMessage.setFrom(sender);
                mailMessage.setTo(details.getRecipient());
                mailMessage.setText("Votre mot de passe de connexion est : "+utilisateurRepository.getPasswordByEmail(details.getRecipient()));
                mailMessage.setSubject("Recuperation mot de passe");

                // Sending the mail
                javaMailSender.send(mailMessage);
                res=1;
            }

            // Catch block to handle the exceptions
            catch (Exception e) {
                System.out.println(e.getMessage());
                res=-1;
            }
        }
        return res;

    }

    // Method 2
    // To send an email with attachment
    public String sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}
