/**
 * BasicTile.java
 * @author Gabriel Dechichi <gabrieldechichi@gmail.com>
 * Created on 2017-02-17
 */
package com.dechichi.game.level.tiles;

import com.dechichi.game.gfx.JScreen;
import com.dechichi.game.level.Level;

/**
 * @author gabri
 *
 */
public class BasicTile extends Tile{
	protected int tileId;
	protected int tileColour;

	public BasicTile(int id, int x, int y, int tileColour) {
		super(id, false, false);
		this.tileId = x + y;
		this.tileColour = tileColour;
	}

	@Override
	public void render(JScreen screen, Level level, int x, int y) {
		screen.Render(x, y, tileId, tileColour);
	}
}
