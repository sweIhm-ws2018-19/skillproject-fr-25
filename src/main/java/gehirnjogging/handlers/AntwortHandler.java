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
    /*
     * DatumJahr
     * Antwort
     * Nummber
     * Sprache 
     * */

    @Override
    public Optional<Response> handle(HandlerInput input) {
        request = input.getRequestEnvelope().getRequest();
        intentRequest = (IntentRequest) request;
        intent = intentRequest.getIntent();
        slots = intent.getSlots();
        if(SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][2].equalsIgnoreCase("DatumJahr")) {
            slots.get("jahres_datum").getValue();
        }else if(SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][2].equalsIgnoreCase("Antwort")) {
            slots.get("antwort").getValue();
        }else if(SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][2].equalsIgnoreCase("Number")) {
            slots.get("antwort_number").getValue();
        }else if(SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][2].equalsIgnoreCase("Sprache")) {
            slots.get("antwort_sprache").getValue();
        }

        setTrueAnser();
        checkAnswer();
        if(antwortRichtig == true) {
            SpeechStrings.richtig++;
            return input.getResponseBuilder()
                    .withSpeech(" <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Sehr gut! Auf zur nächsten Frage. Einverstanden?")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        } 
        else {
            return input.getResponseBuilder()
                    .withSpeech("<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_02'/> Das war leider nicht richtig. Aber versuch es mit der nächsten Frage doch gleich noch einmal.")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        }
    }


    //RICHTIGE_ANTWORT_NUMBER setzten 
    void setTrueAnser() {
        SpeechStrings.RICHTIGE_ANTWORT =  SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][1];
    }

    void checkAnswer() {
        if(SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][2].equalsIgnoreCase("DatumJahr")) {

            if (slots.get("jahres_datum").getValue().equalsIgnoreCase(SpeechStrings.RICHTIGE_ANTWORT)) {
                antwortRichtig = true;
            } else {
                antwortRichtig = false;
            }
        }else if(SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][2].equalsIgnoreCase("Antwort")) {
            if (slots.get("antwort").getValue().equalsIgnoreCase(SpeechStrings.RICHTIGE_ANTWORT)) {
                antwortRichtig = true;
            } else {
                antwortRichtig = false;
            }
        }else if(SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][2].equalsIgnoreCase("Number")) {
            if (slots.get("antwort_number").getValue().equalsIgnoreCase(SpeechStrings.RICHTIGE_ANTWORT)) {
                antwortRichtig = true;
            } else {
                antwortRichtig = false;
            }
        }else if(SpeechStrings.questions[SpeechStrings.FRAGE_NUMBER][2].equalsIgnoreCase("Sprache")) {
            if (slots.get("antwort_sprache").getValue().equalsIgnoreCase(SpeechStrings.RICHTIGE_ANTWORT)) {
                antwortRichtig = true;
            } else {
                antwortRichtig = false;
            }
        } 
    }
}