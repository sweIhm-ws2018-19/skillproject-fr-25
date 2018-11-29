package test.java;

import org.junit.Assert;
import org.junit.Test;

import gehirnjogging.SpeechStrings;

public class SpeechStringTest {

    @Test
    public void getSpeechStringSkill() {
        Assert.assertEquals("Gehirnjogging.", SpeechStrings.SKILLNAME);
    }
    
    @Test
    public void getSpeechStringEnd() {
        Assert.assertEquals("Ich möchste das Gehirnjogging beenden.", SpeechStrings.ENDQUIZ);
    }
    
    @Test
    public void getSpeechStringStart() {
        Assert.assertEquals("Starte das Gehirnjogging.", SpeechStrings.STARTQUIZ);
    }
    @Test
    public void getSpeechStringStop() {
        Assert.assertEquals("Auf Wiedersehen.", SpeechStrings.STOP);
    }
    @Test
    public void getSpeechStringSorryRepeatTest() {
        Assert.assertEquals("Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.", SpeechStrings.FALLBACK);
    }
    
    @Test
    public void getSpeechStringHelpTest() {
        Assert.assertEquals("Ich helfe dir dein Gehirn zu trainieren. Bitte sage mir zum Beispiel: Es kann losgehen", SpeechStrings.LAUNCHREQUEST);
    }

}
