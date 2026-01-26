package dev.nihilncunia.fantasy_builder.domain.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 이메일 발송 유틸리티
 * 
 * <p>
 * 이메일 발송 기능을 담당합니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailHelper {

  private final JavaMailSender mailSender;

  @Value("${spring.mail.username:}")
  private String fromEmail;

  @Value("${app.base-url:http://localhost:8080}")
  private String baseUrl;

  /**
   * 이메일 발송
   * 
   * @param to 수신자 이메일
   * @param subject 제목
   * @param text 내용
   */
  public void sendEmail(String to, String subject, String text) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(fromEmail);
      message.setTo(to);
      message.setSubject(subject);
      message.setText(text);
      mailSender.send(message);
      log.info("이메일 발송 성공: {}", to);
    } catch (Exception e) {
      log.error("이메일 발송 실패: {}", to, e);
      throw new RuntimeException("이메일 발송에 실패했습니다.", e);
    }
  }

  /**
   * 비밀번호 재설정 링크 이메일 발송
   * 
   * @param to 수신자 이메일
   * @param resetToken 비밀번호 재설정 토큰
   */
  public void sendPasswordResetEmail(String to, String resetToken) {
    String resetUrl = baseUrl + "/auth/reset-password?token=" + resetToken;
    String subject = "[Fantasy Builder] 비밀번호 재설정";
    String text = String.format(
        "안녕하세요.\n\n" +
            "비밀번호 재설정을 요청하셨습니다.\n\n" +
            "아래 링크를 클릭하여 비밀번호를 재설정하세요:\n" +
            "%s\n\n" +
            "이 링크는 1시간 동안 유효합니다.\n\n" +
            "만약 비밀번호 재설정을 요청하지 않으셨다면, 이 이메일을 무시하셔도 됩니다.\n\n" +
            "감사합니다.",
        resetUrl);

    sendEmail(to, subject, text);
  }
}
