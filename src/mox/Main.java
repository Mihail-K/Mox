/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox;

import mox.states.TestState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Mihail
 */
public class Main extends StateBasedGame
{

    public Main(String name)
    {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.addState(new TestState());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Main("Test"));
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(true);
        app.start();
    }

}
