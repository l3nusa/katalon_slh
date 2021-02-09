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

WebUI.click(findTestObject('SharedComponent/SSB/Location'))
WebUI.setText(findTestObject('SharedComponent/SSB/Location'), location1)
WebUI.verifyElementVisible(findTestObject('SharedComponent/SSB/AutosuggestOptions'))

WebUI.click(findTestObject('SharedComponent/SSB/Checkin'))
Date checkin = CustomKeywords.'customPackage.ssb.getActiveCheckinDate'()
WebUI.click(findTestObject('SharedComponent/SSB/Datepicker_FirstActiveDay'))

WebUI.click(findTestObject('SharedComponent/SSB/Location'))
WebUI.click(findTestObject('SharedComponent/SSB/SearchBtn'))

CustomKeywords.'customPackage.ssb.verifyDestinationPageUrl'(WebUI.getUrl(), '/explore-hotels')

String label = WebUI.getText(findTestObject('PageSpecific/ExploreHotels/Results_Label'))
WebUI.verifyMatch(label, '.*' + location1 + '$', true, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('SharedComponent/SSB/Location'))
WebUI.setText(findTestObject('SharedComponent/SSB/Location'), location2)

WebUI.click(findTestObject('SharedComponent/SSB/Location'))
WebUI.click(findTestObject('SharedComponent/SSB/SearchBtn'))

//sleep(3000)
label = WebUI.getText(findTestObject('PageSpecific/ExploreHotels/Results_Label'))
WebUI.verifyMatch(label, '.*' + location1 + location2 + '$', true, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

