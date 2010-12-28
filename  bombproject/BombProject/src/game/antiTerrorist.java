package game;

import machine.client;

public class antiTerrorist extends player {
	private client cl;

	public antiTerrorist(client c) {
		super(false);
		setCl(c);
	}

	public void setCl(client cl) {
		this.cl = cl;
	}

	public client getCl() {
		return cl;
	}
}
