package model.utility;

import java.util.HashMap;
import java.util.Map;

import model.input.InputImpl;
import model.object.Tank;

public class AI {
    
    private static Map<Direction, Boolean> movement = new HashMap<>();
    private static InputImpl input;
    
    public static void act(Tank enemy, Tank player) {
        
        if (Calculate.distance(enemy.getPosition(), player.getPosition()) > 200) {
            goCloser(enemy, player);
        } else {
            goAway(enemy, player);
        }
        
        input = new InputImpl(movement, null);  // Manca metodo per cannone + shot, quindi per ora null
        
        // Probabilmente in realtà la act ritorna l'input e la enemy.update va chiamata nel controller
        // ma per ora la metto qua e lascio void act
        enemy.update(input);
    }
    
    private static void goAway(Tank enemy, Tank player) {
        // Vedere se si può chiamare goCloser e poi semplicemente invertire velocità
    }
    
    private static void goCloser(Tank enemy, Tank player) {
        
        // RIGHT and LEFT
        if (enemy.getPosition().getFirst() - player.getPosition().getFirst() > 0) {
            movement.put(Direction.LEFT, true);
            movement.put(Direction.RIGHT, false);
        } else {
            movement.put(Direction.RIGHT, true);
            movement.put(Direction.LEFT, false);
        }
        
        // UP and DOWN
        if (enemy.getPosition().getSecond() - player.getPosition().getSecond() > 0) {
            movement.put(Direction.UP, true);
            movement.put(Direction.DOWN, false);
        } else {
            movement.put(Direction.DOWN, true);
            movement.put(Direction.UP, false);
        }
    }
    
}
