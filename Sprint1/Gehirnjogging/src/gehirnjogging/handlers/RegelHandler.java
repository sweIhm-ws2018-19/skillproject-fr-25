package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;



public class RegelHandler implements RequestHandler { {

}

@Override
public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("RegelIntent"));
}

@Override
public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
            .withSpeech("Du bekommst eine Frage gestellt welche Sie beantworten müssen <break time=\"1s\"/> um das Quiz zu starten sage los !")
            .withReprompt("bist du eingeschlafen ?")
            .build();
}
}
