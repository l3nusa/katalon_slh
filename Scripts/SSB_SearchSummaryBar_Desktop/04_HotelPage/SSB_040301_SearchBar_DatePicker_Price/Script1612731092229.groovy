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

WebUI.openBrowser(GlobalVariable.baseURL + '/hotels/dar-ahlam')
WebUI.maximizeWindow()
WebUI.click(findTestObject('SharedComponent/AcceptCookieBtn'))

WebUI.click(findTestObject('PageSpecific/HotelPage/SecondaryNav/SelectDatesBtn'))

WebUI.click(findTestObject('PageSpecific/HotelPage/SecondaryNav/SSB/Checkin'))
WebUI.verifyElementNotPresent(CustomKeywords.'customPackage.ssb.verifyDatePickerPriceByDate'(CustomKeywords.'customPackage.ssb.getActiveCheckinDate'() - 1), 0)
WebUI.verifyElementPresent(CustomKeywords.'customPackage.ssb.verifyDatePickerPriceByDate'(CustomKeywords.'customPackage.ssb.getActiveCheckinDate'()), 0)
WebUI.click(findTestObject('SharedComponent/SSB/Datepicker_FirstActiveDay'))
WebUI.verifyElementPresent(CustomKeywords.'customPackage.ssb.verifyDatePickerPriceByDate'(CustomKeywords.'customPackage.ssb.getActiveCheckoutDate'()), 0)
WebUI.click(findTestObject('SharedComponent/SSB/Datepicker_FirstActiveDay'))

WebUI.closeBrowser()

