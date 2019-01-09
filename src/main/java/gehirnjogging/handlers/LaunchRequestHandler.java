/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
 */

package gehirnjogging.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import gehirnjogging.Logic;
import gehirnjogging.SpeechStrings;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

        if (persistentAttributes.get("pause") == null || !(persistentAttributes.get("pause").equals("yes"))) {
            String welcome = Logic.pickPhrase(SpeechStrings.WELCOME);
            Logic.newGame();
            Logic.initializeNumbers();
            Logic.initializeQuestions();
            Logic.STATUS_ID = 0;
            Logic.inizialPoints();
            return input.getResponseBuilder().withSpeech(welcome).withReprompt("bist du bist du bereit ?").build();
        } else {

            //***********STRINGS***********
            if(persistentAttributes.get("player1")!=null) {
                Logic.player1=(String) persistentAttributes.get("player1"); 
            }
            if(persistentAttributes.get("player2")!=null) {
                Logic.player2=(String) persistentAttributes.get("player2"); 
            }
            if(persistentAttributes.get("player3")!=null) {
                Logic.player3=(String) persistentAttributes.get("player3");
            }
            if(persistentAttributes.get("currentPlayer")!=null) {
                Logic.currentPlayer=(String) persistentAttributes.get("currentPlayer");
            }
            if(persistentAttributes.get("RICHTIGE_ANTWORT")!=null) {
                Logic.RICHTIGE_ANTWORT=(String) persistentAttributes.get("RICHTIGE_ANTWORT");
            }

            //***********INTEGER***********
            Logic.STATUS_ID=Integer.parseInt((String) persistentAttributes.get("STATUS_ID"));
            Logic.EINSTELLUNGS_ID=Integer.parseInt((String) persistentAttributes.get("EINSTELLUNGS_ID"));
            Logic.EINSTELLUNGS_COUNTER=Integer.parseInt((String) persistentAttributes.get("EINSTELLUNGS_COUNTER"));
            Logic.EINSTELLUNGS_COUNTER_R=Integer.parseInt((String) persistentAttributes.get("EINSTELLUNGS_COUNTER_R"));
            Logic.fragenWiederholung=Integer.parseInt((String) persistentAttributes.get("fragenWiederholung"));
            Logic.FRAGE_NUMBER=Integer.parseInt((String) persistentAttributes.get("FRAGE_NUMBER"));
            Logic.richtig=Integer.parseInt((String) persistentAttributes.get("richtig"));
            Logic.counter=Integer.parseInt((String) persistentAttributes.get("counter"));
            Logic.fragenZahl=Integer.parseInt((String) persistentAttributes.get("fragenZahl"));
            Logic.random=Integer.parseInt((String) persistentAttributes.get("random"));
            Logic.richtigAntwortZahl=Integer.parseInt((String) persistentAttributes.get("richtigAntwortZahl"));

            //***********BOOLEAN***********
            Logic.antwortRichtig=Boolean.parseBoolean((String) persistentAttributes.get("antwortRichtig"));

            //***********Array***********
            Logic.questions = new String[Logic.size][3];
            Logic.initializeQuestions();
            Logic.numbers = new ArrayList<Integer>();
            Logic.points = new int[3];

            for( int i = 0; i < 3; i++) {
                Logic.points[i] =Integer.parseInt((String) persistentAttributes.get("points_" + i));
            }

            for( int i = 0; i <= Logic.random; i++) {
                Logic.numbers.add(Integer.parseInt((String) persistentAttributes.get("number_" + i)));
            }

            switch (Logic.STATUS_ID) {
            case 1:
                return input.getResponseBuilder()
                        .withSpeech("Möchtest du etwas über den Spielablauf oder die Punktvergabe wissen?")
                        .withShouldEndSession(false)
                        .build();
            case 2:
                return input.getResponseBuilder()
                        .withSpeech("Hier kommt noch ein mal die Frage " + Logic.counter + " " + Logic.questions[Logic.FRAGE_NUMBER][0])
                        .withShouldEndSession(false)
                        .build();
            case 3:
                if (Logic.EINSTELLUNGS_ID == 1) {
                    return input.getResponseBuilder()
                            .withSpeech("Möchtest du alleine spielen? oder hast du Freunde dabei")
                            .withShouldEndSession(false)
                            .build();
                } else if (Logic.EINSTELLUNGS_ID == 2) {
                    return input.getResponseBuilder()
                            .withSpeech("Wie viele Fragen sollen gespielt werden ? Sagen Sie zum Beispiel Ich möchte 5 Fragen spielen")
                            .withShouldEndSession(false)
                            .build();
                } else if (Logic.EINSTELLUNGS_ID == 5) {
                	  return input.getResponseBuilder()
                              .withSpeech("Hier kommt noch ein mal die Frage " + Logic.counter + " " + Logic.questions[Logic.FRAGE_NUMBER][0])
                              .withShouldEndSession(false)
                              .build();
                } else {
                    return input.getResponseBuilder()
                            .withSpeech("Wie viele Spieler seid ihr? Antworte mit 2 Spieler oder 3 Spieler")
                            .withShouldEndSession(false)
                            .build();
                }
            case 5:
                return input.getResponseBuilder()
                        .withSpeech("Hier kommt noch ein mal die Frage " + Logic.counter + " " + Logic.questions[Logic.FRAGE_NUMBER][0])
                        .withShouldEndSession(false)
                        .build();
            default:
                return input.getResponseBuilder()
                        .withSpeech("Tut mir leid ich habe dich ledier nicht verstanden.")
                        .build();
            }
        }
    }
}