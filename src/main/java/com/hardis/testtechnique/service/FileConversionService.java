package com.hardis.testtechnique.service;

import java.util.List;

import com.hardis.testtechnique.entity.ConversionReport;

/**
 * Interface to describe the different methods to convert file content
 * 
 * @author fabien
 *
 */
public interface FileConversionService {

	/**
	 * Convert all lines and send back a report
	 * 
	 * @param fileName
	 * 			file name to be converted
	 * @param lines
	 * 			file content
	 * @return conversion report
	 */
	public ConversionReport convertFile(String fileName, List<String> lines);
}
