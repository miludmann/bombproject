package game;

import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;
import MessageComponent.LIMessage;
import MessageComponent.MessageFramwork;
import MessageComponent.MessageListenerInterface;

public class nxtBrick implements MessageListenerInterface {
	
	private static MessageFramwork mF;
	private NXTInfo m_info;
	private commandNXT cmd;


	public nxtBrick(String name, String address){
		
		setM_info(new NXTInfo(NXTCommFactory.BLUETOOTH, name, address));
		setMF(MessageFramwork.getInstance());
		getMF().addMessageListener(this);
		getMF().ConnectToNXT(m_info);
	}
	
	public nxtBrick(commandNXT cnxt, String name, String address){
		this(name, address);
		setCmd(cnxt);
	}
	
	@Override
	public void recievedNewMessage(LIMessage msg) {
		System.out.println(msg.getM_payload());
	}

	public static void setMF(MessageFramwork mF) {
		nxtBrick.mF = mF;
	}

	public static MessageFramwork getMF() {
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

}
