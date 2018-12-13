package pa.iscde.javaTasks;

import java.io.File;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Activator implements BundleActivator {

	private static Activator instance;
	
	private JavaEditorServices javaServ;
	private ProjectBrowserServices browServ;
	
	public static Activator getInstance() {
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		instance = this;
		ServiceReference<JavaEditorServices> editorServiceReferance = bundleContext
				.getServiceReference(JavaEditorServices.class);
		ServiceReference<ProjectBrowserServices> browserServiceReference = bundleContext
				.getServiceReference(ProjectBrowserServices.class);
		javaServ = bundleContext.getService(editorServiceReferance);
		browServ = bundleContext.getService(browserServiceReference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		
	}

	public JavaEditorServices getJavaServ() {
		return javaServ;
	}

	public ProjectBrowserServices getBrowServ() {
		return browServ;
	}

}
