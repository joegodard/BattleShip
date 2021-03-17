public class PlayGame {
    public static void main (String [] args) {
     Game game = new Game(10, 10, 3, 5);
     
     game.setupBoards(true);
     
     game.playGame();
    }

   }