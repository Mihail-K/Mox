/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.entities;

import mox.handles.HandleManager;

/**
 *
 * @author Mihail
 */
public class EntityManager
{
    
    private final HandleManager<Entity> entities;
    
    public EntityManager()
    {
        entities = new HandleManager<>();
    }
    
    public EntityManager(int capacity)
    {
        entities = new HandleManager<>(capacity);
    }
    
}
