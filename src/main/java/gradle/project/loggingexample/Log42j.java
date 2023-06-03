package gradle.project.loggingexample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains method to Check the class Logs.
 *
 * @author Khan Tarique
 */
public class Log42j {

	private static Logger demoLogger = LogManager.getLogger(Log42j.class.getName());

	/**
	 * Launches the application
	 *
	 * @param args For main method
	 */
	public static void main(String[] args) {

		demoLogger.info("Click Succesfull");
		demoLogger.error("DB Connection Failed");
		demoLogger.info("Click Succesfull");
		demoLogger.error("DB Connection Failed");
		demoLogger.debug("This Debug");
		demoLogger.fatal("This is Fatal");
		demoLogger.info("Click Succesfull");
		demoLogger.error("DB Connection Failed");
		demoLogger.info("Click Succesfull");
		demoLogger.error("DB Connection Failed");
		demoLogger.debug("This Debug");
		demoLogger.fatal("This is Fatal");
		demoLogger.info("Click Succesfull");
		demoLogger.error("DB Connection Failed");
		demoLogger.info("Click Succesfull");
		demoLogger.error("DB Connection Failed");
		demoLogger.debug("This Debug");
		demoLogger.fatal("This is Fatal");
	}
}
