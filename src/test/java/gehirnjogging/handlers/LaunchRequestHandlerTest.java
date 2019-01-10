package gehirnjogging.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.amazon.ask.model.Response;
import gehirnjogging.Logic;
import gehirnjogging.TestUtil;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class LaunchRequestHandlerTest{
	LaunchRequestHandler handler = new LaunchRequestHandler();
    Logic haus = new Logic();

    @Test
    public void testCanHandle(){
    	
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }
    
    @Test
    public void testHandle() {
        haus.newGame();
        haus.initializeNumbers();
        haus.initializeQuestions();
        haus.STATUS_ID = 0;
        haus.inizialPoints();


        Map<String, Object> sessAtt = new HashMap<String, Object>();
        sessAtt.put("pause", null);
        final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, sessAtt, null);

        Optional<Response> res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        Response response = res.get();
        System.out.println(response.getOutputSpeech().toString());
        assertTrue(response.getOutputSpeech().toString().contains("Willkommen bei Train your Brain Gehirnjogging!"));
        assertEquals(0, haus.STATUS_ID);
    }

    @Test
    public void testHandlep2() {
        haus.newGame();
        haus.initializeNumbers();
        haus.initializeQuestions();
        haus.STATUS_ID = 0;
        haus.inizialPoints();


        Map<String, Object> sessAtt = new HashMap<String, Object>();
        sessAtt.put("pause", "yes");
        sessAtt.put("player1","2");
        sessAtt.put("player2","2");
        sessAtt.put("player3","2");
        sessAtt.put("currentPlayer","2");
        sessAtt.put("RICHTIGE_ANTWORT","2");

//        final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, sessAtt, null);
//
//        Optional<Response> res = handler.handle(mockInput);
//        assertTrue(res.isPresent());
//        Response response = res.get();
//        System.out.println(response.getOutputSpeech().toString());
//
//        assertEquals(2,haus.player1);
//        assertEquals(2,haus.player2);
//        assertEquals(2,haus.player3);
//        assertEquals(2,haus.currentPlayer);
//        assertEquals(2,haus.RICHTIGE_ANTWORT);
//
//
//
//
//        assertTrue(response.getOutputSpeech().toString().contains("Willkommen bei Train your Brain Gehirnjogging!"));


    }

}
