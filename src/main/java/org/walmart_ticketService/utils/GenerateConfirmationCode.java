package org.walmart_ticketService.utils;

import java.util.UUID;

public class GenerateConfirmationCode {
	
	//Generate a unique confirmation code for every reservation
	public static String generate(){
		return UUID.randomUUID().toString();
	}

}
