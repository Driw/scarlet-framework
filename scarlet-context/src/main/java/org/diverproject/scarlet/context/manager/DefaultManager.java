package org.diverproject.scarlet.context.manager;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.diverproject.scarlet.context.LoggerFactory;
import org.diverproject.scarlet.logger.Logger;

import static org.diverproject.scarlet.context.ScarletContextConstants.COMPARABLE_MAJOR;
import static org.diverproject.scarlet.context.ScarletContextConstants.COMPARABLE_MINOR;

@Data
@Accessors(chain = true)
public class DefaultManager implements Manager {

	private static final Logger logger = LoggerFactory.get(Manager.class);

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
				break;

			case RUNNING:
				this.onRunning();
				break;

			case RESTARTING:
				this.onRestarting();
				break;

			case STOPPING:
				this.onStop();
				break;

			case FINISHING:
				this.onFinish();
				break;

			default:
				logger.warn(ManagerContextLanguage.UNEXPECTED_TICK_STATUS, this.getStatus());
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
	public int compareTo(Manager manager) {
		if (manager.getOrder() == 0 && this.getOrder() > 0)
			return COMPARABLE_MINOR;

		if (this.getOrder() == 0 && manager.getOrder() > 0)
			return COMPARABLE_MAJOR;

		return Integer.compare(this.getOrder(), manager.getOrder());
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	protected void onStarting() {
		this.setStatus(ManagerStatus.RUNNING);
	}

	protected void onRunning() {
		this.update();
	}

	protected void onRestarting() {
		this.setStatus(ManagerStatus.STARTING);
		this.start();
	}

	protected void onStop() {
		this.setStatus(ManagerStatus.STOPPED);
	}

	protected void onFinish() {
		this.setStatus(ManagerStatus.FINISHED);
	}

	protected void update() {
		// default implementation ignored
	}
}
