package gehirnjogging.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

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


public class FallbackIntentHandlerTest {
	FallbackIntentHandler handler = new FallbackIntentHandler();

    @Test
    public void testCanHandle(){
    	
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }
    
    @Test
    public void testHandle() {
        Logic haus = new Logic();

        Map<String, Object> sessAtt = new HashMap<String, Object>();
        sessAtt.put("test","tests");
        final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);


        haus.STATUS_ID = 3;
        haus.EINSTELLUNGS_ID = 1;
        Optional<Response> res = handler.handle(mockInput);
        Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("alleine Spielen ?"));

        haus.STATUS_ID = 3;
        haus.EINSTELLUNGS_ID = 3;
        res = handler.handle(mockInput);
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Wie viele Spieler seid ihr?"));

        haus.STATUS_ID = 3;
        haus.EINSTELLUNGS_ID = 2;
        res = handler.handle(mockInput);
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("ie viele Fragen sollen gespielt werden ?"));

        haus.STATUS_ID = 1;
        haus.EINSTELLUNGS_ID = 2;
        res = handler.handle(mockInput);
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("vor dem Spielbeginn noch die Spielregeln"));

        haus.STATUS_ID = 2;
        haus.EINSTELLUNGS_ID = 2;
        res = handler.handle(mockInput);
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Tut mir Leid, ich habe sie leider nicht verstanden"));



    }

}
