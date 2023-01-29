package com.hardis.testtechnique;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.hardis.testtechnique.entity.ConversionReport;
import com.hardis.testtechnique.service.FileConversionService;
import com.hardis.testtechnique.service.FileManagementService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class HardisApplication implements CommandLineRunner {

	@Autowired
	FileManagementService fileManagementService;
	
	@Autowired
	FileConversionService fileConversionService;

	/**
	 * Program to convert a text file into Reference and send back a conversion report
	 * @param args
	 * 		[0] full input file path
	 * 		[1] output format (J for Json, X for Xml)
	 * 		[2] full output file path
	 * @throws Exception
	 * 
	 * example: java -jar hardis.testtechnique.jar C:\Users\fabien\temp\test.txt J C:\Users\fabien\temp\testoutaa.txt
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(HardisApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws IOException {
		if (args == null || args.length != 3) {
			throw new IllegalArgumentException("3 arguments are expected");
		}
		switch (args[1]) {
		case "J": {
			getConversionJsonReport(args[0], args[2]);
			break;
		}
		case "X": {
			getConversionXmlReport(args[0], args[2]);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + args[1]);
		}
		System.out.printf("Conversion done into file %s", args[2]);
	}

	/*
	 * Method to convert file content into Reference and send back a JSON conversion report
	 *  
	 * @param sourcePath
	 * 			full source file path
	 * @param destinationPath
	 * 			full destination file path
	 * @throws IOException
	 */
	private void getConversionJsonReport(String sourcePath, String destinationPath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		fileManagementService.writeFile(destinationPath, mapper.writeValueAsString(getConversionReport(sourcePath)));
	}

	/*
	 * Method to convert file content into Reference and send back a XML conversion report
	 *  
	 * @param sourcePath
	 * 			full source file path
	 * @param destinationPath
	 * 			full destination file path
	 * @throws IOException
	 */
	private void getConversionXmlReport(String sourcePath, String destinationPath) throws IOException {
		XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
		fileManagementService.writeFile(destinationPath, mapper.writeValueAsString(getConversionReport(sourcePath)));
	}
	
	/*
	 * Method to convert file content into ConversionReport
	 *  
	 * @param sourcePath
	 * 			full source file path
	 * @throws IOException
	 */
	private ConversionReport getConversionReport(String sourcePath) throws IOException {
		return fileConversionService.convertFile(fileManagementService.getFileName(sourcePath), 
				fileManagementService.readFile(sourcePath));
	}

}
