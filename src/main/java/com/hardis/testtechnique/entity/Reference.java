package com.hardis.testtechnique.entity;

/**
 * This class contains the product information
 * 
 * @author fabien
 *
 */
public class Reference {
	/** reference number */
	private String numReference;
	/** product size */
	private Integer size;
	/** product price */
	private Double price;
	/** product color */
	private ColorAccepted type;

	/**
	 * Initialize the class with all variables
	 * 
	 * @param numReference
	 * 			reference number
	 * @param size
	 * 			product size
	 * @param price
	 * 			product price
	 * @param type
	 * 			product color
	 */
	public Reference(String numReference, Integer size, Double price, ColorAccepted type) {
		super();
		this.numReference = numReference;
		this.size = size;
		this.price = price;
		this.type = type;
	}

	/** Return the reference number */
	public String getNumReference() {
		return numReference;
	}

	/** Return the product size */
	public Integer getSize() {
		return size;
	}

	/** Return the product price */
	public Double getPrice() {
		return price;
	}

	/** Return the product color */
	public ColorAccepted getType() {
		return type;
	}
}