package gehirnjogging.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import com.amazon.ask.model.Response;
import gehirnjogging.SpeechStrings;
import gehirnjogging.TestUtil;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class CancelandStopIntentHandlerTest {
	CancelandStopIntentHandler handler = new CancelandStopIntentHandler();

    @Test
    public void testCanHandle(){
    	
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }
    
    @Test
    public void testHandle() {
        Map<String, Object> sessAtt = new HashMap<String, Object>();
        sessAtt.put("test","tests");
        final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

        final Optional<Response> res = handler.handle(mockInput);

        assertTrue(res.isPresent());
        final Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains("W"));

    }

}
