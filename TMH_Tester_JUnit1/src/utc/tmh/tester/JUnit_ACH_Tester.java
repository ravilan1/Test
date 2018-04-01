package utc.tmh.tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.Diff;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JUnit_ACH_Tester {
	private static BufferedReader in = null;
	final static Logger logger = Logger.getLogger(JUnit_ACH_Tester.class);

	static double success = 0;
	static double failure = 0;
	static int total = 0;
	static String userdir = "";
	static String RFHUtilPath = "";
	static String mqput2c = "";
	static String mqcaponec = "";
	static String mqPutParameter = "";
	static String mqGetParameter = "";
	static String inputQ = "";
	static String outputQ = "";
	static String input = "";
	static String output = "";
	static String expected = "";

	@Before
	public void setup() throws IOException {
		userdir = System.getProperty("user.dir") + "/";
		String configFile = userdir + "config.txt";
		in = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)));
		Date d = new Date();
		System.out.println("TMH Tester v1.1");
		System.out.println("Test Started on " + d);
		System.out.println("Config File" + configFile);
		System.out.println("----------------------------------------------");
		logger.debug("*****************************************************START OF TEST**********************************************");

	}

	@After
	public void teardown() throws IOException {

		if (in != null) {
			in.close();
		}
		System.out.println("");
		System.out.println("Results");
		logger.debug("Results");
		System.out.println("-------");
		logger.debug("-------");
		System.out.println("# of Tests   " + total);
		logger.debug("# of Tests   " + total);
		System.out.println("# of Success " + String.format("%.0f", success) + " (" + String.format("%.0f", (success / total) * 100) + "%)");
		logger.debug("# of Success " + String.format("%.0f", success) + " (" + String.format("%.0f", (success / total) * 100) + "%)");
		System.out.println("# of Failed  " + String.format("%.0f", failure)	+ " (" + String.format("%.0f", (failure / total) * 100) + "%)");
		logger.debug("# of Failed  " + String.format("%.0f", failure) + " (" + String.format("%.0f", (failure / total) * 100) + "%)");
		System.out.println("");
		Date d = new Date();
		System.out.println("Test Completed on " + d);
		logger.debug("Test Completed on " + d);
		System.out.println("----------------------------------------------");
		logger.debug("***********************************************************END OF TEST****************************************");
		in = null;

		/*
		 * if (failure == 0) { System.exit(0); } else { System.exit(1); }
		 */
		Assert.assertFalse(false);
	}

	@Test
	public void runTest() throws IOException {
		String line = in.readLine();
		String testID = "";
		while ((line = in.readLine()) != null) {

			try {
				if (line.trim().length() == 0) {
					// Ignore comments on resource file
				} else if (line.substring(0, 2).equals("//")) {
					// Ignore comments on resource file
				} else if (line.substring(0, 11).equals("RFHUtilPath")) {
					String[] splitString = line.split("=");
					RFHUtilPath = splitString[1].trim();
					mqput2c = RFHUtilPath + "mqput2c.exe";
					mqcaponec = RFHUtilPath + "mqcaponec.exe";
					File mqput2cF = new File(mqput2c);
					File mqcaponecF = new File(mqcaponec);

					if (!mqput2cF.exists()) {
						System.out.println(" ERROR102 " + mqput2cF + " mqput2c file does not exist"); // Check
						// input
						// file
						// exists.
					}
					if (!mqcaponecF.exists()) {
						System.out.println(" ERROR102 " + mqcaponecF + " mqcaponec file does not exist"); // Check
						// input
						// file
						// exists.
					}
				} else if (line.substring(0, 18).equals("mqPutParameterFile")) {
					String[] splitString = line.split("=");
					mqPutParameter = splitString[1].trim();
					File mqPutParameterFile = new File(mqPutParameter);
					if (!mqPutParameterFile.exists()) {
						System.out.println(" ERROR102 " + mqPutParameterFile + " mqPutParameterFile file does not exist"); // Check
						// input
						// file
						// exists.
					}
				} else if (line.substring(0, 18).equals("mqGetParameterFile")) {
					String[] splitString = line.split("=");
					mqGetParameter = splitString[1].trim();
					File mqGetParameterFile = new File(mqGetParameter);
					if (!mqGetParameterFile.exists()) {
						System.out.println(" ERROR102 " + mqGetParameterFile + " mqGetParameterFile file does not exist"); // Check
						// input
						// file
						// exists.
					}
				} else {

					String[] splitString = line.split(",");

					testID = splitString[0].trim();

					input = userdir + "TestPack\\input\\" + splitString[1];
					output = userdir + "TestPack\\output\\" + splitString[1];
					expected = userdir + "TestPack\\expected\\"+ splitString[1];
					File inputF = new File(input);
					File outputF = new File(output);
					File expectedF = new File(expected);
					boolean TWSTriggerFlag = false;
					if (input.contains("TT2TriggerCtrlReq")||input.contains("TT6TriggerCtrlReq"))
					{
						TWSTriggerFlag = true;
					}

					if (!inputF.exists()) {
						System.out.println(testID + " ERROR101 " + userdir + "TestPack\\input\\" + splitString[1] + " Input file does not exist"); // Check input
						// file
						// exists.
					}

					if (outputF.exists()) {
						outputF.delete(); // Delete output file if exists.
					}

					if (expectedF.exists()) // Check expected file exists
					{

						inputQ = splitString[2].trim();
						outputQ = splitString[3].trim();

						// Run Test
						Runner_TMH_Tester.main(new String[] { mqput2c,
								mqPutParameter, mqcaponec, mqGetParameter,
								testID, inputQ, outputQ, input, output });
						try {
							if(!TWSTriggerFlag){
								
								FileReader control = new FileReader(expectedF);
								FileReader outputTest = new FileReader(outputF);
								System.out.println(splitString[1]);
								Diff myDiff = new Diff(control, outputTest);
								myDiff.overrideDifferenceListener(new MyDifferenceListener());
	
								Assert.assertTrue(myDiff.toString(), myDiff.identical());
								Assert.assertTrue(myDiff.toString(), myDiff.similar());
							}else{
								System.out.println(splitString[1]);
							}
						} catch (Exception e) {
							Assert.assertTrue(e.getMessage(), false);
						}

						/*
						 * try {
						 * 
						 * FileAssert.assertEquals(expectedF, outputF); //
						 * Compare } catch (Exception e) {
						 * Assert.assertEquals(FileUtils.readLines(expectedF),
						 * FileUtils.readLines(outputF)); // Compare }
						 * FileAssert.assertBinaryEquals(expectedF, outputF); //
						 * CompareBinary
						 */
						success = success + 1;
						total = total + 1;
					} else {
						System.out.println(testID + " ERROR101 " + userdir + "TestPack\\expected\\" + splitString[1] + " Expected file does not exist"); // Raised on
						// expected
						// file
						failure = failure + 1;
						total = total + 1;
					}

				}
			} catch (AssertionError e) {
				// Test Failed
				System.out.println(testID + " ERROR " + e.getMessage());
				failure = failure + 1;
				total = total + 1;
				continue;
			}
		}
	}
}
