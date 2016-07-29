package com.agg.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

@Configuration
public class JasperReportsConfig {
	
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
	    JasperReportsViewResolver resolver = new JasperReportsViewResolver();

	    resolver.setViewClass(JasperReportsMultiFormatView.class);
	    resolver.setPrefix("classpath:/jrxml/");
	    resolver.setSuffix(".jrxml");
	    resolver.setReportDataKey("datasource");
	    resolver.setViewNames("rpt_*");
	    resolver.setOrder(0);

	    return resolver;
	}
}
