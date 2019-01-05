package gehirnjogging.handlers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

public class GrundlageDerTests {
	
	
	
//    public static HandlerInput mockHandlerInput(Map<String, String> slots, Map<String, Object> sessionAttributes, Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {
//        final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
//        when(attributesManagerMock.getSessionAttributes()).thenReturn(sessionAttributes);
//        when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);
//        when(attributesManagerMock.getRequestAttributes()).thenReturn(requestAttributes);
//
//        final Intent.Builder intentBuilder = Intent.builder();
//        slots.forEach((key, value) ->
//                intentBuilder.putSlotsItem(key, Slot.builder().withName(key).withValue(value).build())
//        );
//
//        // Mock Slots
//        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
//                .withRequest(IntentRequest.builder()
//                        .withIntent(intentBuilder.build())
//                        .build())
//                .build();
//
//
//        // Mock Handler input attributes
//        final HandlerInput input = Mockito.mock(HandlerInput.class);
//        when(input.getAttributesManager()).thenReturn(attributesManagerMock);
//        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
//        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);
//
//        return input;
//    }

    
    
    
//    public static HandlerInput mockHandlerInput(String playerName, Map<String, Object> sessionAttributes, Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {
//        return mockHandlerInput(Collections.singletonMap("PlayerName", playerName), sessionAttributes, persistentAttributes, requestAttributes);
//    }

//    public static Response standardTestForHandle(RequestHandler handler) {
//        final Map<String, Object> sessionAttributes = new HashMap<>();
//        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
//        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.PLAY);
//        final HandlerInput inputMock = mockHandlerInput(Collections.emptyMap(), sessionAttributes, Collections.emptyMap(), Collections.emptyMap());
//        final Optional<Response> res = handler.handle(inputMock);
//
//        assertTrue(res.isPresent());
//        final Response response = res.get();
//
//        assertFalse(response.getShouldEndSession());
//        assertNotEquals("Test", response.getReprompt());
//        assertNotNull(response.getOutputSpeech());
//        return response;
//    }
}
