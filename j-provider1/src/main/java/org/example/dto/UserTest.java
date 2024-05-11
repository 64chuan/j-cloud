package org.example.dto;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.dto.MyAnnottation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 面向切面编程
 * 注解 @Component 标记此类为组件，让Spring能够在应用程序启动时自动扫描并加载这些组件。
 * 注解 @Aspect 标注此类是个切面类
 *
 * Joinpoint(连接点): 就是方法，可以说每个controller里面的方法都可以称作连接点。
 * Pointcut(切入点): 就是挖掉共性功能的方法，可以说连接点（方法）有的被挖出了一些功能，这些被抽出共性代码的连接点叫切入点。
 * Advice(通知)： 就是切入点被挖出的共性功能代码，组成了一个方法称为通知
 * Aspect(切面): 就是共性功能（通知）与挖的位置的对应关系，
 *          因为该共性功能可以在：切入点前面(前置通知)，切入点后面(后置通知)，切入点周围(环绕通知)，切入点抛异常时执行(抛异常通知)，切入点返回后执行(返回后通知)，
 *          可以理解为切入点方法和通知方法配置时放在了一个类中来描述两者的关系，该类具有切面的功能。
 * Target(目标对象): 就是挖掉功能的方法对应的类产生的对象，这种对象无法直接完成最终功能，
 *          例如一个controller里面有方法被挖去了共性功能组成了一个通知，此时必须两者一起才能完成总功能。
 * Weaving(织入): 就是将挖掉的功能回填到对应的方法的动态过程。
 * Proxy(代理): 目标对象无法直接完成工作，需要对其进行功能回填，通过创建原始对象的代理对象来实现。
 * Introduction(引入/引介)： 就是对原始对象无中生有的添加成员变量的成员方法，原始对象被织入被挖去的功能叫织入，也可以添加原来不存在的方法、属性，此时叫引入或介入。
 */
@Component
@Aspect
public class UserTest {

    /**
     * 扫描切入点
     */
    @Pointcut("execution(* org.example.service.AnnotationService.*(..))")
    public void pointCut(){
    }

    /**
     * 调用切点方法前调用
     */
    @Before("pointCut()")
    public void before(){
        System.out.println(" pointCut()  前置通知");
    }

    /**
     * 调用切点方法后调用
     */
    @After("pointCut()")
    public void after(){
        System.out.println(" pointCut()  后置通知");
    }

    /**
     * 添加了@Around注解  如果方法前缀相同，按照后缀按从小到大的顺序调用；
     * 如果不同，则使用 @Order 注解调用
     *
     * @param joinPoint 链接点
     * @param myAnnottation
     * @return Object
     * @throws Throwable
     */
    @Around("@annotation(myAnnottation)")
    public Object around1(ProceedingJoinPoint joinPoint, MyAnnottation myAnnottation) throws Throwable{

        System.out.println("注解环绕通知前置 + 注解值" + myAnnottation.role());

        // 继续进行原方法
        Object result = joinPoint.proceed();

        System.out.println("注解环绕通知后置 + 注解值" + myAnnottation.role());
        return result;
    }

    /**
     * 添加了@Around注解  如果方法前缀相同，按照后缀按从小到大的顺序调用
     *
     * @param joinPoint 链接点
     * @return Object
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object around2(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();

        System.out.println(args);

        Object target = joinPoint.getTarget();
        System.out.println(target.getClass().getName());


        System.out.println("环绕前通知");

        // 继续进行原方法
        Object result = joinPoint.proceed();

        System.out.println("环绕后通知");

        return result;
    }

    /**
     * 抛出异常后处理
     * @param t 异常
     */
    @AfterThrowing(value = "pointCut()", throwing = "t")
    public void afterThrowing(Throwable t){
        System.out.println("异常通知：" + t.getMessage());
    }
}
