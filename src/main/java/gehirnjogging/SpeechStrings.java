package gehirnjogging;

import java.util.ArrayList;
import java.util.Random;

public class SpeechStrings {
    
    public static final String SKILLNAME = "Gehirnjogging.";
    public static final String WELCOME = "Hallo <break time=\"1s\"/>  Willkommen beim Gehirnjogging Skill";
    public static final String WELCOME_REPROMPT = "Bist du noch da <break time=\"1s\"/>? Der Gehirnjogging Skill ist bereits offen.";
    public static final String ENDQUIZ = "Ich moechte das Gehirnjogging beenden.";
    public static final String STARTQUIZ = "Starte das Gehirnjogging.";
    public static final String STOP = "Auf Wiedersehen.";
    public static final String FALLBACK = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";
    public static final String LAUNCHREQUEST = "Ich helfe dir dein Gehirn zu trainieren. Bitte sage mir zum Beispiel: Es kann losgehen";
   
    public static String RICHTIGE_ANTWORT = ""; // Richtige Antwort zb. "Antwort A","Antwort D","Antwort C"
    public static int FRAGE_NUMBER = 0;  // Zufaellige Zahl welche vorgibt welche Frage Gestellt wird
    public static int richtig = 0;
    public static int counter = 1;
    public static int size = 10;
    public static String[][] questions = new String[size][2];
    public static ArrayList<Integer> numbers = new ArrayList<Integer>();
    
    public static void initializeQuestions() { 
        questions[0][0]="In welchem Jahr begann der 1. Weltkrieg? Antwort A: 1914 Antwort B: 1945 Antwort C: 2018 Antword D: 1990";
        questions[0][1]="Antwort A";
        questions[1][0]="In welchem Jahr wurde die Bundesrepublik Deutschland gegründet? Antwort A: 1914 Antwort B: 1949 Antwort C: 2018 Antword D: 1990 ";
        questions[1][1]="Antwort B";
        questions[2][0]="Wie lautet die chemische Formel für Ozon? Antwort A: OZ Antwort B: O2 Antwort C: O4 Antword D: O3";
        questions[2][1]="Antwort D";
        questions[3][0]="Welcher Stoff wird durch die Photosynthese frei gesetzt? Antwort A: Sauerstoff Antwort B: Wasserstoff Antwort C: Nanostoff Antword D: Kohlenstoff";
        questions[3][1]="Antwort A";
        questions[4][0]="Wie nennt man die aufgeschriebenen Lebenserinnerungen Prominenter? Antwort A: Roman  Antwort B: Kurzgeschichte Antwort C: Memoiren Antword D: Fabel ";
        questions[4][1]="Antwort C";
        questions[5][0]="Welcher Rohstoff ist die Basis fast aller Kunststoffe? Antwort A: Kalk  Antwort B: Holz Antwort C: Eisen Antword D: Erdöl ";
        questions[5][1]="Antwort D";
        questions[6][0]="Was ist 800 mal 0,007 ? Antwort A:56  Antwort B:4,9 Antwort C: 5,6 Antword D: 2,71 ";
        questions[6][1]="Antwort C";
        questions[7][0]="Wie heißt der höchste Berg Österreichs? Antwort A: Zugspitze  Antwort B: Großglockner Antwort C: Wildspitze Antword D: Dachstein ";
        questions[7][1]="Antwort B";
        questions[8][0]="In welchem Jahr fiel die „Mauer“ in Deutschland? Antwort A: 1989  Antwort B: 1988 Antwort C: 1987 Antword D: 1985";
        questions[8][1]="Antwort A";
        questions[9][0]="Welcher Frankenkönig wurde im Jahr 800 zum Kaiser gekrönt? Antwort A: Karl der große  Antwort B: Alexander der große Antwort C: Ludwig der erste Antword D: Ludwig der fünfte ";
        questions[9][1]="Antwort A";
    }

    public static void initializeNumbers() {
        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }
    }
    
    public static void getRandom() {
        int rnd = new Random().nextInt(numbers.size());
        FRAGE_NUMBER = numbers.get(rnd);
        numbers.remove(FRAGE_NUMBER);
    }
}