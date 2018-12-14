package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.SpeechStrings;

public class EndHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("EndIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		if (SpeechStrings.counter == 0) {
			return input.getResponseBuilder()
					.withSpeech("<audio src='soundbank://soundlibrary/human/amzn_sfx_baby_cry_01'/>"
							+ "Schade, dass du doch nicht spielen m�chtest, ich hoffe, dass du bald wiederkommst")
					.withReprompt("bist du eingeschlafen ?").withShouldEndSession(true).build();
		}
		return input.getResponseBuilder()
				.withSpeech("<audio src='soundbank://soundlibrary/human/amzn_sfx_clear_throat_ahem_01'/>"
						+ "Der Spieler hat " + SpeechStrings.richtig + " von " + SpeechStrings.counter
						+ " Fragen richtig beantwortet. <audio src='soundbank://soundlibrary/human/amzn_sfx_crowd_applause_03'/>"
						+ "Sch�n, dass du da warst! Ich hoffe wir sehen uns bald wieder zu einem spannenden Spiel! Machs gut!")
				.withReprompt("bist du eingeschlafen ?").withShouldEndSession(true).build();
	}
}