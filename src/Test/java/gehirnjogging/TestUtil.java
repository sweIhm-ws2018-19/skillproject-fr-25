package gehirnjogging;
import static org.mockito.Mockito.when;

import java.util.Map;

import com.amazon.ask.model.*;
import org.mockito.Mockito;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.response.ResponseBuilder;


import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class TestUtil {


    public static HandlerInput mockHandlerInput(String input, Map<String, Object> sessionAttributes, Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {

        final AttributesManager mockAttributesManager = Mockito.mock(AttributesManager.class);
        when(mockAttributesManager.getSessionAttributes()).thenReturn(sessionAttributes);
        when(mockAttributesManager.getPersistentAttributes()).thenReturn(persistentAttributes);
        when(mockAttributesManager.getRequestAttributes()).thenReturn(requestAttributes);

        final RequestEnvelope mockRequestEnvelope = RequestEnvelope.builder().withRequest(IntentRequest.builder()
                .withIntent(Intent.builder()
                        .putSlotsItem("test",
                                Slot.builder().withName("tests").withValue(input).build())
                        .build())
                .build()).build();

        final HandlerInput mockHandlerInput = Mockito.mock(HandlerInput.class);
        when(mockHandlerInput.getAttributesManager()).thenReturn(mockAttributesManager);
        when(mockHandlerInput.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(mockHandlerInput.getRequestEnvelope()).thenReturn(mockRequestEnvelope);

        return mockHandlerInput;

    }

    static Response standardTestForHandle(RequestHandler handler) {

        return parameterizedTestForHandle(handler ,"Bitcoin", "asdf", "0", "0");
    }
    static Response parameterizedTestForHandle(RequestHandler handler, String primaryCurrency, String secondaryCurrency, String rateDate, String currencyAmount) {

        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put("LIST_OF_CURRENCIES", "Test");


        String test  = "";
        Map<String, Object> test3 = new HashMap<>();



        final HandlerInput inputMock = TestUtil.mockHandlerInput(test, test3, test3, test3);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();


        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        return response;
    }
}
