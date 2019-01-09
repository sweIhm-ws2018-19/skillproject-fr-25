package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

public class NoHandler implements RequestHandler {

    /**
     *    Logic.STATUS_ID
     * 0| Willkommen (Begrüßung)
     * 1| Bereit loszulegen oder Regeln
     * 2| Frage wird gestellt
     * 3| Spieleinstellungen
     * 4| 
     * 5| Antwort
     * 6| Platzhalter
     * 7| Spielende
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.NoIntent"));	
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        if(Logic.STATUS_ID==0) {
            Logic.STATUS_ID=1;
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            persistentAttributes.clear();
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
            return input.getResponseBuilder()
                    .withSpeech("Schade, dass du doch nicht spielen möchtest, ich hoffe, dass du bald wiederkommst")
                    .withReprompt("bist du eingeschlafen ?")
                    .withShouldEndSession(true)
                    .build();
        } else if (Logic.STATUS_ID==1) {
            if(Logic.EINSTELLUNGS_COUNTER_R==1) {
                Logic.STATUS_ID=5;
                Logic.EINSTELLUNGS_ID=5;
            }else {
                Logic.STATUS_ID=3;
                Logic.EINSTELLUNGS_ID=5;// TEST!
                
            }

            if (Logic.random >= 0) {
                Logic.getRandom();
                Logic.counter += 1;
                Logic.fragenWiederholung=0;
                return input.getResponseBuilder()
                        .withSpeech("Dann legen wir los! Hier kommt Frage " + Logic.counter + " " + Logic.questions[Logic.FRAGE_NUMBER][0])
                        .withReprompt("möchtet du, dass ich die Frage wiederhole?"+"  "+Logic.STATUS_ID+"     "+Logic.EINSTELLUNGS_COUNTER)
                        .build();
            }else if(Logic.STATUS_ID==2) {
                return input.getResponseBuilder()
                        .withSpeech("gut dann lasse ich dir noch Zeit zum Nachdeken wenn du doch die Frage nocheinmal hören willst sage Frage wiederholen")
                        .withReprompt("bist du eingeschlafen ?")
                        .build();
            }
        }else if(Logic.STATUS_ID==3) {
            Logic.EINSTELLUNGS_ID=3;
            return input.getResponseBuilder()
                    .withSpeech("Wie viele Spieler seid ihr?")
                    .withReprompt("Antworte mit 2 spieler oder drei spieler")
                    .build();

        }else if(Logic.STATUS_ID==4) {
            return input.getResponseBuilder()
                    .withSpeech("")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        }else if(Logic.STATUS_ID==5) {
            return input.getResponseBuilder()
                    .withSpeech("")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        }else if(Logic.STATUS_ID==6) {
            return input.getResponseBuilder()
                    .withSpeech("")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();

        }else if(Logic.STATUS_ID==7) {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            persistentAttributes.clear();
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
            return input.getResponseBuilder()
                    .withSpeech("Schön das du da warst Ich hoffe wir sehen uns bald wieder")
                    .withReprompt("bist du eingeschlafen ?")
                    .withShouldEndSession(true)
                    .build();
        }
        return input.getResponseBuilder()
                .withSpeech("Diesen Input konnte ich nicht verarbeiten")
                .withReprompt("Sage Hilfe wenn du nicht mehr weiter kommst")
                .build();
    }
    //		  return input.getResponseBuilder()
    //		            .withSpeech("" )
    //		            .withReprompt("bist du eingeschlafen ?")
    //		            .build();




}