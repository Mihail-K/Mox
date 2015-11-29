/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.states;

import mox.Engine;
import mox.components.PositionComponent;
import mox.entities.EntityManager;
import mox.systems.PositionSystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
        engine.registerSystem(new PositionSystem(engine.getEntityManager()));
        
        engine.getEntityManager().addEntity(new PositionComponent(
                new Vector2f(100, 100), new Vector2f(25, 0)));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException
    {
        engine.getEntityManager().getComponents(PositionComponent.class)
                .stream().map(component -> (PositionComponent) component)
                .forEach((PositionComponent component) ->
        {
            g.drawOval(
                    component.getPosition().x,
                    component.getPosition().y,
                    10, 10);
        });
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i)
            throws SlickException
    {
        engine.getSystems().stream().forEach(system -> system.update(i));
    }
    
}
