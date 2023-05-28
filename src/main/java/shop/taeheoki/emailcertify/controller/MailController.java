package shop.taeheoki.emailcertify.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.taeheoki.emailcertify.dto.MailDto;
import shop.taeheoki.emailcertify.service.MailService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;

    @PostMapping("/send-mail")
    public String execMail(MailDto mailDto) {
        mailService.mailSend(mailDto);
        return "ok";
    }
}
