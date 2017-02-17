package com.dechichi.game;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.dechichi.game.gfx.Colours;
import com.dechichi.game.gfx.Font;
import com.dechichi.game.gfx.JScreen;
import com.dechichi.game.gfx.SpriteSheet;
import com.dechichi.game.level.Level;

/**
 * JGame.java
 * @author Gabriel Dechichi <gabrieldechichi@gmail.com>
 * Created on 2017-02-15
 */

public class JGame extends Canvas implements Runnable{
	
	private static boolean running = false;
	private static int targetFPS = 60;
	private static int tickCount = 0;
	
	//JFrame stuff
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 3;
	public static final String NAME="Game";
	
	private JFrame frame;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[6*6*6];//6 shades of each color
	
	private JScreen screen;
	private InputHandler inputHandler;
	public Level level;
	
	private int xPos = 0, yPos = 0;
	
	public JGame()
	{
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public synchronized void Start()
	{
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void Stop()
	{
		running = false;
	}
	
	private void Init()
	{
		int index = 0;
		for (int r = 0; r <= 255; r+= (255/5)) {
			for (int g = 0; g <= 255; g+= (255/5)) {
				for (int b = 0; b <= 255; b+= (255/5)) {
					int vaitomarnocu = r << 16 | g << 8 | b;
					colours[index++] = r << 16 | g << 8 | b;
				}
			}
		}
		screen = new JScreen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png"));
		inputHandler = new InputHandler(this);
		level = new Level(64,64);
	}
	
	@Override
	public void run()
	{
		Init();
		JTime.Init();
		double nsBetweenTicks = 1000000000D/targetFPS;
		double lastTickTime = System.nanoTime();
		double currentTickTime;
		
		double lastSecond = System.currentTimeMillis();
		while(running)
		{
			currentTickTime = System.nanoTime();
			if (currentTickTime - lastTickTime >= nsBetweenTicks)
			{
				JTime.Update();
				tick();
				render();
				lastTickTime = currentTickTime;
			}			
			
			if (System.currentTimeMillis() - lastSecond >= 1000)
			{
				//System.out.println(tickCount);
				tickCount = 0;
				lastSecond = System.currentTimeMillis();
			}
		}
	}
	
	public void tick()
	{
		tickCount++;

		if (inputHandler.up.isPressed()) yPos -= 1;
		if (inputHandler.down.isPressed()) yPos += 1;
		if (inputHandler.left.isPressed()) xPos -= 1;
		if (inputHandler.right.isPressed()) xPos += 1;
		
		level.tick();
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(2);
			return;
		}
		
		//Player on center of the screen
		int xOffset = xPos - (screen.width/2);
		int yOffset = yPos - (screen.height/2);
		
		//Render level tiles
		level.renderTiles(screen, xOffset, yOffset);
		
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colourCode = screen.pixels[x+y*screen.width];
				if (colourCode < 255) pixels[x + y*WIDTH] = colours[colourCode];
			}
		}
		
		Graphics g=bs.getDrawGraphics();
		g.drawRect(0,0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new JGame().Start();
	}

}
