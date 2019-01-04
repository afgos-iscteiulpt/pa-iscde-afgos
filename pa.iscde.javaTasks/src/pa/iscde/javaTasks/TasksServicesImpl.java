package pa.iscde.javaTasks;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pa.iscde.javaTasks.ext.Task;
import pa.iscde.javaTasks.ext.TasksServices;

public class TasksServicesImpl implements TasksServices{

	public TasksServicesImpl() {
	}
	
	@Override
	public Set<Task> getTasks() {
		Set<Task> returnSet = new HashSet<Task>();
		Map<String, Set<Task>> tasksMap = JavaTasksView.getInstance().getTaskList();
		for(Set<Task> set : tasksMap.values())
			returnSet.addAll(set);
		return returnSet;
	}

	@Override
	public void update() {
		JavaTasksView.getInstance().update();
	}

}
