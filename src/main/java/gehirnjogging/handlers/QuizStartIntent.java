package gehirnjogging.handlers;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.SpeechStrings;

public class QuizStartIntent implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("StartGameIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		if (SpeechStrings.random >= 0) {
			SpeechStrings.getRandom();
			SpeechStrings.counter += 1;
			return input.getResponseBuilder()
					.withSpeech("Dann legen wir los! Hier kommt Frage " + SpeechStrings.counter + ": "
							+ SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][0])
					.withReprompt("m�chtet du, dass ich die Frage wiederhole?").build();
		} else {
			return input.getResponseBuilder()
					.withSpeech("<audio src='soundbank://soundlibrary/human/amzn_sfx_clear_throat_ahem_01'/>"
							+ " Leider gibt es keine Fragen mehr. Der Spieler hat " + SpeechStrings.richtig + " von "
							+ SpeechStrings.counter
							+ " Fragen richtig beantwortet. <audio src='soundbank://soundlibrary/human/amzn_sfx_crowd_applause_03'/>"
							+ "Sch�n, dass du da warst! Ich hoffe wir sehen uns bald wieder zu einem spannenden Spiel! Machs gut!")
					.withShouldEndSession(true).build();
		}
	}
}