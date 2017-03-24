package com.agg.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author udayd
 *
 */
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EnableAsync
public class AGGApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting AGG application");
		ApplicationContext ctx = SpringApplication.run(AGGApplication.class, args);
	}

}
