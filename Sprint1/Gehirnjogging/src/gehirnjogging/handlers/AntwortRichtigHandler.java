package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class AntwortRichtigHandler implements RequestHandler { {

}

@Override
public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("AntwortIntentRichtig"));
}

@Override
public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
            .withSpeech(" <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_01'/> Diese Antwort ist Richtig, dies war der Prototype bitte sagen sie Ich habe keine Lust mehr um den Alexa skill zu schlieﬂen")
            .withReprompt("bist du eingeschlafen ?")
            .build();
}
}
