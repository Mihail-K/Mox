/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.messages;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Mihail
 */
public class MessageRouter
{
    
    private final Map<String, Set<MessageConsumer>> consumers;
    
    public MessageRouter()
    {
        consumers = new HashMap<>();
    }
    
    void send(Message message)
    {
        consumers.getOrDefault(message.getName(), Collections.emptySet())
                .stream().forEach(consumer -> consumer.consume(message));
    }
    
    public void subscribe(String name, MessageConsumer consumer)
    {
        Set<MessageConsumer> endpoints = consumers.get(name);
        
        if(endpoints == null)
        {
            endpoints = new HashSet<>();
            consumers.put(name, endpoints);
        }
        
        endpoints.add(consumer);
    }
    
    public void unsubscribe(String name, MessageConsumer consumer)
    {
        Set<MessageConsumer> endpoints = consumers.get(name);
        
        if(endpoints != null)
        {
            endpoints.remove(consumer);
            if(endpoints.isEmpty())
            {
                consumers.remove(name);
            }
        }
    }
    
}
