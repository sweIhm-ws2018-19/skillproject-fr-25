package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;

public class PauseHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("Pause"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        
        //***********STRINGS***********
        persistentAttributes.put("pause", "yes");
        
        if(!Logic.player1.equals("")) {
            persistentAttributes.put("player1", Logic.player1);
        }
        if(!Logic.player2.equals("")) {
             persistentAttributes.put("player2", Logic.player2); 
        }
        if(!Logic.player3.equals("")) {
            persistentAttributes.put("player3", Logic.player3);
        }
        if(!Logic.currentPlayer.equals("")) {
            persistentAttributes.put("currentPlayer", Logic.currentPlayer);
        }
        if(!Logic.RICHTIGE_ANTWORT.equals("")) {
            persistentAttributes.put("RICHTIGE_ANTWORT", Logic.RICHTIGE_ANTWORT);
        }
        
        //***********INTEGER***********
        persistentAttributes.put("STATUS_ID", Integer.toString(Logic.STATUS_ID));
        persistentAttributes.put("EINSTELLUNGS_ID", Integer.toString(Logic.EINSTELLUNGS_ID));
        persistentAttributes.put("EINSTELLUNGS_COUNTER", Integer.toString(Logic.EINSTELLUNGS_COUNTER));
        persistentAttributes.put("EINSTELLUNGS_COUNTER_R", Integer.toString(Logic.EINSTELLUNGS_COUNTER_R));
        persistentAttributes.put("fragenWiederholung", Integer.toString(Logic.fragenWiederholung));
        persistentAttributes.put("FRAGE_NUMBER", Integer.toString(Logic.FRAGE_NUMBER));
        persistentAttributes.put("richtig", Integer.toString(Logic.richtig));
        persistentAttributes.put("counter", Integer.toString(Logic.counter));
        persistentAttributes.put("fragenZahl", Integer.toString(Logic.fragenZahl));
        persistentAttributes.put("random", Integer.toString(Logic.random));
        persistentAttributes.put("richtigAntwortZahl", Integer.toString(Logic.richtigAntwortZahl));

        //***********BOOLEAN***********
        persistentAttributes.put("antwortRichtig", Boolean.toString(Logic.antwortRichtig));

        
        //***********Array***********
        for( int i = 0; i < Logic.numbers.size(); i++) {
            persistentAttributes.put("number_" + i, Integer.toString(Logic.numbers.get(i)));
        }
        
        for( int i = 0; i < Logic.points.length; i++) {
            persistentAttributes.put("points_" + i, Integer.toString(Logic.points[i]));
        }


        attributesManager.setPersistentAttributes(persistentAttributes);
        attributesManager.savePersistentAttributes();	

        //           testString("pause","yes",input);	
        //           testInt("Status_ID",Logic.STATUS_ID,input);																												

        Logic.STATUS_ID = 4;
        return input.getResponseBuilder()
                .withSpeech("Das Spiel wird solange pausiert, bis sie den skill das nächste mal starten")
                .withShouldEndSession(true)
                .build();
    }
    void testInt(String tmp1,int tmp2,HandlerInput tmpinput){
        AttributesManager attributesManager = tmpinput.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        persistentAttributes.put(tmp1, tmp2);
        attributesManager.setPersistentAttributes(persistentAttributes);	
    }
    void testString(String tmp1,String tmp2,HandlerInput tmpinput){
        AttributesManager attributesManager = tmpinput.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        persistentAttributes.put(tmp1, tmp2);
        attributesManager.setPersistentAttributes(persistentAttributes);	
    }
}