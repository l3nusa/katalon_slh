package customPackage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.Date

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import org.openqa.selenium.Keys as Keys


public class commonUtils {
	@Keyword
	def static clearElementText(TestObject element) {
		//WebUI.executeJavaScript("arguments[0].value=''", Arrays.asList(element))
		WebUI.click(element)
		WebUI.sendKeys(element, Keys.chord(Keys.CONTROL, "a"))
		WebUI.sendKeys(element, Keys.chord(Keys.DELETE/*.BACK_SPACE*/))
	}

	@Keyword
	def verifyDestinationPageUrl(String currentUrl, String expUrl){
		if (!currentUrl.contains(expUrl)) {
			throw new Exception('Current page URL doesn\'t match to the expected one! ', currentUrl)
		}
	}
	
	@Keyword
	def verifyAppendedGuestsAmount(String currentUrl, int adults, int children){
		if (!currentUrl.contains('adults=' + adults) || !currentUrl.contains('children=' + children)) {
			throw new Exception('[Adults] or [Children] value in current URL doesn\'t match the expected! ', currentUrl)
		}
	}
	
	@Keyword
	def verifyAppendedDates(String currentUrl, String checkin, String checkout){
		String checkinStr = 'startDate='
		String checkoutStr = 'endDate='

		if (checkin){
			checkinStr = checkinStr + checkin
			if (!currentUrl.contains(checkinStr)) {
				throw new Exception('[checkin] value in current URL doesn\'t match the expected! ', currentUrl)
			}
		}
		else {
			if(currentUrl.contains(checkinStr)) {
				throw new Exception('[startDate] value is present in current URL although is shouldn\'t! ' + currentUrl)
			}								
		}

		if (checkout){
			checkoutStr = checkoutStr + checkout
			if (!currentUrl.contains(checkoutStr)) {
				throw new Exception('[checkout] value in current URL doesn\'t match the expected! ', currentUrl)
			}
		}
		else {
			if(currentUrl.contains(checkoutStr)) {
				throw new Exception('[endDate] value is present in current URL although is shouldn\'t! ' + currentUrl)
			}			
		}
	}
		
	@Keyword
	def verifyAppendedQuery(String currentUrl, String query="", String city=null, String country=null, String regions=null){
		if (!currentUrl.contains('query=' + query)) {
			throw new Exception('[query] value in current URL doesn\'t match the expected! ', currentUrl)
		}
	
		if (city) {
			if (!currentUrl.contains('city=' + city)) {
				throw new Exception('[city] value in current URL doesn\'t match the expected! ', currentUrl)
			}
		}
	
		if (country) {
			if (!currentUrl.contains('country=' + country)) {
				throw new Exception('[country] value in current URL doesn\'t match the expected! ', currentUrl)
			}
		}
	
		if (regions) {
			if (!currentUrl.contains('regions=' + regions)) {
				throw new Exception('[regions] value in current URL doesn\'t match the expected! ', currentUrl)
			}
		}
	}


	@Keyword
	def verifyAppendedCurrency(String currentUrl, String currency){
		if (!currentUrl.contains('currency=' + currency)) {
			throw new Exception('[Currency] value in current URL doesn\'t match the expected [' + currency + '] :' + currentUrl)
		}
	}
		
}
