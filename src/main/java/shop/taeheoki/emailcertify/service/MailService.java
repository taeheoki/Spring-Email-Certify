package shop.taeheoki.emailcertify.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import shop.taeheoki.emailcertify.dto.MailDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "test@test.com";

    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        message.setFrom(FROM_ADDRESS);
        log.info("to={}", message.getTo());
        log.info("subject={}", message.getSubject());
        log.info("text={}", message.getText());
        javaMailSender.send(message);
    }
}
