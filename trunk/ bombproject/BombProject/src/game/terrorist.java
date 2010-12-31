package game;

import machine.server;

public class terrorist extends player {
	private server serv;

	
	public terrorist(server s) {
		super(true);
		super.setServ(s);
		setServ(s);

	}
	
	public void setServ(server serv) {
		this.serv = serv;
	}

	public server getServ() {
		return serv;
	}
}
