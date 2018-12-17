package pa.iscde.javaTasks.ext;

import java.util.Set;

public interface TasksServices {
	
	/**
	 * Getter for a set of all taks in the table
	 * @return Set of {@link Task} Objects
	 */
	
	Set<Task> getTasks();
	
	/**
	 * Update the table
	 */
	void update();

}
