package com.agg.application;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.agg.application.model.ClaimReportExcelDO;
import com.agg.application.utils.StringHeaderWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Bean	
    ItemWriter<ClaimReportExcelDO> databaseCsvItemWriter() {
        FlatFileItemWriter<ClaimReportExcelDO> csvFileWriter = new FlatFileItemWriter<>();
 
        String exportFileHeader = "Claim_Number;contract_id;Dealer_Name";
        StringHeaderWriter headerWriter = new StringHeaderWriter(exportFileHeader);	
        csvFileWriter.setHeaderCallback(headerWriter);
 
        String exportFilePath = "D:/tmp/students.csv";
        csvFileWriter.setResource(new FileSystemResource(exportFilePath));
 
        LineAggregator<ClaimReportExcelDO> lineAggregator = createStudentLineAggregator();
        csvFileWriter.setLineAggregator(lineAggregator);
 
        return csvFileWriter;
    }
 
    private LineAggregator<ClaimReportExcelDO> createStudentLineAggregator() {
        DelimitedLineAggregator<ClaimReportExcelDO> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(";");
 
        FieldExtractor<ClaimReportExcelDO> fieldExtractor = createStudentFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);
 
        return lineAggregator;
    }
 
    private FieldExtractor<ClaimReportExcelDO> createStudentFieldExtractor() {
        BeanWrapperFieldExtractor<ClaimReportExcelDO> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"claimNumber", "contractId", "dealerName"});
        return extractor;
    }
} 