package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.SpeechStrings;

public class SpielablaufHelpHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SpielAblaufIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		SpeechStrings.getRandom();
		return input.getResponseBuilder().withSpeech(
				"Das Spiel beginnt damit, dass zuerst alle Mitspieler abgefragt werden. Gleich danach geht es los mit den ersten Fragen, je nachdem ob man alleine oder mit Freunden spielt werden nun die Fragen gestellt. Am Ende werden die Punkte verk�ndet und das Ranking im High Score mitgeteilt. Wenn man m�chte kann man gleich anschlie�end eine neue Runde beginnen oder das Spiel beenden. W�hrend des Spiels kann die Runde jederzeit unterbrochen oder abgebrochen werden.  Wenn du keine weiteren Fragen hast sage los")
				.withReprompt("bist du eingeschlafen ?").build();
	}
}
