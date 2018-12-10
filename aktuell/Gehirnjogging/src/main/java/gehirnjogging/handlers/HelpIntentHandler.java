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

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {
    
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder()
                .withSpeech("Willkommen bei der Train Your Brain Hilfe Abteilung ? <break time=\"1s\"/> Ich werde Ihnen nun nocheinmal die M�glichkeiten mitteilen wie Sie weiter machen k�nnen. <break time=\"1s\"/> Sagen sie Regeln erkl�ren um sich noch einmal die Spielregeln erkl�ren zu lassen <break time=\"1s\"/> oder los um das Quiz zu starten <break time=\"1s\"/> oder Beenden  um den Skill zu beenden  ")
                .withShouldEndSession(false)
                .build();
    }
}