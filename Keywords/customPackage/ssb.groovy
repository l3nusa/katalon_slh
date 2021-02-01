package customPackage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.SimpleDateFormat as SimpleDateFormat
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.util.Date as Date
import java.util.Calendar as Calendar
import com.kms.katalon.core.testobject.ConditionType

public class ssb {
	@Keyword
	def getCheckoutDate() {
		//Choose Friday, January 22, 2021 as your check-in date. It’s available.
		String checkout = WebUI.getAttribute(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'), 'aria-label')
		checkout = checkout.substring(7, checkout.indexOf("as")-1);
		SimpleDateFormat sdf = new SimpleDateFormat('EEEE, MMMM dd, yyyy')
		return sdf.parse(checkout);
	}

	@Keyword
	def getCheckinDate() {
		String checkin = WebUI.getAttribute(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'), 'aria-label')
		SimpleDateFormat sdf = new SimpleDateFormat('EEEE, MMMM dd, yyyy')
		return sdf.parse(checkin);
	}

	@Keyword
	def getDatePickerObjectByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat('MMMM d, yyyy')
		def day = sdf.format(date).toString()
		String xpath = '//table[@role=\'presentation\']//td[contains(@aria-label,\'' + day + '\')]'
		return new TestObject().addProperty('xpath', ConditionType.EQUALS, xpath)
	}
}
