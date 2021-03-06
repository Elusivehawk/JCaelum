
package com.elusivehawk.caelum.network;


/**
 * 
 * Lambda-friendly interface for doing things with {@link IConnection}s.
 * 
 * @author Elusivehawk
 */
@FunctionalInterface
public interface IConnectionUser
{
	boolean processConnection(Connection con);
	
}
