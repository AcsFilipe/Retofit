package com.example.xkcd;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Random rand = new Random();
        int numero =  rand.nextInt((2152 - 614) + 1) + 614;
        System.out.println(numero);
        assertEquals(4, 2 + 2);
    }
}