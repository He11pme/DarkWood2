package engine;

import creatures.Enemy;
import creatures.Fighter;
import creatures.Player;
import text_styler.Reader;
import text_styler.TextFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class Fight {

    final List<Enemy> enemies;
    final Player player;

    public Fight(List<Enemy> enemies, Player player) {
        this.enemies = enemies;
        this.player = player;
    }

    public boolean startFight() {

        System.out.println("На пути тебе встретились: " + enemiesToString());

        do {

            //Вывод списка врагов
            List<Action> actionInFight = player.getActionInFight();

            //Ход игрока
            while (player.getActionPoints() > 0 && checkLiveEnemies()) {
                //Вывод данных об игроке
                System.out.println(TextFormatter.of("\n" + player).colorize(TextFormatter.CYAN));
                //Вывод возможных действий игрока
                printListActions(actionInFight);
                //Запрос о номере действия
                Action choseAction = actionInFight.get(Reader.readResponse(actionInFight.size()));
                //Если действие требует больше очков действия чем есть
                if (choseAction.getActionPoints(player) > player.getActionPoints()) {
                    System.out.println(TextFormatter.of("Вам не хватает очков действий").colorize(TextFormatter.RED));
                    continue;
                }
                //Если действие - атака
                if (choseAction instanceof Attack choseAttack) {
                    Enemy choseEnemy = getChoseEnemy();
                    choseAttack.attack(player, choseEnemy);

                    System.out.println(enemiesToString());
                }

                player.spendActionPoints(choseAction.getActionPoints(player));
            }

            player.resetActionPoints();

            //Ход противников
            long start = System.nanoTime();
            try (ExecutorService service = Executors.newFixedThreadPool(enemies.size())) {
                List<Future<?>> futures = new ArrayList<>();

                for (Enemy enemy : enemies) {
                    if (!enemy.isLive()) continue;

                    Future<?> future = service.submit(() -> {
                        while (player.isLive() && enemy.getActionPoints() > 0) {
                            Action choseAction = enemy.getActionInFight().get((int) (Math.random() * enemy.getActionInFight().size()));
                            if (choseAction instanceof Attack choseAttack) {
                                synchronized (player) {
                                    choseAttack.attack(enemy, player);
                                }
                            }
                            enemy.spendActionPoints(choseAction.getActionPoints(enemy));
                        }
                    });
                    futures.add(future);
                }

                for (Future<?> future : futures) {
                    future.get();
                }

            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
//            for (Enemy enemy : enemies) {
//                if (!enemy.isLive()) continue;
//                while (player.isLive() && enemy.getActionPoints() > 0) {
//                    Action choseAction = enemy.getActionInFight().get((int) (Math.random() * enemy.getActionInFight().size()));
//                    if (choseAction instanceof Attack choseAttack) {
//                        choseAttack.attack(enemy, player);
//                    }
//                    enemy.spendActionPoints(choseAction.getActionPoints(enemy));
//                }
//            }
            long end = System.nanoTime();
            long duration = end - start;
            System.out.println("Время выполнения: " + duration + " наносекунд");



            for (Enemy enemy : enemies) enemy.resetActionPoints();

        } while (checkLiveEnemies() && player.isLive());

        return player.isLive();

    }

    private Enemy getChoseEnemy() {
        if (enemies.stream().filter(Fighter::isLive).count() > 1) {
            System.out.println("По кому вы хотите ударить?");
            do {
                int index = Reader.readResponse(enemies.size());
                if (enemies.get(index).isLive()) {
                    return enemies.get(index);
                } else {
                    System.out.println(TextFormatter.of("В голове у " + player.getName() + " возникает мысль ударить [italic_on]ТРУП[italic_off], но он вовремя останавливается и меняет свой выбор.")
                            .italicize()
                            .colorize(TextFormatter.YELLOW));
                }
            } while (true);
        } else {
            //Ошибки при вызове .get() не должно быть.
            //Вызов данного метода производится только при наличии живых врагов.
            return enemies.stream().filter(Fighter::isLive).findFirst().get();
        }
    }

    private boolean checkLiveEnemies() {
        return enemies.stream().anyMatch(Fighter::isLive);
    }

    private void printListActions(List<Action> actionInFight) {
        StringBuilder builder = new StringBuilder("\n");

        int i = 0;
        for (Action action : actionInFight) {
            builder
                    .append(++i)
                    .append(". ")
                    .append(action)
                    .append("\n");
        }

        System.out.println(builder);
    }

    private String enemiesToString() {
        StringBuilder builder = new StringBuilder("\n");

        int i = 0;
        for (Enemy enemy : enemies) {
            builder
                    .append("\t")
                    .append(++i)
                    .append(". ")
                    .append(TextFormatter.of(enemy.toString()).colorize(TextFormatter.RED));
            if (enemies.getLast() != enemy) builder.append("; ");
        }

        return builder.toString();
    }

    private String toOrdinalWord(int i) {
        return switch (i) {
            case 1 -> "первому";
            case 2 -> "второму";
            case 3 -> "третьему";
            default -> throw new IllegalArgumentException();
        };
    }

}
