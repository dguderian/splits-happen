object BowlingScoring {
  
  // Translates scoring characters to integer representation
  val rollValues = Map[Char, Int]('X' -> 10, '/' -> 10, '9' -> 9, '8' -> 8, '7' -> 7, '6' -> 6, '5' -> 5, '4' -> 4, '3' ->3, '2' -> 2, '1' -> 1, '0' -> 0, '-' ->0 )  
  
  /**
   * Accepts a string as a command line argument, prints the score of the game to the screen.
   */
  def main(args: Array[String]) : Unit = {
    
    if(args.isEmpty){
      println("Error! BowlingScoring program requires a command line argument of a string of valid rolls")
      System.exit(-1)
    }
    println(scoreGame(args(0)))    
  }
  
  /**
   * Assumes a well formed string of valid rolls as input. Returns an integer representing the score for this game.
   */
  def scoreGame(rollSequence : String) : Int = {
    
    var currentBall = 0
    var currentFrame=1
    var total = 0
    
    while( currentFrame <= 10){
      
      if(rollSequence(currentBall) == 'X'){
        total += 10
        // No need to add intermediate ball to the strike bonus if the next frame ends in a spare
        if(rollSequence(currentBall +2) == '/'){
          total += 10         
        }
        else{
          total += rollValues(rollSequence(currentBall +1)) + rollValues(rollSequence(currentBall +2))
        }        
        currentBall +=1        
      }
      else{
        // if this frame ends in a spare, no need to add the current ball to the total
        if(rollSequence(currentBall +1) == '/'){
          total += 10
          // check to see if there are any balls remaining, avoiding index out of bounds for spare in last frame
          if((currentBall+2) < rollSequence.length){
            total += rollValues(rollSequence(currentBall +2))
          }
        }
        else{
          // Empty frame, add both ball scores
          total += rollValues(rollSequence(currentBall)) + rollValues(rollSequence(currentBall +1))
        }
        currentBall +=2
      }
      currentFrame+=1
    }    
    // return the total score
    total
  }
}