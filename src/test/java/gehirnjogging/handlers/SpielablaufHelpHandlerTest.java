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


public class SpielablaufHelpHandlerTest{
	SpielablaufHelpHandler handler = new SpielablaufHelpHandler();

    @Test
    public void testCanHandle(){
    	
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }
    
    @Test
    public void testHandle() {

        Logic haus = new Logic();
        haus.newGame();
        haus.initializeNumbers();
        haus.initializeQuestions();
        haus.STATUS_ID = 0;
        haus.inizialPoints();

        Map<String, Object> sessAtt = new HashMap<String, Object>();
        sessAtt.put("test","tests");
        final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

        Optional<Response> res = handler.handle(mockInput);
        Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Das Spiel beginnt damit, dass zuerst alle Mitspieler abgefragt werden. Gl"));
    }

}
