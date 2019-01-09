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


public class QuizStartIntentTest{
    QuizStartIntentHandler handler = new QuizStartIntentHandler();
    Logic haus = new Logic();

    @Test
    public void testCanHandle(){
        haus.STATUS_ID = 1;
    	
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

        haus.EINSTELLUNGS_COUNTER_R=1;
        Map<String, Object> sessAtt = new HashMap<String, Object>();
        sessAtt.put("test","tests");
        final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

        Optional<Response> res = handler.handle(mockInput);
        Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Dann legen wir los! Hier kommt Frage ")||
                response.getOutputSpeech().toString().contains("Punkte erreicht <audio src='soundbank:"));

        haus.EINSTELLUNGS_COUNTER_R=2;

        res = handler.handle(mockInput);
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Dann legen wir los! Hier kommt Frage ")||
                response.getOutputSpeech().toString().contains("Wollt ihr noch eine runde spielen ?"));
    }

}
