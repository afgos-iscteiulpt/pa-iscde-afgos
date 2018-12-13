package testExtensivility;

import pa.iscde.javaTasks.ext.TasksAction;
import pa.iscde.javaTasks.ext.Task;

public class TasksActions implements TasksAction {

	@Override
	public String setDescription(Task t) {
		return "I'm dying" + t.getDescription();
	}

	@Override
	public void doubleClick(Task t) {
		System.out.println(t);
	}

}
