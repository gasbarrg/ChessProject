# ♙  ♔  ♕  ♗  ♘  ♖ Overview ♙  ♔  ♕  ♗  ♘  ♖ 

Chess GUI with basic robot opponent. Coded in Java for CIS 163 Final project @ GVSU in Winter 2021 / 2022. 

# Gameplay  

To begin, click any piece on the white side. All avalible moves will be shown. Click again in the desired location to confirm the move.  
Click the "undo" button at any time to undo robot and player moves. 

# Robot Opponent 

The automated opponent will immediately make a responce move after each player move according to a set of pre-defined actions ( defined in the AI method under the ChessModel.java file.)  

Robot order of actions (only one will be chosen, with the most important moves taking precidence.) 
  * -Tries to get out of check  
  * -Takes the enemy king if it is in check  
  * -Tries to put enemy king in check  
  * -Tries to get valuable pieces out of danger  
  * -Tries to take a piece  
  * -Tries to make a safe move towards king  
  * -Moves a random pawn  
