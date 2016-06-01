package de.tu.dresden.ifsr.kurs.java.roguelike.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public enum InputController {
    INSTANCE;

    private final Set<KeyCode> keys;

    InputController() {
        keys = new HashSet<KeyCode>();
    }

    public static InputController getInstance() {
        return INSTANCE;
    }

    public void setKeyPressed(KeyEvent keyEvent) {
    }

    public void setKeyReleased(KeyEvent keyEvent) {
        keys.add(keyEvent.getCode());
    }

    public void resetPressedKeys() {
        keys.clear();
    }

    public boolean keyWasPressed(KeyCode keyCode) {
        for (KeyCode key : keys) {
            if (key.equals(keyCode)) {
                return true;
            }
        }

        return false;
    }

    public boolean keyWasPressed() {
        return !keys.isEmpty();
    }
}
