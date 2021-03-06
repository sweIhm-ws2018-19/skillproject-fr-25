package gehirnjogging.handlers;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

public class QuizStartIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("StartGameIntent"))&&Logic.STATUS_ID!=0&&Logic.STATUS_ID!=3&&Logic.STATUS_ID!=2&&Logic.STATUS_ID!=4;
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		if(Logic.EINSTELLUNGS_COUNTER_R==1) {
			Logic.STATUS_ID=5;
		}else {
			Logic.STATUS_ID=3;
			Logic.EINSTELLUNGS_ID=5;
		}

	    if (Logic.random >= 0) {
	        Logic.getRandom();
	        Logic.counter += 1;
	         return input.getResponseBuilder()
	                    .withSpeech("Dann legen wir los! Hier kommt Frage " + Logic.counter + " " + Logic.questions[Logic.FRAGE_NUMBER][0])
	                    .withReprompt("M�chtet du, dass ich die Frage wiederhole?")
	                    .build();
	    } else {
	    	if(Logic.EINSTELLUNGS_COUNTER_R==1) {
	    		Logic.STATUS_ID=7;
	        return input.getResponseBuilder()
	                .withSpeech("Leider gibt es keine Fragen mehr. Du hast bei  "+  Logic.counter + " Fragen " + Logic.richtig + " Punkte erreicht <audio src='soundbank://soundlibrary/human/amzn_sfx_crowd_applause_03'/>" + "M�chtest du noch eine runde spielen ?")
	                .withReprompt("bist du eingeschlafen ?")
	                .build();
	    	}
	    	Logic.STATUS_ID=7;
	    	 return input.getResponseBuilder()
	                 .withSpeech("Leider gibt es keine Fragen mehr"+Logic.scoreBewerten() + "Wollt ihr noch eine runde spielen ?")
	                 .withReprompt("Wollt ihr noch eine runde spielen ? Du kannst mit ja oder nein Antworten")
	                 .build();
	    }
	}
}