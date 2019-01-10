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

public class YesHandlerTest {
    gehirnjogging.handlers.YesHandler handler = new gehirnjogging.handlers.YesHandler();
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
        final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

        haus.newGame();
        haus.initializeNumbers();
        haus.initializeQuestions();
        haus.STATUS_ID = 0;
        haus.inizialPoints();
        Optional<Response> res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        haus.STATUS_ID = 0;
        Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Möchtest du alleine spielen oder hast du Freunde dabei?"));

        haus.STATUS_ID = 1;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Möchtest du etwas über den Spielablauf oder die Punktvergabe wissen?"));

        haus.STATUS_ID = 2;
        haus.fragenWiederholung = 2;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Tut mir leid, du hast dir bereits zwei mal die Frage wiederholen lassen. ein drittes mal sit gegen"));

        haus.fragenWiederholung = 5;
        haus.STATUS_ID = 5;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Hier kommt noch ein mal die Frage "));




        haus.STATUS_ID = 3;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Ok super! Möchtest du vor dem Spielbeginn noch die Spielregeln hören ?"));


        haus.STATUS_ID =3;
        haus.EINSTELLUNGS_ID = 5;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Hier kommt noch ein mal die Frage "+haus.counter));


        haus.STATUS_ID =6;
        haus.EINSTELLUNGS_COUNTER_R =1;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertEquals(5,haus.STATUS_ID);

        haus.STATUS_ID =6;
        haus.EINSTELLUNGS_COUNTER_R =5;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertEquals(3,haus.STATUS_ID);
        assertEquals(5,haus.EINSTELLUNGS_ID);


        haus.STATUS_ID =6;
        haus.random = 1;
        haus.counter =0;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertEquals(1,haus.counter);
        assertTrue(response.getOutputSpeech().toString().contains("Dann legen wir los! Hier kommt Frage "));



        haus.STATUS_ID = 6;
        haus.random = -1;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("<audio src='soundbank://soundlibrary/human/amzn_sfx_clear_throat_ahem_01'/>" +" Leider gibt"));


        haus.STATUS_ID = 7;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertEquals(0,haus.STATUS_ID);
        assertEquals(0,haus.EINSTELLUNGS_ID);
        assertTrue(response.getOutputSpeech().toString().contains("Willkommen bei Train your Brain Gehirnjogging! <break time=\"1s\"/>? Sollen wir gleich starten den nächsten Albert Einstein zu erschaffen?"));


        haus.STATUS_ID =10000;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("Tut mir leid, diesen Input kann ich leider nicht verarbeiten"));





    }
}
