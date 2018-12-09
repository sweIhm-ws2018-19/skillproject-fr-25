package gehirnjogging.handlers;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class QuizStartIntent implements RequestHandler {
	{

	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("StartGameIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		SpeechStrings.getRandom();
		 return input.getResponseBuilder()
		            .withSpeech("Hier kommt Frage: " + 	SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][0] + "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_countdown_loop_64s_full_01'/>" )
		            .withReprompt("bist du eingeschlafen ?")
		            .build();
	}
}
