import java.util.HashMap;
import java.util.Map;

class GameLoader {

  private Map<String,Game> gameMap;

  GameLoader(){
    gameMap = new HashMap<>();
  }

  Game getGame(String id){
    if (gameMap.containsKey(id)) return gameMap.get(id);
    return newGame(id);
  }

  private Game newGame(String id){
    Game game = new Game(this,id);
    gameMap.put(id,game);
    return game;
  }

  void deleteGame(String id){
    gameMap.remove(id);
  }

}
