import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import java.text.SimpleDateFormat as SimpleDateFormat
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.util.Date as Date
import java.util.Calendar as Calendar

WebUI.callTestCase(findTestCase('SSB_SearchSummaryBar/01_Homepage/SSB_010502_FullSearch_ManualEntry'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('SearchSummaryBar/SearchBtn'))

WebUI.click(findTestObject('SearchSummaryBar/Location'))

WebUI.setText(findTestObject('SearchSummaryBar/Location'), 'pet')

WebUI.click(findTestObject('SearchSummaryBar/AutosuggestOptions'))

WebUI.click(findTestObject('SearchSummaryBar/Checkin'))

Date checkin = CustomKeywords.'customPackage.ssb.getCheckinDate'() + CheckinOffset

WebUI.click(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'))

WebUI.click(findTestObject('SearchSummaryBar/Checkout'))

Date checkout = checkin + StayPeriod

WebUI.click(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'))

WebUI.click(findTestObject('SearchSummaryBar/Guests'))

int i = 0
while (i < AddAdults){
	WebUI.click(findTestObject('SearchSummaryBar/Guests_Adults_Minus'), FailureHandling.STOP_ON_FAILURE)
	i++			
}

i = AddAdults + 
while (i < GlobalVariable.SSB_ChildrenMin) {
    WebUI.click(findTestObject('SearchSummaryBar/Guests_Children_Plus'), FailureHandling.STOP_ON_FAILURE)
	i++    
}

WebUI.click(findTestObject('SearchSummaryBar/Location'))

WebUI.click(findTestObject('SearchSummaryBar/SearchBtn'))

currentUrl = WebUI.getUrl()

if (!(currentUrl.endsWith(((('/explore-hotels?adults=1&children=2&sort=ascName&pageIndex=0&resultsPerPage=10&viewType=list&startDate=' + 
    checkin.format('yyyy-MM-dd')) + '&endDate=') + checkout.format('yyyy-MM-dd')) + '&query=alex'))) {
    throw new Exception('Current URL doesn\'t match the expected : ', currentUrl)
}

WebUI.verifyElementAttributeValue(findTestObject('SearchSummaryBar/Location'), 'value', 'alex', 0)

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Checkin'), checkin.format('dd MMMM'))

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Checkout'), checkout.format('dd MMMM'))

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), ((GlobalVariable.SSB_GuestsTextTextText_Amount - 1) + i) + GlobalVariable.SSB_GuestsTextTextText)

WebUI.closeBrowser()

