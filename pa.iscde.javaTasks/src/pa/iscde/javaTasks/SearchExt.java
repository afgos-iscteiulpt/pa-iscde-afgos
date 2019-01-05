package pa.iscde.javaTasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pa.iscde.javaTasks.ext.Task;
import pa.iscde.search.extensibility.SearchProvider;
import pa.iscde.search.model.MatchResult;

/**
 * Class connected to pa.iscde.search extension point
 * @author MrAndrGodinho
 *
 */
public class SearchExt implements SearchProvider {
	
	private CommentHandler commentHandler;
	private Set<String> tagsSet;

	/**
	 * Returns the search result expected when searching for "Task"
	 */
	@Override
	public List<MatchResult> searchFor(String type, String input) {
		commentHandler = new CommentHandler();
		tagsSet = new HashSet<String>();
		List<MatchResult> list = new ArrayList<MatchResult>();
		if (type.equals("Task")) {
			String[] tagsArray = input.split("\\s");
			for (String s: tagsArray) {
				tagsSet.add(s);
			}
			String rootName = Activator.getInstance().getBrowServ().getRootPackage().getFile().getPath();
			File f = new File(rootName);
			readAllFiles(f);
		}
		Set<Task> tasks = commentHandler.getTaskSet();
		for(Task t: tasks) {
			MatchResult result = new MatchResult(new File(t.getPath() + "/" + t.getResource()), "Task", t.getLine(), t.getOffset());
			list.add(result);
		}
		return list;
	}
	
	/**
	 * Reads all files when looking for tags
	 * @param file directory or file
	 */
	private void readAllFiles(File file) {
		for (File f : file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory())
					return true;
				String name = pathname.getName();
				int index = name.lastIndexOf(".");
				if (index == -1)
					return false;
				return name.substring(index + 1).equals("java");
			}
		})) {
			if (f.isFile())
				readFile(f);
			else
				readAllFiles(f);
		}
	}
	
	/**
	 * Read specific file and look for Tag
	 * @param file
	 */
	private void readFile(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int count = 0;
			int offset = 0;
			while ((line = br.readLine()) != null) {
				count++;
				commentHandler.processString(tagsSet, line, file, count, offset);
				offset += line.length() + 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
