public class PlayGame {
    public static void main (String [] args) {
     Game game = new Game(5, 5, 3);
     
     game.addShip(0, 0, 0, 2, Gameboard.DOWN, 'A');
     game.addShip(0, 0, 4, 2, Gameboard.LEFT, 'B');
     game.addShip(0, 4, 3, 4, Gameboard.UP, 'C');
     
     game.addShip(1, 0, 0, 2, Gameboard.RIGHT, 'A');
     
     game.addShip(2, 0, 0, 3, Gameboard.UP, 'A');
     game.addShip(2, 0, 0, 3, Gameboard.DOWN, 'A');
     
     game.playGame();
    }
   }