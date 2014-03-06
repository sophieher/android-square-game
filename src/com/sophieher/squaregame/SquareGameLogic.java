package com.sophieher.squaregame;

import java.util.Random;

public class SquareGameLogic {
	private static final String TAG = "SquareGameLogic";
	
	private Square [] squares;
	public static final int NUM_SQUARES = 9;
	public static int [] players;
	
	public static int currentMove = 1;
	public static int startPos;
	
	private final int mBoard[];
	public static final int BOARD_SIZE = 16;
	
	public SquareGameLogic(){
		
		// fill the board
		mBoard = new int[BOARD_SIZE];
		for(int i = 0; i < BOARD_SIZE; ++i)
			mBoard[i] = -1;	// -1 sentinel value for open spot

		squares = new Square[NUM_SQUARES];
		for(int i = 0; i < NUM_SQUARES; ++i)
			squares[i] = new Square();
		
		// Create all 16 points, these will not change
		// Line will have indices to the points
		// Square will have indices to the lines: left, top, right, bottom
		// for squares[i], lines are i+3 i, i+4, i+7
		new_game();
	}

	public void new_game() {
		for(int i = 0; i < squares.length; ++i)
			squares[i].setOn(false);
	}
	
	/** Set the given player at the given location on the game board.
	 *  The location must be available, or the board will not be changed.
	 * 
	 * @param player - Whatever player we are indexed to depending on number of players
	 * @param location - The location (0-8) to place the move
	 */
	public boolean setMove(int player, int location) {
		// TODO Auto-generated method stub
		if(currentMove ==1 )
		{
			// set location as the start point if start point is available
			startPos = location;
			++currentMove;
			return false;
		}
		else{
			// use start and location for the line - set color to player's
			
			// if end pos is not 1 away from the startPos, then start over
			if(startPos + 1 == location || startPos - 1 == location || startPos + 4 == location || startPos -4 == location){
				currentMove = 1;
				// mark the board : location holds the startPosition for the line to draw
				mBoard[location] = startPos;
				
				return true;
			}else{
				currentMove = 1;
				return false;
			}
		}
	}

	/*
	 * Returns true if all squares are on and one player has more squares than another
	 */
	public int checkForWinner() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	public int getBoardOccupant(int cellNum) {
		// Log.d(TAG, "cellNum: " + cellNum + " board" + Arrays.toString(mBoard));
		return mBoard[cellNum];
	}
}


