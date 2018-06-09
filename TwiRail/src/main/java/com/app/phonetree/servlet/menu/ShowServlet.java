package com.app.phonetree.servlet.menu;

import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Hangup;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowServlet
 */
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		
		String selectedOption = new servletRequest.getParameter("Digits");
		
		VoiceResponse response;
		switch(selectedOption) {
		case "1":
			response = getSchedulerAssist();
			break;
		case "2":
			response = getAffiliateCode();
			break;
		
		}
		 servletResponse.setContentType("text/xml");
	        try {
	            servletResponse.getWriter().write(response.toXml());
	        } catch (TwiMLException e) {
	            throw new RuntimeException(e);
	        }  
	}
	
	//getSchedulerAssist method
	private VoiceResponse getSchedulerAssist(){
		
		Hangup hangup = new Hangup.Builder().build(); //added
		
		VoiceResponse response = new VoiceResponse.Builder()
                .say(new Say.Builder("Hi, this the scheduler assitant.")
                		.voice(Say.Voice.ALICE)
                        .language(Say.Language.EN_GB)
                        .build())
                .hangup(hangup) //added
                .build();
		return response;
	}
	
	//getAffiliateCode() method
      private VoiceResponse getAffiliateCode(){
    	  
    	  Say say = new Say.Builder("Please, enter your 3-digit code now.")
    			  .voice(Say.Voice.ALICE)
    			  .language(Say.Language.EN_GB)
    			  .build();
    			 
    	  Gather gatherAffiliateCode = new Gather.Builder().inputs(
    	          Arrays.asList(Gather.Input.DTMF, Gather.Input.SPEECH))
    	            .timeout(3).numDigits(3).say(say).build();  //3-digit Affiliate code
    	  			VoiceResponse Coderesponse = new VoiceResponse.Builder().gather(gatherAffiliateCode)
    	            .build();
    	  			
    	  			try {
    	  	            System.out.println(Coderesponse.toXml());
    	  	        } catch (TwiMLException e) {
    	  	            e.printStackTrace();
    	  	        }
    	  		
    	  VoiceResponse response = new VoiceResponse.Builder()
    			  .say(say)
    			  .gather(gatherAffiliateCode)
    			  .build();
    
    	  return response;
    			
      }
	
}
