package io.wickedsolutions.aop.aop;

import io.wickedsolutions.aop.AuditService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

//
@Aspect
@Component
public class AuditPointcut {

    //
    @Autowired
    AuditService auditService;

    @Around("@annotation(Audit)")
    public Object logEndpointUse(ProceedingJoinPoint joinPoint) throws Throwable {

        // get info from endpoint
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // get description from audit annotation
        Audit myAnnotation = method.getAnnotation(Audit.class);
        String description = myAnnotation.description();

        // parse args into readable form
        Object[] args = joinPoint.getArgs();
        String params = Arrays.asList(args)
                .stream()
                .map(object -> object.toString())
                .collect(Collectors.joining(", "));

        // choose your desired method signature format
        String name = signature.toLongString();

        // pass off to async service for saving
        auditService.saveAuditEntry("anton", description, name, params);

        // continue with business logic
        return joinPoint.proceed();
    }

}
