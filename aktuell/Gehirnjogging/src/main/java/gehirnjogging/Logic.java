package gehirnjogging;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import com.amazon.ask.model.Slot;

public class Logic {

	public static String player1 = "";
	public static String player2 = "";
	public static String player3 = "";
	public static String currentPlayer = "";
	public static String pause = "1";
	public static String RICHTIGE_ANTWORT = ""; // Richtige Antwort zb. "Antwort A","Antwort D","Antwort C"
	public static String letzteAntwort = "";

	public static int STATUS_ID = 0;
	public static int EINSTELLUNGS_ID = 0;
	public static int EINSTELLUNGS_COUNTER = 0;
	public static int EINSTELLUNGS_COUNTER_R = 1;
	public static int fragenWiederholung = 0;
	public static int FRAGE_NUMBER = 0; // Zufaellige Zahl welche vorgibt welche Frage Gestellt wird
	public static int richtig = 0;
	public static int counter = 0;
	public static int size = 5;
	public static int fragenZahl = 0;
	public static int random = size - 1;
	public static int richtigAntwortZahl = 0;
	
	public static boolean antwortRichtig = true;

	public static ArrayList<Integer> numbers;
	public static Map<String, Slot> slots = null;
	public static int[] points;
	public static String[][] questions;

	/*
	 * DatumJahr Antwort Nummber Sprache
	 */
	public static void initializeQuestions() {
		questions[0][0] = "Ein Sch�fer besitzt 148 Schafe. Er kauft 75 Schafe dazu. Au�erdem bekommt er 10 Schafe geschenkt. Wie viele Schafe besitzt der Sch�fer nun? ";
		questions[0][1] = "233";
		questions[0][2] = "233 Schafe";

		questions[1][0] = "Mit einer U-Bahn fahren von Montag bis Freitag jeweils 120 Kinder. Samstags fahren 80 und Sonntags 70 Kinder. Wie viele Kinder sind in der ganzen Woche mit der U-Bahn gefahren?";
		questions[1][1] = "750";
		questions[1][2] = "750 Kinder";

		questions[2][0] = "Vor einer roten Ampel stehen mehrere Autos hintereinander. Eines steht vor zwei Autos, eines steht hinter zweien, und eines f�hrt zwischen zwei Wagen. Wie viele Fahrzeuge sind auf der Stra�e?";
		questions[2][1] = "3";
		questions[2][2] = "3 Fahrzeuge";

		questions[3][0] = "Eine Uhr mit Ziffernblatt hat mindestens zwei Zeiger, der Kleine ist der Stundenzeiger, der gro�e der Minutenzeiger. Der groߟe Zeiger bewegt sich nat�rlich schneller als der kleine, und wir gehen davon aus, dass die Uhr richtig geht. Wie oft wird der kleine Zeiger zwischen 12 Uhr mittags und 12 Uhr nachts vom gro�en Zeiger �berholt?";
		questions[3][1] = "10";
		questions[3][2] = "10 mal";

		questions[4][0] = "Welcher Monat ist der K�rzeste?";
		questions[4][1] = "mai";
		questions[4][2] = "Mai ist der k�rzeste Monat, da er nur aus drei buchstaben besteht";

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
		player1 = "";
		player2 = "";
		player3 = "";
		currentPlayer = "";
		STATUS_ID = 0;
		EINSTELLUNGS_ID = 0;
		EINSTELLUNGS_COUNTER = 0;
		EINSTELLUNGS_COUNTER_R = 1;
		RICHTIGE_ANTWORT = "";
		FRAGE_NUMBER = 0;
		richtig = 0;
		counter = 0;
		random = size - 1;
		questions = new String[size][3];
		numbers = new ArrayList<Integer>();
		points = new int[3];
	}

	// zahl zwischen 1und4->1,2,3,4
	public static void zufallszahl() {
		Random rand = new Random();
		richtigAntwortZahl = rand.nextInt(4 - 1 + 1) + 1;
	}

	public static void inizialPoints() {
		points[0] = 0;
		points[1] = 0;
		points[2] = 0;
	}

