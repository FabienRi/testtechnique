package com.hardis.testtechnique;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HardisApplication.class})
class RunApplicationIntegrationTest {
	@Autowired
    HardisApplication hardisApplication;
    
	static final ClassLoader classLoader = RunApplicationIntegrationTest.class.getClassLoader();

	static final String inputPathFile = classLoader.getResource("inputOk.txt").getPath();
	static final String outputPathFile = "outputFile.txt";

    /**
     * Test in case of wrong arguments
     */
    @Test
    void whenWrongArguments() {
    	IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		hardisApplication.run(inputPathFile, "K", outputPathFile);
    	});
    	Assertions.assertEquals("Unexpected value: K", thrown.getMessage());

    	thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		hardisApplication.run(inputPathFile);
    	});
    	Assertions.assertEquals("3 arguments are expected", thrown.getMessage());
    }
}