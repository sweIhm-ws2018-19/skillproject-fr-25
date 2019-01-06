package gehirnjogging.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import static org.junit.Assert.assertTrue;

import gehirnjogging.Logic;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class FrageWiederholenHandlerTest {


    @Test
    public void testCanHandle(){
        FrageWiederholenHandler handler = new FrageWiederholenHandler();
        Logic logic = new Logic();
        logic.STATUS_ID = 5;

        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }
    
    @Test
    public void testHandle() {
    }

}
