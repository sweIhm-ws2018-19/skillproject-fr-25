package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

public class EndHandler implements RequestHandler { 

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("EndIntent"))&&Logic.STATUS_ID!=4;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
           AttributesManager attributesManager = input.getAttributesManager();
           Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

           persistentAttributes.clear();
           attributesManager.setPersistentAttributes(persistentAttributes);
           attributesManager.savePersistentAttributes();
           
    	if(Logic.counter==0) {
    		return input.getResponseBuilder()
                    .withSpeech("<audio src='soundbank://soundlibrary/human/amzn_sfx_baby_cry_01'/>" +"Schade, dass du doch nicht spielen möchtest, ich hoffe, dass du bald wiederkommst")
                    .withReprompt("bist du eingeschlafen ?")
                    .withShouldEndSession(true)
                    .build();
    	}
    	
    	if(Logic.EINSTELLUNGS_COUNTER_R==1) {
    		Logic.STATUS_ID=7;
        return input.getResponseBuilder()
                .withSpeech("Du hast bei  "+  Logic.counter + " Fragen " + Logic.richtig + " Punkte erreicht <audio src='soundbank://soundlibrary/human/amzn_sfx_crowd_applause_03'/>" + "Möchtest du noch eine runde spielen ?")
                .withReprompt("bist du eingeschlafen ?")
                .build();
    	}
    	 return input.getResponseBuilder()
                 .withSpeech(Logic.scoreBewerten() + "Wollt ihr noch eine runde spielen ?")
                 .withReprompt("bist du eingeschlafen ?")
                 .build();
    }
}

//"Schön, dass du da warst! Ich hoffe wir sehen uns bald wieder zu einem spannenden Spiel! Machs gut!