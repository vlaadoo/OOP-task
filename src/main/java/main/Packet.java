package main;

import logic.Command;

public class Packet {

	private Command com;
	private boolean restart;
	private boolean restartConfirm;
	private boolean exit;

	
	public Packet(Command command, boolean restart, boolean restartConfirm, boolean exit) {
		this.setCom(command);
		this.setRestart(restart);
		this.setRestartConfirm(restartConfirm);
		this.setExit(exit);
	}

	public void setCom(Command com) {
		this.com = com;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public void setRestartConfirm(boolean restartConfirm) {
		this.restartConfirm = restartConfirm;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
