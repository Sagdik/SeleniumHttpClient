package qa.cimb.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

public class cimbBase {

public static Properties pro;
SoftAssert softAssert;

/**
 * Constructor to load property file 
 */
	public  cimbBase() {

		try {
			
			pro = new Properties();
			File file = new File(
					System.getProperty("user.dir") + "./config.properties");
			FileInputStream fileinput = new FileInputStream(file);
			pro.load(fileinput);

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
