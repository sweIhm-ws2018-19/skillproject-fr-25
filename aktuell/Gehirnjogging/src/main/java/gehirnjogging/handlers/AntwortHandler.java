package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import gehirnjogging.SpeechStrings;

public class AntwortHandler implements RequestHandler {

    boolean antwortRichtig=true;
    private Request request;
    private IntentRequest intentRequest;
    private Intent intent;
    private Map<String, Slot> slots = null;

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AntwortIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        request = input.getRequestEnvelope().getRequest();
        intentRequest = (IntentRequest) request;
        intent = intentRequest.getIntent();
        slots = intent.getSlots();
        slots.get("antwort").getValue();

        setTrueAnser();
        checkAnswer();
        if(antwortRichtig == true) {
            return input.getResponseBuilder()
                    .withSpeech(" <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_01'/> Diese Antwort ist richtig. Sagen sie nächste Frage um die nächste Frage zu erhalten oder Beenden wenn du keine Lust mehr hast")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        } 
        else {
            return input.getResponseBuilder()
                    .withSpeech("<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_01'/> Diese Antwort ist falsch. Sagen sie nächste Frage um die nächste Frage zu erhalten oder Beenden wenn du keine Lust mehr hast")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        }
    }


    //RICHTIGE_ANTWORT_NUMBER setzten 
    void setTrueAnser() {
        SpeechStrings.RICHTIGE_ANTWORT =  SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][1];
    }

    void checkAnswer() {
        if (slots.get("antwort").getValue().equalsIgnoreCase(SpeechStrings.RICHTIGE_ANTWORT)) {
            antwortRichtig = true;
            SpeechStrings.richtig += 1;
            SpeechStrings.counter += 1;
        } else {
            antwortRichtig = false;
            SpeechStrings.counter += 1;
        }
    }
}