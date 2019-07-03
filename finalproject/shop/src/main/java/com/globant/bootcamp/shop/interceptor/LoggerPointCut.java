package com.globant.bootcamp.shop.interceptor;

import com.globant.bootcamp.shop.model.Store;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LoggerPointCut {
    @Pointcut("execution(* com.globant.bootcamp.shop.endpoint.StoreEndPoint.createStore(..))")
    public void createStore() {
    }

    @After("createStore()")
    public void logCreateStore() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        BufferedWriter writer = new BufferedWriter(new FileWriter("logfile.txt", true));
        String str = "POST store at: " + formatter.format(date);
        writer.append(' ');
        writer.append(str);

        writer.close();
    }

    @Pointcut("execution(* com.globant.bootcamp.shop.endpoint.StoreEndPoint.createStoreProduct(..))")
    public void createProduct() {
    }

    @After("createProduct()")
    public void logCreateProduct() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        BufferedWriter writer = new BufferedWriter(new FileWriter("logfile.txt", true));
        String str = "POST product at: " + formatter.format(date);
        writer.append(' ');
        writer.append(str);

        writer.close();
    }

    @Pointcut("execution(* com.globant.bootcamp.shop.endpoint.StoreEndPoint.createStoreEmployee(..))")
    public void createEmployee() {
    }

    @After("createEmployee()")
    public void logCreateEmployee() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        BufferedWriter writer = new BufferedWriter(new FileWriter("logfile.txt", true));
        String str = "POST employee at: " + formatter.format(date);
        writer.append(' ');
        writer.append(str);

        writer.close();
    }

    @Pointcut("execution(* com.globant.bootcamp.shop.endpoint.StoreEndPoint.createStoreEmployeeAddress(..))")
    public void createAddress() {
    }

    @After("createProduct()")
    public void logCreateAddress() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        BufferedWriter writer = new BufferedWriter(new FileWriter("logfile.txt", true));
        String str = "POST address at: " + formatter.format(date);
        writer.append(' ');
        writer.append(str);

        writer.close();
    }


}
