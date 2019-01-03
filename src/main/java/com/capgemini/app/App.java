package com.capgemini.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.app.bean.Organization;


public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    	Organization orgnization = (Organization) context.getBean("organization");
    	System.out.println(orgnization.toString());
    	System.out.println("IPAdresses : " +orgnization.toString());
    }
}
