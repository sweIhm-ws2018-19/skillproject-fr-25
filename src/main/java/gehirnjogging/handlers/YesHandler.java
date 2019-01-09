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
     * 0| Willkommen (Begr��ung)
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
        return input.matches(intentName("AMAZON.YesIntent"));	}

    @Override
    public Optional<Response> handle(HandlerInput input) {

        if(Logic.STATUS_ID==0) {
            Logic.EINSTELLUNGS_ID=1;
            Logic.STATUS_ID=3;
            return input.getResponseBuilder()
                    .withSpeech("M�chtest du alleine spielen oder hast du Freunde dabei?")
                    .withReprompt("M�chtest du alleine spielen. du kannst mit ja oder nein antworten")
                    .build();

        }else if(Logic.STATUS_ID==1) {
            return input.getResponseBuilder()
                    .withSpeech("M�chtest du etwas �ber den Spielablauf oder die Punktvergabe wissen?")
                    .withReprompt("bist du eingeschlafen ?")
                    .build();
        }else if(Logic.STATUS_ID==2 || Logic.STATUS_ID==5) {
            if(Logic.fragenWiederholung==2) {
                return input.getResponseBuilder()
                        .withSpeech("Tut mir leid, du hast dir bereits zwei mal die Frage wiederholen lassen. ein drittes mal sit gegen die regeln.")
                        .withReprompt("Bitte Antworte auf die Frage, wenn du es nicht wei�st kannst du versuchen zu raten")
                        .build();
            }
            Logic.fragenWiederholung++;
            return input.getResponseBuilder()
                    .withSpeech("Hier kommt noch ein mal die Frage " + Logic.counter + " " + Logic.questions[Logic.FRAGE_NUMBER][0])
                    .withReprompt("M�chtet du, dass ich die Frage wiederhole?")
                    .build();
        }else if(Logic.STATUS_ID==3) {
            if (Logic.EINSTELLUNGS_ID == 5) {
                return input.getResponseBuilder()
                        .withSpeech("Hier kommt noch ein mal die Frage " + Logic.counter + " " + Logic.questions[Logic.FRAGE_NUMBER][0])
                        .withReprompt("M�chtet du, dass ich die Frage wiederhole?")
                        .build();
            }
         	Logic.STATUS_ID=1;
            return input.getResponseBuilder()
                    .withSpeech("Ok super! M�chtest du vor dem Spielbeginn noch die Spielregeln h�ren ?")
                    .withReprompt("Ok super! M�chtest du vor dem Spielbeginn noch die Spielregeln h�ren ? du kannst mit ja oder nein antworten")
                    .build();
        }else if(Logic.STATUS_ID==6) {
            if(Logic.EINSTELLUNGS_COUNTER_R==1) {
                Logic.STATUS_ID=5;
            }else {
                Logic.STATUS_ID=3;
                Logic.EINSTELLUNGS_ID=5;
            }
            if (Logic.random >= 0) {
                Logic.getRandom();
                Logic.counter += 1;
                return input.getResponseBuilder()
                        .withSpeech("Dann legen wir los! Hier kommt Frage " + Logic.counter + " " + Logic.questions[Logic.FRAGE_NUMBER][0])
                        .withReprompt("M�chtet du, dass ich die Frage wiederhole? 121233213")
                        .build();
            } else {
                return input.getResponseBuilder()
                        .withSpeech("<audio src='soundbank://soundlibrary/human/amzn_sfx_clear_throat_ahem_01'/>" +" Leider gibt es keine Fragen mehr. Der Spieler hat " +  Logic.richtig + " von " + Logic.counter + " Fragen richtig beantwortet. <audio src='soundbank://soundlibrary/human/amzn_sfx_crowd_applause_03'/>" + "Sch�n, dass du da warst! Ich hoffe wir sehen uns bald wieder zu einem spannenden Spiel! Machs gut!")
                        .withShouldEndSession(true)
                        .build();
            }
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
                .withSpeech("Tut mir leid, diesen Input kann ich leider nicht verarbeiten" + Logic.STATUS_ID)
                .withReprompt("bist du eingeschlafen ?")
                .build();
    }
}