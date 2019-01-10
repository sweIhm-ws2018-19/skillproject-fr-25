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


public class AntwortHandlerTest {
	gehirnjogging.handlers.AntwortHandler handler = new gehirnjogging.handlers.AntwortHandler();

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

//        haus.newGame();
//        haus.initializeNumbers();
//        haus.initializeQuestions();
//        haus.STATUS_ID = 0;
//        haus.inizialPoints();
//
//        haus.slots = new HashMap<>();
//        haus.getRandom();
//        haus.antwortRichtig = true;
//        haus.random = -1;
//        haus.STATUS_ID = 5;
//        haus.EINSTELLUNGS_COUNTER_R = 1;
//        final Optional<Response> res = handler.handle(mockInput);
//        assertTrue(res.isPresent());
//        Response response = res.get();
//        System.out.println(response.toString());
//        assertTrue(response.getOutputSpeech().toString().contains("Leider gibt es keine Fragen mehr. Du hast bei"));
//
//        assertTrue(res.isPresent());
//        response = res.get();
//        assertTrue(response.getOutputSpeech().toString().contains("."));

    }

}
