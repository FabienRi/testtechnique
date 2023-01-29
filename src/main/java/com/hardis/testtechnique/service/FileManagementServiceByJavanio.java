package com.hardis.testtechnique.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * File management implementation by using java.nio classes
 * 
 * @author fabien
 *
 */
@Service
public class FileManagementServiceByJavanio implements FileManagementService {

	@Override
	public List<String> readFile(String sourcePath) throws IOException {
		return Files.readAllLines(Paths.get(sourcePath));
	}

	@Override
	public String getFileName(String filePath) {
		return Paths.get(filePath).getFileName().toString();
	}

	@Override
	public void writeFile(String destinationPath, String content) throws IOException {
		Files.write(Files.createFile(Paths.get(destinationPath)), content.getBytes(), StandardOpenOption.WRITE).toString();
	}

}
