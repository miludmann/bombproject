package game;

import machine.server;

public class antiTerrorist extends player {

	public antiTerrorist(server s, boolean isT) {
		super(s, isT);
	}

	public antiTerrorist(server s) {
		super(s, true);
	}
}
