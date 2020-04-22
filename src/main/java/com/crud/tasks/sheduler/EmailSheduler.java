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
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;


    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.sendInfoOnceADay(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                message(size)));
    }

    public String message(long tempSize) {
        String messageIntroduction = "Currently in database you got: ";
        if(tempSize==1) {
            return messageIntroduction + tempSize + " task";
        } else {
            return messageIntroduction + tempSize + " tasks";
        }
    }


}
