package customPackage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class hotelDetails {
	@Keyword
	def getNextGalleryImage() {
		int i = WebUI.getText(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/NavNumbers/Index')).toInteger()

		if (i == WebUI.getText(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/NavNumbers/Total')).toInteger())
			i = 1
		else
			i++

		WebUI.scrollToElement(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/NextBtn'),0)
		WebUI.click(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/NextBtn'))
		assert i == WebUI.getText(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/NavNumbers/Index')).toInteger()

		String altAttr = WebUI.getAttribute(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/Image'),'alt')
		WebUI.comment('[i] : '+i)
		WebUI.comment('[index] : '+WebUI.getText(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/NavNumbers/Index')).toInteger())
		WebUI.comment('[alt] : '+altAttr)
		WebUI.verifyMatch(altAttr,'.*' + i + '$', true, FailureHandling.STOP_ON_FAILURE)
	}

	@Keyword
	def getPreviousGalleryImage() {
		int i = WebUI.getText(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/NavNumbers/Index')).toInteger()

		if (i == 1)
			i = WebUI.getText(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/NavNumbers/Total')).toInteger()
		else
			i--

		WebUI.scrollToElement(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/PreviousBtn'),0)
		WebUI.click(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/PreviousBtn'))
		assert i == WebUI.getText(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/NavNumbers/Index')).toInteger()
		String altAttr = WebUI.getAttribute(findTestObject('PageSpecific/ExploreHotels/HotelItem/Gallery/Image'),'alt')
		WebUI.verifyMatch(altAttr,'.*' + i + '$', true, FailureHandling.STOP_ON_FAILURE)
	}
}
