package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;


public class FrageWiederholenHandler  implements RequestHandler { 

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("FrageWiederholenIntent"))&&Logic.STATUS_ID==5;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	if(Logic.fragenWiederholung==2) {
    	  	 return input.getResponseBuilder()
    		            .withSpeech("Tut mir leid, du hast dir bereits zwei mal die Frage wiederholen lassen. ein drittes mal sit gegen die regeln.")
    		            .withReprompt("Bitte Antworte auf die Frage, wenn du es nicht weist kannst du versuchen zu raten")
    		            .withShouldEndSession(false)
    		            .build();
    	}
     Logic.fragenWiederholung++;
   	 return input.getResponseBuilder()
	            .withSpeech("Hier kommt noch ein mal die Frage " + Logic.counter + ": " + Logic.questions[Logic.FRAGE_NUMBER][0])
	            .withReprompt("Möchtet du, dass ich die Frage wiederhole? 78")
	            .build();
}
}
