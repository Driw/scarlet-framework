package org.diverproject.scarlet.context;

import lombok.Data;
import lombok.experimental.Accessors;
import org.diverproject.scarlet.context.reflection.ReflectionInterfaceUtils;
import org.diverproject.scarlet.context.singleton.SingletonContext;

@Data
@Accessors(chain = true)
public class DefaultScarletContext implements ScarletContext {

	private SingletonContext singletonContext;

	@Override
	public void initialize(String[] args) {
		this.setSingletonContext(ReflectionInterfaceUtils.getInstanceOf(SingletonContext.class));
		this.getSingletonContext().initialize(this);
	}
}
