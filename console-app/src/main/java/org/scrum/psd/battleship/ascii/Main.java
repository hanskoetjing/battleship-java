package org.scrum.psd.battleship.ascii;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import org.scrum.psd.battleship.controller.GameController;
import org.scrum.psd.battleship.controller.dto.HitStatus;
import org.scrum.psd.battleship.controller.dto.Letter;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.Ship;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static List<Ship> myFleet;
	private static List<Ship> enemyFleet;
	private static ColoredPrinter console;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        console = new ColoredPrinter.Builder(1, false).build();

        console.setForegroundColor(Ansi.FColor.MAGENTA);
        console.println("                                     |__");
        console.println("                                     |\\/");
        console.println("                                     ---");
        console.println("                                     / | [");
        console.println("                              !      | |||");
        console.println("                            _/|     _/|-++'");
        console.println("                        +  +--|    |--|--|_ |-");
        console.println("                     { /|__|  |/\\__|  |--- |||__/");
        console.println("                    +---------------___[}-_===_.'____                 /\\");
        console.println("                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _");
        console.println(" __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7");
        console.println("|                        Welcome to Battleship                         BB-61/");
        console.println(" \\_________________________________________________________________________|");
        console.println("");
        console.clear();

        InitializeGame();

        StartGame();
    }

    private static void StartGame() {
    	int numOfMyShips = myFleet.size();
    	int numOfEnemyShips = enemyFleet.size();
        Scanner scanner = new Scanner(System.in);

        console.print("\033[2J\033[;H");
        console.println("                  __");
        console.println("                 /  \\");
        console.println("           .-.  |    |");
        console.println("   *    _.-'  \\  \\__/");
        console.println("    \\.-'       \\");
        console.println("   /          _/");
        console.println("  |      _  /\" \"");
        console.println("  |     /_\'");
        console.println("   \\    \\_/");
        console.println("    \" \"\" \"\" \"\" \"");

        do {
            /*PLAYER TURN*/
			System.out.println(ANSI_GREEN + "Remaining Player Fleet" + ANSI_RESET);
			for (Ship ship : myFleet) {
				System.out.print(ANSI_GREEN + ship.getName() + " || " + ANSI_RESET);
			}
			System.out.println(" ");
			System.out.println(" ");

			System.out.println(ANSI_RED + "Remaining Computer Fleet" + ANSI_RESET);
			for (Ship ship : enemyFleet) {
				System.out.print(ANSI_RED + ship.getName() + " || " + ANSI_RESET);
			}
			System.out.println(" ");
			System.out.println(" ");
	
            console.println("");
            console.println("Player, it's your turn");
            console.println("Enter coordinates for your shot :");
            Position position = parsePosition(scanner.next());

            HitStatus hitStatus = GameController.checkIsHit(enemyFleet, position);
            if (hitStatus.isHit()) {
            	numOfEnemyShips--;

                beep();

                console.println("                \\         .  ./");
                console.println("              \\      .:\" \";'.:..\" \"   /");
                console.println("                  (M^^.^~~:.'\" \").");
                console.println("            -   (/  .    . . \\ \\)  -");
                console.println("               ((| :. ~ ^  :. .|))");
                console.println("            -   (\\- |  \\ /  |  /)  -");
                console.println("                 -\\  \\     /  /-");
                console.println("                   \\  \\   /  /");
            }

           console.println(hitStatus.getDesc());

            /*COMPUTER TURN*/
            if (hitStatus.isTurnEnd()) {
                position = getRandomPosition();
                hitStatus = GameController.checkIsHit(myFleet, position);
                console.println("");
                console.println(String.format("Computer shoot in %s%s and %s", position.getColumn(), position.getRow(), hitStatus.isHit() ? "hit your ship !" : "miss"));
                if (hitStatus.isHit()) {
                    beep();

                    console.println("                \\         .  ./");
                    console.println("              \\      .:\" \";'.:..\" \"   /");
                    console.println("                  (M^^.^~~:.'\" \").");
                    console.println("            -   (/  .    . . \\ \\)  -");
                    console.println("               ((| :. ~ ^  :. .|))");
                    console.println("            -   (\\- |  \\ /  |  /)  -");
                    console.println("                 -\\  \\     /  /-");
                    console.println("                   \\  \\   /  /");

                }
            }  else {
                console.println("Please try again...");
            }

            if(numOfMyShips == 0 )
            {
            	console.println("You lost!");
            	break;
            }
            if(numOfEnemyShips == 0)
            {
            	console.println("You are the winner!");
            	break;
            }

        } while (true);
    }

    private static void beep() {
        console.print("\007");
    }

    protected static Position parsePosition(String input) {
    	
    	Letter letter;
    	int number = Integer.parseInt(input.substring(1));
    	Position position = new Position();
    	
    	if (Letter.isOnPlayingBoard(input.toUpperCase().substring(0, 1)) && (number>=1 && number<=8)) {
    		letter = Letter.valueOf(input.toUpperCase().substring(0, 1));
    		number = Integer.parseInt(input.substring(1));
    	position.setValid(true);
    	position.setColumn(letter);
    	position.setRow(number);
    		 
    	} else {
    		position.setValid(false);
    	}
    	
    	return  position;
        
       
    }

    private static Position getRandomPosition() {
        int rows = 8;
        int lines = 8;
        Random random = new Random();
        Letter letter = Letter.values()[random.nextInt(lines)];
        int number = random.nextInt(rows);
        Position position = new Position(letter, number);
        return position;
    }

    private static void InitializeGame() {
        InitializeMyFleet();

        InitializeEnemyFleet();
    }

    private static void InitializeMyFleet() {
        Scanner scanner = new Scanner(System.in);
        myFleet = GameController.initializeShips();

        console.println("Please position your fleet (Game board has size from A to H and 1 to 8) :");

        for (Ship ship : myFleet) {
            console.println("");
            console.println(String.format("Please enter the positions for the %s (size: %s)", ship.getName(), ship.getSize()));
            for (int i = 1; i <= ship.getSize(); i++) {
            	boolean isValidCoordinate = true;
            	do
            	{
            		
            		 console.println(String.format("Enter position %s of %s (i.e A3):", i, ship.getSize()));

                     String positionInput = scanner.next();
                     Position position = parsePosition(positionInput);
                     if (position.isValid() && ship.isValidPosition(position)) {
                     	boolean isOverlaped = GameController.checkIsOverlap(myFleet, position);
                         
                         if (isOverlaped) {
                         	console.println(String.format("Coordinate "+positionInput+" is Ovelapping Another Ship, Please Insert Another Coordinate..!!"));
                         	
                         }
                         else {
                        	 isValidCoordinate = true;
                        	 ship.addPosition(positionInput);
                        	 console.println(String.format("Success add Coordinate "+positionInput));
                         }
                     }else {
                    	 isValidCoordinate = false;
                     	 console.println(String.format("Coordinate "+positionInput+" is not valid, Please Insert Another Coordinate..!!"));
                     }
            		
            	}while(!isValidCoordinate);
               
                
            }
        }

    }

    private static void InitializeEnemyFleet() {
        enemyFleet = GameController.initializeShips();

        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 5));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 6));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 7));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 8));

        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 6));
        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 7));
        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 8));
        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 9));

        enemyFleet.get(2).getPositions().add(new Position(Letter.A, 3));
        enemyFleet.get(2).getPositions().add(new Position(Letter.B, 3));
        enemyFleet.get(2).getPositions().add(new Position(Letter.C, 3));

        enemyFleet.get(3).getPositions().add(new Position(Letter.F, 8));
        enemyFleet.get(3).getPositions().add(new Position(Letter.G, 8));
        enemyFleet.get(3).getPositions().add(new Position(Letter.H, 8));

        enemyFleet.get(4).getPositions().add(new Position(Letter.C, 5));
        enemyFleet.get(4).getPositions().add(new Position(Letter.C, 6));
    }
}
