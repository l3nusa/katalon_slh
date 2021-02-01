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

WebUI.setText(findTestObject('SearchSummaryBar/Location'), 'ahl')

WebUI.click(findTestObject('SearchSummaryBar/AutosuggestOptions'))

WebUI.click(findTestObject('SearchSummaryBar/Checkin'))

def checkin = CustomKeywords.'customPackage.ssb.getCheckinDate'()

WebUI.click(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'))

WebUI.click(findTestObject('SearchSummaryBar/Checkout'))

def checkout = CustomKeywords.'customPackage.ssb.getCheckoutDate'()

WebUI.click(findTestObject('SearchSummaryBar/Datepicker_ActiveDate'))

WebUI.click(findTestObject('SearchSummaryBar/Guests'))

int i = 1

while (i >= 1) {
    WebUI.click(findTestObject('SearchSummaryBar/Guests_Adults_Min'), FailureHandling.STOP_ON_FAILURE)

    i--
}

while (i < 2) {
    WebUI.click(findTestObject('SearchSummaryBar/Guests_Children_Plus'), FailureHandling.STOP_ON_FAILURE)

    i++
}

WebUI.click(findTestObject('SearchSummaryBar/Location'))

WebUI.click(findTestObject('SearchSummaryBar/SearchBtn'))

currentUrl = WebUI.getUrl()

if (!(currentUrl.endsWith('hotels/dar-ahlam?adults=1&children=2&startDate=' + checkin.format('yyyy-MM-dd') + '&endDate=' + checkout.format('yyyy-MM-dd')))) {
    throw new Exception('Current URL doesn\'t match the expected : ', currentUrl)
}

WebUI.closeBrowser()

