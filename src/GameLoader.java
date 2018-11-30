import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class GameLoader {

  private Map<String,Game> gameMap;

  GameLoader(){
    // thread-safe map
    gameMap = new ConcurrentHashMap<>();
  }

  // always returns a game...
  // either an existing game or a new game keyed to id
  Game getGame(String id){
    // atomic
    gameMap.putIfAbsent(id, new Game(this,id));
    // atomic
    return gameMap.get(id);
  }

  void deleteGame(String id){
    gameMap.remove(id);
  }

}
