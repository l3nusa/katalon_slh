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
WebUI.setText(findTestObject('SearchSummaryBar/Location'), 'alex')

WebUI.click(findTestObject('SearchSummaryBar/Checkin'))
Date checkin = CustomKeywords.'customPackage.ssb.getActiveCheckinDate'()
WebUI.click(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'))

WebUI.click(findTestObject('SearchSummaryBar/Checkout'))
Date checkout = CustomKeywords.'customPackage.ssb.getActiveCheckoutDate'()
WebUI.click(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'))

WebUI.click(findTestObject('SearchSummaryBar/Guests'))
CustomKeywords.'customPackage.ssb.setAdultsAmount'(GlobalVariable.SSB_AdultsMin)
CustomKeywords.'customPackage.ssb.setChildrenAmount'(GlobalVariable.SSB_ChildrenMin)

WebUI.click(findTestObject('SearchSummaryBar/Location'))
WebUI.click(findTestObject('SearchSummaryBar/SearchBtn'))

CustomKeywords.'customPackage.ssb.verifyDestinationPageUrl'(WebUI.getUrl(), '/explore-hotels')

WebUI.comment('********************** Search page ******************************')

WebUI.waitForElementClickable(findTestObject('SearchSummaryBar/Location'), 10)
CustomKeywords.'customPackage.CommonUtils.clearElementText'(findTestObject('SearchSummaryBar/Location'))
WebUI.setText(findTestObject('SearchSummaryBar/Location'), 'london')
WebUI.click(findTestObject('SearchSummaryBar/AutosuggestOptions'))

WebUI.click(findTestObject('SearchSummaryBar/Checkin'))
checkin = CustomKeywords.'customPackage.ssb.getActiveCheckinDate'() + checkinOffset
WebUI.click(CustomKeywords.'customPackage.ssb.getDatePickerObjectByDate'(checkin))

WebUI.click(findTestObject('SearchSummaryBar/Checkout'))
checkout = (checkin + stayPeriod)
WebUI.click(CustomKeywords.'customPackage.ssb.getDatePickerObjectByDate'(checkout))

WebUI.click(findTestObject('SearchSummaryBar/Guests'))
CustomKeywords.'customPackage.ssb.setAdultsAmount'(adults)
CustomKeywords.'customPackage.ssb.setChildrenAmount'(children)

WebUI.click(findTestObject('SearchSummaryBar/Location'))
WebUI.click(findTestObject('SearchSummaryBar/SearchBtn'))

CustomKeywords.'customPackage.ssb.verifyDestinationPageUrl'(WebUI.getUrl(), '/explore-hotels')
// verifyAppendedQuery(String currentUrl, String query="", String city=null, String country=null, String regions=null)
CustomKeywords.'customPackage.ssb.verifyAppendedQuery'(WebUI.getUrl(), 'London%2C%20United%20Kingdom', 'London', 'United%20Kingdom', 'London')
CustomKeywords.'customPackage.ssb.verifyAppendedDates'(WebUI.getUrl(), checkin.format('dd+MMM+yyyy'), checkout.format('dd+MMM+yyyy'))
CustomKeywords.'customPackage.ssb.verifyAppendedGuestsAmount'(WebUI.getUrl(), adults, children)

WebUI.verifyElementAttributeValue(findTestObject('SearchSummaryBar/Location'), 'value', 'London%2C%20United%20Kingdom', 0)
WebUI.verifyElementText(findTestObject('SearchSummaryBar/Checkin'), checkin.format('dd MMMM'))
WebUI.verifyElementText(findTestObject('SearchSummaryBar/Checkout'), checkout.format('dd MMMM'))
WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), (adults + children) + GlobalVariable.SSB_GuestsText)

WebUI.closeBrowser()

