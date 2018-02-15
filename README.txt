=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: sunhanyu
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D array
  I use the 2D array to simulate the motion of the snake.
  Because the snake is not a simple GameObject, it might be twisted in
  a really complicated way.
  I use the 2D array and label each unit 0 when it's not occupied by a snake
  and label it by number 1 to 4 when it's occupied by a snake.
  The number 1 to 4 represents the direction the snake is moving at this very junction.
  You can view the detail in the table[][] in the Snake class.

  2. Dynamic Dispatch
  I use an Apples(abstract class that extends GameObj) to represent all kinds apples.
  And different kinds of apples(BadApple, GoodApple, MagicApple) all extends Apples.
  I use it because there are always two apples in the gamecourt but their types might vary.
  And all kinds of apples share similar features(e.g. same size, has a eatEffect(), mayVanish(), duration etc.)
  So, using the Apple abstract class is a very reasonable and necessary choice.
  
  3. I/O
  I use I/O to store the highest 5 scores and usernames and display such information to the user everytime
  he/she finishes playing the game.

  4. Testable Component
  I use the JUnit test to test out the I/O, the Snake state and the Apples.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Apples(abstract class extends GameObj) :
    An abstract class representing features of an apple.
  BadApple(class extending Apples) :
    Just, you know, bad apple, the snake will lose a life after eating it.
    However, it vanishes after 50 ticks and would be replaced by another apple.
  GoodApple(class extending Apples) ：
    good apple... They don't vanish. They will make your snake longer when be eaten.
  MagicApple(class extending Apples) :
    magic apple. Make your snake gain one life when eaten. 
    Vanish after only 10 ticks though.
  Direction: already provided
  GameObj : already provided
  GameCourt: where all the game logic lies.
             basicly in charge of the game court.
  Game: top-level frame of the whole game.
  PopOut: in charge of all the I/O related stuff.
  Snake: In charge of the state of state. 
         All the simulations of snake movement is in this class.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  I really struggled while trying to model the snake's motion in a 2D array.
  Because each time you need to move the head forward and at the same time remove
  the tail.
  At first I used a boolean 2D array and thinks it would sufficient to model the snake
  simply by putting true and false to indicate whether the snake has occupied the block,
  and that is an over-simplified model.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I think there is a good separation of functionality.
  I think the private state is well encapsulated. Most of the variables are defined as private.
  I think if I had the chance I would probably use constant variable instead of direct numbers while 
  writing the game so that I could easily change the size of the game frame if I want.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  I used the Game, GameObj, parts of GameCourt, Direction that are already provided in
  the "Mushroom Doom" in my game.
  The images of apples and heart are downloaded from Google.
  

