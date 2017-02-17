/**
 * InputHandler.java
 * @author Gabriel Dechichi <gabrieldechichi@gmail.com>
 * Created on 2017-02-17
 */
package com.dechichi.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	public class Key
	{
		private boolean pressed = false;
		private int numTimesPressed = 0;
		
		public void Set(boolean isPressed)
		{
			pressed = isPressed;
			if (pressed) numTimesPressed++;
		}
		
		public boolean isPressed(){return pressed;}
		public int getTimesPressed() {return numTimesPressed;}
	}

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	
	public InputHandler(JGame game)
	{
		game.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		setKey(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		setKey(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void setKey(int keyCode, boolean isPressed)
	{
		switch (keyCode) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			up.Set(isPressed);		
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			down.Set(isPressed);	
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			left.Set(isPressed);		
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			right.Set(isPressed);	
			break;
		}
	}

}
