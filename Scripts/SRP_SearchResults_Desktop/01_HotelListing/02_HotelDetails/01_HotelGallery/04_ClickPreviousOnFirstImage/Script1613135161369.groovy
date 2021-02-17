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
WebUI.click(findTestObject('SharedComponent/AcceptCookieBtn'))

WebUI.click(findTestObject('SharedComponent/SSB/Location'))
WebUI.setText(findTestObject('SharedComponent/SSB/Location'), 'barce')
WebUI.click(findTestObject('SharedComponent/SSB/AutosuggestOptions'))

WebUI.click(findTestObject('SharedComponent/SSB/SearchBtn'))

WebUI.comment('********************** Search page ******************************')

CustomKeywords.'customPackage.commonUtils.verifyDestinationPageUrl'(WebUI.getUrl(), '/explore-hotels')

CustomKeywords.'customPackage.hotelDetails.getPreviousGalleryImage'()

WebUI.closeBrowser()


