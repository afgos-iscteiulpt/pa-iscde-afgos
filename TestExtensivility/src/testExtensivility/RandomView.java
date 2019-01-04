package testExtensivility;

import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import pa.iscde.javaTasks.ext.Task;
import pa.iscde.javaTasks.ext.TasksServices;
import pt.iscte.pidesco.extensibility.PidescoView;

public class RandomView implements PidescoView {

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		Button pushButton = new Button(viewArea, SWT.PUSH);
		pushButton.setLocation(50, 50);
		pushButton.setText("Im a Push Button");
		pushButton.pack();
		pushButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				Activator a = Activator.getInstance();
				TasksServices ts = a.getTaskService();
				Set<Task> s = ts.getTasks();
				for(Task t: s)
					System.out.println(t);	
				
			}
		});
		
	}


}
