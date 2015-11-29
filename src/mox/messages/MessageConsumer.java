/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.messages;

/**
 *
 * @author Mihail
 */
public interface MessageConsumer
{
    
    default void consume(Message message)
    {
        // Children may implement as necessary.
    }
    
}
