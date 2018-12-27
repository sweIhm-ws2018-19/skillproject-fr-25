package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

public class FortsetzenHandler implements RequestHandler { 

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("Forsetzen")) && Logic.STATUS_ID == 4;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	Logic.STATUS_ID=Logic.STATUS_ID_OLD;
    	Logic.EINSTELLUNGS_ID=Logic.EINSTELLUNGS_ID_OLD;

        switch (Logic.STATUS_ID) {
        case 3:   
            Logic.STATUS_ID = 1;
            if(Logic.EINSTELLUNGS_ID==1) {
                Logic.EINSTELLUNGS_ID=1;
                Logic.STATUS_ID=3;
                return input.getResponseBuilder()
                        .withSpeech("Möchtest du alleine spielen?")
                        .withShouldEndSession(false)
                        .build();
            }else if(Logic.EINSTELLUNGS_ID==2) {
            	return input.getResponseBuilder()
                        .withSpeech("Wie viele Fragen sollen gespielt werden ? Sagen Sie zum Beispiel Ich möchte 5 Fragen spielen")
                        .withShouldEndSession(false)
                        .build();
            }else if(Logic.EINSTELLUNGS_ID==5) {
   	         return input.getResponseBuilder()
	                    .withSpeech("Dann legen wir los! Hier kommt Frage " + Logic.counter + ": " + Logic.questions[Logic.FRAGE_NUMBER][0])
	                    .withReprompt("Möchtet du, dass ich die Frage wiederhole?")
	                    .withShouldEndSession(false)
	                    .build();
            }else {
            	return input.getResponseBuilder()
                        .withSpeech("Wie viele Spieler seid ihr? Antworte mit 2 Spieler oder 3 Spieler")
                        .withShouldEndSession(false)
                        .build();
            }
        case 1:   
            return input.getResponseBuilder()
                    .withSpeech("Möchtest du etwas über den Spielablauf oder die Punktvergabe wissen?")
                    .withShouldEndSession(false)
                    .build();
        case 2:   
            return input.getResponseBuilder()
                    .withSpeech("Hier kommt noch ein mal die Frage "  + Logic.counter + ":"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                    .withShouldEndSession(false)
                    .build();
        case 5:   
            return input.getResponseBuilder()
                    .withSpeech("Hier kommt noch ein mal die Frage "  + Logic.counter + ":"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                    .withShouldEndSession(false)
                    .build();
        default: 
            return input.getResponseBuilder()
                    .withSpeech("Error")
                    .build();
        }
    }
}