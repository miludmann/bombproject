package game;

import machine.server;

public class terrorist extends player {
	private server serv;

	
	public terrorist(server s) {
		super(true);
		setServ(s);
		synchronyseClient();
	}
	
	
	private void synchronyseClient() {
		getServ().getTs().sendMsgClient(Long.toString(getTimeStart()));
	}
	
	
	public void setServ(server serv) {
		this.serv = serv;
	}

	public server getServ() {
		return serv;
	}
}
