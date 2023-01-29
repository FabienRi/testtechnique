package com.hardis.testtechnique.service;

import java.io.IOException;
import java.util.List;

/**
 * Interface to describe the different methods to manage a file
 * 
 * @author fabien
 *
 */
public interface FileManagementService {

	/**
	 * Read all lines of a file
	 * 
	 * @param sourcePath
	 * 			the full file path to read
	 * @return all lines
	 * @throws  IOException if an I/O error occurs
	 */
	public List<String> readFile(String sourcePath) throws IOException;
	
	/**
	 * Get file name
	 * 
	 * @param filePath
	 * 			the full file path
	 * @return file name
	 */
	public String getFileName(String filePath);

	/**
	 * Write all lines in a file
	 * 
	 * @param destinationPath
	 * 			the full file path to write
	 * @param content
	 * 			the content to write
	 * @throws  IOException if an I/O error occurs
	 */
	public void writeFile(String destinationPath, String content) throws IOException ;

}
