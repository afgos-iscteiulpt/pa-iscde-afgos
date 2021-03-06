package pa.iscde.javaTasks.ext;

import java.io.File;

import pa.iscde.javaTasks.Activator;

/**
 * Class used by the extension in javaTasks component
 * @author MrAndrGodinho
 *
 */
public interface TasksAction {

	/**
	 * Sets the Task description string <br>
	 * default is Task's description
	 * 
	 * @param t Task
	 * @return String that will be written in the description
	 */
	default String setDescription(Task t) {
		return t.getDescription();
	}

	/**
	 * Action for executed in case of double click <br>
	 * default is to put cursor in the task Line
	 * 
	 * @param t Task
	 */
	default void doubleClick(Task t) {
		Activator.getInstance().getJavaServ().selectText(new File(t.getPath() + "/" + t.getResource()), t.getOffset(),
				0);
	}

}
