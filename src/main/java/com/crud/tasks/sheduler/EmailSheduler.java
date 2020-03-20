package com.crud.tasks.sheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailSheduler {

    private static final String SUBJECT = "Tasks: Once a day e-mail";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskrepository;

    @Autowired
    private AdminConfig adminConfig;

    private String message(long tempSize) {
        String messageIntroduction = "Currently in database you got: ";
        if(tempSize==1) {
            return messageIntroduction + tempSize + " task";
        } else {
            return messageIntroduction + tempSize + " tasks";
        }
    }

    @Scheduled(/*cron = "0 0 10 * * *"*/ fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskrepository.count();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                message(size)));
    }


}
