package testExtensivility;

import pa.iscde.javaTasks.ext.TasksAction;
import pa.iscde.javaTasks.ext.Task;

public class TasksActions implements TasksAction {

	@Override
	public String setDescription(Task t) {
		return "It works?" + t.getDescription();
	}

	@Override
	public void doubleClick(Task t) {
		System.out.println(t);
	}

}
