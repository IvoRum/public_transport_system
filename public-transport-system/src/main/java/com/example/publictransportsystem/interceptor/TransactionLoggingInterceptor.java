package com.example.publictransportsystem.interceptor;

import com.example.publictransportsystem.persitence.TransactionLogEntity;
import com.example.publictransportsystem.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.Transactional;


@Interceptor
@TransactionLogged
public class TransactionLoggingInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingInterceptor.class);
    @Inject
    private LogRepository logRepository;

    @AroundInvoke
    @Transactional
    public Object logTransaction(InvocationContext ctx) throws Exception {
        String className = ctx.getTarget().getClass().getSimpleName();
        String methodName = ctx.getMethod().getName();
        final TransactionLogEntity transactionLogEntity = new TransactionLogEntity();
        transactionLogEntity.setClassName(className);
        transactionLogEntity.setMethodName(methodName);
        transactionLogEntity.setMessage("Transaction START");
        logger.info("Transaction START: {}.{}", className, methodName);
        logRepository.logMessage(transactionLogEntity);
        try {
            Object result = ctx.proceed();
            logger.info("Transaction END: {}.{}", className, methodName);
            final TransactionLogEntity transactionLogEntityEND = new TransactionLogEntity();
            transactionLogEntityEND.setClassName(className);
            transactionLogEntityEND.setMethodName(methodName);
            transactionLogEntityEND.setMessage("Transaction END");
            logRepository.logMessage(transactionLogEntityEND);
            return result;
        } catch (Exception e) {
            logger.error("Transaction ERROR: {}.{} - {}", className, methodName, e.getMessage());
            transactionLogEntity.setMessage("Transaction ERROR: " + e.getMessage());
            final TransactionLogEntity transactionLogEntityERROR = new TransactionLogEntity();
            transactionLogEntityERROR.setClassName(className);
            transactionLogEntityERROR.setMethodName(methodName);
            logRepository.logMessage(transactionLogEntityERROR);
            throw e;
        }
    }
}
