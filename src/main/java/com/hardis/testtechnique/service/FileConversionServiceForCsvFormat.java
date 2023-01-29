package com.hardis.testtechnique.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.hardis.testtechnique.entity.ColorAccepted;
import com.hardis.testtechnique.entity.ConversionError;
import com.hardis.testtechnique.entity.ConversionReport;
import com.hardis.testtechnique.entity.Reference;

/**
 * File conversion implementation for file on CSV format
 * 
 * @author fabien
 *
 */
@Service
public class FileConversionServiceForCsvFormat implements FileConversionService{

	@Override
	public ConversionReport convertFile(String fileName, List<String> lines) {
		ConversionReport report = new ConversionReport(fileName);
		if (lines != null && !lines.isEmpty()) {
			IntStream.range(0, lines.size()).forEach(lineNumber -> {
				try {
					report.getReferences().add(csvToReference(lines.get(lineNumber), ";"));
				} catch (RuntimeException e) {
					report.getConversionErrors().add(new ConversionError(lineNumber+1, e.getMessage(), lines.get(lineNumber)));
				}
			});
		}
		return report;
	}

	/*
	 * Method to make the data control before to send back a Reference
	 */
	private Reference csvToReference(String text, String separator) throws RuntimeException {
		String[] values = text.split(separator);
		if (values != null) {
			switch (values.length) {
			case 4: {
				return new Reference(values[0], Integer.valueOf(values[3]), Double.valueOf(values[2]), getColorAccepted(values[1]));			}
			}
		}
		throw new RuntimeException("Conversion not implemented");
	}

	/*
	 * Method to get the color
	 */
	private ColorAccepted getColorAccepted(String value) throws RuntimeException  {
		try {
			return ColorAccepted.valueOf(value);
		} catch (Exception e) {
			throw new RuntimeException("Incorrect value for color");
		}
	}
}
