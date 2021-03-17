
public class Tile{
  //Stores if this tile has been attacked
  private boolean wasAttacked;
  //Stores if this tile has a ship
  private boolean hasShip;
  //Stores a letter that represents a ship
  private char displayLetter;
  
  //Constructor method
  public Tile(){
    //Sets the initial display to a wave
    displayLetter = '~';
    //Sets both statuses to false
    wasAttacked = false;
    hasShip = false;
  }
  
  //Modifier method for displayLetter
  public void setDisplayLetter(char letter){
    //Sets the displayLetter to the given char
    displayLetter = letter;
    //The Tile now has a ship
    hasShip = true;
  }
  
  //Returns a string to be displayed for the tile
  public String getDisplay(boolean isOpponent){
    //String to be returned
    String symbol = "";
    //If it is the player's board
    if(!isOpponent){
      //If there is a ship that hasn't been attacked, return displayLetter
      if(!wasAttacked && hasShip)
        symbol = Character.toString(displayLetter);
      //If there is no ship or attack, return a wave
      else if(!wasAttacked && !hasShip)
        symbol = "~";
      //If there is no ship and it has been attacked, return a splash
      else if(wasAttacked && !hasShip)
        symbol = "^";
      //If there was a ship that has been attacked, return an explosion
      else if(wasAttacked && hasShip)
        symbol = "*";
    }
    //If it is the opponent's board, ships can't be seen
    else{
      //If it hasn't been attacked, return a wave
      if(!wasAttacked)
        symbol = "~";
      //If there is no ship and an attack, return a splash
      else if(wasAttacked && !hasShip)
        symbol = "^";
      //If there is a ship and an attack, return an explosion
      else if(wasAttacked && hasShip)
        symbol = "*";
    }
    //Return the string
    return symbol;
  }
  
  //Method to Attack the tile
  public void attack(){
    wasAttacked = true; 
  }
  
  //Determines if a ship can be placed
  public boolean canPlaceShip(){
    //If the tile has no ship and hasn't been attacked, it can be placed
    return !wasAttacked && !hasShip; 
  }
  
  //Determines if there is a ship in this tile
  public boolean activeShip(){
    //If the tile has a ship and has not been attacked, it is active
    return !wasAttacked && hasShip; 
  }
}