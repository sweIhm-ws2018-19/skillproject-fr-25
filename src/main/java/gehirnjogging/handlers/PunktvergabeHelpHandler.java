package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;


public class PunktvergabeHelpHandler  implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("PunkteVergabeIntent"))&&Logic.STATUS_ID!=4;
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		 Logic.getRandom();
		 return input.getResponseBuilder()
		            .withSpeech("Die Punktvergabe ist ganz einfach, f�r jede richtige Antwort gibt es einen Punkt. Bei mehreren Spielern darf derjenige zu erst Antworten der als erstes seinen Namen gesagt hat. Bei einer richtigen Antwort erh�lt derjenige einen Punkt, bei einer falschen Antwort wird die Frage an den n�chsten Spieler weitergegeben oder wahlwei�e zur n�chsten Frage �bergegangen. Hast du sonst noch Fragen?" )
		            .withReprompt("bist du eingeschlafen ?")
		            .build();
	}
}
