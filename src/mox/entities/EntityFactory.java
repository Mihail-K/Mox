/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import mox.Component;

/**
 *
 * @author Mihail
 */
public final class EntityFactory
{
    
    private EntityFactory()
    {
        // Hidden Constructor.
    }
    
    public static Entity createEntity()
    {
        return new Entity();
    }
    
    public static Entity createEntity(Component... components)
    {
        return new Entity(Arrays.stream(components).collect(
                Collectors.toMap(Component::getClass, Function.identity())));
    }
    
    public static Entity createEntity(Class<?>... types)
            throws InstantiationException, IllegalAccessException
    {
        Map<Class<?>, Component> components = new HashMap<>();
        for(Class<?> type : types)
        {
            components.put(type, (Component) type.newInstance());
        }
        return new Entity(components);
    }
    
}
