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
import gehirnjogging.Logic;

public class MehrspielerHandler implements RequestHandler {
    private Request request;
    private IntentRequest intentRequest;
    private Intent intent;
    private Map<String, Slot> slots = null;

    @Override
    public boolean canHandle(HandlerInput input) {
    	return input.matches(intentName("EinstellungsIntent"))&&(Logic.STATUS_ID==2 || Logic.STATUS_ID==3);
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        request = input.getRequestEnvelope().getRequest();
        intentRequest = (IntentRequest) request;
        intent = intentRequest.getIntent();
        slots = intent.getSlots();

        
        if(Logic.EINSTELLUNGS_ID==0) {
            Logic.EINSTELLUNGS_ID=1;
            Logic.STATUS_ID=1;
            return input.getResponseBuilder()
                    .withSpeech("M�chtest du alleine spielen?")
                    .withReprompt("Du kannst mit ja oder nein antworten")
                    .build();

        }else if(Logic.EINSTELLUNGS_ID==1) {
            if(slots.get("AlleineOdNicht").getValue().contains("ja")||slots.get("AlleineOdNicht").getValue().contains("ich m�chte alleine spielen")) {
            	Logic.STATUS_ID=1;
                return input.getResponseBuilder()
                        .withSpeech("Ok super! M�chtest du vor dem Spielbeginn noch die Spielregeln h�ren ?")
                        .withReprompt("Ok super! M�chtest du vor dem Spielbeginn noch die Spielregeln h�ren ? du kannst mit ja oder nein antworten")
                        .build();
            } else  if(slots.get("AlleineOdNicht").getValue().contains("nein")||slots.get("AlleineOdNicht").getValue().contains("ich m�chte nicht alleine spielen")) {
                Logic.EINSTELLUNGS_ID=3;
                return input.getResponseBuilder()
                        .withSpeech("Wie viele Spieler seid ihr? Antworte mit 2 Spieler oder 3 Spieler")
                        .withReprompt("bist du eingeschlafen ?")
                        .build();
            }  else {
                return input.getResponseBuilder()
                        .withSpeech("Tut mir leid ich habe sie nicht verstanden, m�chten sie alleine spielen ? sie k�nnen mit ja oder nein antworten")
                        .withReprompt("bist du eingeschlafen ?")
                        .build();
            }

        }else if(Logic.EINSTELLUNGS_ID==3) {

            if(Logic.EINSTELLUNGS_COUNTER==0) {
                if(Integer.parseInt(slots.get("SpielerAnzahlNumber").getValue())>3) {
                    return input.getResponseBuilder()
                            .withSpeech("Ihre eingabe war ung�ltig. Sie k�nnen nur zu zweit oder zu drit spielen")
                            .withReprompt("bist du eingeschlafen ?")
                            .build(); 
                }
                Logic.EINSTELLUNGS_COUNTER=Integer.parseInt(slots.get("SpielerAnzahlNumber").getValue());
                return input.getResponseBuilder()
                        .withSpeech("Hallo Spieler " + Logic.EINSTELLUNGS_COUNTER_R +" um dich bei dem Quiz zu registrieren sage  Spieler " + Logic.EINSTELLUNGS_COUNTER_R + " und deinen Vornamen" )
                        .withReprompt("bist du eingeschlafen ?")
                        .build();


            }
            if(Logic.EINSTELLUNGS_COUNTER_R==1) {
                Logic.player1=slots.get("EigeneNamen").getValue();
            }else if(Logic.EINSTELLUNGS_COUNTER_R==2) {
                Logic.player2=slots.get("EigeneNamen").getValue();
            }else if(Logic.EINSTELLUNGS_COUNTER_R==3){
                Logic.player3=slots.get("EigeneNamen").getValue();
            }
            Logic.EINSTELLUNGS_COUNTER_R++;
            if(Logic.EINSTELLUNGS_COUNTER_R <= Logic.EINSTELLUNGS_COUNTER) {
                return input.getResponseBuilder()
                        .withSpeech("Hallo Spieler " + Logic.EINSTELLUNGS_COUNTER_R +", um dich bei dem Quiz zu registrieren sage  Spieler " + Logic.EINSTELLUNGS_COUNTER_R + " und deinen Vornamen" )
                        .withReprompt("bist du eingeschlafen ?")
                        .build();
            }
            Logic.STATUS_ID=1;
            return input.getResponseBuilder()
                    .withSpeech("Ok super! M�chtest du vor dem Spielbeginn noch die Spielregeln h�ren ?")
                    .withReprompt("Ok super! M�chtest du vor dem Spielbeginn noch die Spielregeln h�ren ? du kannst mit ja oder nein antworten")
                    .build();

        } else if(Logic.EINSTELLUNGS_ID==2) {
            if(Logic.STATUS_ID==3&&Logic.EINSTELLUNGS_ID==1) {
                return input.getResponseBuilder()
                        .withSpeech("Ich habe dich nicht verstanden M�chtest du alleine Spielen ? du kannst mit Ja oder Nein Antworten")
                        .withReprompt("M�chtest du alleine spielen?")
                        .build();
            }else if(Logic.STATUS_ID==3&&Logic.EINSTELLUNGS_ID==3) {
                return input.getResponseBuilder()
                        .withSpeech("Ich habe dich nicht verstanden. Wie viele Spieler seid ihr? Antworte mit 2 Spieler oder 3 Spieler ? ")
                        .withReprompt("Antworte mit 2 Spieler oder 3 Spieler ?")
                        .build();
            }else if(Logic.STATUS_ID==4) {
                return input.getResponseBuilder()
                        .withSpeech("Du machst gerade pause um weiter zu spielen sagen fortsetzten")
                        .withShouldEndSession(false)
                        .build(); 
            }else if(Logic.STATUS_ID==3&&Logic.EINSTELLUNGS_ID==2) {
                return input.getResponseBuilder()
                        .withSpeech("Ich habe dich nicht verstanden. Wie viele Fragen sollen gespielt werden ? Sagen Sie zum Beispiel Ich m�chte 5 Fragen spielen")
                        .withReprompt("Sagen Sie zum Beispiel Ich m�chte 5 Fragen spielen")
                        .build();

            }else if(Logic.STATUS_ID==1&&Logic.EINSTELLUNGS_ID==2) {
                return input.getResponseBuilder()
                        .withSpeech("Ich habe dich nicht verstanden. Ok super! M�chtest du vor dem Spielbeginn noch die Spielregeln h�ren ? Antworte mit Ja oder nein")
                        .withReprompt("Antworte mit Ja oder nein")
                        .build();
            }

        } else if(Logic.EINSTELLUNGS_ID==5) {
            if(slots.get("EigeneNamen").getValue().equalsIgnoreCase(Logic.player1)) {
                Logic.currentPlayer=Logic.player1;
                Logic.STATUS_ID=5;
            }else if(slots.get("EigeneNamen").getValue().equalsIgnoreCase(Logic.player2)) {
                Logic.currentPlayer=Logic.player2;
                Logic.STATUS_ID=5;
            }else if(slots.get("EigeneNamen").getValue().equalsIgnoreCase(Logic.player3)) {
                Logic.currentPlayer=Logic.player3;
                Logic.STATUS_ID=5;
            }else {
                return input.getResponseBuilder()
                        .withSpeech("Fehler!! Spieler nicht gefunden")
                        .withReprompt("Bitte sage noch ein mal deinen Namen")
                        .build();
            }
            return input.getResponseBuilder()
                    .withSpeech(Logic.currentPlayer + " nenne mir nun deine Antwort!")
                    .withReprompt("Bitte sage noch ein mal deinen Namen")
                    .build();
        } 
        return input.getResponseBuilder()
                .withSpeech("Ich habe dich leider nicht verstanden bitte wiederhole deine eingabe")
                .withReprompt("Wenn du hilfe brauchst sage hilfe ?")
                .build();

    }
}