package net.runelite.client.rsb.event.impl;

import net.runelite.client.rsb.event.events.MessageEvent;
import net.runelite.client.rsb.event.listener.MessageListener;

import java.util.logging.Logger;

public class MessageLogger implements MessageListener {

	private final Logger log = Logger.getLogger(MessageLogger.class.getName());

	public void messageReceived(final MessageEvent e) {
		if (e.getSender().equals("")) {
			log.info("[" + e.getID() + "] " + e.getMessage());
		} else {
			log.info("[" + e.getID() + "] " + e.getSender() + ": " + e.getMessage());
		}
	}
}
