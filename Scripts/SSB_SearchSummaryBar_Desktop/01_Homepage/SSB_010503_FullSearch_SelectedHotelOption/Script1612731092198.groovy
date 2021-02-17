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
WebUI.setText(findTestObject('SharedComponent/SSB/Location'), 'ahl')
WebUI.click(findTestObject('SharedComponent/SSB/AutosuggestOptions'))

WebUI.click(findTestObject('SharedComponent/SSB/Checkin'))
def checkin =CustomKeywords.'customPackage.ssb.getActiveCheckinDate'()
WebUI.click(findTestObject('SharedComponent/SSB/Datepicker/FirstActiveDay'))

WebUI.click(findTestObject('SharedComponent/SSB/Checkout'))
def checkout = CustomKeywords.'customPackage.ssb.getActiveCheckoutDate'()
WebUI.click(findTestObject('SharedComponent/SSB/Datepicker/FirstActiveDay'))

WebUI.click(findTestObject('SharedComponent/SSB/Guests'))
CustomKeywords.'customPackage.ssb.setAdultsAmount'(GlobalVariable.SSB_AdultsMin)
CustomKeywords.'customPackage.ssb.setChildrenAmount'(children)

WebUI.click(findTestObject('SharedComponent/SSB/Location'))
WebUI.click(findTestObject('SharedComponent/SSB/SearchBtn'))

CustomKeywords.'customPackage.commonUtils.verifyDestinationPageUrl'(WebUI.getUrl(),'/hotels/dar-ahlam')
CustomKeywords.'customPackage.commonUtils.verifyAppendedDates'(WebUI.getUrl(), checkin.format('yyyy-MM-dd'), checkout.format('yyyy-MM-dd'))
CustomKeywords.'customPackage.commonUtils.verifyAppendedGuestsAmount'(WebUI.getUrl(), GlobalVariable.SSB_AdultsMin, children)

WebUI.closeBrowser()

