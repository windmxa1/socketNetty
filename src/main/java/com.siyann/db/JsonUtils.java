package com.siyann.db;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	/**  
     *   
     */
	private static ObjectMapper mapper;

	/**  
     *   
     */
	private JsonUtils() {

	}

	/**
	 * 
	 * @return
	 */
	public static ObjectMapper getInstance() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
}
