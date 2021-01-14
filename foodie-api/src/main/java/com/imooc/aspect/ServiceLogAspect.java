package com.imooc.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhaocan3
 * @date 2021年1月13日17:05:25
 */
@Component
@Aspect
public class ServiceLogAspect {
    private  static  final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * Aop 通知：
     * 1.前置通知：方法执行前执行通知
     * 2.后置通知：方法执行后执行通知
     * 3.环绕通知：在方法调用前和调用之后，分别都可以执行通知
     * 4.异常通知：在方法调用异常时通知
     * 5.最终通知：在方法调用后执行，类似finally
     */

    /**
     * 切面表达式
     * execution: 代表所要执行的表达式主体
     * 第一处：代表返回值类型 ， * 代表所有类型
     * 第二处：包名代表aop监控类所在的包
     * 第三处 ： .. 代表所在包及其子包的所有类方法
     * 第四处： * ； 代表类名  * 代表所有类
     * 第五处： *(..)
     */
    /**
     * @Description  切
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.imooc.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws  Throwable {
        log.info("======= 开始执行 {}.{}========",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long takeTime = end -begin;
        if (takeTime > 3000) {
            log.error("========= 执行结束 耗时：{} 毫秒=========",takeTime);
        }else if (takeTime > 2000){
            log.warn("========= 执行结束 耗时：{} 毫秒=========",takeTime);
        }else{
            log.info("========= 执行结束 耗时：{} 毫秒=========",takeTime);
        }
        return result;
    }
}
