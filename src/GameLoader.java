import java.util.HashMap;
import java.util.Map;

class GameLoader {

  private Map<String,Game> gameMap;
  private Controller controller;

  GameLoader(Controller controller){
    this.controller = controller;
    gameMap = new HashMap<>();
  }

  Game getGame(String id){
    if (gameMap.containsKey(id)) return gameMap.get(id);
    return null;
  }

  Game newGame(String id){
    Game game = new Game(controller,id);
    gameMap.put(id,game);
    return game;
  }

  void deleteGame(String id){
    gameMap.remove(id);
  }

}
