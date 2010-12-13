package game;

import java.awt.Color;

import machine.server;

public class terrorist{
	
	private server serv;
	private gameWindow gw;
	
	public terrorist(server s){
		setServ(s);

		setGw(new gameWindow());
				
	}
	
	public void setServ(server serv) {
		this.serv = serv;
	}

	public server getServ() {
		return serv;
	}

	public void setGw(gameWindow gw) {
		this.gw = gw;
	}

	public gameWindow getGw() {
		return gw;
	}
}
