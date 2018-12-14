package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.SpeechStrings;

public class PunktvergabeHelpHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("PunkteVergabeIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		SpeechStrings.getRandom();
		return input.getResponseBuilder().withSpeech(
				"Die Punktvergabe ist ganz einfach, für jede richtige Antwort gibt es einen Punkt. Bei mehreren Spielern darf derjenige zu erst Antworten der als erstes seinen Namen gesagt hat. Bei einer richtigen Antwort erhält derjenige einen Punkt, bei einer falschen Antwort wird die Frage an den nächsten Spieler weitergegeben oder wahlweiße zur nächsten Frage übergegangen. Hast du sonst noch Fragen?")
				.withReprompt("bist du eingeschlafen ?").build();
	}
}
