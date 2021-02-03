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

	@Keyword
	def setAdultsAmount(int expAdultsAmount){
		int curAdultsAmount = Integer.parseInt(WebUI.getText(findTestObject('SearchSummaryBar/Guests_Adults_Amount')))

		int i = 0
		if ((expAdultsAmount < curAdultsAmount) && (expAdultsAmount >= GlobalVariable.SSB_AdultsMin)) {
			while (i < curAdultsAmount - expAdultsAmount) {
				WebUI.click(findTestObject('SearchSummaryBar/Guests_Adults_Minus'), FailureHandling.STOP_ON_FAILURE)
				i++
				WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), (curAdultsAmount - 1) + GlobalVariable.SSB_GuestsText)
				WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests_Adults_Amount'), (curAdultsAmount - i).toString())
			}
			return curAdultsAmount - i
		}
		else {
			while (i < expAdultsAmount - curAdultsAmount) {
				WebUI.click(findTestObject('SearchSummaryBar/Guests_Adults_Plus'), FailureHandling.STOP_ON_FAILURE)
				i++
				WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests_Adults_Amount'), (curAdultsAmount + i).toString())
				WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), (curAdultsAmount + i) + GlobalVariable.SSB_GuestsText)
			}
			return curAdultsAmount + i
		}

		if ((expAdultsAmount < GlobalVariable.SSB_AdultsMin) || (expAdultsAmount > GlobalVariable.SSB_AdultsMax)){
			throw new Exception('Please check expected Adults value!')
		}
	}

	@Keyword
	def setChildrenAmount(int expChildrenAmount, int guestsAmount = GlobalVariable.SSB_GuestsAmount){
		int curChildrenAmount = Integer.parseInt(WebUI.getText(findTestObject('SearchSummaryBar/Guests_Children_Amount')))

		int i = 0
		if ((expChildrenAmount < curChildrenAmount) && (expChildrenAmount >= GlobalVariable.SSB_ChildrenMin)){
			while (i < curChildrenAmount - expChildrenAmount) {
				WebUI.click(findTestObject('SearchSummaryBar/Guests_Children_Minus'), FailureHandling.STOP_ON_FAILURE)
				i++
				WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests_Children_Amount'), (curChildrenAmount - i).toString())
				WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), (curChildrenAmount - i + guestsAmount) + GlobalVariable.SSB_GuestsText)
			}
			return curChildrenAmount - i
		}
		else {
			while (i < expChildrenAmount - curChildrenAmount) {
				WebUI.click(findTestObject('SearchSummaryBar/Guests_Children_Plus'), FailureHandling.STOP_ON_FAILURE)
				i++
				WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests_Children_Amount'), (curChildrenAmount + i).toString())
				WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), (curChildrenAmount + i + guestsAmount) + GlobalVariable.SSB_GuestsText)
			}
			return curChildrenAmount + i
		}

		if ((expChildrenAmount < GlobalVariable.SSB_ChildrenMin) || (expChildrenAmount > GlobalVariable.SSB_ChildrenMax)){
			throw new Exception('Please check expected Children value!')
		}
	}

	@Keyword
	def verifyAppendedGuestsAmount(String currentUrl, int adults, int children){
		if (!currentUrl.contains('adults=' + adults) || !currentUrl.contains('children=' + children)) {
			throw new Exception('[Adults] or [Children] value in current URL doesn\'t match the expected! ', currentUrl)
		}
	}

	@Keyword
	def verifyAppendedDates(String currentUrl, String checkin=null, String checkout=null){
		String checkinStr = 'startDate='
		String checkoutStr = 'endDate='

		if (checkin){
			checkinStr = checkinStr + checkin
		}
		if (checkout){
			checkoutStr = checkoutStr + checkout
		}

		if (!currentUrl.contains(checkinStr) || !currentUrl.contains(checkoutStr)) {
			throw new Exception('[checkin] or [checkout] value in current URL doesn\'t match the expected! ', currentUrl)
		}
	}
	
	@Keyword
	def verifyAppendedBookingDates(String currentUrl, Date checkin, Date checkout ){
		String checkinStr = 'startDate='
		String checkoutStr = 'endDate='

		if (!(checkin && checkout)) {
			if (currentUrl.contains(checkinStr) || currentUrl.contains(checkoutStr)) {
				throw new Exception('[checkin] or [checkout] value is present in current URL! ', currentUrl)
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
	def verifyDestinationPageUrl(String currentUrl, String expUrl){
		if (!currentUrl.contains(expUrl)) {
			throw new Exception('Current page URL doesn\'t match to the expected one! ', currentUrl)
		}
	}
}
