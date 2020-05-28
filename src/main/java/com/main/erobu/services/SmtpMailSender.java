package com.main.erobu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SmtpMailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, String subject, String body, String name) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true); // true indicates
        // multipart message
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(
                "<html>"
                        + "<body>"
                        + "<div>Dear "+ name +", "
                        + "<div><strong>This is an automatic email do not respond:</strong></div>"
                        + "<div>"
                        + "<div>"+body +"</div>"
                        + "</div>"
                        + "</div>"
                        + "<div>Thanks,</div>"
                        + "Events.Diversity Team"
                        + "<div><strong>LOGO Events.Diversity Team :</strong></div>"  + "<div>"
                        + "<img src='cid:leftSideImage' style='float:center;width:300px;height:100px;'/>"
                        + "</div></body>"
                        + "</html>", true);

        helper.addInline("leftSideImage",
                new File("src/main/resources/static/images/Community-events-icon .jpg"));
        javaMailSender.send(message);


    }

}
