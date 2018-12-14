package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.SpeechStrings;

public class ScoreHandler implements RequestHandler {
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("ScoreIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		return input.getResponseBuilder()
				.withSpeech("Sie haben derzeit von " + SpeechStrings.counter + " Fragen" + SpeechStrings.richtig
						+ " Richtig beantwortet um weiter zu spielen sagen sie los")
				.withReprompt("bist du eingeschlafen ?").build();
	}
}