package gehirnjogging;
import gehirnjogging.SpeechStrings;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SpeechStringsTest {
    SpeechStrings fisch = new SpeechStrings();

    @Test
    public void testInitializeQuestions(){
        String[][] array = new String[10][3];
        fisch.initializeQuestions();
        array[0][0]="In welchem Jahr begann der 1. Weltkrieg?";
        array[0][1]="1914";
        array[0][2]="DatumJahr";
        
        array[1][0]="In welchem Jahr wurde die Bundesrepublik Deutschland gegründet?";
        array[1][1]="1949";
        array[1][2]="DatumJahr";
        
        array[2][0]="Wie lautet die chemische Formel für Ozon? <break time=\"1s\"/> Antwort A: O Z <break time=\"1s\"/> Antwort B: O 2 <break time=\"1s\"/> Antwort C: O 4 <break time=\"1s\"/> Antwort D: O 3";
        array[2][1]="Antwort D";
        array[2][2]="Antwort";
        
        array[3][0]="Welcher Stoff wird durch die Photosynthese frei gesetzt? <break time=\"1s\"/> Antwort A: Sauerstoff <break time=\"1s\"/> Antwort B: Wasserstoff <break time=\"1s\"/> Antwort C: Nanostoff <break time=\"1s\"/> Antwort D: Kohlenstoff";
        array[3][1]="Antwort A";
        array[3][2]="Antwort";
        
        array[4][0]="Im Schlussverkauf gibt es 40% Rabatt. Wieviel bezahlst du für einen Mantel, der vorher 120 € gekostet hat?";
        array[4][1]="72";
        array[4][2]="Nummer";
        
        array[5][0]="Welche Sprache spricht man in Argentinien?";
        array[5][1]="spanisch";
        array[5][2]="Sprache";
        
        array[6][0]="Was ist 800 mal 0,007 ?";
        array[6][1]="5,6";
        array[6][2]="Nummer";
        
        array[7][0]="Wie heißt der höchste Berg Österreichs? <break time=\"1s\"/> Antwort A: Zugspitze  <break time=\"1s\"/> Antwort B: Großglockner <break time=\"1s\"/> Antwort C: Wildspitze <break time=\"1s\"/> Antwort D: Dachstein ";
        array[7][1]="Antwort B";
        array[7][2]="Antwort";
        
        array[8][0]="In welchem Jahr fiel die „Mauer“ in Deutschland?";
        array[8][1]="1989";
        array[8][2]="DatumJahr";
        
        array[9][0]="Welcher Frankenkönig wurde im Jahr 800 zum Kaiser gekrönt? <break time=\"1s\"/> Antwort A: Karl der große  <break time=\"1s\"/> Antwort B: Alexander der große <break time=\"1s\"/> Antwort C: Ludwig der erste <break time=\"1s\"/> Antwort D: Ludwig der fünfte ";
        array[9][1]="Antwort A";
        array[9][2]="Antwort";
        Assert.assertArrayEquals(array, fisch.questions);
    }

    @Test
    public void testInitializeNumbers(){
        fisch.initializeNumbers();
        Assert.assertEquals(fisch.size, fisch.numbers.size());
        for(int i = 0; i < fisch.size; i++){
            Assert.assertTrue(fisch.numbers.contains(i));
        }
    }

    @Test
    public void testGetRandom(){
        Assert.assertEquals(0, fisch.FRAGE_NUMBER);
        fisch.getRandom();
        fisch.getRandom();
        fisch.getRandom();
        fisch.getRandom();
        fisch.getRandom();
        fisch.getRandom();
        fisch.getRandom();
        fisch.getRandom();
        fisch.getRandom();
        Assert.assertNotEquals(-1,(int) fisch.numbers.get(fisch.size-10));
    }

    @Test
    public void testNewGame(){
        fisch.newGame();
        Assert.assertEquals("", fisch.RICHTIGE_ANTWORT);
        Assert.assertEquals(0, fisch.FRAGE_NUMBER);
        Assert.assertEquals(0,fisch.richtig);
        Assert.assertEquals(0, fisch.counter);
        Assert.assertEquals(fisch.size-1,fisch.random);
        Assert.assertArrayEquals(new String[SpeechStrings.size][3], fisch.questions);
        Assert.assertEquals(new ArrayList<Integer>(), fisch.numbers);
    }

}
