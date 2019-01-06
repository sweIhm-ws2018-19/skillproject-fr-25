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
        //Einzelspieler


        if((Logic.STATUS_ID == 2 || Logic.STATUS_ID == 5)){
            request = input.getRequestEnvelope().getRequest();
            intentRequest = (IntentRequest) request;
            intent = intentRequest.getIntent();
            Logic.slots = intent.getSlots();


            if(Logic.EINSTELLUNGS_COUNTER_R != 1){
                Logic.fragenWiederholung = 0;
            }


            Logic.setTrueAnser();
            Logic.checkAnswer();
            Logic.STATUS_ID = 6;

            if (Logic.antwortRichtig == true) {

                if(Logic.EINSTELLUNGS_COUNTER_R != 1){
                    Logic.givePoint();
                }

                Logic.richtig++;
                Logic.zufallszahl();

                if (Logic.richtigAntwortZahl == 1) {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Toll, die Antwort war richtig, wenn du weitermachen möchtest sage „nächste Aufgabe“")
                            .withReprompt("bist du eingeschlafen ?").build();
                } else if (Logic.richtigAntwortZahl == 2) {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Die Antwort ist richtig. Lass uns mit der nächsten Aufgabe weitermachen. Wenn du bereit bist sage „nächste Frage“")
                            .withReprompt("bist du eingeschlafen ?").build();
                } else if (Logic.richtigAntwortZahl == 3) {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Super das Stimmt! Auf zur nächsten Frage. Einverstanden?")
                            .withReprompt("bist du eingeschlafen ?").build();
                } else {
                    return input.getResponseBuilder().withSpeech(
                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Sehr gut! Auf zur nächsten Frage. Einverstanden?")
                            .withReprompt("bist du eingeschlafen ?").build();
                }
            } else {
                return input.getResponseBuilder().withSpeech(
                        "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_02'/> Leider war die Antwort falsch. Die richtige Antwort würde " + Logic.questions[Logic.FRAGE_NUMBER][2] + " lauten. Versuch es mit der nächsten Aufgabe gleich noch einmal. Bist du bereit")
                        .withReprompt("bist du eingeschlafen ?").build();
            }

        }
//
//
//        if (Logic.EINSTELLUNGS_COUNTER_R == 1 && (Logic.STATUS_ID == 2 || Logic.STATUS_ID == 5)) {
//            request = input.getRequestEnvelope().getRequest();
//            intentRequest = (IntentRequest) request;
//            intent = intentRequest.getIntent();
//            Logic.slots = intent.getSlots();
//
//            Logic.setTrueAnser();
//            Logic.checkAnswer();
//            Logic.STATUS_ID = 6;
//
//
//            if (Logic.antwortRichtig == true) {
//                Logic.richtig++;
//                Logic.zufallszahl();
//
//                if (Logic.richtigAntwortZahl == 1) {
//                    return input.getResponseBuilder().withSpeech(
//                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Toll, die Antwort war richtig, wenn du weitermachen möchtest sage „nächste Aufgabe“")
//                            .withReprompt("bist du eingeschlafen ?").build();
//                } else if (Logic.richtigAntwortZahl == 2) {
//                    return input.getResponseBuilder().withSpeech(
//                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Die Antwort ist richtig. Lass uns mit der nächsten Aufgabe weitermachen. Wenn du bereit bist sage „nächste Frage“")
//                            .withReprompt("bist du eingeschlafen ?").build();
//                } else if (Logic.richtigAntwortZahl == 3) {
//                    return input.getResponseBuilder().withSpeech(
//                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Super das Stimmt! Auf zur nächsten Frage. Einverstanden?")
//                            .withReprompt("bist du eingeschlafen ?").build();
//                } else {
//                    return input.getResponseBuilder().withSpeech(
//                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Sehr gut! Auf zur nächsten Frage. Einverstanden?")
//                            .withReprompt("bist du eingeschlafen ?").build();
//                }
//            } else {
//                return input.getResponseBuilder().withSpeech(
//                        "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_02'/> Leider war die Antwort falsch. Die richtige Antwort würde " + Logic.questions[Logic.FRAGE_NUMBER][2] + " lauten. Versuch es mit der nächsten Aufgabe gleich noch einmal. Bist du bereit")
//                        .withReprompt("bist du eingeschlafen ?").build();
//            }
//        } else if (Logic.EINSTELLUNGS_COUNTER_R != 1 && (Logic.STATUS_ID == 2 || Logic.STATUS_ID == 5)) {
//            //Mehrspieler
//            request = input.getRequestEnvelope().getRequest();
//            intentRequest = (IntentRequest) request;
//            intent = intentRequest.getIntent();
//            Logic.slots = intent.getSlots();
//
//            Logic.fragenWiederholung = 0;
//
//            Logic.setTrueAnser();
//            Logic.checkAnswer();
//            Logic.STATUS_ID = 6;
//
//            if (Logic.antwortRichtig == true) {
//                Logic.givePoint();
//                Logic.richtig++;
//                Logic.zufallszahl();
//
//                if (Logic.richtigAntwortZahl == 1) {
//                    return input.getResponseBuilder().withSpeech(
//                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Toll, die Antwort war richtig, wenn du weitermachen möchtest sage „nächste Aufgabe“")
//                            .withReprompt("bist du eingeschlafen ?").build();
//                } else if (Logic.richtigAntwortZahl == 2) {
//                    return input.getResponseBuilder().withSpeech(
//                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Die Antwort ist richtig. Lass uns mit der nächsten Aufgabe weitermachen. Wenn du bereit bist sage „nächste Frage“")
//                            .withReprompt("bist du eingeschlafen ?").build();
//                } else if (Logic.richtigAntwortZahl == 3) {
//                    return input.getResponseBuilder().withSpeech(
//                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Super das Stimmt! Auf zur nächsten Frage. Einverstanden?")
//                            .withReprompt("bist du eingeschlafen ?").build();
//                } else {
//                    return input.getResponseBuilder().withSpeech(
//                            " <audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_positive_response_02'/> Sehr gut! Auf zur nächsten Frage. Einverstanden?")
//                            .withReprompt("bist du eingeschlafen ?").build();
//                }
//
//            } else {
//                Logic.erasePoint();
//                return input.getResponseBuilder().withSpeech(
//                        "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_02'/> Leider war die Antwort falsch, hierfür muss ich dir leider einen Punkt abziehen. Die richtige Antwort würde " + Logic.questions[Logic.FRAGE_NUMBER][2] + " lauten. Versuch es mit der nächsten Aufgabe gleich noch einmal. Bist du bereit")
//                        .withReprompt("bist du eingeschlafen ?").build();
//            }
//        }
        /**
         *    Logic.STATUS_ID
         * 0| Willkommen (Begrüßung)
         * 1| Bereit loszulegen oder Regeln
         * 2| Frage wird gestellt
         * 3| Spieleinstellungen
         * 4| 
         * 5| Antwort 
         * 6| Frage beantwortet
         * 7| Spielende
         */

        switch (Logic.STATUS_ID) {
            case 0:
                return input.getResponseBuilder()
                        .withSpeech(SpeechStrings.WELCOME + ", du kannst mit Ja oder Nein Antworten")
                        .withReprompt("Möchtest du alleine spielen?")
                        .build();
            case 1:
                if (Logic.EINSTELLUNGS_ID == 2) {
                    return input.getResponseBuilder()
                            .withSpeech("Ich habe dich nicht verstanden. Ok super! Möchtest du vor dem Spielbeginn noch die Spielregeln hören ? Antworte mit Ja oder nein")
                            .withReprompt("Antworte mit Ja oder nein")
                            .build();
                }
                break;

            case 3:
                switch (Logic.EINSTELLUNGS_ID) {
                    case 1:
                        return input.getResponseBuilder()
                                .withSpeech("Ich habe dich nicht verstanden Möchtest du alleine Spielen ? du kannst mit Ja oder Nein Antworten")
                                .withReprompt("Möchtest du alleine spielen?")
                                .build();
                    case 2:
                        return input.getResponseBuilder()
                                .withSpeech("Ich habe dich nicht verstanden. Wie viele Fragen sollen gespielt werden ? Sagen Sie zum Beispiel Ich möchte 5 Fragen spielen")
                                .withReprompt("Sagen Sie zum Beispiel Ich möchte 5 Fragen spielen")
                                .build();
                    case 3:
                        return input.getResponseBuilder()
                                .withSpeech("Ich habe dich nicht verstanden. Wie viele Spieler seid ihr? Antworte mit 2 Spieler oder 3 Spieler ? ")
                                .withReprompt("Antworte mit 2 Spieler oder 3 Spieler ?")
                                .build();
                    default:
                        break;

                }
            case 4:
                return input.getResponseBuilder()
                        .withSpeech("Du machst gerade pause um weiter zu spielen sagen fortsetzten")
                        .withShouldEndSession(false)
                        .build();
            default:
                break;
        }
//
//        if (Logic.STATUS_ID == 0) {
//            return input.getResponseBuilder()
//                    .withSpeech(SpeechStrings.WELCOME + ", du kannst mit Ja oder Nein Antworten")
//                    .withReprompt("Möchtest du alleine spielen?")
//                    .build();
//        } else if (Logic.STATUS_ID == 3 && Logic.EINSTELLUNGS_ID == 1) {
//            return input.getResponseBuilder()
//                    .withSpeech("Ich habe dich nicht verstanden Möchtest du alleine Spielen ? du kannst mit Ja oder Nein Antworten")
//                    .withReprompt("Möchtest du alleine spielen?")
//                    .build();
//        } else if (Logic.STATUS_ID == 3 && Logic.EINSTELLUNGS_ID == 3) {
//            return input.getResponseBuilder()
//                    .withSpeech("Ich habe dich nicht verstanden. Wie viele Spieler seid ihr? Antworte mit 2 Spieler oder 3 Spieler ? ")
//                    .withReprompt("Antworte mit 2 Spieler oder 3 Spieler ?")
//                    .build();
//        } else if (Logic.STATUS_ID == 4) {
//            return input.getResponseBuilder()
//                    .withSpeech("Du machst gerade pause um weiter zu spielen sagen fortsetzten")
//                    .withShouldEndSession(false)
//                    .build();
//        } else if (Logic.STATUS_ID == 3 && Logic.EINSTELLUNGS_ID == 2) {
//            return input.getResponseBuilder()
//                    .withSpeech("Ich habe dich nicht verstanden. Wie viele Fragen sollen gespielt werden ? Sagen Sie zum Beispiel Ich möchte 5 Fragen spielen")
//                    .withReprompt("Sagen Sie zum Beispiel Ich möchte 5 Fragen spielen")
//                    .build();
//
//        } else if (Logic.STATUS_ID == 1 && Logic.EINSTELLUNGS_ID == 2) {
//            return input.getResponseBuilder()
//                    .withSpeech("Ich habe dich nicht verstanden. Ok super! Möchtest du vor dem Spielbeginn noch die Spielregeln hören ? Antworte mit Ja oder nein")
//                    .withReprompt("Antworte mit Ja oder nein")
//                    .build();
//        }
        return input.getResponseBuilder()
                .withSpeech("Spieler nicht gefunden bitte sage bevor du antwortest deinen Namen")
                .withShouldEndSession(false)
                .build();
    }
}