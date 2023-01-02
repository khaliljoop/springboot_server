package sn.ssi.ersen.Email;

public interface EmailService {
    // Method
    // To send a simple email
    Integer sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
