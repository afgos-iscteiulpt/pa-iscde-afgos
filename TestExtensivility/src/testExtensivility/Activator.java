package testExtensivility;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pa.iscde.javaTasks.ext.TasksServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class Activator implements BundleActivator {

	private static Activator instance;
	private TasksServices taskServ;
	private JavaEditorServices javaServ;

	public static Activator getInstance() {
		return instance;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("HEY! HEY! HEY! HEY YOU! HEY!");
		instance = this;

		ServiceReference<TasksServices> taskServiceReference = bundleContext
				.getServiceReference(TasksServices.class);
		taskServ = bundleContext.getService(taskServiceReference);
		
		ServiceReference<JavaEditorServices> editorServiceReferance = bundleContext
				.getServiceReference(JavaEditorServices.class);
		javaServ = bundleContext.getService(editorServiceReferance);

	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}
	
	public TasksServices getTaskService() {
		return taskServ;
	}

	public JavaEditorServices getJavaServ() {
		return javaServ;
	}
}
