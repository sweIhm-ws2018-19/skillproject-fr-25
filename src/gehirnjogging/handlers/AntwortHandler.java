package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class AntwortHandlerimplements RequestHandler { 

boolean antwortRichtig;

	request = input.getRequestEnvelope().getRequest();
    intentRequest = (IntentRequest) request;
    intent = intentRequest.getIntent();
    slots = intent.getSlots();
    
private Map<String, Slot> slots = null;


@Override
public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("AntwortIntent"));
}

@Override
public Optional<Response> handle(HandlerInput input) {
	setTrueAnser();
	checkAnswer();
	if(antwortRichtig==true) {
	    return input.getResponseBuilder()
	            .withSpeech(" <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_01'/> Diese Antwort ist Richtig, sagen sie nächste Frage um die nächste Frage zu erhalten oder Beenden wenn du keine Lust mehr hast")
	            .withReprompt("bist du eingeschlafen ?")
	            .build();
	}
if(antwortRichtig==false) {
	  return input.getResponseBuilder()
	            .withSpeech("<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_01'/> Diese Antwort ist Falsch,sagen sie nächste Frage um die nächste Frage zu erhalten oder Beenden wenn du keine Lust mehr hast")
	            .withReprompt("bist du eingeschlafen ?")
	            .build();
}
//RICHTIGE_ANTWORT_NUMBER setzten 
setTrueAnser(){
	SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][1]=SpeechStrings.RICHTIGE_ANTWORT;
}


checkAnswer(){
	if(slots.get("antwort").getValue() == SpeechStrings.RICHTIGE_ANTWORT) {
		antwortRichtig=true;
	}else {
		antwortRichtig=false;
	}
	
}


}
 
