package com.example.restApi.stepDefs;



import com.example.restApi.CucumberRunnerTest;
import com.example.restApi.MailSender;
import com.example.restApi.Models.GmailData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class MyStepdefs extends CucumberRunnerTest {

    @Value("${fromMail}")
    private String fromMail;

    @Value("${toMail}")
    private String toMail;

    @Value("${toMailWrong}")
    private String toMailWrong;

    @Value("${subject}")
    private String subject;

    @Value("${bodyText}")
    private String bodyText;

    @Value("${filePath}")
    private String filePath;



    @Autowired
    MailSender mailSender;

    @Autowired
    GmailData gmailData;

    @Given("User send a mail using {string} credential to {string}")
    public void userSendAMailUsingCredentialTo(String arg0, String to) throws MessagingException, GeneralSecurityException, IOException {
        if(to.equalsIgnoreCase("recepient1")) {
            mailSender.sendEmail(fromMail, toMail, subject, bodyText,false,null);
            gmailData.setRightMail(true);
        }else if (to.equalsIgnoreCase("wrongRecepient1")){
            mailSender.sendEmail(fromMail, toMailWrong, subject, bodyText,false,null);
            gmailData.setRightMail(false);
        }
    }

    @Then("verify that the email has been recieved by the recipient")
    public void verifyThatTheEmailHasBeenRecievedByTheRecipient() throws IOException, InterruptedException {
        Thread.sleep(2000);
        boolean hasNotReceieved = mailSender.isBounced(gmailData.getService(),gmailData.getMessage().getThreadId());
        if(gmailData.isRightMail()==true) {
            Assert.assertTrue(!hasNotReceieved);
        }else {
            Assert.assertTrue(hasNotReceieved);
        }
    }

    @When("User send a mail with attatchmtn using {string} credential to {string}")
    public void userSendAMailWithAttatchmtnUsingCredentialTo(String arg0, String to) throws MessagingException, GeneralSecurityException, IOException {
        File file = new File(System.getProperty("user.dir")+filePath);
        if(to.equalsIgnoreCase("recepient1")) {
            mailSender.sendEmail(fromMail, toMail, subject, bodyText,true,file);
            gmailData.setRightMail(true);
        }else if (to.equalsIgnoreCase("wrongRecepient1")){
            mailSender.sendEmail(fromMail, toMailWrong, subject, bodyText,true,file);
            gmailData.setRightMail(false);
        }
    }
}
