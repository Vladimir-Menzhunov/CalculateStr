import org.junit.Test;
import static org.junit.Assert.*;
public class TestApp {

    @Test
    public void calculateStr() throws Exception {
        Calc cal = new Calc("(5 + (6 + 7)) + 15 + 50 + 54 * 5 / 15 + 4 + 6 * 6 * (6 + (5 + 5))");
        assertEquals(681., cal.calculateStr(), 1E-40);
        cal = new Calc("5-(5+5)*6*6*6");
        assertEquals(2155., Math.abs(cal.calculateStr()), 1E-40);
        cal = new Calc("6*6*6 - 5 / 15 / 16 * 16 * 15");
        assertEquals(211., Math.abs(cal.calculateStr()), 1E-40);
        cal = new Calc("6*6/6 - 15 + 20 +20 * 48");
        assertEquals(971., Math.abs(cal.calculateStr()), 1E-40);
    }

}
