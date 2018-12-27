package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

public class EndHandler implements RequestHandler { 

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("EndIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	if(Logic.counter==0) {
    		return input.getResponseBuilder()
                    .withSpeech("<audio src='soundbank://soundlibrary/human/amzn_sfx_baby_cry_01'/>" +"Schade, dass du doch nicht spielen m�chtest, ich hoffe, dass du bald wiederkommst")
                    .withReprompt("bist du eingeschlafen ?")
                    .withShouldEndSession(true)
                    .build();
    	}
    	
    	if(Logic.EINSTELLUNGS_COUNTER_R==1) {
    		Logic.STATUS_ID=7;
        return input.getResponseBuilder()
                .withSpeech("Du hast bei  "+  Logic.counter + " Fragen " + Logic.richtig + " Punkte erreicht <audio src='soundbank://soundlibrary/human/amzn_sfx_crowd_applause_03'/>" + "M�chtest du noch eine runde spielen ?")
                .withReprompt("bist du eingeschlafen ?")
                .build();
    	}
    	 return input.getResponseBuilder()
                 .withSpeech(Logic.scoreBewerten() + "Wollt ihr noch eine runde spielen ?")
                 .withReprompt("bist du eingeschlafen ?")
                 .build();
    }
}

//"Sch�n, dass du da warst! Ich hoffe wir sehen uns bald wieder zu einem spannenden Spiel! Machs gut!