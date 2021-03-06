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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import java.text.SimpleDateFormat as SimpleDateFormat
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.util.Date as Date
import java.util.Calendar as Calendar
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.logging.KeywordLogger

public class ssb {
	@Keyword
	def getActiveCheckoutDate() {
		//Choose Friday, January 22, 2021 as your check-in date. It’s available.
		String checkout = WebUI.getAttribute(findTestObject('SharedComponent/SSB/Datepicker/FirstActiveDay'), 'aria-label')
		checkout = checkout.substring(7, checkout.indexOf("as")-1);
		SimpleDateFormat sdf = new SimpleDateFormat('EEEE, MMMM dd, yyyy')
		return sdf.parse(checkout);
	}

	@Keyword
	def getActiveCheckinDate() {
		String checkin = WebUI.getAttribute(findTestObject('SharedComponent/SSB/Datepicker/FirstActiveDay'), 'aria-label').replaceAll('Selected. ','')
		SimpleDateFormat sdf = new SimpleDateFormat('EEEE, MMMM dd, yyyy')
		return sdf.parse(checkin);
	}

	@Keyword
	def getDatePickerObjectByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat('MMMM d, yyyy')
		String xpath = '//table[@role=\'presentation\']//td[contains(@aria-label,\'' + sdf.format(date).toString() + '\')]'
		return new TestObject().addProperty('xpath', ConditionType.EQUALS, xpath)
	}

	@Keyword
	def verifyDatePickerPriceByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat('MMMM d, yyyy')
		String xpath = '//table[@role=\'presentation\']//td[contains(@aria-label,\'' + sdf.format(date).toString() + '\')]//div[@class=\'apps-datepicker__text\']'
		return new TestObject().addProperty('xpath', ConditionType.EQUALS, xpath)
	}

	@Keyword
	def setAdultsAmount(int expAdultsAmount){
		int curAdultsAmount = Integer.parseInt(WebUI.getText(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_AdultsAmount')))
		int curChildrenAmount = Integer.parseInt(WebUI.getText(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_ChildrenAmount')))

		int i = 0
		if ((expAdultsAmount < curAdultsAmount) && (expAdultsAmount >= GlobalVariable.SSB_AdultsMin)) {
			while (i < curAdultsAmount - expAdultsAmount) {
				WebUI.click(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_Adults_Minus'), FailureHandling.STOP_ON_FAILURE)
				i++
				WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Guests'), (curAdultsAmount + curChildrenAmount - i) + GlobalVariable.SSB_GuestsText)
				WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_AdultsAmount'), (curAdultsAmount - i).toString())
			}
		}
		else {
			while (i < expAdultsAmount - curAdultsAmount) {
				WebUI.click(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_Adults_Plus'), FailureHandling.STOP_ON_FAILURE)
				i++
				WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Guests'), (curAdultsAmount + curChildrenAmount + i) + GlobalVariable.SSB_GuestsText)
				WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_AdultsAmount'), (curAdultsAmount + i).toString())
			}
		}

		if ((expAdultsAmount < GlobalVariable.SSB_AdultsMin) || (expAdultsAmount > GlobalVariable.SSB_AdultsMax)){
			throw new Exception('Please check expected Adults value!')
		}
	}

	@Keyword
	def setChildrenAmount(int expChildrenAmount){
		int curAdultsAmount = Integer.parseInt(WebUI.getText(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_AdultsAmount')))
		int curChildrenAmount = Integer.parseInt(WebUI.getText(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_ChildrenAmount')))

		int i = 0
		if ((expChildrenAmount < curChildrenAmount) && (expChildrenAmount >= GlobalVariable.SSB_ChildrenMin)){
			while (i < curChildrenAmount - expChildrenAmount) {
				WebUI.click(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_Children_Minus'), FailureHandling.STOP_ON_FAILURE)
				i++
				WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Guests'), (curAdultsAmount + curChildrenAmount - i) + GlobalVariable.SSB_GuestsText)
				WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_ChildrenAmount'), (curChildrenAmount - i).toString())
			}
		}
		else {
			while (i < expChildrenAmount - curChildrenAmount) {
				WebUI.click(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_Children_Plus'), FailureHandling.STOP_ON_FAILURE)
				i++
				WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Guests'), (curAdultsAmount + curChildrenAmount + i) + GlobalVariable.SSB_GuestsText)
				WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Guests_dropdown/Guests_ChildrenAmount'), (curChildrenAmount + i).toString())
			}
		}

		if ((expChildrenAmount < GlobalVariable.SSB_ChildrenMin) || (expChildrenAmount > GlobalVariable.SSB_ChildrenMax)){
			throw new Exception('Please check expected Children value!')
		}
	}
}
