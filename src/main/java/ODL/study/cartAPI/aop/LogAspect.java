package ODL.study.cartAPI.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("execution(* ODL.study.cartAPI..*(..))")
    public void log() {
    }

    @Before("log()")
    public void beforeAdvice(JoinPoint jp) {
        StringBuilder args = new StringBuilder();
        for (Object obj : jp.getArgs()) {
            if (null != obj)
                args.append(obj.getClass() + "  ");
        }
        log.debug("beforeAdvice-Method name: {}, input ARGS: {}", jp.getSignature().getName(), args);
    }

    @AfterReturning(value = "log()", returning = "string")
    public void afterAdvice(JoinPoint jp, String string) {
        log.debug("afterAdvice-Method name: {}, , method return: {}", jp.getSignature().getName(), string);
    }

    @AfterThrowing(value = "log()", throwing = "e")
    public void throwingAdvice(JoinPoint jp, Throwable e) {
        log.error("throwingAdvice-Method name: {}, method return: {}", jp.getSignature().getName(), e.getMessage());
    }
}
