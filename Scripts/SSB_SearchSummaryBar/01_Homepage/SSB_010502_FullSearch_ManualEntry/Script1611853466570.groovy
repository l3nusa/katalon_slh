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


WebUI.openBrowser(GlobalVariable.baseURL)

WebUI.maximizeWindow()

WebUI.click(findTestObject('SearchSummaryBar/Location'))

WebUI.setText(findTestObject('SearchSummaryBar/Location'), 'lon')

WebUI.click(findTestObject('SearchSummaryBar/AutosuggestOptions'))

WebUI.click(findTestObject('SearchSummaryBar/Checkin'))

Date checkin = CustomKeywords.'customPackage.ssb.getCheckinDate'()

WebUI.click(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'))

WebUI.click(findTestObject('SearchSummaryBar/Checkout'))

Date checkout = CustomKeywords.'customPackage.ssb.getCheckoutDate'()

WebUI.click(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'))

WebUI.click(findTestObject('SearchSummaryBar/Guests'))

int i = 0
while (i < GlobalVariable.SSB_AdultsMin){
	WebUI.click(findTestObject('SearchSummaryBar/Guests_Adults_Min'), FailureHandling.STOP_ON_FAILURE)
	i++			
}

i = 0
while (i < AddChild) {
    WebUI.click(findTestObject('SearchSummaryBar/Guests_Children_Plus'), FailureHandling.STOP_ON_FAILURE)
	i++    
}

WebUI.click(findTestObject('SearchSummaryBar/Location'))

WebUI.click(findTestObject('SearchSummaryBar/SearchBtn'))

currentUrl = WebUI.getUrl()

if (!(currentUrl.endsWith(((('/explore-hotels?query=Barcelona%2C+Spain&city=Barcelona&country=Spain&regions=Barcelona&startDate=' + 
    checkin.format('dd+MMM+yyyy')) + '&endDate=') + checkout.format('dd+MMM+yyyy')) + '&adults=' + GlobalVariable.SSB_AdultsMin + '&children=' + AddChild))) {
    throw new Exception('Current URL doesn\'t match the expected : ', currentUrl)
}

WebUI.verifyElementAttributeValue(findTestObject('SearchSummaryBar/Location'), 'value', 'Barcelona, Spain', 0)

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Checkin'), checkin.format('d MMMM'))

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Checkout'), checkout.format('d MMMM'))

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), (GlobalVariable.SSB_AdultsMin + AddChild) + GlobalVariable.SSB_Guests)

WebUI.closeBrowser()

