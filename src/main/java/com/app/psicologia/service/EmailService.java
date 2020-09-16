package com.app.psicologia.service;

import javax.mail.MessagingException;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
    public void sendEmailWithAttachment(String to, String subject, String pathToAttachment ,String pathResource) throws MessagingException;
}
