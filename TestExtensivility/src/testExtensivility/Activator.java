package testExtensivility;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import pa.iscde.javaTasks.ext.TasksServices;

public class Activator implements BundleActivator {

	private static Activator instance;
	private TasksServices taskService;

	public static Activator getInstance() {
		return instance;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		instance = this;
//		System.out.println("hey");
//		ServiceReference<TasksServices> tasksServiceReference = bundleContext
//				.getServiceReference(TasksServices.class);
//		TasksServices taskService = bundleContext.getService(tasksServiceReference);
//		taskService.update();
	}

	@Override
	public void stop(BundleContext context) throws Exception {

	}

	public TasksServices getTaskService() {
		return taskService;
	}
}
