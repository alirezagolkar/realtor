package com.artinrayan.foodi.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
	
	@Around("execution(* com.artinrayan.foodi.core.HostService.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("logAround() is running!");
		long start = System.currentTimeMillis();
		System.out.println("hijacked method : " + joinPoint.getSignature().getName());
		System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
		
		System.out.println("Around before is running!");
		Object ret = joinPoint.proceed();
		System.out.println("Around after is running!");
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Method execution time: " + elapsedTime + " milliseconds.");
		System.out.println("******");

		return ret;
	}

	/**
	 *
	 * @param joinPoint
	 * @param ex
	 * @throws Throwable
     */
	@AfterThrowing(pointcut = "execution(* com.artinrayan.foodi.core.HostService.*(..))", throwing = "ex")
	public void logAfterThrowingAllMethods(JoinPoint joinPoint, Exception ex) throws Throwable
	{
		System.out.println("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
		System.out.println("****Method name " + joinPoint.getSignature());
		System.out.println("****Argument " + joinPoint.getArgs()[0]);
	}
	
}