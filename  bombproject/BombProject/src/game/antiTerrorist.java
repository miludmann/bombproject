package game;

import machine.client;

public class antiTerrorist extends player {
	private client cl;

	public antiTerrorist(client c) {
		super(false);
		super.setCl(c);
		setCl(c);
	}

	public void setCl(client cl) {
		this.cl = cl;
	}

	public client getCl() {
		return cl;
	}
}
