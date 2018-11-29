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
		 return input.getResponseBuilder()
		            .withSpeech("Hier kommt die erste Frage:  Wie hieﬂ der erste Amerikanische Pr‰sident der USA  Ist es Antwort A <break time=\"1s\"/> George Washington oder Antwort B <break time=\"1s\"/> James Madison oder Antwort C <break time=\"1s\"/> George W. Bush oder Antwort D Abraham Lincoln. <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_countdown_loop_64s_full_01'/>" )
		            .withReprompt("bist du eingeschlafen ?")
		            .build();
	}
}
