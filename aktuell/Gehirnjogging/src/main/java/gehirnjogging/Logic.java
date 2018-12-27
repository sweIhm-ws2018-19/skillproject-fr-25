package gehirnjogging;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import com.amazon.ask.model.Slot;

public class Logic {
	public static int STATUS_ID_OLD = 0;
    public static int EINSTELLUNGS_ID_OLD = 0;
	public static int[] points = new int[3];
    public static  String player1=""; 
    public static String player2=""; 
    public static String player3=""; 
    public static  String currentPlayer="";
    public static int STATUS_ID=0;
    public static int EINSTELLUNGS_ID=0;
    public static int EINSTELLUNGS_COUNTER=0;
    public static int EINSTELLUNGS_COUNTER_R=1;


    public static String RICHTIGE_ANTWORT = ""; // Richtige Antwort zb. "Antwort A","Antwort D","Antwort C"
    public static int FRAGE_NUMBER = 0;  // Zufaellige Zahl welche vorgibt welche Frage Gestellt wird
    public static int richtig = 0;
    public static int counter = 0;
    public static int size = 10;
    public static int fragenZahl = 0;
    public static int random = size - 1;
    public static int richtigAntwortZahl = 0;
    public static String[][] questions;
    public static ArrayList<Integer> numbers;

    
    public static boolean antwortRichtig = true;
    public static Map<String, Slot> slots = null;

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
        player1=""; 
        player2=""; 
        player3=""; 
        currentPlayer="";
        STATUS_ID=0;
        EINSTELLUNGS_ID=0;
        EINSTELLUNGS_COUNTER=0;
        EINSTELLUNGS_COUNTER_R=1;
        RICHTIGE_ANTWORT = "";
        FRAGE_NUMBER = 0;
        richtig = 0;
        counter = 0;
        random = size - 1;
        questions = new String[size][3];
        numbers = new ArrayList<Integer>();
    }

    //zahl zwischen 1und4->1,2,3,4
    public static void zufallszahl(){
        Random random = new Random();
        richtigAntwortZahl= random.nextInt(4 - 1 + 1) + 1;
    }
    public static void inizialPoints(){
        points[0]=0;
        points[1]=0;
        points[2]=0;
    }


    // RICHTIGE_ANTWORT_NUMBER setzten
    public static void setTrueAnser() {
        RICHTIGE_ANTWORT = questions[FRAGE_NUMBER][1];
    }
    
    
    public static void givePoint() {
        if(currentPlayer.equalsIgnoreCase(player1)) {

        }else if(currentPlayer.equalsIgnoreCase(player1)){

        }else if(currentPlayer.equalsIgnoreCase(player2)){

        } 
    }
    
    public static void checkAnswer() {
        if (slots.get("jahres_datum").getValue()!=null) {
            if (slots.get("jahres_datum").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
                antwortRichtig = true;
            } else {
                antwortRichtig = false;
            }
        } else if (slots.get("antwort").getValue()!=null)  {
            if (slots.get("antwort").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
                antwortRichtig = true; 
            } else {
                antwortRichtig = false; 
             }
        } else if (slots.get("antwort_number").getValue()!=null)  {
            if (slots.get("antwort_number").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
                antwortRichtig = true;
            } else {
            	 antwortRichtig = false;
            }
        } else if (slots.get("antwort_sprache").getValue()!=null)  {
            if (slots.get("antwort_sprache").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
                antwortRichtig = true;  
            } else {
                antwortRichtig = false;
            }
        }else  {
        	antwortRichtig = false;
        }
    }
    public static String pickPhrase(String[] list) {
        int randomIndex = new SecureRandom().nextInt(list.length);
        return list[randomIndex];  
    }
    
    //NEU***********************
    public static String scoreBewerten() {
if(EINSTELLUNGS_COUNTER_R==2) {
	if(points[0]>points[1]) {
		return "Platz 2 geht an " +player2+ "mit " + points[1] + " Punkten und Platz eins geht an "  +player1+ "mit " +points[0]+" Punkten";
	}else if(points[0]<points[1]) {
		return "Platz 2 geht an " +player1+ "mit " + points[0] + " Punkten und Platz eins geht an "  +player2+ "mit " +points[1]+" Punkten";
	}else {
	return "Unentschieden " +  player1+"  "+player2+" habn jeweils "+ points[0] +" erziehlt";
}	
}	
if(points[0]>points[1]&& points[0]>points[2]) {
	return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Der Sieger ist "+player1+" dieser erreichte"+points[0]+" Punkte"; 
}
if(points[1]>points[0]&& points[1]>points[2]) {
	return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Der Sieger ist "+player2+" dieser erreichte"+points[1]+" Punkte"; 
}
if(points[2]>points[0]&& points[2]>points[1]) {
	return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Der Sieger ist "+player3+" dieser erreichte"+points[2]+" Punkte"; 
}
return "Unentschieden " +  player1+"  "+player2+ player3+" habn jeweils "+ points[0] +" erziehlt";

}

	
}