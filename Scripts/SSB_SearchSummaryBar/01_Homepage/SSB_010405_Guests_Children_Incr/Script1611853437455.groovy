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

WebUI.click(findTestObject('SearchSummaryBar/Guests'))

int i = 0

while ( i < GlobalVariable.SSB_ChildrenMax ){
	WebUI.click(findTestObject('SearchSummaryBar/Guests_Children_Plus'), FailureHandling.STOP_ON_FAILURE)
	i++
	WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), (GlobalVariable.SSB_Guests_Amount + i) + GlobalVariable.SSB_Guests)
}

WebUI.click(findTestObject('SearchSummaryBar/Guests_Children_Plus'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('SearchSummaryBar/Guests'), ((GlobalVariable.SSB_Guests_Amount + GlobalVariable.SSB_ChildrenMax) + GlobalVariable.SSB_Guests))

WebUI.closeBrowser()

