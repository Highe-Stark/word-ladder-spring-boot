package com.wordladder.backend;
import java.util.*;
import java.io.*;
//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * This file implements entry to build word ladder
 * @author Higher Stark
 */
@RestController
public class Entry
{
	 static Logger log = LoggerFactory.getLogger(Entry.class);

	@RequestMapping(value="/wordladder", method=RequestMethod.GET)
	public String main(@RequestParam(value="_beg", defaultValue="word") String _beg, @RequestParam(value="_end", defaultValue="path") String _end)
	{
		String beg = _beg;		// the word to begin in the word ladder
		String end = _end;		// the destination word of the ladder
		HashSet<String> dict = new HashSet<String>();	//the dictionary
		// ClassLoader.get
		//String userdir = System.getProperty("user.dir");
		// String dictPath = userdir + "\\src\\main\\resources\\static\\dictionary-1.txt";
		String dictPath = "src/main/resources/static/dictionary-1.txt";
		Scanner dictFile;
		try {
		dictFile = new Scanner(new File(dictPath), "UTF-8");
		}
		catch (IOException e) {
			System.out.println("Error: " + dictPath + " not found!");
			System.out.println("Program Abort");
			return "Error";
		}
		// log open file
		log.info("Open dictionary: " + dictPath);
		if (dictFile == null) {
			log.error("Open dictionary failed.");
		}
		while (dictFile.hasNext()) {
			dict.add(dictFile.next());
		}
		try {
			WordLadder wl = new WordLadder(beg, end, dict);
			log.info("Request: " + beg + " -> " + end);
			return (wl.findLadder());
		}
		catch (NullPointerException e){
			log.error("Error: Null Pointer exception");
			return (e.getMessage());
		}
		catch (IllegalArgumentException e) {
			log.error("Error: Illegal Arguments");
			return (e.getMessage());
		}
	}

}
