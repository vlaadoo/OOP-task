package connect;

import logic.Command;

import java.io.Serializable;

public class Packet implements Serializable {

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

	public Command getCom() {
		return com;
	}

	public void setCom(Command com) {
		this.com = com;
	}

	public boolean isRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public boolean isRestartConfirm() {
		return restartConfirm;
	}

	public void setRestartConfirm(boolean restartConfirm) {
		this.restartConfirm = restartConfirm;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
