package gehirnjogging.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import gehirnjogging.Logic;
import gehirnjogging.TestUtil;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NoHandlerTest {
    gehirnjogging.handlers.NoHandler handler = new gehirnjogging.handlers.NoHandler();
    Logic haus = new Logic();


    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandle() {
        Logic haus = new Logic();

        Map<String, Object> sessAtt = new HashMap<String, Object>();
        sessAtt.put("test", "tests");
        final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, sessAtt, null);

        haus.newGame();
        haus.initializeNumbers();
        haus.initializeQuestions();
        haus.STATUS_ID = 0;
        haus.inizialPoints();


        Optional<Response> res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Schade, dass du doch nicht spielen möchtest, ich hoffe, dass du bald wiederkommst"));
        assertEquals(1,haus.STATUS_ID);


        haus.STATUS_ID = 1;
        haus.EINSTELLUNGS_COUNTER_R =1;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertEquals(5,haus.STATUS_ID);
        assertEquals(5,haus.EINSTELLUNGS_ID);

        haus.STATUS_ID = 1;
        haus.EINSTELLUNGS_COUNTER_R =3;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertEquals(3,haus.STATUS_ID);
        assertEquals(5,haus.EINSTELLUNGS_ID);


        haus.STATUS_ID = 1;
        haus.random = 1;
        haus.counter =0;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertEquals(1, haus.counter);
        assertEquals(0,haus.fragenWiederholung);
        assertTrue(response.getOutputSpeech().toString().contains("Dann legen wir los! Hier kommt Frage "));


        haus.STATUS_ID = 3;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertEquals(3, haus.EINSTELLUNGS_ID);
        assertTrue(response.getOutputSpeech().toString().contains("Wie viele Spieler seid ihr?"));

        haus.STATUS_ID = 3;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertEquals(3, haus.EINSTELLUNGS_ID);
        assertTrue(response.getOutputSpeech().toString().contains(""));

        haus.STATUS_ID = 4;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains(""));

        haus.STATUS_ID = 5;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains(""));

        haus.STATUS_ID = 6;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains(""));

        haus.STATUS_ID = 7;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Schön das du da warst Ich hoffe wir sehen uns bald wieder"));

        haus.STATUS_ID = 10000;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Diesen Input konnte ich nicht verarbeiten"));



    }
}
