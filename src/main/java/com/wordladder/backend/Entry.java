package com.wordladder.backend;
import java.util.*;
import java.nio.file.Paths;
import java.io.*;
import java.net.URL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * This file implements entry to build word ladder
 * @author Higher Stark
 */
@RestController
public class Entry
{
	@RequestMapping("/wordladder")
	public String main(@RequestParam(value="_beg", defaultValue="word") String _beg, @RequestParam(value="_end", defaultValue="path") String _end)
	{
		String beg = _beg;		// the word to begin in the word ladder
		String end = _end;		// the destination word of the ladder
		HashSet<String> dict = new HashSet<String>();	//the dictionary
		// ClassLoader classloader = getClass().getClassLoader();
		// File dict = classloader.getResource("static/dictionary-1.txt");
		String userdir = System.getProperty("user.dir");
		//System.out.println(userdir);
		String dictPath = userdir + "\\src\\main\\resources\\static\\dictionary-1.txt";
		/*boolean hasArgs = false;  // no arguments in args

		if (args.length != 0) {
			String help = "Usage:\n  -d <path> \n\tPath of the dictionary file.\n  -b <word> \n\tthe word where the ladder starts.\n  -e <word> \n\tthe destination word.\n";
			if (args.length != 6) {
				System.out.println("Invalid paramaters\n");
				System.out.println(help);
				return;
			}
			if (!(args[0].equals("-d") && args[2].equals("-b") && args[4].equals("-e"))) {
				System.out.println("Invalid paramaters\n" + help);
				return;
			}
			dictPath = args[1];
			beg = args[3];
			end = args[5];
			hasArgs = true;
		}*/

		/* Scanner in  = new Scanner(System.in);
		if (!hasArgs) {
			System.out.print("Dictionary file path: ");
			dictPath = in.nextLine();
		}*/
		Scanner dictFile;
		try {
		dictFile = new Scanner(new File(dictPath), "UTF-8");
		}
		catch (IOException e) {
			System.out.println("Error: " + dictPath + " not found!");
			System.out.println("Program Abort");
			return "Error";
		}
		while (dictFile.hasNext()) {
			dict.add(dictFile.next());
		}
		try {
			WordLadder wl = new WordLadder(beg, end, dict);
			return (wl.findLadder());
		}
		catch (NullPointerException e){
			return (e.getMessage());
		}
		catch (IllegalArgumentException e) {
			return (e.getMessage());
		}
	}

	// private static String[] parseArgs(String[] a
}
