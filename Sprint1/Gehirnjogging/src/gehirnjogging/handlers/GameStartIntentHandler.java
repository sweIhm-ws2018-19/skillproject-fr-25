package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class GameStartIntentHandler implements RequestHandler {
	{

	}
	String test;

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("StarteIntent"));
	}

@Override
public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
            .withSpeech("Gehirnjogging wird gestartet. Kennst du bereits die Spielregeln ?  Wenn nicht sage Regeln erklären oder los wenn sie Hilfe benötigen sagen sie Hilfe")
            .withReprompt("bist du eingeschlafen ?")
            .build();
}

}
