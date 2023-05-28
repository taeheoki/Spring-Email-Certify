package shop.taeheoki.emailcertify.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MailDto {
    private String address;
    private String title;
    private String message;
    private MultipartFile file;
}
