package com.example.restApi.Models;


import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GmailData {

    private Gmail service;
    private Message message;
    private boolean isRightMail;
}
