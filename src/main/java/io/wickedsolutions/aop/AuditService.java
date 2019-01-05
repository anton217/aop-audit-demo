package io.wickedsolutions.aop;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AuditService {

    @Async
    public void saveAuditEntry(String user, String description, String method, String params) throws InterruptedException {

        // TODO - implement how your application would save the audit info

        String auditEntry = new StringBuilder()
                .append(user).append(" --> ")
                .append(description).append(" --> ")
                .append(method).append(" --> ")
                .append(params)
                .toString();

        System.out.println(auditEntry);

        // simulate an unrealistically long DB write
        TimeUnit.SECONDS.sleep(1);

        System.out.println("Audit Entry Saved");
    }

}
