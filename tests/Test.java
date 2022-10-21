package tests;

import junit.framework.TestCase;
import prr.app.App;

public class Test extends TestCase {
    public void test() {
        System.setProperty("import", "A-01-02-M-ok.import");
        App.main(null);
    }
}
