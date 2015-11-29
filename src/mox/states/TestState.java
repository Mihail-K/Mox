/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.states;

import mox.Engine;
import mox.components.PositionComponent;
import mox.components.SpriteComponent;
import mox.entities.EntityManager;
import mox.systems.PositionSystem;
import mox.systems.SpriteSystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Mihail
 */
public class TestState extends BasicGameState
{
    
    private Engine engine;

    @Override
    public int getID()
    {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException
    {
        engine = new Engine(new EntityManager());
        engine.registerSystem(PositionSystem.class);
        engine.registerSystem(SpriteSystem.class);
        
        engine.getEntityManager().addEntity(
                new PositionComponent(new Vector2f(100, 100), new Vector2f(25, 0)),
                new SpriteComponent(new Circle(0, 0, 25)));
        engine.init();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException
    {
        engine.getEntityManager().getComponents(SpriteComponent.class).stream()
                .map(c -> ((SpriteComponent) c).getShape()).forEach(g::draw);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException
    {
        engine.update(delta);
    }
    
}
