/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mox.entities.EntityManager;
import mox.systems.System;

/**
 *
 * @author Mihail
 */
public class Engine
{
    
    private final EntityManager manager;
    private final List<System> systems;
    
    /* this() */
    {
        systems = new ArrayList<>();
    }
    
    public Engine(EntityManager manager)
    {
        this.manager = manager;
    }
    
    public EntityManager getEntityManager()
    {
        return manager;
    }
    
    public List<System> getSystems()
    {
        return Collections.unmodifiableList(systems);
    }
    
    public void registerSystem(System system)
    {
        systems.add(system);
    }
    
    public void update(int delta)
    {
        systems.stream().forEach((system) ->
        {
            system.update(delta);
        });
    }
    
    public void unregisterSystem(System system)
    {
        systems.remove(system);
    }
    
}
