package com.btcetrade.bitcoin.dbconfig;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBConfigurattion {

	
	public SessionFactory getSessionFactory(){
		ApplicationContext context= new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SessionFactory sessionFactory=(SessionFactory) context.getBean("sessionFactory");
		System.out.println(sessionFactory);
		return sessionFactory;
	}
	
	
	public static void main(String[] args) {
		new DBConfigurattion().getSessionFactory();
	}
	
}
