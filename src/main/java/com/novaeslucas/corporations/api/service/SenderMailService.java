package com.novaeslucas.corporations.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SenderMailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviar(String destinatario, String titulo, String conteudo) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("no-reply@tcm.ba.gov.br");
        email.setTo(destinatario);
        email.setSubject(titulo);
        email.setText(conteudo);
        this.mailSender.send(email);
    }
}
