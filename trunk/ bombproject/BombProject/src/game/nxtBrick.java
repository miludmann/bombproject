package game;

import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;
import machine.server;
import MessageComponent.LIMessage;
import MessageComponent.LIMessageType;
import MessageComponent.MessageFramework;
import MessageComponent.MessageListenerInterface;


public class nxtBrick implements MessageListenerInterface {
	
	private static MessageFramework mF;
	private NXTInfo m_info;

	private boolean isPlayer;

	private commandNXT cmd;
	private server serv;


	public nxtBrick(String name, String address){
		
		setM_info(new NXTInfo(NXTCommFactory.BLUETOOTH, name, address));
		setMF(MessageFramework.getInstance());
		getMF().addMessageListener(this);
		getMF().ConnectToNXT(m_info);
	}
	
	public nxtBrick(commandNXT cnxt, String name, String address){
		this(name, address);
		setCmd(cnxt);
		setPlayer(true);
	}
	
	public nxtBrick(server server, String name, String address) {
		this(name, address);
		setServ(server);
		setPlayer(false);
	}

	@Override
	public void recievedNewMessage(LIMessage msg) {
		
		if ( this.isPlayer() )
		{
			getCmd();
			// Message coming from player's NXT Brick
			commandNXT.getUnit().interpretBrick(msg.getPayload());
		}
		else
		{
			// Message coming from bomb's NXT Brick
			getServ().interpretBomb(msg.getPayload());
		}
	}

	public static void setMF(MessageFramework mF) {
		nxtBrick.mF = mF;
	}

	public static MessageFramework getMF() {
		return mF;
	}

	public void setM_info(NXTInfo m_info) {
		this.m_info = m_info;
	}

	public NXTInfo getM_info() {
		return m_info;
	}

	public void setCmd(commandNXT cmd) {
		this.cmd = cmd;
	}

	public commandNXT getCmd() {
		return cmd;
	}

	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setServ(server serv) {
		this.serv = serv;
	}

	public server getServ() {
		return serv;
	}
	
	public void sendMessage(String s){
		getMF().SendMessage(new LIMessage(LIMessageType.Command, s));
	}

}
