package de.tu.dresden.ifsr.kurs.java.roguelike.model;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.CharacterSuite;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.StructuresSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CharacterSuite.class,
        StructuresSuite.class})
public class ModelSuite {
    // No code here, because it's a test-suite
}
