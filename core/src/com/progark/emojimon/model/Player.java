package com.progark.emojimon.model;

import com.progark.emojimon.model.interfaces.Die;
import com.progark.emojimon.model.strategyPattern.CanClearStrategy;
import com.progark.emojimon.model.strategyPattern.MoveValidationStrategy;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Player {

    private boolean creator = false;
    private int homeAreaStartIndex;
    private int homeAreaEndIndex;
    private boolean moveClockwise;
    private MoveValidationStrategy moveValidationStrategy;
    private CanClearStrategy canClearStrategy;
    private List<Die> dice;
    private int piecesInGame; // hold count of pieces that haven't been cleared off
    private int blot; // blot: piece/s that can be thrown out to bar


    public Player(int piecesInGame, int homeAreaStartIndex, int homeAreaEndIndex, boolean moveClockwise, MoveValidationStrategy moveValidationStrategy, CanClearStrategy canClearStrategy, int blot, boolean isCreator){
        this.piecesInGame = piecesInGame;
        this.homeAreaStartIndex = homeAreaStartIndex;
        this.homeAreaEndIndex = homeAreaEndIndex;
        this.moveClockwise = moveClockwise;
        this.moveValidationStrategy = moveValidationStrategy;
        // set strategies for piece movement
        this.canClearStrategy = canClearStrategy;
        this.blot = blot;
        this.creator = isCreator;
    }

    public Move getAvailableBarMove(List<Die> dice, List<Position> positions){
            // check for possible moves from bar
        int barIndex = 0; //barIndex is the first position
        for (int diceIndex = 0; diceIndex < dice.size(); diceIndex++){
            int diceValue = dice.get(diceIndex).getValue();
            int endPositionIndex = moveClockwise ? (barIndex + diceValue) : (barIndex - diceValue);
            Position endPosition = positions.get(endPositionIndex);

            //apply move validation strategy to check if move is valid
            if(moveValidationStrategy.isAvailableMove(positions.get(0), endPosition, blot)){
//                moves.add(new Move(barIndex, endPositionIndex));
                return new Move(barIndex, endPositionIndex);
            }
        }
        return null; // no moves in bar left
    }

    //Get all available moves
    //(currently only checking individual die, not combinations)
    public List<Move> getAvailableMoves(List<Die> dice, List<Position> positions) {
        //TODO: check if there are any dice moves left (i.e. after moving pieces of bar)
        List<Move> moves = new ArrayList<Move>();

        //find all possible moves for player given die values in dice
        for(int positionIndex = 1; positionIndex < positions.size(); positionIndex++){
            Position startPosition = positions.get(positionIndex);
            if(startPosition.getOwner() == this){
                //check for possible moves
                for(int diceIndex = 0; diceIndex < dice.size(); diceIndex++){
                    int diceValue = dice.get(diceIndex).getValue();
                    int endPositionIndex = moveClockwise ? (positionIndex + diceValue) : (positionIndex - diceValue);
                    Position endPosition = positions.get(endPositionIndex);

                    //apply move validation strategy to check if move is valid
                    if(moveValidationStrategy.isAvailableMove(startPosition, endPosition, blot)){
                        moves.add(new Move(positionIndex, endPositionIndex));
                    }
                }
            }
        }

        return moves;
    }

    //GETTERS
    public int getHomeAreaStartIndex() {
        return homeAreaStartIndex;
    }

    public int getHomeAreaEndIndex() {
        return homeAreaEndIndex;
    }

    public boolean getMoveClockwise(){
        return moveClockwise;
    }

    // returns whether player has cleared all of their pieces, i.e. won
    public boolean isDone() {
        return (piecesInGame == 0);
    }

    // everytime a piece is cleared up, decrement pieces in play
    public void updatePieceClearance(){
        --piecesInGame;
    }

    //returns whether player is in a position to start clearing pieces
    public boolean canClear(List<Position> boardPositions) {
        return canClearStrategy.canClear(this, boardPositions);
    }

    // returns whether move is available
    public boolean isAvailableMove(Position start, Position end){
        return moveValidationStrategy.isAvailableMove(start, end, blot);
    }

    public boolean isCreator() {
        return creator;
    }

    // set players dice from gameboard
    public void setDice(List<Die> dice){
        this.dice = dice;
    }

    // if a move is made, reduce players dice list
    public void updateDice(){
        dice.remove(dice.size() - 1);
    }

    // returns whether the players turn has ended
    public boolean finishedTurn(){
        return (dice.size() == 0);
    }

}
