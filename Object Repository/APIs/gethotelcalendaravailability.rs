<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>gethotelcalendaravailability</name>
   <tag></tag>
   <elementGuidId>3dafc288-a775-452d-b421-d56a42b560a8</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <katalonVersion>7.9.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${GlobalVariable.baseURL}/api/gethotelcalendaravailability?startDate=2021-04-01&amp;adultsCount=2&amp;childrenCount=0&amp;hotelCode=78358&amp;currencyCode=USD</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'2021-04-01'</defaultValue>
      <description></description>
      <id>eca654fe-addb-4e58-acee-5b38740a3482</id>
      <masked>false</masked>
      <name>startDate</name>
   </variables>
   <variables>
      <defaultValue>2</defaultValue>
      <description></description>
      <id>22a5884a-6a44-45f0-8b8a-ceeba60da30a</id>
      <masked>false</masked>
      <name>adultsCount</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>0e323f95-b890-4384-9895-c4cd1f6a7d7e</id>
      <masked>false</masked>
      <name>childrenCount</name>
   </variables>
   <variables>
      <defaultValue>78358</defaultValue>
      <description></description>
      <id>db580ac2-28c3-4a1f-a0be-c336641b085d</id>
      <masked>false</masked>
      <name>hotelCode</name>
   </variables>
   <variables>
      <defaultValue>'USD'</defaultValue>
      <description></description>
      <id>61dff4d1-18f3-4dec-ab7f-ec9b8e0bccc0</id>
      <masked>false</masked>
      <name>currencyCode</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()
ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

WS.verifyResponseStatusCode(response, 200)
assertThat(response.getStatusCode()).isEqualTo(200)

JsonSlurper jsonSlurper = new JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response.getResponseText())

</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
