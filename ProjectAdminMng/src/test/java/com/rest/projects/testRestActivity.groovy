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
		def http = new HTTPBuilder( 'http://127.0.0.1:1313/ProjectAdminMng/rest/' )

		http.get( path: 'activity/getAll') { resp, json ->
			println resp.status
			println json

			assert resp.status == 200
			println "Got response: ${resp.status}"
			println "Content-Type: ${resp.headers.'Content-Type'}"


			def		builder2 = new JsonBuilder()
			builder2 {
				data json.data.collect {data ->
					return {
						data.each {key, value ->
							"$key" value
						}
					}
				}
			}

			println builder2.toPrettyString()
			//states.data.each { println it.toString() }
		}
	}
}
