package com.example.publictransportsystem.interceptor;

import com.example.publictransportsystem.persitence.ApplicationLogEntity;
import com.example.publictransportsystem.repository.ApplicationLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Interceptor
@TransactionLogged
public class TransactionLoggingInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingInterceptor.class);
    @Inject
    private ApplicationLogRepository applicationLogRepository;

    @AroundInvoke
    @Transactional
    public Object logTransaction(InvocationContext ctx) throws Exception {
        final String className = ctx.getTarget().getClass().getSimpleName();
        final String methodName = ctx.getMethod().getName();
        final LocalDateTime ts = LocalDateTime.now();

        final ApplicationLogEntity applicationLogEntity = getApplicationLogEntity(className, methodName, "Transaction START");
        applicationLogEntity.setTimestamp(ts);
        logger.info("Transaction START: {}.{}", className, methodName);
        applicationLogRepository.logMessage(applicationLogEntity);
        try {
            Object result = ctx.proceed();
            logger.info("Transaction END: {}.{}", className, methodName);
            final ApplicationLogEntity applicationLogEntityEND = getApplicationLogEntity(className, methodName, "Transaction END");
            applicationLogEntityEND.setTimestamp(ts);
            applicationLogRepository.logMessage(applicationLogEntityEND);
            return result;
        } catch (Exception e) {
            logger.error("Transaction ERROR: {}.{} - {}", className, methodName, e.getMessage());
            applicationLogEntity.setMessage("Transaction ERROR: " + e.getMessage());
            final ApplicationLogEntity applicationLogEntityERROR = getApplicationLogEntity(className, methodName, "Transaction ERROR: " + e.getMessage());
            applicationLogRepository.logMessage(applicationLogEntityERROR);
            throw e;
        }
    }

    private static ApplicationLogEntity getApplicationLogEntity(String className, String methodName, String e) {
        final ApplicationLogEntity applicationLogEntityERROR = new ApplicationLogEntity();
        applicationLogEntityERROR.setClassName(className);
        applicationLogEntityERROR.setMethodName(methodName);
        applicationLogEntityERROR.setMessage(e);
        return applicationLogEntityERROR;
    }
}
