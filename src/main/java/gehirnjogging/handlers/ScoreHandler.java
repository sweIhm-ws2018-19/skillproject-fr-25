package gehirnjogging.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import gehirnjogging.Logic;


public class ScoreHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ScoreIntent")) && Logic.STATUS_ID != 4;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        if (Logic.EINSTELLUNGS_COUNTER_R == 1) {
            return input.getResponseBuilder()
                    .withSpeech("Sie haben derzeit  " + Logic.counter + " Fragen" + Logic.richtig + " Richtig beantwortet. Um weiter zu spielen sagen sie los")
                    .withReprompt("Um weiter zu spielen sage nächste frage")
                    .build();
        }

        if (Logic.EINSTELLUNGS_COUNTER_R == 3) {
            return input.getResponseBuilder()
                    .withSpeech("Derzeitiger Punktestand: " + Logic.player1 + "hat" + Logic.points[0] + "punkte" + Logic.player2 + "hat" + Logic.points[1] + "punkte und " + Logic.player3 + "hat" + Logic.points[2] + "punkte um weiter zu spielen sage nächste frage")
                    .withReprompt("Um weiter zu spielen sage nächste frage")
                    .build();
        }

        return input.getResponseBuilder()
                .withSpeech("Derzeitiger Punktestand: " + Logic.player1 + "hat" + Logic.points[0] + "punkte" + Logic.player2 + "hat" + Logic.points[1] + "punkte um weiter zu spielen sage nächste frage")
                .withReprompt("Um weiter zu spielen sage nächste frage")
                .build();

    }
}