package com.baek.prime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class PrintPrimesTest {
    @Test
    public void basicMakeSureMatchesGold() throws IOException {
        PrintPrimes.main(new String[0]);
    }

    private PrintStream out;

    @BeforeAll
    public void setUp() throws FileNotFoundException {
        out = System.out;
        System.setOut(new PrintStream(new FileOutputStream("lead")));
    }

    @AfterAll
    public void tearDown() {
        System.setOut(out);
        new File("lead").delete();
    }

    @Test
    public void makeSureMatchesGold() throws IOException {
        //given
        new PrintPrimes().main(new String[0]);
        BufferedReader lead = new BufferedReader(new FileReader("lead"));
        BufferedReader gold = new BufferedReader(new FileReader("src/test/java/com/baek/prime/gold"));
        String line;

        //when
        while((line = gold.readLine()) != null)
            assertEquals(line, lead.readLine());

        //then
        assertEquals(null, lead.readLine());
    }
}