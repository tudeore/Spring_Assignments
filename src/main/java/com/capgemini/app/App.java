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
		/* System.out.println(orgnization.toString()); */
    	System.out.println("Name : "+orgnization.getName());
    	System.out.println("OrgId : "+orgnization.getOrgId());
    	System.out.println("Share Value : "+orgnization.getShareValue());
    	System.out.println("DateOfEstablishment : "+orgnization.getDateOfEstablishment());
    	System.out.println("IPAdresses : " +orgnization.getIpAddresses());
    	System.out.println("cities : "+orgnization.getBoardMembers());
    	System.out.println("Board Members : "+orgnization.getBoardMembers());
    	System.out.println("Branch Managers : "+orgnization.getBranchManagers());
    	
    }
}
