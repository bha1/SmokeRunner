package com.test.core;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.smoke.core.StreamMachine;


public class StreamMachineTest {
	
	private final static String THIS_COMPONENT_NAME = StreamMachineTest.class.getName();
	
	private final static Logger LOGGER = Logger.getLogger(THIS_COMPONENT_NAME);

	private static StreamMachine streamMachine;
	
	@BeforeClass
	public static void initStreamMachine(){
		LOGGER.info("Junit for StreamMachine");
		streamMachine = new StreamMachine();
	}
	
	@Before
	public void beforeEachTest(){
		LOGGER.info("This is test case begining");
	}
	
	@After
	public void afterEachTest(){
		LOGGER.info("This is test case end");
	}
	
	@Test
	public void testGenerateTemplatedocument(){
		streamMachine.getTemplateFile();
	}
}
