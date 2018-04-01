package com.wordladder.backend;

import java.util.HashSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendTests {

    @Test
    public void testFindLadder() {
        HashSet<String> dict = new HashSet<String>();
        dict.add("cat");
        WordLadder wl = new WordLadder("cat", "cat", dict);
        String res = wl.findLadder();
        assertEquals(res, "cat");
    }

    @Test
    public void testFindLadder2() {
        HashSet<String> dict = new HashSet<String>();
        String[] list = {"cat", "dog", "java", "bat", "rat", "bot", "dot", "test", "english"};
        for (String s : list) {
            dict.add(s);
        }
        WordLadder wl = new WordLadder("cat", "dog", dict);
        String res = wl.findLadder();
        assertEquals("cat -> bat -> bot -> dot -> dog", res);
    }

}
