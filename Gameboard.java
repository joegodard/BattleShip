
public class Gameboard{
  //A two dimensional array of tiles
  private Tile[][] board;
  //Stores if it is an opponent's board
  private boolean isOpponent;
  //Integers representing directions used in ship placement
  public static final int UP = 1;
  public static final int DOWN = 2;
  public static final int LEFT = 3;
  public static final int RIGHT = 4;
  //Constructor method
  public Gameboard(int row, int column, boolean isOpponent){
    //Creates the array
    board = new Tile[row][column];
    //Initializes each tile
    for(int r = 0; r<row; r++){
      for(int c = 0; c<column; c++){
        board[r][c] = new Tile(); 
      }
    }
    //Sets isOpponent
    this.isOpponent = isOpponent;
  }
  
  //Returns a string representation of the board
  public String toString(){
    //The string to be returned
    String s = " ";

    for(int col = 0; col<board[0].length; col++){
      s += " " + col;
    }
    s += "\n";
    //Loops through each row
    for(int row = 0; row<board.length; row++){
      //Grabs each row and adds to the string
      s += row + this.getRow(row) + "\n";
    }
    //Returns the string
    return s;
  }
  
  //Returns a row of the array as a string
  public String getRow(int rowNum){
    //String to be returned
    String row = "";
    //Loops through each column
    for(int col = 0; col<board[rowNum].length; col++){
      //Adds the display character to the string
      row += " " + board[rowNum][col].getDisplay(isOpponent); 
    }
    //Returns the string
    return row;
  }
  
  //Attacks the given tile
  public void doAttack(int row, int column){
    board[row][column].attack(); 
  }
  
  //Returns an array of the given row
  public Tile[] extractRow(int row){
    //Creates a new array the same length as the given row
    Tile[] array = new Tile[board[row].length];
    //Loops through the row array, adding the board values to the new array
    for(int i = 0; i<array.length; i++){
      array[i] = board[row][i]; 
    }
    //Returns the array
    return array;
  }
  
  //Returns an array of the given column
  public Tile[] extractColumn(int column){
    //Creates a new array the same length as the given column
    Tile[] array = new Tile[board.length];
    //Loops through the column array, adding the board values to the new array
    for(int i = 0; i<array.length; i++){
      array[i] = board[i][column]; 
    }
    //Returns the array
    return array;
  }
  
  //Reverses a given array
  public static void reverse(Tile[] data){
    //Creates a new array of the same size
    Tile[] reversedArray = new Tile[data.length];
    //Loops through the array
    for(int i = 0; i<reversedArray.length; i++){
      //Adds the value from the opposite position to the ith position
      reversedArray[i] = data[data.length-1-i]; 
    }
    //Loops through the array
    for(int j = 0; j<data.length; j++){
      //Sets the data array references to point to reversedArray
      data[j] = reversedArray[j];
    }
  }
  
  //Adds a ship at the given position, direction and of given length
  public boolean addShip(int row, int col, int length, int direction, char display){
    //Creates an array
    Tile[] array = null;
    //determines if a ship has been added
    boolean addedShip = false;
    //If the direction is vertical, extract a column
    if(direction == UP || direction == DOWN){
      array = extractColumn(col);
    }
    //If the direction is horizontal, extract a row
    else if(direction == LEFT || direction == RIGHT){
      array = extractRow(row);
    }
    
    //If the direction is up, move the starting position down to the end
    //of the ship and place it as a downward ship
    if(direction == UP)
      row = (row-length)+1;
    
    //If the direction is left, move the starting position left to the end
    //of the ship and place it as a rightward ship
    if(direction == LEFT)
      col = (col-length)+1;
    
    //If the direction is vertical
    if(direction == UP || direction == DOWN){
      //Check if it can be placed
      if(canPlaceShipInArray(array, row, length)){
        //places the ship
        placeShipInArray(array, row, length, display);
        addedShip = true;
      }
    }
    //If the direction is horizontal
    else if(direction == LEFT || direction == RIGHT){
      //Check if it can be placed
      if(canPlaceShipInArray(array, col, length)){
        //places the ship
        placeShipInArray(array, col, length, display);
        addedShip = true;
      }
    }
    //Returns a boolean of whether or not a ship has been placed
    return addedShip;
  }
  
  //Determines if a ship can be placed in the given array
  private static boolean canPlaceShipInArray(Tile[] tiles, int start, int length){
    //A boolean storing if it can be placed
    boolean canPlace = true;
    //Makes sure the ship length fits on the board
    if(length <= tiles.length){
      //Makes sure the ship can fit between the starting point and the end of the board
      if((tiles.length-start) < length){
        //The ship can't be placed
        canPlace = false;
      }
      //Makes sure the starting position is greater than 0
      if(canPlace && start >= 0){
        //Loops through the array
        for(int i = 0; i<length; i++){
          //Checks if the ship can be placed on each tile
          if(!tiles[i+start].canPlaceShip()){
            //The ship can't be placed
            canPlace = false; 
          }
        }
      }
      //If the starting position is less than 0
      else{
        //The ship can't be placed
        canPlace = false; 
      }
    }
    //If the ship doesn't fit on the board
    else{
      //The ship can't be placed
      canPlace = false; 
    }
    //Returns whether or not the ship can be placed
    return canPlace;
  }
  
  //Method to place ship in the given array
  private static void placeShipInArray(Tile[] tiles, int start, int length, char letter){
    //If the ship can be placed in the array
    if(canPlaceShipInArray(tiles, start, length)){
      //Loops through the array
      for(int i = 0; i<length; i++){
        //Sets the displayLetter for each tile to the given letter
        tiles[start+i].setDisplayLetter(letter); 
      }
    }
  }
  
  //Determines if a board has lost
  public boolean hasLost(){
    //Boolean to be returned
    boolean hasLost = true;
    //Loops through the board
    for(int row = 0; row<board.length; row++){
      for(int col = 0; col<board[row].length; col++){
        //If any tile has an active ship
        if(board[row][col].activeShip())
          //The board has not lost
          hasLost = false;
      }
    }
    //Returns the bullet
    return hasLost;
  }
}