	// RICHTIGE_ANTWORT_NUMBER setzten
	public static void setTrueAnser() {
		RICHTIGE_ANTWORT = questions[FRAGE_NUMBER][1];
	}

	public static void erasePoint() {
		if (Logic.currentPlayer == Logic.player1) {
			if (Logic.points[0] > 0) {
				Logic.points[0]--;
			}
		} else if (Logic.currentPlayer == Logic.player2) {
			if (Logic.points[1] > 0) {
				Logic.points[1]--;
			}
		} else if (Logic.currentPlayer == Logic.player3) {
			if (Logic.points[2] > 0) {
				Logic.points[2]--;
			}
		}
	}

	public static void givePoint() {
		if (currentPlayer.equalsIgnoreCase(player1)) {
			points[0]++;
		} else if (currentPlayer.equalsIgnoreCase(player1)) {
			points[1]++;
		} else if (currentPlayer.equalsIgnoreCase(player2)) {
			points[2]++;
		}
	}

	public static void checkAnswer() {
		if (slots.get("jahres_datum").getValue() != null) {
			if (slots.get("jahres_datum").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
				antwortRichtig = true;
			} else {
				antwortRichtig = false;
			}
		} else if (slots.get("antwort").getValue() != null) {
			if (slots.get("antwort").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
				antwortRichtig = true;
			} else {
				antwortRichtig = false;
			}
		} else if (slots.get("antwort_number").getValue() != null) {
			if (slots.get("antwort_number").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
				antwortRichtig = true;
			} else {
				antwortRichtig = false;
			}
			
		} else if (slots.get("testt").getValue() != null) {
			if (slots.get("testt").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
				antwortRichtig = true;
			} else {
				antwortRichtig = false;
			}
		} else if (slots.get("test").getValue() != null) {
			if (slots.get("test").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
				antwortRichtig = true;
			} else {
				antwortRichtig = false;
			}
			
		} else if (slots.get("Tier").getValue() != null) {
			if (slots.get("Tier").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
				antwortRichtig = true;
			} else {
				antwortRichtig = false;
			}
		} else if (slots.get("Monat").getValue() != null) {
			if (slots.get("Monat").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
				antwortRichtig = true;
			} else {
				antwortRichtig = false;
			}
			
		} else if (slots.get("antwort_sprache").getValue() != null) {
			if (slots.get("antwort_sprache").getValue().equalsIgnoreCase(Logic.RICHTIGE_ANTWORT)) {
				antwortRichtig = true;
			} else {
				antwortRichtig = false;
			}
		} else {
			antwortRichtig = false;
		}
	}

	public static String pickPhrase(String[] list) {
		int randomIndex = new SecureRandom().nextInt(list.length);
		return list[randomIndex];
	}

	// NEU***********************
	public static String scoreBewerten() {
		if (points[0] > points[1] && points[0] > points[2]) {
			if(points[0]==1) {
				return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Der Sieger ist " + player1
						+ " dieser erreichte einen" + " Punkt";
			}
			return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Der Sieger ist " + player1
					+ " dieser erreichte" + points[0] + " Punkte";
		}
		
		if (points[1] > points[0] && points[1] > points[2]) {
			if(points[1]==1) {
				return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Der Sieger ist " + player2
						+ " dieser erreichte einen" + " Punkt";
			}
			return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Der Sieger ist " + player2
					+ " dieser erreichte" + points[1] + " Punkte";
		}
		if (points[2] > points[0] && points[2] > points[1]) {
			if(points[2]==1) {
				return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Der Sieger ist " + player3
						+ " dieser erreichte einen" + " Punkt";
			}
			return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Der Sieger ist " + player3
					+ " dieser erreichte" + points[2] + " Punkte";
		}else if(points[0]==1&&points[1]==1&&points[2]==1) {
			return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Unentschieden alle Spieler haben haben jeweils einen Punkt erziehlt";
	 
	 }	
		
		return "<audio src='soundbank://soundlibrary/musical/amzn_sfx_trumpet_bugle_03'/> Unentschieden alle Spieler haben haben jeweils" + points[0] + " erziehlt";
	}
	

}