package com.hardis.testtechnique.entity;

/**
 * This class contains the conversion error detail
 * 
 * @author fabien
 *
 */
public class ConversionError {
		/** line number where the error occurs */
        private Integer line;
        /** description of the error */
        private String message;
        /** value which generate the error */
        private String value;

        /** Initialize the class with all variables */
        public ConversionError(Integer line, String message, String value) {
                super();
                this.line = line;
                this.message = message;
                this.value = value;
        }

		/** Return the line number where the error occurs */
        public Integer getLine() {
                return line;
        }
        /** Return the description of the error */
        public String getMessage() {
                return message;
        }
        /** Return the value which generate the error */
        public String getValue() {
                return value;
        }
}