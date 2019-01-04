package testExtensivility;

import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import pa.iscde.javaTasks.ext.Task;
import pt.iscte.pidesco.extensibility.PidescoView;

public class RandomView implements PidescoView {

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		for(Task t: Activator.getInstance().getTaskService().getTasks())
			System.out.println(t);
		
	}


}
