package usg.lostlink.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  @Before("execution(* usg.lostlink.server.controllers..*(..)) && " +
      "!execution(* usg.lostlink.server.controllers.AuthController.*(..)) && " +
      "!execution(* usg.lostlink.server.controllers.UserController.*(..))")
  public void logBeforeController(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    Object[] args = joinPoint.getArgs();

    log.info("===> Entering CONTROLLER method: {} with args: {}", methodName, args);
  }
}
