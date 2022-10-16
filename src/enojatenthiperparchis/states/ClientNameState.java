
package enojatenthiperparchis.states;

import enojatenthiperparchis.Game;
import java.awt.Graphics;

public class ClientNameState extends State{
    public ClientNameState(Game game){
        super(game);
    }

    @Override
    public void tick() {
        game.setState(new ClientState(game,"demma"));
    }

    @Override
    public void render(Graphics g) {
    }
}
