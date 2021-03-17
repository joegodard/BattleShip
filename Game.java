import java.util.Scanner;
public class Game{
  //Turn determines which player acts
  private int turn;
  //An array storing every board
  private Gameboard[] boards;
  //Stores the height and width of the boards
  private int height;
  private int width;
  
  //Constructor method
  public Game(int height, int width, int numPlayers){
    //Creates an array of gameboards with one for each player
    boards = new Gameboard[numPlayers];
    //Loops through the array
    for(int i = 0; i<boards.length; i++){
      //If it is the first board in the array (the player's board, isOpponent is set to false
      if(i == 0)
        boards[i] = new Gameboard(height, width, false);
      //If it is anyone else's board, isOpponent is set to true
      else
        boards[i] = new Gameboard(height, width, true);
    }
    //The initial turn is set to 0
    turn = 0;
    //Sets the height and width of the boards
    this.height = height;
    this.width = width;
  }
  
  //Method to choose a random integer
  public static int random(int max){
    return (int)(Math.random() * max); 
  }
  
  //Attacks a random tile on a random board
  public void randomAttack(){
    //The player to be attacked
    int player = random(boards.length);
    //Picks a random row and column
    int row = random(height);
    int column = random(width);
    //Pick a different target until a board is chosen that is not the current
    //user's and has not lost already
    while(turn == player || boards[player].hasLost()){
      player = random(boards.length); 
    }
    //Prints out which player is attacking which player and which tile
    System.out.println("Player " + turn + " is attacking Player " + player
                         + " at row " + row + " and column " + column);
    //Attacks the given tile
    attack(player, row, column);
  }
  
  //Attacks the given tile
  public void attack(int playerID, int row, int column){
    boards[playerID].doAttack(row, column); 
  }
  
  //Adds a ship at the given spot and of the given length and direction
  public void addShip(int player, int row, int col, int length, int direction, char letter){
    boards[player].addShip(row, col, length, direction, letter);
  }
  
  //Begins the game
  public void playGame(){
    //As long as the game is not over, loop through
    while(!gameOver()){
      //If it is the player's turn
      if(turn == 0){
        //Print out all of the boards
        for(int i = 0; i<boards.length; i++){
          System.out.println(boards[i]); 
        }
        //Create a new scanner
        Scanner kbd = new Scanner(System.in);
        //Ask the player for a target
        System.out.println("Attack which player?");
        int target = kbd.nextInt();
        //Asks the player for a row
        System.out.println("Attack which Row?");
        int row = kbd.nextInt();
        //Asks the player for a column
        System.out.println("Attack which Column?");
        int col = kbd.nextInt();
        //As long as it is a valid target,
        if(target > 0 && target < boards.length && row >=0
             && row < height && col >= 0 && col < width){
          //Attack the given target
          attack(target, row, col); 
        }
        //If it is invalid, tell the player and skip their turn
        else{
          System.out.println("Invalid input"); 
        }
      }
      //If it is a computer's opponent
      else{
        //Randomly attack
        randomAttack();
      }
      //Loops through each board
      for(int i = 0; i<boards.length; i++){
        //If a board has lost, tell the player
        if(boards[i].hasLost()){
          System.out.println("Player " + i + " has no more ships!"); 
        }
      }
      //Go to the next turn, returning to the player's turn once all opponents have gone
      turn = (turn+1)%boards.length;
    }
    //Once the game is over, determine the winner
    int winner = findWinner();
    //Prints out the winner
    System.out.println("Player " + winner + " wins!");
  }
  
  //Determines if the game is over
  public boolean gameOver(){
    //Integer storing how many baords have lost
    int boardsLost = 0;
    //Boolean storing if the the game is over
    boolean gameOver = false;
    //Loops through each board
    for(int i = 0; i<boards.length; i++){
      //If the board has lost, increment how many boards have lost
      if(boards[i].hasLost()){
        boardsLost++; 
      }
    }
    //If only one board has not lost
    if(boardsLost == boards.length-1){
      //The game is over
      gameOver = true; 
    }
    //Return the boolean
    return gameOver;
  }
  
  //Determine the winner
  public int findWinner(){
    //An interger representing the winner
    int winner = -1;
    //Loops through each board
    for(int i = 0; i<boards.length; i++){
      //If it finds a board that has not lost, set it as the winner
      if(!boards[i].hasLost())
        winner = i;
    }
    //Return the winner
    return winner;
  }
}