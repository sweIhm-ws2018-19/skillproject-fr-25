package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

public class PauseHandler implements RequestHandler { 

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("Pause"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        
 
    	Logic.EINSTELLUNGS_ID_OLD = Logic.EINSTELLUNGS_ID;
        Logic.STATUS_ID_OLD = Logic.STATUS_ID;
        Logic.STATUS_ID = 4;
        return input.getResponseBuilder()
                .withSpeech("Das Spiel wird solange pausiert, bis sie fortsetzen sagen")
                .withShouldEndSession(false)
                .build();
    }
}