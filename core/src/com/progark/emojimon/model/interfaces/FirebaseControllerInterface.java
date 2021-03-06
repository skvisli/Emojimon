package com.progark.emojimon.model.interfaces;

import com.progark.emojimon.GameManager.GameStatus;
import com.progark.emojimon.model.Player;
import com.progark.emojimon.model.fireBaseData.GameData;
import com.progark.emojimon.model.fireBaseData.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface FirebaseControllerInterface {
    void testWrite(String testMessage);
    // Add new
    void createNewGame(String creatorPlayer, Settings strategies);
    void addGameDataChangeListener(String gameID); // TODO Do we need it here?
    void updateLastTurn(String gameID, boolean player, List<Integer> dices, List<List<Integer>> actions);

    // Setters
    void setGameBoardByGameID(String gameID, List<List<Integer>> gameBoard);
    void setGameStatusByGameID(String gameID, GameStatus newStatus);

    void joinGameById(String gamId);
    void addSubscriber(SubscriberToFirebase subscriber);
    void endGame(String gameId, boolean isCreator);
    void updateGameData (String gameId, GameData gameData);
    void getAllWaitingGames ();

    ArrayList getGameStateByGameID(String id);


}
