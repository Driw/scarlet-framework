package org.diverproject.scarlet.context.manager;

import static org.diverproject.scarlet.context.ScarletContextConstants.COMPARABLE_MAJOR;
import static org.diverproject.scarlet.context.ScarletContextConstants.COMPARABLE_MINOR;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DefaultManager implements Manager {

	private int order;
	private ManagerStatus status;

	public DefaultManager() {
		this.setStatus(ManagerStatus.IDLE);
	}

	@Override
	public void tick() {
		switch (this.getStatus()) {
			case STARTING:
				this.onStarting();
				this.setStatus(ManagerStatus.RUNNING);
				break;

			case RUNNING:
				this.onRunning();
				break;

			case RESTARTING:
				this.onRestarting();
				this.start();
				break;

			case STOPPING:
				this.onStop();
				this.setStatus(ManagerStatus.STOPPED);
				break;

			case FINISHING:
				this.onFinish();
				this.setStatus(ManagerStatus.FINISHED);
				break;
		}
	}

	@Override
	public void start() {
		this.setStatus(ManagerStatus.STARTING);
	}

	@Override
	public void restart() {
		this.setStatus(ManagerStatus.RESTARTING);
	}

	@Override
	public void stop() {
		this.setStatus(ManagerStatus.STOPPING);
	}

	@Override
	public void finish() {
		this.setStatus(ManagerStatus.FINISHING);
	}

	@Override
	public boolean equals(Object object) {
		return object == this;
	}

	@Override
	public int compareTo(Manager manager) {
		if (manager.getOrder() == 0 && this.getOrder() > 0) {
			return COMPARABLE_MINOR;
		}

		if (this.getOrder() == 0 && manager.getOrder() > 0) {
			return COMPARABLE_MAJOR;
		}

		return Integer.compare(this.getOrder(), manager.getOrder());
	}

	public void onStarting() {
	}

	public void onRunning() {
	}

	public void onRestarting() {
	}

	public void onStop() {
	}

	public void onFinish() {
	}
}
