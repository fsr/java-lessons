package de.tu.dresden.ifsr.kurs.java.roguelike;

import de.tu.dresden.ifsr.kurs.java.roguelike.controller.ControllerSuite;
import de.tu.dresden.ifsr.kurs.java.roguelike.exceptions.ExceptionsSuite;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.ModelSuite;
import de.tu.dresden.ifsr.kurs.java.roguelike.view.ViewSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExceptionsSuite.class,
        ControllerSuite.class,
        ModelSuite.class,
        ViewSuite.class})
public class MainSuite {
    // No code here, because it's a test-suite
}
