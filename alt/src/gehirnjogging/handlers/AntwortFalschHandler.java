package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class AntwortFalschHandler implements RequestHandler { {

}

@Override
public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("AntwortIntentFalsch"));
}

@Override
public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
            .withSpeech("<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_01'/> Diese Antwort ist Falsch, dies war der Prototypen bitte sagen sie Ich habe keine Lust mehr um den Alexa skill zu schlieﬂen")
            .withReprompt("bist du eingeschlafen ?")
            .build();
}
}
