/**
 * JTime.java
 * @author Gabriel Dechichi <gabrieldechichi@gmail.com>
 * Created on 2017-02-15
 */
package com.dechichi.game;

/**
 * @author gabri
 *
 */
public class JTime {
	public static double deltaTime; 
	private static long lastTime;
	
	public static void Init()
	{
		lastTime = System.currentTimeMillis();
		deltaTime = 0;
	}
	public static void Update()
	{
		long currentTime = System.currentTimeMillis();
		deltaTime = (double)(currentTime-lastTime)/1000L;
		lastTime = currentTime;
	}
}
