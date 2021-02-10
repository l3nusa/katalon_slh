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

WebUI.click(findTestObject('SharedComponent/SSB/Location'))
WebUI.setText(findTestObject('SharedComponent/SSB/Location'), 'london')

WebUI.click(findTestObject('SharedComponent/SSB/SearchBtn'))

CustomKeywords.'customPackage.CommonUtils.verifyDestinationPageUrl'(WebUI.getUrl(),'/explore-hotels')
CustomKeywords.'customPackage.ssb.verifyAppendedQuery'(WebUI.getUrl(), 'london')
CustomKeywords.'customPackage.ssb.verifyAppendedDates'(WebUI.getUrl(), null, null)
CustomKeywords.'customPackage.ssb.verifyAppendedGuestsAmount'(WebUI.getUrl(), GlobalVariable.SSB_GuestsAmount, GlobalVariable.SSB_ChildrenMin)

WebUI.verifyElementAttributeValue(findTestObject('SharedComponent/SSB/Location'), 'value', 'london', 0)
WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Checkin'), GlobalVariable.SSB_Datepicker)
WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Checkout'), GlobalVariable.SSB_Datepicker)
WebUI.verifyElementText(findTestObject('SharedComponent/SSB/Guests'), GlobalVariable.SSB_GuestsAmount + GlobalVariable.SSB_GuestsText)

WebUI.closeBrowser()

