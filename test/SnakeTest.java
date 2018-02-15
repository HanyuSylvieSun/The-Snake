import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

public class SnakeTest {
    
    @Test
    //Determines as hit the wall when the snakes hit the wall
    public void HitTheWall() {
        Snake snake = new Snake(28, 20, 28*10, 20*10, 300, 300);
        for (int i = 1; i <= 20; i++)
          snake.move();
        assertEquals(snake.hitWall(),Direction.UP);
    }
    
    @Test
    //Determines as eatItSelf when the snake eats itself
    public void EatItself() {
        Snake snake = new Snake(28, 20, 28*10, 20*10, 300, 300);
        snake.setVx(-10);
        snake.setVy(0);
        snake.move();
        snake.setVx(0);
        snake.setVy(10);
        snake.move();
        snake.setVx(10);
        snake.setVy(0);
        assertTrue(snake.eatItself());
    }
    
    @Test
    public void NotEatItself() {
        Snake snake = new Snake(28, 20, 28*10, 20*10, 300, 300);
        snake.setVx(-10);
        snake.setVy(0);
        snake.move();
        snake.setVx(0);
        snake.setVy(10);
        snake.move();
        assertFalse(snake.eatItself());
    }
    
    @Test
    //Make sure the program knows that the snake will eat an apple
    //when it actually will eat an apple.
    public void eatApple() {
        Snake snake = new Snake(28, 20, 28*10, 20*10, 300, 300);
        Apples apple = new GoodApple(300, 300, 28*10, 18*10);
        snake.move();
        assertTrue(snake.willIntersect(apple));
    }
}