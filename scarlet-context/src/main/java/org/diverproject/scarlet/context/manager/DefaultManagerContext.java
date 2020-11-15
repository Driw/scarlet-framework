package org.diverproject.scarlet.context.manager;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import lombok.Data;
import lombok.experimental.Accessors;
import org.diverproject.scarlet.context.ContextNameGenerator;
import org.diverproject.scarlet.context.DefaultInstanceEntry;
import org.diverproject.scarlet.context.InstanceEntry;
import org.diverproject.scarlet.context.LoggerFactory;
import org.diverproject.scarlet.context.reflection.ClassUtils;
import org.diverproject.scarlet.context.reflection.ReflectionInstanceUtils;
import org.diverproject.scarlet.context.reflection.ReflectionInterfaceUtils;
import org.diverproject.scarlet.context.reflection.ReflectionUtils;
import org.diverproject.scarlet.context.utils.ContextStreamUtils;
import org.diverproject.scarlet.context.utils.Pair;
import org.diverproject.scarlet.logger.Logger;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

@Data
@Accessors(chain = true)
public class DefaultManagerContext implements ManagerContext {

	private static final Logger logger = LoggerFactory.get(ManagerContext.class);

	private boolean running;
	private boolean initialized;
	private Map<String, Manager> managerInstances;
	private ManagerQueue managerQueue;
	private Thread thread;

	public DefaultManagerContext() {
		this.setManagerInstances(new TreeMap<>());
		this.setManagerQueue(ReflectionInstanceUtils.getInstanceOf(ManagerQueue.class));
	}

	@Override
	public Map<String, Manager> initialize() {
		this.setInitialized(true);

		return this.setManagerInstances(this.initializeManagers())
			.getManagerInstances();
	}

	@Override
	public void register(InstanceEntry<String, Manager> instanceEntry) {
		Manager oldManager = this.getManagerInstances().put(instanceEntry.getKey(), instanceEntry.getValue());

		logger.notice(ManagerContextLanguage.MANAGER_REGISTERED_SUCCESSFULLY, instanceEntry.getKey(), instanceEntry.getValue());

		if (Objects.nonNull(oldManager)) {
			logger.info(ManagerContextLanguage.MANAGER_REPLACED_BY_ANOTHER, instanceEntry.getKey(), instanceEntry.getValue(), nameOf(oldManager));
		}

		this.getManagerQueue().add(instanceEntry.getValue());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Manager> T get(Class<T> managerClass) {
		String key = ContextNameGenerator.generateKeyFor(managerClass);
		Manager manager = this.getManagerInstances().get(key);

		if (Objects.isNull(manager)) {
			return null;
		}

		if (ReflectionUtils.isInstanceOf(manager, managerClass)) {
			return (T) manager;
		}

		throw ManagerContextError.invalidManagerInstanceOf(managerClass, key, manager);
	}

	@Override
	public boolean isAlive() {
		return Objects.nonNull(this.getThread()) && this.getThread().isAlive() && this.isRunning();
	}

	@Override
	public void start() {
		Thread thread = this.getThread(); // TODO add as specification

		if (!Objects.isNull(thread) && Thread.currentThread().equals(thread)) {
			this.run();
		} else {
			this.startAsParallelThread();
		}
	}

	@Override
	public void stop() {
		for (Manager manager : this.getManagerQueue()) {
			manager.stop();
		}
	}

	@Override
	public void restart() {
		for (Manager manager : this.getManagerQueue()) {
			manager.restart();
		}
	}

	@Override
	public void finish() {
		for (Manager manager : this.getManagerQueue()) {
			manager.finish();
		}
	}

	@Override
	public Thread thread() {
		return this.getThread();
	}

	public Map<String, Manager> initializeManagers() {
		this.findAllManagerImplementations().entrySet()
			.stream()
			.map(this::create)
			.forEach(this::register);

		return this.getManagerInstances();
	}

	@SuppressWarnings("unchecked")
	public <M extends Manager> Map<String, Class<M>> findAllManagerImplementations() {
		Set<Class<?>> managerImplementations = ReflectionInterfaceUtils.getAllImplementationsOf(Manager.class);
		ClassUtils.removeDuplicatedImplementations(managerImplementations);

		return managerImplementations.stream()
			.sorted(ReflectionUtils.compareByPriorityAnnotation())
			.map(managerClass -> (Class<M>) managerClass)
			.map(managerClass -> new Pair<>(ContextNameGenerator.generateKeyFor(managerClass), managerClass))
			.filter(ContextStreamUtils.distinctByKey(Pair::getFirstValue))
			.collect(ContextStreamUtils.mapPair());
	}

	public <T extends Manager> InstanceEntry<String, T> create(Map.Entry<String, Class<T>> managerClassEntry) {
		String key = managerClassEntry.getKey();
		T manager = ReflectionUtils.createInstanceOfEmptyConstructor(managerClassEntry.getValue());

		return new DefaultInstanceEntry<>(key, manager);
	}

	private void startAsParallelThread() {
		thread = new Thread(this.threadRunnable());
		thread.setName(nameOf(this)); // TODO add as configuration
		thread.setDaemon(true);
		thread.start();
		this.setThread(thread);
	}

	protected Runnable threadRunnable() {
		return DefaultManagerContext.this::run;
	}

	public void run() {
		this.setRunning(true);

		while (DefaultManagerContext.this.isAlive()) {
			for (Manager manager : DefaultManagerContext.this.getManagerQueue()) {
				DefaultManagerContext.this.touchManager(manager);
			}
		}
	}

	protected void touchManager(Manager manager) {
		manager.tick();
	}

}
