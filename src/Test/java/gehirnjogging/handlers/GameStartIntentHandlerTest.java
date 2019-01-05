package gehirnjogging.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class GameStartIntentHandlerTest {
	GameStartIntentHandler handler = new GameStartIntentHandler();

    @Test
    public void testCanHandle(){
    	
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }
    
    @Test
    public void testHandle() {
    }

}
