import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_NullList_ThrowIllegalArgumentException() {

        String expectedMessage = "Horses cannot be null.";
        List<Horse> horses = null;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void constructor_EmptyList_ThrowIllegalArgumentException() {

        String expectedMessage = "Horses cannot be empty.";
        List<Horse> horses = new ArrayList<>();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getHorses_ReturnsListWithAllHorses() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++)
            horses.add(new Horse("Horse" + i, i, i));
        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(30, hippodrome.getHorses().size());
        assertEquals("Horse0", hippodrome.getHorses().get(0).getName());
        assertEquals("Horse11", hippodrome.getHorses().get(11).getName());
        assertEquals("Horse22", hippodrome.getHorses().get(22).getName());
    }

    @Test
    void move_CallsMoveMethodForAllHorses() {

        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            horses.add(Mockito.mock(Horse.class));
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse: horses)
            Mockito.verify(horse, Mockito.times(1)).move();

    }

    @Test
    void getWinner_ReturnsCorrectWinner() {

        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("horse1", 1, 10),
                new Horse("horse2", 2, 20),
                new Horse("horse3", 3, 30)
        ));

        assertEquals("horse3", hippodrome.getWinner().getName());
    }
}