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

package gehirnjogging;

import com.amazon.ask.Skill;





import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import gehirnjogging.handlers.AntwortFalschHandler;
import gehirnjogging.handlers.AntwortRichtigHandler;
import gehirnjogging.handlers.CancelandStopIntentHandler;
import gehirnjogging.handlers.EndHandler;
import gehirnjogging.handlers.FallbackIntentHandler;
import gehirnjogging.handlers.GameStartIntentHandler;
import gehirnjogging.handlers.HelpIntentHandler;
import gehirnjogging.handlers.LaunchRequestHandler;
import gehirnjogging.handlers.QuizStartIntent;
import gehirnjogging.handlers.RegelHandler;
import gehirnjogging.handlers.SessionEndedRequestHandler;



public class GehirnjoggingStreamHandler extends SkillStreamHandler {



    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new QuizStartIntent(),
                        new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler(),
                        new GameStartIntentHandler(),
                        new RegelHandler(),
                        new EndHandler(),
                        new AntwortFalschHandler(),
                        new AntwortRichtigHandler(),
                        new  SessionEndedRequestHandler())
                .withSkillId("amzn1.ask.skill.58b4fb46-62fe-4d55-a144-5de47094bc94")
                .build();
    }

    public GehirnjoggingStreamHandler() {
        super(getSkill());
    }

}
