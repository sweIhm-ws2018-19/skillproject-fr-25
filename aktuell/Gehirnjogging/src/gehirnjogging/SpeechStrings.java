// org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package

package gehirnjogging;

public class SpeechStrings {
    public static final String SKILLNAME = "Gehirnjogging.";
    public static final String WELCOME = "Hallo <break time=\"1s\"/>  Willkommen beim Gehirnjogging Skill";
    public static final String WELCOME_REPROMPT = "Bist du noch da <break time=\"1s\"/>? Der Gehirnjogging Skill ist bereits offen.";
    public static final String ENDQUIZ = "Ich m�chste das Gehirnjogging beenden.";
    public static final String STARTQUIZ = "Starte das Gehirnjogging.";
    public static final String STOP = "Auf Wiedersehen.";
    public static final String FALLBACK = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";
    public static final String LAUNCHREQUEST = "Ich helfe dir dein Gehirn zu trainieren. Bitte sage mir zum Beispiel: Es kann losgehen";
    public static final int FRAGE_NUMBER; // Zuf�llige Zahl welche vorgibt welche Frage Gestellt wird
    public static final String RICHTIGE_ANTWORT; // Richtige Antwort zb. "Antwort A","Antwort D","Antwort C"
    static int size = 10;
    static String[][] questions = new String[size][2];
    static ArrayList<Integer> numbers = new ArrayList<Integer>();
    
    public static void initializeQuestions() throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("file.txt"));
            String line = null;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                if (index % 2 == 0) {
                    questions[index][0] = line;
                }
                else {
                    questions[index][1] = line;
                    index++;
                }
            }
        } finally {
            reader.close();
        }
    }
    
    public static void initializeNumbers() {
        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }
    }
    public static void getRandom() {
        int rnd = new Random().nextInt(numbers.size());
        FRAGE_NUMBER=numbers.get(rnd);
        numbers.remove(FRAGE_NUMBER);
    }
}