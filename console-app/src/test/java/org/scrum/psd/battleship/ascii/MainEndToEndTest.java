package org.scrum.psd.battleship.ascii;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.scrum.psd.battleship.controller.GameController;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.Ship;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class MainEndToEndTest {
    @ClassRule
    public static final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @ClassRule
    public static final TextFromStandardInputStream gameInput = emptyStandardInputStream();

    @Test
    public void testPlayGameShotHits() {
        try {
            gameInput.provideLines("a1", "a2", "a3", "a4", "a5", "b1", "b2", "b3", "b4", "c1", "c2", "c3", "d1", "d2", "d3", "e1", "e2", "b4");

            Main.main(new String[]{});
        } catch(NoSuchElementException e) {
            Assert.assertTrue(systemOutRule.getLog().contains("Welcome to Battleship"));
            Assert.assertTrue(systemOutRule.getLog().contains("Yeah ! Nice hit !"));
        }
    }

    @Test
    public void testPlayGameShotMisses() {
        try {
            gameInput.provideLines("a1", "a2", "a3", "a4", "a5", "b1", "b2", "b3", "b4", "c1", "c2", "c3", "d1", "d2", "d3", "e1", "e2", "e4");

            Main.main(new String[]{});
        } catch(NoSuchElementException e) {
            Assert.assertTrue(systemOutRule.getLog().contains("Welcome to Battleship"));
            Assert.assertTrue(systemOutRule.getLog().contains("Miss"));
        }
    }

    @Test
    public void testInvalidPositionSetting() {
        Position testPosition = Main.parsePosition("Z30");

        Assert.assertFalse(testPosition.isValid());
    }

    @Test
    public void testValidPositionSetting() {
        Position testPosition = Main.parsePosition("H8");

        Assert.assertTrue(testPosition.isValid());
    }

    @Test
    public void testPositivePositionHorizontal() {
        try {
            gameInput.provideLines("c1", "d1", "e1", "f1", "g1", "b1", "b2", "b3", "b4", "c1", "c2", "c3", "d1", "d2", "d3", "e1", "e2", "e4");

            Main.main(new String[]{});
        } catch(NoSuchElementException e) {
            Assert.assertTrue(systemOutRule.getLog().contains("Welcome to Battleship"));
        }

    }

    @Test
    public void testPositivePositionVertical() {
        try {
            gameInput.provideLines("c1", "c2", "c3", "c4", "c5", "b1", "b2", "b3", "b4", "c1", "c2", "c3", "d1", "d2", "d3", "e1", "e2", "e4");

            Main.main(new String[]{});
        } catch(NoSuchElementException e) {
            Assert.assertTrue(systemOutRule.getLog().contains("Welcome to Battleship"));
        }
    }

    @Test
    public void testDiagonal() {
        try {
            gameInput.provideLines("f1", "e2", "d3", "c4", "b5", "b1", "b2", "b3", "b4", "c1", "c2", "c3", "d1", "d2", "d3", "e1", "e2", "e4");

            Main.main(new String[]{});
        }
        catch(NoSuchElementException e) {
            Assert.assertTrue(systemOutRule.getLog().contains("Coordinate e2 is not valid, Please Insert Another Coordinate..!!"));
        }

    }

}
