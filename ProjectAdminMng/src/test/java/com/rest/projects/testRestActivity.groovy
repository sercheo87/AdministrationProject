package com.rest.projects;

import groovy.json.JsonBuilder
import groovyx.net.http.HTTPBuilder

import org.junit.Before
import org.junit.Test

public class testRestActivity {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCustomerResource() {
		println 'GET ALL ACTIVITIES'
		def http = new HTTPBuilder( 'http://127.0.0.1:1313/ProjectAdminMng/rest/' )
		http.get( path: 'activity/getAll') { resp, json ->
			println "Got response: ${resp.status}"
			println "Content-Type: ${resp.headers.'Content-Type'}"

			assert resp.status == 200
			JsonBuilder res= new restTools().convertToJson(json)
			assert json.get('data').size()
			println res.toString()
		}
	}
}
