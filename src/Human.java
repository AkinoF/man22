package org.example;
import java.util.*;

public class Human {
    private String name; // Имя
    private int age;     // Возраст
    private int health;  // Здоровье в процентах
    private boolean isAlive; // Жив ли он

    // Массив имен
    private static String[] names = {
            "Иван", "Анна", "Петр", "Мария", "Алексей",
            "Ольга", "Дмитрий", "Екатерина", "Сергей", "Татьяна"
    };

    // Конструктор
    public Human(String name) {
        this.name = name;
        this.age = generateRandomAge(); // Случайный возраст от 15 до 50
        this.health = generateRandomHealth(); // Состояние здоровья от 10 до 100
        this.isAlive = true; // По умолчанию считается, что человек жив
    }

    // Метод для генерации случайного возраста от 15 до 50
    private int generateRandomAge() {
        Random random = new Random();
        return random.nextInt(36) + 15; // Возраст от 15 до 50
    }

    // Метод для генерации состояния здоровья от 10 до 100
    private int generateRandomHealth() {
        Random random = new Random();
        return random.nextInt(91) + 10; // Здоровье от 10 до 100
    }

    // Метод Talk
    public void talk() {
        Random random = new Random();
        int dialogueOption = random.nextInt(3) + 1; // Случайное число от 1 до 3
        if (isAlive) {
            switch (dialogueOption) {
                case 1:
                    System.out.println(getGreeting() + ", рад(а) познакомиться.");
                    break;
                case 2:
                    System.out.println("Мне " + age + " лет. А тебе?");
                    break;
                case 3:
                    if (health >= 50) {
                        System.out.println("Да я здоров(а) как бык!");
                    } else {
                        System.out.println("Со здоровьем у меня хреново, дожить бы до " + (age + 10) + " лет.");
                    }
                    break;
            }
        } else {
            System.out.println(name + " мертв(а) и не может говорить.");
        }
    }

    // Метод для определения обращения (мужское / женское)
    private String getGreeting() {
        return "Привет, меня зовут " + name;
    }

    // Метод Go
    public void go() {
        if (isAlive) {
            if (health >= 40) {
                System.out.println(name + " мирно прогуливается по городу.");
            } else {
                System.out.println(name + " болен(льна) и не может прогуливаться по городу.");
            }
        } else {
            System.out.println(name + " не может идти, он умер(ла).");
        }
    }

    // Метод Kill
    public void kill() {
        if (isAlive) {
            isAlive = false;
            health = 0; // Состояние здоровья при смерти
            System.out.println(name + " умер(ла).");
        } else {
            System.out.println(name + " уже мертв(а). Создадим нового человека.");
        }
    }

    // Метод IsAlive
    public boolean isAlive() {
        return isAlive;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Human human = createNewHuman(); // Создаем первого человека с случайным именем
        int choice;

        do {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Говорить");
            System.out.println("2. Идти");
            System.out.println("3. Убить");
            System.out.println("4. Проверить, жив ли");
            System.out.println("5. Создать нового человека");
            System.out.println("0. Выход");
            choice = scanner.nextInt();switch (choice) {
                case 1:
                    human.talk();
                    break;
                case 2:
                    human.go();
                    break;
                case 3:
                    human.kill();
                    break;
                case 4:
                    System.out.println("Человек " + (human.isAlive() ? "жив." : "мертв."));
                    break;
                case 5:
                    if (!human.isAlive()) {
                        System.out.println("Создаем нового человека...");
                        human = createNewHuman(); // Создание нового человека с случайным именем.
                    } else {
                        System.out.println("Первый человек все еще жив. Убейте его, чтобы создать нового.");
                    }
                    break;
                case 0:
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 0);

        scanner.close();
    }

    // Метод для создания нового человека с случайным именем и состоянием здоровья
    private static Human createNewHuman() {
        Random random = new Random();
        String randomName = names[random.nextInt(names.length)]; // Случайное имя из массива
        return new Human(randomName);
    }
}
