package pa.iscde.javaTasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import pa.iscde.javaTasks.ext.Task;
import pa.iscde.javaTasks.ext.TasksAction;

/**
 * Extensions handler
 * @author MrAndrGodinho
 *
 */
public class EvaluateContributionsHandler {

	private Set<String> tags = new HashSet<>(Arrays.asList("TODO", "DEBUG", "FIXME"));
	private IExtensionRegistry registry = Platform.getExtensionRegistry();
	private IConfigurationElement[] config = registry.getConfigurationElementsFor(TAGSSERVICE_ID);

	private static final String TAGSSERVICE_ID = "pa.iscde.javaTasks.ext";

	/**
	 * Constructor that checks all extensions for possible TAGS that they want to
	 * look for
	 */
	public EvaluateContributionsHandler() {
		for (IConfigurationElement e : config) {
			if(e.getAttribute("TagName") != null)
				tags.add(e.getAttribute("TagName"));
		}
	}

	/**
	 * Get class implementation ox extension and runs their setDescription()
	 * 
	 * @param set Set<Task>
	 * @return Set<Task>
	 */
	public Set<Task> processTags(Set<Task> set) {
		Set<Task> tasks = new HashSet<>();
		try {
			for (Task t : set) {
				String newDescription = t.getDescription();
				for (IConfigurationElement e : config) {
					Object o = e.createExecutableExtension("TasksAction");
					if (o instanceof TasksAction) {
						newDescription = ((TasksAction) o).setDescription(t);
					}
				}
				tasks.add(
						new Task(t.getTag(), newDescription, t.getResource(), t.getPath(), t.getLine(), t.getOffset()));
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
		return tasks;
	}

	/**
	 * Looks for a action the extensions want to run when double clicking a task
	 * 
	 * @param task Task Selected
	 */
	public void doubleClick(Task task) {
		if (config.length == 0)
			new TasksAction() {}.doubleClick(task);
		try {
			for (IConfigurationElement e : config) {
				Object o = e.createExecutableExtension("TasksAction");
				if (o instanceof TasksAction) {
					((TasksAction) o).doubleClick(task);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Getter for Tags
	 * 
	 * @return Set of Tags the plugin need to search
	 */
	public Set<String> getTags() {
		return tags;
	}
}
