package qa.cimb.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomeListner implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("TEST START NOW "+"\n\r "+result);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("TEST END SUCCESSFULLY "+"\n\r "+result);
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(" TEST FAIL "+"\n\r "+ result);
	}
	

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(" YOUR TEST SKIPPED PLEASE TRY AGAIN "+"\n\r "+result);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("YOUR TEST FAIL BUT WITH THE IN SUCCESS PRECENTAGE "+"\n\r "+result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	

}
