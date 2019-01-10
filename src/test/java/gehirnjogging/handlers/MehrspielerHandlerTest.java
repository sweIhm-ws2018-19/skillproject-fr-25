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

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MehrspielerHandlerTest {
    gehirnjogging.handlers.MehrspielerHandler handler = new gehirnjogging.handlers.MehrspielerHandler();
    Logic haus = new Logic();

    @Test
    public void testCanHandle() {
        haus.STATUS_ID =2;
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }


    @Test
    public void testHandle() {

        Map<String, Object> sessAtt = new HashMap<String, Object>();
        sessAtt.put("test", "tests");
        final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

        haus.newGame();
        haus.initializeNumbers();
        haus.initializeQuestions();
        haus.STATUS_ID = 0;
        haus.inizialPoints();


        haus.EINSTELLUNGS_ID = 0;
        Optional<Response> res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("htest du alleine spielen oder hast du Freunde dabei?"));

//todo: Slots logik fehlt
//        haus.EINSTELLUNGS_ID = 1;
//        res = handler.handle(mockInput);
//        assertTrue(res.isPresent());
//        response = res.get();
//        assertTrue(response.getOutputSpeech().toString().contains("Tut mir leid ich habe sie nicht verstanden, m"));

        haus.EINSTELLUNGS_COUNTER_R = 4;
        haus.EINSTELLUNGS_COUNTER = 5;
        haus.EINSTELLUNGS_ID = 3;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("wie darf ich dich nennen"));

        haus.EINSTELLUNGS_COUNTER_R = 5;
        haus.EINSTELLUNGS_COUNTER = 5;
        haus.EINSTELLUNGS_ID = 3;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("test du vor dem Spielbeginn noch die Spielregeln h"));


        haus.STATUS_ID = 3;
        haus.EINSTELLUNGS_ID = 1;
        haus.EINSTELLUNGS_ID = 2;
        res = handler.handle(mockInput);
        assertTrue(res.isPresent());
        response = res.get();
        System.out.println(response.toString());
//        assertTrue(response.getOutputSpeech().toString().contains("Ich habe dich nicht verstanden Möchtest du alleine Spielen ? d"));



    }
}
