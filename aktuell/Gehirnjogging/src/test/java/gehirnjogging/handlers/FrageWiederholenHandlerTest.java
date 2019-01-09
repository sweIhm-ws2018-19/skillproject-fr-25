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


public class FrageWiederholenHandlerTest {
    FrageWiederholenHandler handler = new FrageWiederholenHandler();


    @Test
    public void testCanHandle(){
        Logic logic = new Logic();
        logic.STATUS_ID = 5;

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


        haus.fragenWiederholung = 2;
        Optional<Response> res = handler.handle(mockInput);
        Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("ut mir leid, du hast dir bereits zwei mal die Frage wiederholen lassen."));


        haus.newGame();
        haus.initializeNumbers();
        haus.initializeQuestions();
        haus.STATUS_ID = 0;
        haus.inizialPoints();

        haus.fragenWiederholung = 7;
        haus.initializeQuestions();
        res = handler.handle(mockInput);
        response = res.get();
        assertEquals(8,haus.fragenWiederholung);

        assertTrue(response.getOutputSpeech().toString().contains("mal"));
    }

}
