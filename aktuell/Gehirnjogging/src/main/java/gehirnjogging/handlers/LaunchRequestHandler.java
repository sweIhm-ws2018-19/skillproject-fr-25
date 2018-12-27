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
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import gehirnjogging.Logic;
import gehirnjogging.SpeechStrings;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {
    
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String welcome = Logic.pickPhrase(SpeechStrings.WELCOME);
        Logic.newGame();
        Logic.initializeNumbers();
        Logic.initializeQuestions();
        Logic.STATUS_ID = 0;
        Logic.inizialPoints();
        return input.getResponseBuilder()
                .withSpeech(welcome)
                .withReprompt("bist du eingeschlafen ?")
                .build();
    }
}