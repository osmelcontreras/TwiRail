package com.app.phonetree.servlet.ivr;

import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Play; //might not use
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		Say greet = new Say.Builder("Hello, welcome to Affiliate Program. Listen to the IVR")
			  .voice(Say.Voice.ALICE)
  			  .language(Say.Language.EN_GB)
  			  .build();
		
		VoiceResponse response = new VoiceResponse.Builder()
                .say(greet)
				.gather(new Gather.Builder()
                        .action("/menu/show")
                        .numDigits(1)
                        .build())
                .build();
		
		servletResponse.setContentType("text/xml");
        try {
            servletResponse.getWriter().write(response.toXml());
        } catch (TwiMLException e) {
            throw new RuntimeException(e);
        }
	}

}
