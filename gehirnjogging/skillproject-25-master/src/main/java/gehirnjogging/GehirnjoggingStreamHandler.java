/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

//

//Das ist ein Kommentar für den Michi, beste Grüße aus Hogwarts

package main.java.gehirnjogging;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import main.java.gehirnjogging.handlers.CancelandStopIntentHandler;
import main.java.gehirnjogging.handlers.FallbackIntentHandler;
import main.java.gehirnjogging.handlers.HelpIntentHandler;
import main.java.gehirnjogging.handlers.LaunchRequestHandler;
import main.java.gehirnjogging.handlers.SessionEndedRequestHandler;

public class GehirnjoggingStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {

        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler())
                .withSkillId("amzn1.ask.skill.42044a10-0bc9-4c88-9b29-54ff426cbc4f")
                .build();
    }

    public GehirnjoggingStreamHandler() {
        super(getSkill());
    }

}
