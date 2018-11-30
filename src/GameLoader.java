import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class GameLoader {

  private Map<String,Game> gameMap;

  GameLoader(){
    // thread-safe map
    gameMap = new ConcurrentHashMap<>();
  }

  Game getGame(String id){
    if (gameMap.containsKey(id)) return gameMap.get(id);
    return newGame(id);
  }

  private Game newGame(String id){
    // atomic
    gameMap.putIfAbsent(id, new Game(this,id));
    // atomic
    return gameMap.get(id);
  }

  void deleteGame(String id){
    gameMap.remove(id);
  }

}
