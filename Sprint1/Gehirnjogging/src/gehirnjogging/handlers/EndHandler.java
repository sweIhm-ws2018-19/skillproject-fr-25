package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class EndHandler implements RequestHandler { {

}

@Override
public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("EndIntent"));
}

@Override
public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
            .withSpeech("Schön das du da warst, es hat sehr viel spaß gemacht mit dir zu spielen. Ich wünsche dir noch einen schönen Tag")
            .withReprompt("bist du eingeschlafen ?")
            .withShouldEndSession(true)
            .build();
}
}
