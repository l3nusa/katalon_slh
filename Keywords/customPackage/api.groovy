
package customPackage

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.text.SimpleDateFormat as SimpleDateFormat


import groovy.json.JsonSlurper

import internal.GlobalVariable

public class api {

	
	@Keyword
	def getUnavailabledDate(Date startDate, int adultsCount, int childrenCount, int hotelCode=78358, String currencyCode='USD' ) {
		SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd')
		
		def response = WS.sendRequestAndVerify(findTestObject('APIs/gethotelcalendaravailability', [('startDate') : sdf.format(startDate).toString(), ('adultsCount') : adultsCount,
			('childrenCount') : childrenCount, ('hotelCode') : hotelCode, ('currencyCode') : currencyCode]))

		
		
		def dailyAvailabilities = jsonResponse.DailyAvailabilities
		String unavailableDate = ''
		
		for(def day : dailyAvailabilities) {			
			if( day.AvailabilityStatus == 'Closed') {
				unavailableDate = day.Date
				break
			}
		}
		
		return unavailableDate
	}
}
