/**
 * Font.java
 * @author Gabriel Dechichi <gabrieldechichi@gmail.com>
 * Created on 2017-02-17
 */
package com.dechichi.game.gfx;

public class Font {
	public static String chars = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ      "
	+ "0123456789.,:;'\"!?$%()-=+/      ";

	public static void render(String msg, JScreen screen, int x, int y,
			int colour) {
		msg = msg.toUpperCase();

		for (int i = 0; i < msg.length(); i++) {
			int charIndex = chars.indexOf(msg.charAt(i));
			if (charIndex >= 0) {
				screen.Render(x + i * 8, y, charIndex + 30 * 32, colour);
			}
		}
	}
}
