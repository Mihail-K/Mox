/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.systems;

import java.util.stream.Stream;

import mox.components.PositionComponent;
import mox.entities.EntityManager;

/**
 *
 * @author Mihail
 */
public class PositionSystem extends System
{

    public PositionSystem(EntityManager manager)
    {
        super(manager);
    }

    public Stream<PositionComponent> getComponents()
    {
        return getManager().getComponents(PositionComponent.class)
                .stream().map(component -> (PositionComponent) component);
    }

    @Override
    public void update(int delta)
    {
        getComponents().forEach(component -> component.update(delta));
    }

}
