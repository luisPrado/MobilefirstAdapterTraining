/*
 *    Licensed Materials - Property of IBM
 *    5725-I43 (C) Copyright IBM Corp. 2015, 2016. All Rights Reserved.
 *    US Government Users Restricted Rights - Use, duplication or
 *    disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

package com.bcr.adapters;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ibm.mfp.adapter.api.ConfigurationAPI;
import com.ibm.mfp.adapter.api.OAuthSecurity;

@Api(value = "Sample Adapter Resource")
@Path("/resource")
public class ItemAdapterResource {
	/*
	 * For more info on JAX-RS see
	 * https://jax-rs-spec.java.net/nonav/2.0-rev-a/apidocs/index.html
	 */

	// Define logger (Standard java.util.Logger)
	static Logger logger = Logger.getLogger(ItemAdapterResource.class.getName());
	final String DATA = "["
						+ "{"
						+ "\"id\":1,"
						+ "\"name\":\"Iphone 11\","
						+ "\"image\":\"https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-11-pro-max-gold-select-2019?wid=940&hei=1112&fmt=png-alpha&qlt=80&.v=1566953859132\","
						+ " \"brand\":\"Apple\","
						+ " \"description\":\"Apple iPhone 11 Pro (64 GB), Plateado\","
						+ " \"price\":12"
						+ "},"
						+ "{" 
						+ "\"id\":2,"
						+ "\"name\":\"Xiaomi Mi 9T Pro\","
						+ "\"image\":\"https://images-na.ssl-images-amazon.com/images/I/61u2D0UFFxL._AC_SX522_.jpg\","
						+ " \"brand\":\"Xiaomi\","
						+ " \"description\":\"Xiaomi Mi 9T Pro (128 GB, 6 GB de RAM) pantalla de 6.39 pulgadas, Snapdragon 855, cámara trasera AI, doble SIM GSM desbloqueado de fábrica - US & Global 4G LTE Versión Internacional (Flame Red)\","
						+ " \"price\":12"
						+ "},"
						+ "{" 
						+ "\"id\":3,"
						+ "\"name\":\"Samsung Galaxy S10\","
						+ "\"image\":\"https://cdn.tmobile.com/content/dam/t-mobile/en-p/cell-phones/samsung/samsung-galaxy-s10/prism-black/Samsung-Galaxy-S10-Prism-Black-1-3x.jpg\","
						+ " \"brand\":\"Samsung\","
						+ " \"description\":\"Samsung Galaxy Factory Teléfono desbloqueado, S10, Negro\","
						+ " \"price\":12"
						+ "}"
						+ "]";
	// Inject the MFP configuration API:
	@Context
	ConfigurationAPI configApi;

	/*
	 * Path for method:
	 * "<server address>/mfp/api/adapters/ItemAdapter/resource/getAll"
	 */

	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/getProducts")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public String getAll() {
		return DATA;
	}
	
	/*
	 * Path for method:
	 * "<server address>/mfp/api/adapters/ItemAdapter/resource/getAll"
	 */

	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/getProduct")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(scope="accessRestricted")
	public JSONObject getProduct(@QueryParam("id") int id  ) {
		JSONParser parser = new JSONParser();
		try {
			JSONArray jsonArray = (JSONArray) parser.parse(DATA);
			for(Object json : jsonArray) {
				JSONObject jsonObject = (JSONObject) json;
				long idJson = (Long)jsonObject.get("id");
				System.out.println(id);
				if(id==idJson){
					return jsonObject;
				}
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		return null;
	}
	
	/*
	 * Path for method:
	 * "<server address>/mfp/api/adapters/ItemAdapter/resource/getAllProtected"
	 */

	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/getAllProtected")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(scope="accessRestricted")
	public String getAllProtected() {
		return DATA;
	}





}
