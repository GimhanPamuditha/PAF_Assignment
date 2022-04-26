package com;

import model.Complain;
//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Complain")
public class ComplainService {
	
	Complain complainObj = new Complain();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readComplains() {
		
		return complainObj.readComplains();
		
		
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertComplains(@FormParam("complainCategory") String complainCategory,
							@FormParam("complainSubject") String complainSubject,
							@FormParam("complainMessage") String complainMessage,
							@FormParam("complainDate") String complainDate,
							@FormParam("contactNumber") String contactNumber)
						
	{
		
		String output = complainObj.insertComplains(complainCategory, complainSubject, complainMessage, complainDate, contactNumber);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateComplains(String complainData)
	{
		//Convert input string to a JSON object
		JsonObject complainObject = new JsonParser().parse(complainData).getAsJsonObject();
		
		//Read values from JSON object
		String complainID = complainObject.get("complainID").getAsString();
		String complainCategory = complainObject.get("complainCategory").getAsString();
		String complainSubject = complainObject.get("complainSubject").getAsString();
		String complainMessage = complainObject.get("complainMessage").getAsString();
		String complainDate = complainObject.get("complainDate").getAsString();
		String contactNumber = complainObject.get("contactNumber").getAsString();
		
		
		String output = complainObj.updateComplain(complainID, complainCategory, complainSubject, complainMessage, complainDate, contactNumber);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteComplains(String complainData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(complainData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String complainID = doc.select("complainID").text();
		
		String output = complainObj.deleteComplain(complainID);
		return output;
		
	}

}
