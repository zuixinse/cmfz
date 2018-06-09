package com.baizhi.aop;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao.LogDAO;
import com.baizhi.entity.AdminLog;
import com.baizhi.entity.User;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.UUID;

public class LogAOP implements MethodInterceptor {
    @Autowired
    private LogDAO logDAO;
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //时间
        Date date=new Date();
        //谁
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String name = ((User) requestAttributes.getRequest().getSession().getAttribute("user")).getName();
        //干了什么
        LogAnnotation annotation = methodInvocation.getMethod().getAnnotation(LogAnnotation.class);
        String method = annotation.name();
        //结果
        boolean flag=false;
        Object proceed=null;
        String flags=null;
        try{
            proceed = methodInvocation.proceed();
            flag=true;
            flags="成功";
        }catch (Exception e){
            flag=false;
            flags="失败";
            e.printStackTrace();
        }
        AdminLog adminLog=new AdminLog(UUID.randomUUID().toString(),name,method,date,flags);
        logDAO.insert(adminLog);
        return proceed;
    }

   
}
