package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;
import gehirnjogging.SpeechStrings;

public class YesHandler implements RequestHandler {

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
        return input.matches(intentName("AMAZON.YesIntent"))&&Logic.STATUS_ID!=0;	}

    @Override
    public Optional<Response> handle(HandlerInput input) {

        if(Logic.STATUS_ID==0) {
            Logic.EINSTELLUNGS_ID=1;
            Logic.STATUS_ID=3;
            return input.getResponseBuilder()
                    .withSpeech("Möchtest du alleine spielen?")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();

        }else if(Logic.STATUS_ID==1) {
            return input.getResponseBuilder()
                    .withSpeech("Möchtest du etwas über den Spielablauf oder die Punktvergabe wissen?")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        }else if(Logic.STATUS_ID==2) {
            return input.getResponseBuilder()
                    .withSpeech("Hier kommt nocheinmal die Frage " + Logic.counter + ": " + Logic.questions[Logic.FRAGE_NUMBER][0])
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        }else if(Logic.STATUS_ID==3) {
            Logic.EINSTELLUNGS_ID=2;
            return input.getResponseBuilder()
                    .withSpeech("Wie viele Fragen sollen gespielt werden ? sagen sie zb. ich möchte 5 Fragen spielen")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        }else if(Logic.STATUS_ID==4) {

        }else if(Logic.STATUS_ID==5) {

        }else if(Logic.STATUS_ID==6) {

        }else if(Logic.STATUS_ID==7) {
            String welcome = Logic.pickPhrase(SpeechStrings.WELCOME);
            Logic.newGame();
            Logic.initializeNumbers();
            Logic.initializeQuestions();
            Logic. STATUS_ID = 0;
            Logic.EINSTELLUNGS_ID=0;
            return input.getResponseBuilder()
                    .withSpeech(welcome)
                    .withReprompt("bist du eingeschlafen ?")
                    .build(); 
        }
        return input.getResponseBuilder()
                .withSpeech("Tut mir leide, diesen Input kann ich leider nicht verarbeiten")
                .withReprompt("bist du eingeschlafen ?")
                .build();
    }
}