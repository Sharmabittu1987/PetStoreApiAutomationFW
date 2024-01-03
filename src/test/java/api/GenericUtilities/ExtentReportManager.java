package api.GenericUtilities;

import java.text.SimpleDateFormat;

import org.apache.poi.hpsf.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
	
/**
	 * This Class provides Implementation to ITestListener Interface of TestNG. 
	 * 
	 * Example for Abstraction.
	 * 
	 * @author Bittu Kumar Sharma
	 * 
	 */


	public class ExtentReportManager implements ITestListener {

		ExtentReports report;
		ExtentTest test;
		String repName;
		
		
		public void onTestStart(ITestResult result) {
			
			String methodName = result.getMethod().getMethodName();
			System.out.println("----- EXECUTION STARTED ----- "+methodName);
			System.out.println(" ");
			
			test = report.createTest(methodName);
			
		}

		public void onTestSuccess(ITestResult result) {
			
			String methodName = result.getMethod().getMethodName();
			
			System.out.println("----- PASS ----- "+methodName);
			System.out.println(" ");
			
			test.log(Status.PASS, "----- PASS ----- "+methodName);
		
		}

		public void onTestFailure(ITestResult result) {
			
			String methodName = result.getMethod().getMethodName();
			
			System.out.println("----- FAIL ----- "+methodName);
			System.out.println(" ");
			
			test.log(Status.FAIL, "----- FAIL ----- "+methodName);
			
			// System.out.println(result.getThrowable());
			
			test.log(Status.INFO, result.getThrowable());
			
		}

		public void onTestSkipped(ITestResult result) {
			
			String methodName = result.getMethod().getMethodName();
			
			System.out.println("----- SKIP ----- "+methodName);
			System.out.println(" ");
			
			test.log(Status.SKIP, "----- SKIP ----- "+methodName);
			
			//System.out.println(result.getThrowable());
			
			test.log(Status.INFO, result.getThrowable());
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
		}

		public void onTestFailedWithTimeout(ITestResult result) {
		}

		public void onStart(ITestContext context) {
			
			// Start of <Suite> - @BeforeSuite
			
			System.out.println("----- SUITE EXECUTION STARTED -----");
			System.out.println(" ");
			
			// Configure the Extent Report.
			
			//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName = "Test-Report -"+".html";
			
			ExtentSparkReporter htmlreport = new ExtentSparkReporter(".\\ExtentReports\\Report-"+repName);
			
			htmlreport.config().setDocumentTitle("RESTASSURED AUTOMATION PROJECT REPORT");
			htmlreport.config().setReportName("PETSTORE EXECUTION REPORT");
			htmlreport.config().setTheme(Theme.DARK);
			
			
			// Report Generation 
			
			report = new ExtentReports();
			report.attachReporter(htmlreport);
			report.setSystemInfo("Application", "Pet Store API");
			report.setSystemInfo("BASE PLATFORM", "TESTING ENVIRONMENT");
			report.setSystemInfo("BASE URL", "https://petstore.swagger.io");
			report.setSystemInfo("BASE OS", "WINDOWS");
			report.setSystemInfo("BASE REPORTER", "BITTU KUMAR SHARMA");
			
		}

		public void onFinish(ITestContext context) {
			// TODO Auto-generated method stub
			
			System.out.println("----- SUITE EXECUTION ENDED -----");
			System.out.println("******************************************************* ");
			
			
			// Report Generation.
			
			report.flush();
	}	
}
