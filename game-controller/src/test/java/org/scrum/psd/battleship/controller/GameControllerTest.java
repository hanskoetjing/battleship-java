package org.scrum.psd.battleship.controller;

import org.junit.Assert;
import org.junit.Test;
import org.scrum.psd.battleship.controller.dto.HitStatus;
import org.scrum.psd.battleship.controller.dto.Letter;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.Ship;

import java.util.Arrays;
import java.util.List;

public class GameControllerTest {
    @Test
    public void testCheckIsHitTrue() {
        List<Ship> ships = GameController.initializeShips();
        int counter = 0;

        for (Ship ship : ships) {
            Letter letter = Letter.values()[counter];

            for (int i = 0; i < ship.getSize(); i++) {
                ship.getPositions().add(new Position(letter, i));
            }

            counter++;
        }

        HitStatus result = GameController.checkIsHit(ships, new Position(Letter.A, 1));

        Assert.assertTrue(result.isHit());
    }

    @Test
    public void testCheckIsHitFalse() {
        List<Ship> ships = GameController.initializeShips();
        int counter = 0;

        for (Ship ship : ships) {
            Letter letter = Letter.values()[counter];

            for (int i = 0; i < ship.getSize(); i++) {
                ship.getPositions().add(new Position(letter, i));
            }

            counter++;
        }

        HitStatus hitStatus = GameController.checkIsHit(ships, new Position(Letter.H, 1));

        Assert.assertFalse(hitStatus.isHit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckIsHitPositstionIsNull() {
        GameController.checkIsHit(GameController.initializeShips(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckIsHitShipIsNull() {
        GameController.checkIsHit(null, new Position(Letter.H, 1));
    }

    @Test
    public void testIsShipValidFalse() {
        Ship ship = new Ship("TestShip", 3);
        boolean result = GameController.isShipValid(ship);

        Assert.assertFalse(result);
    }

    @Test
    public void testIsShipValidTrue() {
        List<Position> positions = Arrays.asList(new Position(Letter.A, 1), new Position(Letter.A, 1), new Position(Letter.A, 1));
        Ship ship = new Ship("TestShip", 3, positions);

        boolean result = GameController.isShipValid(ship);

        Assert.assertTrue(result);
    }

    @Test
    public void testGameEnd() {

    }

    @Test
    public void testHitOutsidePlayingFieldPositive() {
        List<Ship> ships = GameController.initializeShips();
        
        ships.get(0).getPositions().add(new Position(Letter.B, 4));
        ships.get(0).getPositions().add(new Position(Letter.B, 5));
        ships.get(0).getPositions().add(new Position(Letter.B, 6));
        ships.get(0).getPositions().add(new Position(Letter.B, 7));
        ships.get(0).getPositions().add(new Position(Letter.B, 8));

        HitStatus hitStatus = GameController.checkIsHit(ships, new Position(Letter.H, 100));

        Assert.assertEquals("Miss. This posisiton is outside the playing field.", hitStatus.getDesc());
    }

    @Test
    public void testHitOutsidePlayingFieldNegative() {
        List<Ship> ships = GameController.initializeShips();

        ships.get(0).getPositions().add(new Position(Letter.B, 4));
        ships.get(0).getPositions().add(new Position(Letter.B, 5));
        ships.get(0).getPositions().add(new Position(Letter.B, 6));
        ships.get(0).getPositions().add(new Position(Letter.B, 7));
        ships.get(0).getPositions().add(new Position(Letter.B, 8));

        HitStatus hitStatus = GameController.checkIsHit(ships, new Position(Letter.B, 8));

        Assert.assertEquals("Yeah ! Nice hit !", hitStatus.getDesc());
    }

    @Test
    public void testOverlapShipPositive() {

        List<Ship> ships = GameController.initializeShips();

        ships.get(0).getPositions().add(new Position(Letter.B, 4));
        ships.get(0).getPositions().add(new Position(Letter.B, 5));
        ships.get(0).getPositions().add(new Position(Letter.B, 6));
        ships.get(0).getPositions().add(new Position(Letter.B, 7));
        ships.get(0).getPositions().add(new Position(Letter.B, 8));

        boolean isOverLap = GameController.checkIsOverlap(ships, new Position(Letter.B, 4));

        Assert.assertEquals(true, isOverLap);
    }

    @Test
    public void testOverlapShipNegative() {

        List<Ship> ships = GameController.initializeShips();

        ships.get(0).getPositions().add(new Position(Letter.B, 4));
        ships.get(0).getPositions().add(new Position(Letter.B, 5));
        ships.get(0).getPositions().add(new Position(Letter.B, 6));
        ships.get(0).getPositions().add(new Position(Letter.B, 7));
        ships.get(0).getPositions().add(new Position(Letter.B, 8));

        boolean isOverLap = GameController.checkIsOverlap(ships, new Position(Letter.A, 8));

        Assert.assertEquals(false, isOverLap);
    }

}
