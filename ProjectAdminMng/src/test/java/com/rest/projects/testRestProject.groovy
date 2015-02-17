package com.rest.projects

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.DELETE
import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST
import static groovyx.net.http.Method.PUT
import static org.junit.Assert.*
import groovyx.net.http.HTTPBuilder

import org.junit.*

class testRestProject {
	def PATH_REST = 'http://127.0.0.1:1313/ProjectAdminMng/rest'
	def PATH_MODULE = PATH_REST + '/project'

	def static DATA_ROW = ''

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProjectsAdd() {
		println 'Add PROJECT'
		def http = new HTTPBuilder( PATH_MODULE + '/add' )
		http.request( PUT, JSON ) { req ->
			body = [
				name:'pdp-marketing',
				description:'marketing',
				scope:'total',
				target:'principal'
			]

			response.success = { resp, json ->
				println "Got response: ${resp.status}"
				println "Content-Type: ${resp.headers.'Content-Type'}"
				assert resp.status == 201
				return DATA_ROW
			}
		}
	}

	@Test
	public void testProjectsGetById() {
		println 'GET PROJECT BY ID'
		def http = new HTTPBuilder( PATH_MODULE + '/2' )
		http.request( GET, JSON ) { req ->

			response.success = { resp, json ->
				println "Got response: ${resp.status}"
				println "Content-Type: ${resp.headers.'Content-Type'}"
				assert resp.status == 200
				return DATA_ROW
			}
		}
	}
}
