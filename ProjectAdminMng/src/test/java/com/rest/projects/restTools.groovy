package com.rest.projects

import groovy.json.JsonBuilder

public final class restTools {
	public JsonBuilder convertToJson(def response){
		def	builder = new JsonBuilder()
		builder {
			data response.data.collect {data ->
				return {
					data.each {key, value ->
						"$key" value
					}
				}
			}
		}
		return builder
	}
}
