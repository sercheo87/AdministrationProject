package com.rest.projects;

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.DELETE
import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST
import static groovyx.net.http.Method.PUT
import static org.junit.Assert.*
import groovy.json.JsonBuilder
import groovyx.net.http.HTTPBuilder

import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testRestJob {

	def PATH_REST = 'http://127.0.0.1:1313/ProjectAdminMng/rest'
	def PATH_MODULE = PATH_REST + '/job'

	def static DATA_ROW = ''

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testJobs1Add() {
		println 'Add JOBS'
		def http = new HTTPBuilder( PATH_MODULE + '/add' )
		http.request( PUT, JSON ) { req ->
			body = [
				description:'Arquitecto de aplicaciones',
				job:'ArquitectoTest'
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
	public void testJobs2GetAll() {
		println 'GET ALL JOBS'
		def http = new HTTPBuilder( PATH_MODULE + '/getAll' )
		http.request( GET, JSON ) { req ->

			response.success = { resp, json ->
				println "Got response: ${resp.status}"
				println "Content-Type: ${resp.headers.'Content-Type'}"

				assert resp.status == 200
				JsonBuilder res= new restTools().convertToJson(json)
				assert json.get('data').size() > 0

				DATA_ROW = json.get('data').collect().find{ it.job == 'ArquitectoTest' }
				println res.toPrettyString()
			}
		}
	}

	@Test
	public void testJobs3Update() {
		println 'UPDATE ALL JOBS'
		def http = new HTTPBuilder( PATH_MODULE + '/update' )
		println 'ROW SELECTED: ' + DATA_ROW

		def DATA_ROW_ALTER = DATA_ROW
		DATA_ROW_ALTER.description = 'change'

		http.request( POST, JSON ) { req ->
			body = DATA_ROW_ALTER
			response.success = { resp, json ->
				println "Got response: ${resp.status}"
				println "Content-Type: ${resp.headers.'Content-Type'}"
				assert resp.status == 201
			}
		}

		http = new HTTPBuilder( PATH_MODULE + '/getAll' )
		http.request( GET, JSON ) { req ->

			response.success = { resp, json ->
				println "Got response: ${resp.status}"
				println "Content-Type: ${resp.headers.'Content-Type'}"

				assert resp.status == 200
				assert json.get('data').collect().find{ it.id == DATA_ROW.id }.description == 'change'
			}
		}
	}

	@Test
	public void testJobs4Remove() {
		println 'REMOVE JOB'
		def http = new HTTPBuilder( PATH_MODULE + '/delete/' + DATA_ROW.id )
		println 'ROW SELECTED: ' + DATA_ROW

		http.request( DELETE, JSON ) { req ->

			response.success = { resp, json ->
				println "Got response: ${resp.status}"
				println "Content-Type: ${resp.headers.'Content-Type'}"

				assert resp.status == 200
			}
		}

		http = new HTTPBuilder( PATH_MODULE + '/getAll' )
		http.request( GET, JSON ) { req ->

			response.success = { resp, json ->
				println "Got response: ${resp.status}"
				println "Content-Type: ${resp.headers.'Content-Type'}"

				assert resp.status == 200
				assert json.get('data').collect().find{ it.id == DATA_ROW.id }.collect().size() == 0
			}
		}
	}
}
