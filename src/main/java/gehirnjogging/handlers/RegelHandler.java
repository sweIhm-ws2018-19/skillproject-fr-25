package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

public class RegelHandler implements RequestHandler { 

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RegelIntent").or(intentName("AMAZON.HelpIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder()
                .withSpeech("Möchtest du etwas über den Spielablauf oder die Punktvergabe wissen?")
                .withReprompt("Möchtest du etwas über den Spielablauf oder die Punktvergabe wissen?")
                .build();
    }
}
