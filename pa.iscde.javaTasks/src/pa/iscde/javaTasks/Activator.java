package pa.iscde.javaTasks;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pa.iscde.javaTasks.ext.TasksServices;
import pa.iscde.search.services.SearchService;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Activator implements BundleActivator {

	private static Activator instance;

	private JavaEditorServices javaServ;
	private ProjectBrowserServices browServ;
	private SearchService searchServ;

	public static Activator getInstance() {
		return instance;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		instance = this;

		TasksServices services = new TasksServicesImpl();
		bundleContext.registerService(TasksServices.class, services, null);
		ServiceReference<JavaEditorServices> editorServiceReferance = bundleContext
				.getServiceReference(JavaEditorServices.class);
		ServiceReference<ProjectBrowserServices> browserServiceReference = bundleContext
				.getServiceReference(ProjectBrowserServices.class);
		ServiceReference<SearchService> searchServiceReference = bundleContext.getServiceReference(SearchService.class);
		javaServ = bundleContext.getService(editorServiceReferance);
		browServ = bundleContext.getService(browserServiceReference);
		searchServ = bundleContext.getService(searchServiceReference);

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

	public SearchService getSearchServ() {
		return searchServ;
	}

}
