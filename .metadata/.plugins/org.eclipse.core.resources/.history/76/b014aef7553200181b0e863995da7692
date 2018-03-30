package utc.tmh.tester;

import java.io.*;

import org.apache.log4j.Logger;

public class Runner_TMH_Tester {

	public static void main(String[] args) throws IOException {

		final Logger logger = Logger.getLogger(Runner_TMH_Tester.class);

		if (args.length != 9) {
			// Inputs: ID, folderName, ruleJar, inputFile
			System.out.println("Invalid Input Parameter");
			System.exit(1);
		}

		String mqput2c = args[0];
		String mqPutParameter = args[1];
		String mqcaponec = args[2];
		String mqGetParameter = args[3];
		String testID = args[4];
		String inputQ = args[5];
		String outputQ = args[6];
		String input = args[7]; // Input File
		String output = args[8]; // Output File
		Boolean TWStriggerFlag = false;

		// Update mqPutParameter file with queue name and output file name
		try {
			File mqPutParameterF = new File(mqPutParameter);
			BufferedReader reader = new BufferedReader(new FileReader(
					mqPutParameterF));
			String line = "";
			String strText = "";
			while ((line = reader.readLine()) != null) {
				if (line.length() > 4) {
					if (line.substring(0, 5).equals("qname")) {
						line = "qname=" + inputQ;
					}
				}
				if (line.length() > 9) {
					if (line.substring(0, 10).equals("[filelist]")) {
						strText += line + "\r\n";
						line = reader.readLine();
						line = input;
					}
				}
				if (line.contains("TT2TriggerCtrlReq") == true
						|| line.contains("TT6TriggerCtrlReq") == true) {
					TWStriggerFlag = true;
					
				}
				strText += line + "\r\n";
			}

			reader.close();

			FileWriter writer = new FileWriter(mqPutParameterF);
			writer.write(strText);
			writer.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		// Update mqGetParameter file with queue name and output file name

		try {
			File mqGetParameterF = new File(mqGetParameter);
			BufferedReader reader = new BufferedReader(new FileReader(
					mqGetParameterF));
			String line = "";
			String strText = "";
			while ((line = reader.readLine()) != null) {
				if (line.length() > 4) {
					if (line.substring(0, 5).equals("qname")) {
						line = "qname=" + outputQ;
					}
				}
				if (line.length() > 13) {
					if (line.substring(0, 14).equals("outputFilename")) {
						line = "outputFilename=" + output;
					}
				}
				strText += line + "\r\n";
			}
			reader.close();

			FileWriter writer = new FileWriter(mqGetParameterF);
			writer.write(strText);
			writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		// --------

		String command = mqput2c + " -f \"" + mqPutParameter + "\""; // mqput2c
		// -f
		// mqPutParameter
		Runtime r = Runtime.getRuntime(); // A Runtime object has methods
		// for
		// dealing with the OS
		Process p; // Process tracks one external native process
		BufferedReader is = null; // reader for output of process for input
		// stream
		String line; // can be used to debug

		p = r.exec(command); // Command to Run
		if (!TWStriggerFlag) {
			is = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((line = is.readLine()) != null)
				// can be used to debug
				logger.debug(line); // can be used to debug

			System.out.flush();
			try {
				p.waitFor(); // wait for process to complete
			} catch (InterruptedException e) {
				System.out.println(e);
				System.out.println("Exit code: " + p.exitValue() + " testID "
						+ testID);
				return;
			}

			// --------
			String command2 = mqcaponec + " -f \"" + mqGetParameter
					+ "\" -o \"" + output + "\""; // mqcaponec -f mqGetParameter
			// -o output
			Runtime r2 = Runtime.getRuntime(); // A Runtime object has methods
			// for
			// dealing with the OS
			Process p2; // Process tracks one external native process
			BufferedReader is2; // reader for output of process for input stream
			String line2; // can be used to debug
			p2 = r2.exec(command2); // Command to Run
			is2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));

			while ((line2 = is2.readLine()) != null)
				// can be used to debug
				logger.debug(line2); // can be used to debug

			System.out.flush();
			try {
				p2.waitFor(); // wait for process to complete
			} catch (InterruptedException e) {
				System.out.println(e);
				System.out.println("Exit code: " + p.exitValue() + " testID "
						+ testID);
				return;
			}
		}
		return;
	}
}