package net.runelite.client.rsb.event.impl;

import net.runelite.api.Point;
import net.runelite.client.rsb.botLauncher.RuneLite;
import net.runelite.client.rsb.event.listener.PaintListener;
import net.runelite.client.rsb.methods.MethodContext;
import net.runelite.client.rsb.wrappers.RSGroundItem;
import net.runelite.client.rsb.wrappers.RSModel;
import net.runelite.client.rsb.wrappers.RSPlayer;
import net.runelite.client.rsb.wrappers.RSTile;

import java.awt.*;

public class DrawItems implements PaintListener {

	private final MethodContext ctx;

	public DrawItems(RuneLite bot) {
		ctx = bot.getMethodContext();
	}

	public void onRepaint(final Graphics render) {
		if (!ctx.game.isLoggedIn()) {
			return;
		}
		final RSPlayer player = ctx.players.getMyPlayer();
		if (player == null) {
			return;
		}
		final FontMetrics metrics = render.getFontMetrics();
		final RSTile location = player.getLocation();
		final int locX = location.getWorldLocation().getX();
		final int locY = location.getWorldLocation().getY();
		final int tHeight = metrics.getHeight();
		for (int x = locX - 25; x < locX + 25; x++) {
			for (int y = locY - 25; y < locY + 25; y++) {
				final Point screen = ctx.calc.tileToScreen(new RSTile(x, y));
				if (!ctx.calc.pointOnScreen(screen)) {
					continue;
				}
				final RSGroundItem[] items = ctx.groundItems.getAllAt(x, y);
				if (items.length > 0) {
					RSModel model = items[0].getModel();
					if (model != null) {
						render.setColor(Color.BLUE);
						for (Polygon polygon : model.getTriangles()) {
							render.drawPolygon(polygon);
						}
					}
				}
				for (int i = 0; i < items.length; i++) {
					render.setColor(Color.RED);
					render.fillRect((int) screen.getX() - 1, (int) screen.getY() - 1, 2, 2);
					final String s = "" + items[i].getItem().getID();
					final int ty = screen.getY() - tHeight * (i + 1) + tHeight / 2;
					final int tx = screen.getX() - metrics.stringWidth(s) / 2;
					render.setColor(Color.green);
					render.drawString(s, tx, ty);
				}
			}
		}
	}
}
