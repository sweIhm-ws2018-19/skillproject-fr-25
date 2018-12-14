package gehirnjogging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SpeechStrings {
    
    public static final String SKILLNAME = "Gehirnjogging.";
    public static final String WELCOME = "Willkommen bei Train your Brain Gehirnjogging. <break time=\"1s\"/>?  Bist du bereit gleich los zulegen?";
    public static final String WELCOME_V = "Ok super! Möchtest du vor dem Spielbeginn noch die Spielregeln hören oder bist du schon bereit für die erste frage?";
    public static final String WELCOME_REPROMPT = "Bist du noch da <break time=\"1s\"/>? Der Gehirnjogging Skill ist bereits offen.";
    public static final String ENDQUIZ = "Ich moechte das Gehirnjogging beenden.";
    public static final String STARTQUIZ = "Starte das Gehirnjogging.";
    public static final String STOP = "Auf Wiedersehen.";
    public static final String FALLBACK = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";
    public static final String LAUNCHREQUEST = "Ich helfe dir dein Gehirn zu trainieren. Bitte sage mir zum Beispiel: Es kann losgehen ";
   
    public static String RICHTIGE_ANTWORT = ""; // Richtige Antwort zb. "Antwort A","Antwort D","Antwort C"
    public static int FRAGE_NUMBER = 0;  // Zufaellige Zahl welche vorgibt welche Frage Gestellt wird
    public static int richtig = 0;
    public static int counter = 0;
    public static int size = 10;
    public static int random = size - 1;
    public static int richtigAntwortZahl = 0;
    public static String[][] questions;
    public static ArrayList<Integer> numbers;
    /*
     * DatumJahr
     * Antwort
     * Nummber
     * Sprache 
     */
    public static void initializeQuestions() { 
        questions[0][0]="In welchem Jahr begann der 1. Weltkrieg?";
        questions[0][1]="1914";
        questions[0][2]="DatumJahr";
        
        questions[1][0]="In welchem Jahr wurde die Bundesrepublik Deutschland gegründet?";
        questions[1][1]="1949";
        questions[1][2]="DatumJahr";
        
        questions[2][0]="Wie lautet die chemische Formel für Ozon? <break time=\"1s\"/> Antwort A: O Z <break time=\"1s\"/> Antwort B: O 2 <break time=\"1s\"/> Antwort C: O 4 <break time=\"1s\"/> Antwort D: O 3";
        questions[2][1]="Antwort D";
        questions[2][2]="Antwort";
        
        questions[3][0]="Welcher Stoff wird durch die Photosynthese frei gesetzt? <break time=\"1s\"/> Antwort A: Sauerstoff <break time=\"1s\"/> Antwort B: Wasserstoff <break time=\"1s\"/> Antwort C: Nanostoff <break time=\"1s\"/> Antwort D: Kohlenstoff";
        questions[3][1]="Antwort A";
        questions[3][2]="Antwort";
        
        questions[4][0]="Im Schlussverkauf gibt es 40% Rabatt. Wieviel bezahlst du für einen Mantel, der vorher 120 € gekostet hat?";
        questions[4][1]="72";
        questions[4][2]="Nummer";
        
        questions[5][0]="Welche Sprache spricht man in Argentinien?";
        questions[5][1]="spanisch";
        questions[5][2]="Sprache";
        
        questions[6][0]="Was ist 800 mal 0,007 ?";
        questions[6][1]="5,6";
        questions[6][2]="Nummer";
        
        questions[7][0]="Wie heißt der höchste Berg Österreichs? <break time=\"1s\"/> Antwort A: Zugspitze  <break time=\"1s\"/> Antwort B: Großglockner <break time=\"1s\"/> Antwort C: Wildspitze <break time=\"1s\"/> Antwort D: Dachstein ";
        questions[7][1]="Antwort B";
        questions[7][2]="Antwort";
        
        questions[8][0]="In welchem Jahr fiel die „Mauer“ in Deutschland?";
        questions[8][1]="1989";
        questions[8][2]="DatumJahr";
        
        questions[9][0]="Welcher Frankenkönig wurde im Jahr 800 zum Kaiser gekrönt? <break time=\"1s\"/> Antwort A: Karl der große  <break time=\"1s\"/> Antwort B: Alexander der große <break time=\"1s\"/> Antwort C: Ludwig der erste <break time=\"1s\"/> Antwort D: Ludwig der fünfte ";
        questions[9][1]="Antwort A";
        questions[9][2]="Antwort";
    }

    public static void initializeNumbers() {
        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }
    
    public static void getRandom() {
        FRAGE_NUMBER = numbers.get(random);
        numbers.remove(random);
        random--;
    }
    
    public static void newGame() {
        RICHTIGE_ANTWORT = "";
        FRAGE_NUMBER = 0;
        richtig = 0;
        counter = 0;
        random = size - 1;
        questions = new String[SpeechStrings.size][3];
        numbers = new ArrayList<Integer>();
    }
    
    //zahl zwischen 1und4->1,2,3,4
    public static void zufallszahl(){
        Random random = new Random();
    	richtigAntwortZahl= random.nextInt(4 - 1 + 1) + 1;
      }
    
    

    
    
    
//    questions[0][0]="In welchem Jahr begann der 1. Weltkrieg? Antwort A: 1914 <break time=\"1s\"/> Antwort B: 1945 <break time=\"1s\"/> Antwort C: 2018 <break time=\"1s\"/> Antwort D: 1990";
//    questions[0][1]="Antwort A";
//    questions[1][0]="In welchem Jahr wurde die Bundesrepublik Deutschland gegründet? <break time=\"1s\"/> Antwort A: 1914 <break time=\"1s\"/> Antwort B: 1949 <break time=\"1s\"/> Antwort C: 2018 <break time=\"1s\"/> Antwort D: 1990 ";
//    questions[1][1]="Antwort B";
//    questions[2][0]="Wie lautet die chemische Formel für Ozon? <break time=\"1s\"/> Antwort A: OZ <break time=\"1s\"/> Antwort B: O2 <break time=\"1s\"/> Antwort C: O4 <break time=\"1s\"/> Antwort D: O3";
//    questions[2][1]="Antwort D";
//    questions[3][0]="Welcher Stoff wird durch die Photosynthese frei gesetzt? <break time=\"1s\"/> Antwort A: Sauerstoff <break time=\"1s\"/> Antwort B: Wasserstoff <break time=\"1s\"/> Antwort C: Nanostoff <break time=\"1s\"/> Antwort D: Kohlenstoff";
//    questions[3][1]="Antwort A";
//    questions[4][0]="Wie nennt man die aufgeschriebenen Lebenserinnerungen Prominenter? <break time=\"1s\"/> Antwort A: Roman  <break time=\"1s\"/> Antwort B: Kurzgeschichte <break time=\"1s\"/> Antwort C: Memoiren <break time=\"1s\"/> Antwort D: Fabel ";
//    questions[4][1]="Antwort C";
//    questions[5][0]="Welcher Rohstoff ist die Basis fast aller Kunststoffe? <break time=\"1s\"/> Antwort A: Kalk  <break time=\"1s\"/> Antwort B: Holz <break time=\"1s\"/> Antwort C: Eisen <break time=\"1s\"/> Antwort D: Erdöl ";
//    questions[5][1]="Antwort D";
//    questions[6][0]="Was ist 800 mal 0,007 ? <break time=\"1s\"/> Antwort A:56  <break time=\"1s\"/> Antwort B:4,9 <break time=\"1s\"/> Antwort C: 5,6 <break time=\"1s\"/> Antwort D: 2,71 ";
//    questions[6][1]="Antwort C";
//    questions[7][0]="Wie heißt der höchste Berg Österreichs? <break time=\"1s\"/> Antwort A: Zugspitze  <break time=\"1s\"/> Antwort B: Großglockner <break time=\"1s\"/> Antwort C: Wildspitze <break time=\"1s\"/> Antwort D: Dachstein ";
//    questions[7][1]="Antwort B";
//    questions[8][0]="In welchem Jahr fiel die „Mauer“ in Deutschland? <break time=\"1s\"/> Antwort A: 1989  <break time=\"1s\"/> Antwort B: 1988 <break time=\"1s\"/> Antwort C: 1987 <break time=\"1s\"/> Antwort D: 1985";
//    questions[8][1]="Antwort A";
//    questions[9][0]="Welcher Frankenkönig wurde im Jahr 800 zum Kaiser gekrönt? <break time=\"1s\"/> Antwort A: Karl der große  <break time=\"1s\"/> Antwort B: Alexander der große <break time=\"1s\"/> Antwort C: Ludwig der erste <break time=\"1s\"/> Antwort D: Ludwig der fünfte ";
//    questions[9][1]="Antwort A";
}