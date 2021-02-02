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

WebUI.openBrowser(GlobalVariable.baseURL)

WebUI.maximizeWindow()

WebUI.click(findTestObject('SearchSummaryBar/Location'))

WebUI.setText(findTestObject('SearchSummaryBar/Location'), 'petit')

WebUI.click(findTestObject('SearchSummaryBar/AutosuggestOptions'))

WebUI.click(findTestObject('SearchSummaryBar/SearchBtn'))

currentUrl = WebUI.getUrl()

if (!(currentUrl.endsWith((('/explore-hotels?query=Petit+St.+Vincent+Island%2C+Saint+Vincent+and+the+Grenadines&city=Petit+St.+Vincent+Island&country=Saint+Vincent+and+the+Grenadines&startDate=&endDate=&adults=' + 
    GlobalVariable.SSB_GuestsAmount) + '&children=') + GlobalVariable.SSB_ChildrenMin))) {
    throw new Exception('Current URL doesn\'t match the expected : ', currentUrl)
}

WebUI.verifyElementAttributeValue(findTestObject('SearchSummaryBar/Location'), 'value', 'Petit St. Vincent Island, Saint Vincent and the Grenadines', 
    0)

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Checkin'), GlobalVariable.SSB_Datepicker)

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Checkout'), GlobalVariable.SSB_Datepicker)

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), GlobalVariable.SSB_GuestsAmount + GlobalVariable.SSB_GuestsText)

WebUI.closeBrowser()

