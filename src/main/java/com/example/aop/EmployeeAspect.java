package com.example.aop;

import com.example.entity.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class EmployeeAspect {

    @Before(value = "execution(* com.example.controller.EmployeeController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Request to" + joinPoint.getSignature() + "started at: "+ new Date());
    }

    @After(value = "execution(* com.example.controller.EmployeeController.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        System.out.println("Request to" + joinPoint.getSignature() + "ended at: "+ new Date());
    }

    @Before(value = "execution(* com.example.service.EmployeeService.*(..))")
    public void beforeAdviceForService(JoinPoint joinPoint){
        System.out.println("Request to service layer " + joinPoint.getSignature() + "started at: "+ new Date());
    }

    @After(value = "execution(* com.example.service.EmployeeService.*(..))")
    public void afterAdviceForService(JoinPoint joinPoint){
        System.out.println("Request to service layer " + joinPoint.getSignature() + "ended at: "+ new Date());
    }

    @AfterReturning(value = "execution(* com.example.service.EmployeeService.addEmployee(..))", returning = "employee")
        public void afterReturningAdviceForAddEmpService(JoinPoint joinPoint, Employee employee){
        System.out.println("Business logic to save an employee ran successfully and employee is saved with id: "+ employee.getId());
    }

    @AfterThrowing(value = "execution(* com.example.service.EmployeeService.addEmployee(..))", throwing = "exception")
    public void afterThrowingAdviceForAddEmpService(JoinPoint joinPoint, Exception exception){
        System.out.println("Business logic to save an employee throw an exception "+ exception.getMessage());
    }

    @Around(value = "execution(* com.example.service.EmployeeService.addEmployee(..))")
    public Employee aroundAdviceForAddEmpService(ProceedingJoinPoint joinPoint){
        System.out.println("Inside Around Advice in Aspect : Business logic to save an employee started at " + new Date());
        try{
//            Employee[] empArr= new Employee[1];     //with this can change data
//            Employee dummyEmployee = new Employee();
//            dummyEmployee.setName("Dummy");
//            empArr[0]=dummyEmployee;
//            Employee employee = (Employee) joinPoint.proceed(empArr);

          Employee employee = (Employee) joinPoint.proceed();    //if comment it than business logic will not be implemented
            return employee;
        }catch (Throwable e){
            System.out.println("Inside Around Advice in Aspect : Business logic to save an employee failed terribly " + e.getMessage());
        }
        System.out.println("Inside Around Advice in Aspect : Business logic to save an employee ended at " + new Date());
        return null;
    }

}
