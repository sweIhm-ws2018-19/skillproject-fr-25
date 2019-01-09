package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;
import gehirnjogging.SpeechStrings;

public class AntwortHandler implements RequestHandler {


    private Request request;
    private IntentRequest intentRequest;
    private Intent intent;


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AntwortIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Logic.letzteAntwort = Logic.questions[Logic.FRAGE_NUMBER][2];
        //Einzelspieler
        if (Logic.EINSTELLUNGS_COUNTER_R == 1&&(Logic.STATUS_ID==2||Logic.STATUS_ID==5)) {
            request = input.getRequestEnvelope().getRequest();
            intentRequest = (IntentRequest) request;
            intent = intentRequest.getIntent();
            Logic.slots = intent.getSlots();

            Logic.setTrueAnser();
            Logic.checkAnswer();


            if (Logic.antwortRichtig == true) {
                if (Logic.random < 0) {
                    Logic.STATUS_ID=7;
                    return input.getResponseBuilder()
                            .withSpeech(" <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Toll, die Antwort war richtig." + " Leider gibt es keine Fragen mehr. Du hast bei " +  Logic.counter + " Fragen " + Logic.richtig + " Punkte erreicht <audio src='soundbank://soundlibrary/human/amzn_sfx_crowd_applause_03'/>" + "Möchtest du noch eine runde spielen ?")
                            .withReprompt("Wollt ihr noch eine runde spielen ? Du kannst mit ja oder nein Antworten")
                            .build();
                }
                Logic.counter += 1;
                Logic.getRandom();
                Logic.richtig++;
                Logic.zufallszahl();

                if(Logic.EINSTELLUNGS_COUNTER_R==1) {
                    Logic.STATUS_ID=5;
                }else {
                    Logic.STATUS_ID=3;
                    Logic.EINSTELLUNGS_ID=5;
                }
                if (Logic.richtigAntwortZahl == 1) {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Toll, die Antwort war richtig.  Hier kommt Frage " + Logic.counter + "<break time=\"1s\"/>"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                            .withReprompt("möchtest du die frage nocheinmal hören").build();
                } else if (Logic.richtigAntwortZahl == 2) {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Die Antwort ist richtig. Lass uns mit der nächsten Aufgabe weitermachen. Hier kommt Frage " + Logic.counter + "<break time=\"1s\"/>"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                            .withReprompt("möchtest du die frage nocheinmal hören").build();
                } else if (Logic.richtigAntwortZahl == 3) {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Super das Stimmt! Auf zur nächsten Frage. Hier kommt Frage " + Logic.counter + "<break time=\"1s\"/>"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                            .withReprompt("möchtest du die frage nocheinmal hören").build();
                } else {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Sehr gut! Auf zur nächsten Frage. Hier kommt Frage " + Logic.counter + "<break time=\"1s\"/>"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                            .withReprompt("möchtest du die frage nocheinmal hören").build();
                }
            } else {
                if (Logic.random < 0) {
                    Logic.STATUS_ID=7;
                    return input.getResponseBuilder()
                            .withSpeech("<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_02'/> Leider war die Antwort falsch. Die richtige Antwort würe " + Logic.letzteAntwort + " lauten. " + " Leider gibt es keine Fragen mehr. Du hast bei " +  Logic.counter + " Fragen " + Logic.richtig + " Punkte erreicht <audio src='soundbank://soundlibrary/human/amzn_sfx_crowd_applause_03'/>" + " Möchtest du noch eine runde spielen ?")
                            .withReprompt("Wollt ihr noch eine runde spielen ? Du kannst mit ja oder nein Antworten")
                            .build();
                }
                Logic.counter += 1;
                Logic.getRandom();
                return input.getResponseBuilder().withSpeech(
                        "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_02'/> Leider war die Antwort falsch. Die richtige Antwort würe "+ Logic.letzteAntwort +" lauten. Versuch es mit der nächsten Aufgabe gleich noch einmal. Hier kommt Frage " + Logic.counter + "<break time=\"1s\"/>"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                        .withReprompt("möchtest du die frage nocheinmal hören").build();
            }
        } else if(Logic.EINSTELLUNGS_COUNTER_R != 1&&(Logic.STATUS_ID==2||Logic.STATUS_ID==5)) {
            //Mehrspieler
            request = input.getRequestEnvelope().getRequest();
            intentRequest = (IntentRequest) request;
            intent = intentRequest.getIntent();
            Logic.slots = intent.getSlots();
            Logic.fragenWiederholung=0;
            Logic.setTrueAnser();
            Logic.checkAnswer();


            if(Logic.EINSTELLUNGS_COUNTER_R==1) {
                Logic.STATUS_ID=5;
            }else {
                Logic.STATUS_ID=3;
                Logic.EINSTELLUNGS_ID=5;
            }

            if (Logic.antwortRichtig == true) {
                if (Logic.random < 0) {
                    Logic.STATUS_ID=7;
                    return input.getResponseBuilder()
                            .withSpeech(" <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Toll, die Antwort war richtig. Leider gibt es keine Fragen mehr "+ Logic.scoreBewerten() + " Wollt ihr noch eine runde spielen ?")
                            .withReprompt("Wollt ihr noch eine runde spielen ? Du kannst mit ja oder nein Antworten")
                            .build();
                }
                Logic.givePoint();
                Logic.richtig++;
                Logic.zufallszahl();
                Logic.counter += 1;
                Logic.getRandom();

                if (Logic.richtigAntwortZahl == 1) {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Toll, die Antwort war richtig,  Hier kommt Frage " + Logic.counter + "<break time=\"1s\"/>"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                            .withReprompt("möchtet ihr die frage nocheinmal hören").build();
                } else if (Logic.richtigAntwortZahl == 2) {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Die Antwort ist richtig. Lass uns mit der nächsten Aufgabe weitermachen. Hier kommt Frage " + Logic.counter+ "<break time=\"1s\"/>"  +Logic.questions[Logic.FRAGE_NUMBER][0])
                            .withReprompt("möchtet ihr die frage nocheinmal hören").build();
                } else if (Logic.richtigAntwortZahl == 3) {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Super das Stimmt! Auf zur nächsten Frage. Hier kommt Frage " + Logic.counter + "<break time=\"1s\"/>"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                            .withReprompt("möchtet ihr die frage nocheinmal hören").build();
                } else {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Sehr gut! Auf zur nächsten Frage. Hier kommt Frage " + Logic.counter + "<break time=\"1s\"/>"  + Logic.questions[Logic.FRAGE_NUMBER][0])
                            .withReprompt("möchtet ihr die frage nocheinmal hören").build();
                }

            } else {
                Logic.erasePoint();
                if (Logic.random < 0) {
                    Logic.STATUS_ID=7;
                    return input.getResponseBuilder()
                            .withSpeech("<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_02'/> Leider war die Antwort falsch, hier für muss ich dir leider einen Punkt abziehen. Die richtige Antwort würe " + Logic.letzteAntwort + " lauten. Leider gibt es keine Fragen mehr " + Logic.scoreBewerten() + " Wollt ihr noch eine runde spielen ?")
                            .withReprompt("Wollt ihr noch eine runde spielen ? Du kannst mit ja oder nein Antworten")
                            .build();
                }
                Logic.counter += 1;
                Logic.getRandom();
                return input.getResponseBuilder().withSpeech(
                        "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_02'/> Leider war die Antwort falsch, hier für muss ich dir leider einen Punkt abziehen. Die richtige Antwort würe " + Logic.letzteAntwort + " lauten. Versuch es mit der nächsten Aufgabe gleich noch einmal. Hier kommt Frage " + Logic.counter + "<break time=\"1s\"/>"  +Logic.questions[Logic.FRAGE_NUMBER][0])
                        .withReprompt("möchtet ihr die frage nocheinmal hören").build();
            }
        }
        /**
         *    Logic.STATUS_ID
         * 0| Willkommen (BegräÃƒÅ¸ung)
         * 1| Bereit loszulegen oder Regeln
         * 2| Frage wird gestellt
         * 3| Spieleinstellungen
         * 4| 
         * 5| Antwort 
         * 6| Frage beantwortet
         * 7| Spielende
         */
        if(Logic.STATUS_ID==0) {
            return input.getResponseBuilder()
                    .withSpeech(SpeechStrings.WELCOME+", du kannst mit Ja oder Nein Antworten")
                    .withReprompt("Möchtest du alleine spielen oder hast du Freunde dabei ?")
                    .build();
        }else   if(Logic.STATUS_ID==3&&Logic.EINSTELLUNGS_ID==1) {
            return input.getResponseBuilder()
                    .withSpeech("Ich habe dich nicht verstanden Möchtest du alleine Spielen ? du kannst mit Ja oder Nein Antworten")
                    .withReprompt("Möchtest du alleine spielen?")
                    .build();
        }else if(Logic.STATUS_ID==3&&Logic.EINSTELLUNGS_ID==3) {
            return input.getResponseBuilder()
                    .withSpeech("Ich habe dich nicht verstanden. Wie viele Spieler seid ihr? Antworte mit 2 Spieler oder 3 Spieler ? ")
                    .withReprompt("Nenne mir die Spieler anzahl")
                    .build();
        }else if(Logic.STATUS_ID==4) {
            return input.getResponseBuilder()
                    .withSpeech("Error")
                    .withShouldEndSession(false)
                    .build(); 

        }else if(Logic.STATUS_ID==1&&Logic.EINSTELLUNGS_ID==2) {
            return input.getResponseBuilder()
                    .withSpeech("Ich habe dich nicht verstanden. Ok super! Möchtest du vor dem Spielbeginn noch die Spielregeln hören ? Antworte mit Ja oder nein")
                    .withReprompt("Antworte mit Ja oder nein")
                    .build();
        }else if (Logic.STATUS_ID==7) {
            return input.getResponseBuilder()
                    .withSpeech("Möchtest du noch eine Runde spielen?")
                    .withReprompt("Du kannst mit ja oder nein antworten")
                    .build();
        }
        return input.getResponseBuilder()
                .withSpeech("Spieler nicht gefunden bitte sage bevor du antwortest deinen Namen")
                .withShouldEndSession(false)
                .build();
    }
}