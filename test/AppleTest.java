import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

//This test class makes sure that the apple has
//reasonable and expected effect on snake when it
//is eaten by snake.

public class AppleTest {
    
    // Test for GoodApple
    @Test
    public void LengthChange() {
        Snake snake = new Snake(28, 20, 28*10, 20*10, 300, 300);
        assertEquals(snake.length(),5);
        Apples apple = new GoodApple(300, 300, 28*10, 18*10);
        apple.eatEffect(snake);
        assertEquals(snake.length(),6);
        apple.eatEffect(snake);
        assertEquals(snake.length(),7);
    }
    
    @Test
    public void NotVanish() {
        Apples apple = new GoodApple(300, 300, 28*10, 18*10);
        assertFalse(apple.mayVanish());
    }
    
    //Test for BadApple
    @Test
    public void LifeLost() {
        Snake snake = new Snake(28, 20, 28*10, 20*10, 300, 300);
        assertEquals(snake.getLife(),3);
        Apples apple = new BadApple(300, 300, 28*10, 18*10);
        apple.eatEffect(snake);
        assertEquals(snake.getLife(),2);
        apple.eatEffect(snake);
        assertEquals(snake.getLife(),1);
    }
    
    @Test
    public void BadVanishTest() {
        Apples apple = new BadApple(300, 300, 28*10, 18*10);
        assertTrue(apple.mayVanish());
        assertEquals(apple.duration(), 50);
    }
    
    //Test for MagicApple
    @Test
    public void LifeGained() {
        Snake snake = new Snake(28, 20, 28*10, 20*10, 300, 300);
        assertEquals(snake.getLife(),3);
        Apples apple = new MagicApple(300, 300, 28*10, 18*10);
        apple.eatEffect(snake);
        assertEquals(snake.getLife(),4);
        apple.eatEffect(snake);
        assertEquals(snake.getLife(),5);
    }
    
    @Test
    public void MagicVanishTest() {
        Apples apple = new MagicApple(300, 300, 28*10, 18*10);
        assertTrue(apple.mayVanish());
        assertEquals(apple.duration(), 10);
    }
        
}