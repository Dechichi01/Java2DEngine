/**
 * JScreen.java
 * @author Gabriel Dechichi <gabrieldechichi@gmail.com>
 * Created on 2017-02-16
 */
package com.dechichi.game.gfx;

public class JScreen {
	
	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH -1;
	
	public int[] pixels;
	
	//Makes possible to move the screen in 2D
	public int xOffset = 0;
	public int yOffset = 0;
	
	public int width;
	public int height;
	
	public SpriteSheet sheet;
	
	public JScreen(int width, int height, SpriteSheet sheet)
	{
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		
		pixels = new int[width*height];	
	}

	/**
	 * Render a 8x8 tile fom rthe sprite_sheet.png at a given position
	 * @param xPos
	 * @param yPos
	 * @param tile
	 * @param colour
	 */
	public void Render(int xPos, int yPos, int tile, int colour, boolean flipX, boolean flipY)
	{
		xPos -= xOffset;
		yPos -= yOffset;
		
		int xTile = tile % 32;//column
		int yTile = tile / 32;//row
		int pixelIndex = (xTile*8) + (yTile*8)*sheet.width;
		
		for (int y = 0; y < 8; y++) {
			//Check if it's inside bounds
			if (y+yPos < 0 || y+yPos >= height) continue;
			int ySheet = (flipY)? (7-y):y;
			for (int x = 0; x < 8; x++) {
				if (x+xPos < 0 || x+xPos >= width) continue;
				int xSheet = (flipX)?(7-x):x;
				int col = (colour >> (sheet.pixels[xSheet + ySheet*sheet.width + pixelIndex]*8)) & 255;
				if (col<255) pixels[(x+xPos) + (y+yPos)*width] = col;
			}
		}
	}
	
	public void Render(int xPos, int yPos, int tile, int colour) {
        Render(xPos, yPos, tile, colour, false, false);
}
	
	
}
