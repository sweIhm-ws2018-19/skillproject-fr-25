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


public class ScoreHandlerTest{
	ScoreHandler handler = new ScoreHandler();

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

        haus.EINSTELLUNGS_COUNTER_R = 1;
        Optional<Response> res = handler.handle(mockInput);
        Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Richtig beantwortet. Um weiter zu spielen"));

        haus.EINSTELLUNGS_COUNTER_R = 2;
        res = handler.handle(mockInput);
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("punkte um weiter zu spielen sage nächste frage"));

        haus.EINSTELLUNGS_COUNTER_R = 3;
         res = handler.handle(mockInput);
         response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("punkte um weiter zu spielen sage nächste frage"));

    }

}
