package pa.iscde.javaTasks;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pa.iscde.javaTasks.ext.TasksServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Activator implements BundleActivator {

	private static Activator instance;

	private JavaEditorServices javaServ;
	private ProjectBrowserServices browServ;
//	private DocGenServices docGen;

	public static Activator getInstance() {
		return instance;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		instance = this;

		TasksServices services = new TasksServicesImpl();
		bundleContext.registerService(TasksServices.class, services, null);
		System.out.println("activator - " + services);
		ServiceReference<JavaEditorServices> editorServiceReferance = bundleContext
				.getServiceReference(JavaEditorServices.class);
		ServiceReference<ProjectBrowserServices> browserServiceReference = bundleContext
				.getServiceReference(ProjectBrowserServices.class);
		javaServ = bundleContext.getService(editorServiceReferance);
		browServ = bundleContext.getService(browserServiceReference);
//		ServiceReference<DocGenServices> docGenServiceReference = bundleContext
//				.getServiceReference(DocGenServices.class);
//		docGen = bundleContext.getService(docGenServiceReference);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}

	public JavaEditorServices getJavaServ() {
		return javaServ;
	}

	public ProjectBrowserServices getBrowServ() {
		return browServ;
	}

//	public DocGenServices getDocGen() {
//		return docGen;
//	}

}
