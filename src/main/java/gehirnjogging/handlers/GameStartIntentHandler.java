package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

public class GameStartIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("StarteIntent").or(intentName("AMAZON.YesIntent")))&&Logic.STATUS_ID==0&&Logic.STATUS_ID!=4;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
   	 Logic.EINSTELLUNGS_ID=1;
     Logic.STATUS_ID=3;
     return input.getResponseBuilder()
               .withSpeech("Möchtest du alleine spielen?")
               .withReprompt("bist du eingeschlafen ?")
               .build();
    }
}