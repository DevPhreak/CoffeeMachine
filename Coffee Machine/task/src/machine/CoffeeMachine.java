package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        cMachine machine = new cMachine();
        Menu menu = new Menu();
        //menu.printStock();
        while (true) {
            menu.printAction();
            menu.eval();
        }
    }
}

class cMachine {
    private int water = 400, milk = 540, beans = 120, cups = 9, money = 550;

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    Boolean hasEnough(int id) {
        Coffee coffee = new Coffee(id);
        if (getWater() > coffee.getWater()) {
            if (getBeans() > coffee.getBonen()) {
                if (getMilk() > coffee.getMelk()) {
                    if (getCups() >= 1) {
                        System.out.println("I have enough resources, making you a coffee!");
                        return true;
                    } else {
                        System.out.println("Sorry, not enough cups!");
                    }
                    System.out.println("Sorry, not enough milk!");
                }
                System.out.println("Sorry, not enough beans!");
            }
            System.out.println("Sorry, not enough water!");
        }
        return false;
    }

    void buyCoffee(int id) {
        Coffee coffee = new Coffee(id);
        water -= coffee.getWater();
        milk -= coffee.getMelk();
        beans -= coffee.getBonen();
        cups -= 1;
        money += coffee.getPrice();
    }

    void take() {
        System.out.println("I gave you $" + getMoney());
        setMoney(0);
    }

    void fill() {
        Scanner s = new Scanner(System.in);
        int sum;

        System.out.println("Write how many ml of water you want to add:");
        sum = s.nextInt() + getWater();
        setWater(sum);

        System.out.println("Write how many ml of milk you want to add:");
        sum = s.nextInt() + getMilk();
        setMilk(sum);

        System.out.println("Write how many grams of coffee beans you want to add:");
        sum = s.nextInt() + getBeans();
        setBeans(sum);

        System.out.println("Write how many disposable cups of coffee you want to add");
        sum = s.nextInt() + getCups();
        setCups(sum);
    }
}

class Coffee {
    private int type; //1:Espresso//2:latte//3:Cappuccino
    private int[] ingredients; //1:water//2:melk//3:bonen//4:prijs
    private String name;

    public Coffee(int type) {
        switch (type) {
            case 1:
                name = "Espresso";
                ingredients = new int[]{250, 0, 16, 4};
                break;
            case 2:
                name = "Latte";
                ingredients = new int[]{350, 75, 20, 7};
                break;
            case 3:
                name = "Cappuccino";
                ingredients = new int[]{200, 100, 12, 6};
                break;
        }
    }

    int getPrice() {
        return ingredients[3];
    }

    int getWater() {
        return ingredients[0];
    }

    int getMelk() {
        return ingredients[1];
    }

    int getBonen() {
        return ingredients[2];
    }
}

class Menu {
    cMachine machine = new cMachine();
    String input;

    void printStock() {
        System.out.println("The coffee machine has:");
        System.out.println(machine.getWater() + " ml of water");
        System.out.println(machine.getMilk() + " ml of milk");
        System.out.println(machine.getBeans() + " g of coffee beans");
        System.out.println(machine.getCups() + " disposable cups");
        System.out.println("$" + machine.getMoney() + " of money");
    }

    void getInput() {
        Scanner s = new Scanner(System.in);
        input = s.nextLine();
    }

    void eval() {
        getInput();
        switch (input) {
            case "buy":
                printMenu();
                eval();
                break;
            case "fill":
                machine.fill();
                break;
            case "take":
                machine.take();
                break;
            case "remaining":
                printStock();
                break;
            case "back":
                break;
            case "exit":
                System.exit(0);
            case "1":
                if (machine.hasEnough(1)) {
                    machine.buyCoffee(1);
                }
                break;
            case "2":
                if (machine.hasEnough(2)) {
                    machine.buyCoffee(2);
                }
                break;
            case "3":
                if (machine.hasEnough(3)) {
                    machine.buyCoffee(3);
                }
                break;
        }
    }

    void printAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    void printMenu() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }
}
