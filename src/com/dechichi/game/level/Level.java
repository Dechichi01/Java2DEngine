/**
 * Level.java
 * @author Gabriel Dechichi <gabrieldechichi@gmail.com>
 * Created on 2017-02-17
 */
package com.dechichi.game.level;

import com.dechichi.game.gfx.JScreen;
import com.dechichi.game.level.tiles.Tile;

public class Level {
	
	private byte[] tileIds;
	public int width;
	public int height;
	
	public Level(int width, int height)
	{
		tileIds = new byte[width*height];
		this.width = width;
		this.height = height;
		this.generateLevel();
	}
	
	public void generateLevel()
	{
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x * y % 10 < 5)
					tileIds[x + y*width] = Tile.GRASS.getId();
				else
					tileIds[x+y*width] = Tile.STONE.getId();
			}
		}
	}
	
	public void tick(){
		
	}
	
	public void renderTiles(JScreen screen, int xOffset, int yOffset)
	{
		//Clamp
		if (xOffset < 0)
			xOffset = 0;
		if (xOffset > ((width*8) - screen.width))
			xOffset =((width*8) - screen.width);
		if (yOffset < 0)
			yOffset = 0;
		if (yOffset > ((height*8)-screen.height))
			yOffset = ((height*8)-screen.height);
		
		screen.setOffset(xOffset, yOffset);
		
		for (int y = yOffset/8; y <= (yOffset + screen.height/8); y++) {
			for (int x = xOffset/8; x <= (xOffset + screen.width/8); x++) {
				getTile(x,y).render(screen,this, x*8, y*8);
			}
		}
	}
	
	public Tile getTile(int x, int y)
	{
		if (x < 0 || x >= width || y < 0 || y >=height)
			return Tile.VOID;
		
		return Tile.tiles[tileIds[x+y*width]];
	}
}
