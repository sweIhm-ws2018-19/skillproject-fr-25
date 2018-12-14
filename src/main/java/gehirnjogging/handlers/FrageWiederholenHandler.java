package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.SpeechStrings;

public class FrageWiederholenHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("FrageWiederholenIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		return input.getResponseBuilder()
				.withSpeech("Hier kommt nocheinmal die Frage " + SpeechStrings.counter + ": "
						+ SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][0])
				.withReprompt("bist du eingeschlafen ?").build();
	}
}
