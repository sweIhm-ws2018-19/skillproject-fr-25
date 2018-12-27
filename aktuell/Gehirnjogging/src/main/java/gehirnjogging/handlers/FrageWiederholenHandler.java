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
        return input.matches(intentName("FrageWiederholenIntent"))&&Logic.STATUS_ID==2;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
   	 return input.getResponseBuilder()
	            .withSpeech("Hier kommt noch ein mal die Frage " + Logic.counter + ": " + Logic.questions[Logic.FRAGE_NUMBER][0])
	            .withReprompt("Möchtet du, dass ich die Frage wiederhole?")
	            .build();
}
}
