package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    GameStore store = new GameStore();

    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game2 = store.publishGame("Покорение Марса", "Аркады");
    Game game3 = store.publishGame("Арканоид", "Аркады");
    Game game4 = store.publishGame("Формула 1", "Гонки");

    @Test // тест №1. Суммирует время, проигранныые во все игры жанра "Аркады" игроком "Petya",
    // если добавлена ТОЛЬКО ОДНА ИГРА

    public void shouldSumGenreIfOneGame() {

        Player player = new Player("Petya");

        player.installGame(game1);

        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());

        assertEquals(expected, actual);
    }

    @Test // тест №2. Суммирует время, проигранныые во все игры жанра "Аркады" игроком "Petya",
    // если добавлено НЕСКОЛЬКО ИГР одного жанра

    public void shouldSumGenreIfSomeGame() {


        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 3);
        player.play(game2, 5);
        player.play(game3, 12);

        int expected = 3 + 5 + 12;
        int actual = player.sumGenre("Аркады");

        assertEquals(expected, actual);
    }

    @Test // тест №3. Суммирует время, проигранныые во все игры жанра "Аркады" игроком "Petya",
    // если добавлено НЕСКОЛЬКО ИГР разных жанров

    public void sumGenreTwoGames() {

        Player player = new Player("Petya"); // обьявляем игрока(имя)

        player.installGame(game1);      // запускаем игры
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 5);    // обьявляем сколько времени проводим в данной игре
        player.play(game2, 3);
        player.play(game3,3);

        int expected = 5 + 3;
        int actual = player.sumGenre("Аркады");

        assertEquals(expected,actual);
    }

    @Test // тест №4. Считает количество часов, которое играли в игру, если она УСТАНОВЛЕНА

    public void shouldCountHoursWhenGameInstalled() {

        Player player = new Player("Petya");

        player.installGame(game1);

        int actual = player.play(game1, 3);
        int expected = 3;

        assertEquals(expected, actual);
    }

    @Test // тест №5. Выкидывает исключение при попытке сосчитать количество часов,
    // которое играли в игру, если она НЕ УСТАНОВЛЕНА

    public void shouldThrowExceptionWhenGameNotInstalled() {

        Player player = new Player("Petya");

        assertThrows(RuntimeException.class, () -> player.play(game1, 3));
    }

    @Test // тест №6.  Выдает игру заданного жанра "Аркады",
    // в которую играли бОльшее количество часов если играли только в игры одного жанра

    public void shouldReturnMostPlayedByGenre() {

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);

        player.play(game1, 3);
        player.play(game2, 5);

        String expected = "Арканоид";
        Game actual = player.mostPlayerByGenre("Аркады");

        assertEquals(expected, actual);
    }

    @Test // тест№ 7. Выдает игру заданного жанра "Аркады",
    // в которую играли бОльшее количество часов, если добавлены несколько игр разного жанра

    public void shouldReturnMostPlayedByGenreWhenSomeGamesDifferentGenres() {

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game4);

        player.play(game1, 3);
        player.play(game2, 5);
        player.play(game4, 15);

        String expected = "Покорение Марса";
        Game actual =  player.mostPlayerByGenre("Аркады");

        assertEquals(expected, actual);
    }

    @Test // тест №8. Выдает null если в запрашиваемый жанр не играли
    // в которую играли бОльшее количество часов

    public void shouldReturnMostPlayedByGenreIfNoonePlayedThisGenre() {

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game4);

        player.play(game1, 3);
        player.play(game4, 5);

        assertNull(player.mostPlayerByGenre("Квесты"));
    }

}