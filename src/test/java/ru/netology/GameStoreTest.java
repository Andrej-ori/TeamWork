package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GameStoreTest {

    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() { // 1. тест на проверку добавления одной игры

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddTwoGames() { // 2. тест на проверку добавления двух игр

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Игра2", "Аркады");

        assertTrue(store.containsGame(game2));
    }

    @Test
    public void shouldNotContainsGame() { // 3. тест на проверку, покажет ли store игру, которая не была добавлена через метод publishGame

        Game game = store.publishGame("Игра2", "Аркады");
        Game game2 = new Game("Нетология Баттл Онлайн", "Аркады", store);
        Game game3 = store.publishGame("Игра3", "Симуляция");

        assertFalse(store.containsGame(game2));
    }

    @Test
    public void shouldAddHoursIfOneHour() { // 4. тест на проверку добавления времени игры в 1 час

        store.addPlayTime("Игрок1", 1);

        String expected = "Игрок1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotShowAnyPlayer() { // 5. тест на проверку метода getMostPlayer, если игроки отсутствуют

        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldNotAddHoursIfZero() { // 6. тест на проверку добавления времени игры ноль часов

        store.addPlayTime("Игрок1", 0);

        assertNull(store.getMostPlayer());
    }
    @Test
    public void shouldNotAddHoursIfMinus() { // 7. тест на проверку добавления времени с отрицательным значением

        store.addPlayTime("Игрок1", -1);

        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldAddHoursIfBigNumber() { // 8. тест на проверку добавления времени игры 999 999 часов

        store.addPlayTime("Игрок1", 999_999);
        store.addPlayTime("Игрок2", 100);
        store.addPlayTime("Игрок3", 20);

        String expected = "Игрок1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddHours() { // 9. тест на проверку добавления времени игры больше 1 часа

        store.addPlayTime("Игрок1", 2);
        store.addPlayTime("Игрок2", 3);

        String expected = "Игрок2";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddNewHours() { // 10. тест на проверку добавления нового времи для игры в которую уже играли

        store.addPlayTime("Игрок1", 5);
        store.addPlayTime("Игрок1", 6);
        store.addPlayTime("Игрок2", 8);

        String expected = "Игрок1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAddNewHoursIfZero() { // 11. тест на проверку добавления времи равному нулю для игры в которую уже играли

        store.addPlayTime("Игрок1", 5);
        store.addPlayTime("Игрок1", 0);
        store.addPlayTime("Игрок2", 6);

        String expected = "Игрок2";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShowSumPlayedTimeIfOnePlayer() { // 12. тест на проверку общего количество времени проведенного за играми, если один игрок

        store.addPlayTime("Игрок1", 5);

        int expected = 5;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShowSumPlayedTime() { // 13. тест на проверку общего количество времени проведенного за играми, если игроков больше одного

        store.addPlayTime("Игрок1", 5);
        store.addPlayTime("Игрок2", 8);
        store.addPlayTime("Игрок3", 0);

        int expected = 13;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShowSumPlayedTimeIfZero() { // 14. тест на проверку, покажет ли метод getSumPlayedTime общее количество времени, если игроков нет

        int expected = 0;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }
}