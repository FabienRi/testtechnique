package com.hardis.testtechnique.entity;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * This class contains the conversion report
 * 
 * @author fabien
 *
 */
@JacksonXmlRootElement(localName = "report")
public class ConversionReport {
	/** converted file name */
	private String inputFile;

	/** references converted */
	@JacksonXmlElementWrapper(localName = "references")
	@JacksonXmlProperty(localName = "reference")
	private List<Reference> references;

	/** conversion errors */
	@JacksonXmlElementWrapper(localName = "errors")
	@JacksonXmlProperty(localName = "error")
	@JsonProperty("errors")
	private List<ConversionError> conversionErrors;

	/**
	 * Initialize the class with converted file name
	 * 
	 * @param inputFile
	 * 			converted file name
	 */
	public ConversionReport(String inputFile) {
		super();
		this.inputFile = inputFile;
		this.references = new LinkedList<>();
		this.conversionErrors = new LinkedList<>();
	}

	/** Return the converted file name */
	public String getInputFile() {
		return inputFile;
	}

	/** Return the references converted */
	public List<Reference> getReferences() {
		return references;
	}

	/** Return the conversion errors */
	public List<ConversionError> getConversionErrors() {
		return conversionErrors;
	}
}