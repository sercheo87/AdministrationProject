package com.projects.rest
import static org.junit.Assert.*
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class testRest {
	String BASE_URL='http://127.0.0.1:1313/ProjectAdminMng/rest'
	def IN_DATA=[
		description:'COMPUTADORAS',
		name:'RES_PCS'
	]

	@Test
	void addResourcesTest(){
		def path = new HTTPBuilder(BASE_URL+'/typeResource/add');
		def inData=JsonOutput.toJson(IN_DATA)
		println 'indata:'+inData
		path.request(Method.PUT, ContentType.JSON) { req ->
			headers.'Content-Type' = 'application/json'
			headers.Accept = 'application/json';

			body=inData

			response.success = { resp, reader ->
				def jsonSlurper = new JsonSlurper()
				assertTrue resp.status==201
				assertTrue reader.messages[0].code==0
			}

			response.failure = { resp ->
				println 'Request failed with status ${resp.status}'
				assertTrue(false)
			}
		}
	}

	@Test
	void getResourcesTest(){
		def path = new HTTPBuilder(BASE_URL+'/typeResource/getAll');

		path.request(Method.GET, ContentType.JSON) { req ->
			headers.'Content-Type' = 'application/json'
			headers.Accept = 'application/json';

			response.success = { resp, reader ->
				def jsonSlurper = new JsonSlurper()
				assertTrue resp.status==200
				assertTrue reader.data.findAll{ it.name=='RES_PCS' }.size()>0
				return reader.data.findAll{ it.name=='RES_PCS' }[0].id
			}

			response.failure = { resp ->
				println 'Request failed with status ${resp.status}'
				assertTrue(false)
			}
		}
	}

	@Test
	void removeResourcesTest(){
		def pathGet = new HTTPBuilder(BASE_URL+'/typeResource/getAll');

		def TEMP_ID=	pathGet.request(Method.GET, ContentType.JSON) { req ->
			headers.'Content-Type' = 'application/json'
			headers.Accept = 'application/json';

			response.success = { resp, reader ->
				def jsonSlurper = new JsonSlurper()
				assertTrue resp.status==200
				assertTrue reader.data.findAll{ it.name=='RES_PCS' }.size()>0
				return reader.data.findAll{ it.name=='RES_PCS' }[0].id
			}

			response.failure = { resp ->
				println 'Request failed with status ${resp.status}'
				assertTrue(false)
			}
		}

		def path = new HTTPBuilder(BASE_URL+'/typeResource/delete/'+TEMP_ID);
		println 'path:'+TEMP_ID
		path.request(Method.DELETE, ContentType.JSON) { req ->
			headers.'Content-Type' = 'application/json'
			headers.Accept = 'application/json';

			response.success = { resp, reader ->
				println reader
				println resp
				def jsonSlurper = new JsonSlurper()
				assertTrue resp.status==200
				assertTrue reader.data.findAll{ it.name=='RES_PCS' }.size()>0
			}

			response.failure = { resp ->
				println 'Request failed with status ${resp.status}'
				assertFalse()
			}
		}
	}
}
