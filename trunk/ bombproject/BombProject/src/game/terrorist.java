package game;

import machine.server;

public class terrorist extends player {

	public terrorist(server s, boolean isT) {
		super(s, isT);
		synchronyseClient();
	}

	public terrorist(server s) {
		super(s, true);
		synchronyseClient();
	}
	
	
	private void synchronyseClient() {
		getServ().getTs().sendMsgClient(Long.toString(getTimeStart()));
	}
}
