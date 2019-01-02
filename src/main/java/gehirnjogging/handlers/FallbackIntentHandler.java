package gehirnjogging.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

// 2018-July-09: AMAZON.FallackIntent is only currently available in en-US locale.
//              This handler will not be triggered except in that locale, so it can be
//              safely deployed for any locale.
public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

    	if(Logic.STATUS_ID==3&&Logic.EINSTELLUNGS_ID==1) {
			return input.getResponseBuilder()
		               .withSpeech("Ich habe dich nicht verstanden Möchtest du alleine Spielen ? du kannst mit Ja oder Nein Antworten")
		               .withReprompt("Möchtest du alleine spielen?")
		               .build();
		}else if(Logic.STATUS_ID==3&&Logic.EINSTELLUNGS_ID==3) {
			return input.getResponseBuilder()
		               .withSpeech("Ich habe dich nicht verstanden. Wie viele Spieler seid ihr? Antworte mit 2 Spieler oder 3 Spieler ? ")
		               .withReprompt("Antworte mit 2 Spieler oder 3 Spieler ?")
		               .build();
		}else if(Logic.STATUS_ID==3&&Logic.EINSTELLUNGS_ID==2) {
			return input.getResponseBuilder()
		               .withSpeech("Ich habe dich nicht verstanden. Wie viele Fragen sollen gespielt werden ? Sagen Sie zum Beispiel Ich möchte 5 Fragen spielen")
		               .withReprompt("Sagen Sie zum Beispiel Ich möchte 5 Fragen spielen")
		               .build();
		
	}else if(Logic.STATUS_ID==1&&Logic.EINSTELLUNGS_ID==2) {
		return input.getResponseBuilder()
	               .withSpeech("Ich habe dich nicht verstanden. Ok super! Möchtest du vor dem Spielbeginn noch die Spielregeln hören ? Antworte mit Ja oder nein")
	               .withReprompt("Antworte mit Ja oder nein")
	               .build();
	}
	     return input.getResponseBuilder()
	               .withSpeech("Tut mir Leid ich habe sie nicht verstanden Infos: Status ID "+Logic.STATUS_ID+" Einstelugns ID "+Logic.EINSTELLUNGS_ID)
	               .withReprompt("bist du eingeschlafen ?")
	               .build();
	}
    
}