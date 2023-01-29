package com.hardis.testtechnique;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.hardis.testtechnique.entity.ConversionReport;
import com.hardis.testtechnique.service.FileConversionService;

@ContextConfiguration(classes = { TestConfig.class })
@SpringBootTest
class FileConversionServiceTest {
	@Autowired
	FileConversionService fileConversionService;

	static final String fileName = "input file name";
	static final ClassLoader classLoader = FileConversionServiceTest.class.getClassLoader();
	static final List<String> contentOk = new ArrayList<>(IOUtil.readLines(classLoader.getResourceAsStream("inputOk.txt")));
	static final List<String> contentWrongColor = new ArrayList<>(IOUtil.readLines(classLoader.getResourceAsStream("inputWrongColor.txt")));
	static final List<String> contentWrongFormat = new ArrayList<>(IOUtil.readLines(classLoader.getResourceAsStream("inputWrongFormat.txt")));;

	/**
	 * Test in case everything work fine
	 */
	@Test
	void testConvertOk() {
		ConversionReport conversionReport = fileConversionService.convertFile(fileName, contentOk);
		assertNotNull(conversionReport, "return should not be null");
		assertEquals(fileName, conversionReport.getInputFile(), "return should have '" + fileName + "' in inputFile");
		assertEquals(0, conversionReport.getConversionErrors().size(), "return should have no error");
		assertEquals(contentOk.size(), conversionReport.getReferences().size(), "return should have " + contentOk.size() + " reference");
	}

	/**
	 * Test in case of no line
	 */
	@Test
	void testConvertEmpty() {
		ConversionReport conversionReport = fileConversionService.convertFile(fileName, null);
		assertNotNull(conversionReport, "return should not be null");
		assertEquals(fileName, conversionReport.getInputFile(), "return should have '" + fileName + "' in inputFile");
		assertEquals(0, conversionReport.getConversionErrors().size(), "return should have no error");
		assertEquals(0, conversionReport.getReferences().size(), "return should have no reference");
	}

	/**
	 * Test in case of wrong color
	 */
	@Test
	void testConvertWrongColor() {
		ConversionReport conversionReport = fileConversionService.convertFile(fileName, contentWrongColor);
		assertNotNull(conversionReport, "return should not be null");
		assertEquals(fileName, conversionReport.getInputFile(), "return should have '" + fileName + "' in inputFile");
		assertNotEquals(0, conversionReport.getConversionErrors().size(), "return should have error");
		assertEquals(contentWrongColor.size(), conversionReport.getReferences().size() + conversionReport.getConversionErrors().size(), "return should have " + contentWrongColor.size() + " reference and error");
	}

	/**
	 * Test in case of wrong format
	 */
	@Test
	void testConvertWrongFormat() {
		ConversionReport conversionReport = fileConversionService.convertFile(fileName, contentWrongFormat);
		assertNotNull(conversionReport, "return should not be null");
		assertEquals(fileName, conversionReport.getInputFile(), "return should have '" + fileName + "' in inputFile");
		assertNotEquals(0, conversionReport.getConversionErrors().size(), "return should have error");
		assertEquals(contentWrongFormat.size(), conversionReport.getReferences().size() + conversionReport.getConversionErrors().size(), "return should have " + contentWrongFormat.size() + " reference and error");
	}
}
