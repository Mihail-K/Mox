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
import mox.messages.MessageRouter;
import mox.systems.System;
import mox.systems.SystemFactory;

/**
 *
 * @author Mihail
 */
public class Engine
{
    
    private final EntityManager manager;
    private final MessageRouter router;
    private final List<System> systems;
    
    /* this() */
    {
        systems = new ArrayList<>();
        router = new MessageRouter();
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
    
    public void init()
    {
        systems.stream().forEach(system -> system.init());
    }
    
    public boolean registerSystem(Class<?> type)
    {
        System system = SystemFactory.create(type, manager, router);
        if(system == null) return false;
        
        registerSystem(system);
        return true;
    }
    
    public void registerSystem(System system)
    {
        systems.add(system);
    }
    
    public void update(int delta)
    {
        systems.stream().forEach(system -> system.update(delta));
    }
    
    public boolean unregisterSystem(Class<?> type)
    {
        System system = systems.stream()
                .filter(s -> s.getClass().equals(type))
                .findFirst().orElse(null);
        if(system == null) return false;
        
        unregisterSystem(system);
        return true;
    }
    
    public void unregisterSystem(System system)
    {
        systems.remove(system);
    }
    
}
