package shop.taeheoki.emailcertify.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import shop.taeheoki.emailcertify.dto.MailDto;
import shop.taeheoki.emailcertify.dto.MailHandler;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "test@test.com";

//    public void mailSend(MailDto mailDto) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(mailDto.getAddress());
//        message.setSubject(mailDto.getTitle());
//        message.setText(mailDto.getMessage());
//        message.setFrom(FROM_ADDRESS);
//        log.info("to={}", message.getTo());
//        log.info("subject={}", message.getSubject());
//        log.info("text={}", message.getText());
//        javaMailSender.send(message);
//    }

    public void mailSend(MailDto mailDto){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            sb.append((char) (random.nextInt(26) + 'A'));
        }
        String verificationCode = sb.toString();

        try {
            MailHandler mailHandler = new MailHandler(javaMailSender);
            mailHandler.setTo(mailDto.getAddress());
            mailHandler.setSubject("인증메일입니다.");
//            String htmlContent = "<p>" + mailDto.getMessage() +"<p> <img src='cid:sample-img'>";
            String htmlContent = "<html>"
                    + "<head>"
                    + "<title>이메일 인증</title>"
                    + "<style>"
                    + "h2 {"
                    + "color: #2c3e50;"
                    + "}"
                    + "p {"
                    + "color: #7f8c8d;"
                    + "}"
                    + ".verification-code {"
                    + "background-color: #f5f5f5;"
                    + "color: #595959;"
                    + "border-radius: 10px;"
                    + "padding: 20px;"
                    + "margin-top: 20px;"
                    + "}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h2>" + mailDto.getMessage() +"</h2>"
                    + "<p>본인 인증을 위해 아래의 인증 코드를 입력해주세요.</p>"
                    + "<div class=\"verification-code\"><h2>" + verificationCode + "</h2></div>"
                    + "</body>"
                    + "</html>";
            mailHandler.setText(htmlContent, true);
//            mailHandler.setAttach(mailDto.getFile().getOriginalFilename(), mailDto.getFile());
//            mailHandler.setInline("sample-img", mailDto.getFile());
            mailHandler.send();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
