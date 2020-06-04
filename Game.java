package com.ganzenbord;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final Scanner reader;
    private final Dice dice;
    private final ArrayList<Player> listPlayers;
    public boolean gameOn;
    private int rounds;
    private int playerInPothole;

    public Game() {
        this.reader = new Scanner(System.in);
        this.dice = new Dice();
        this.listPlayers = new ArrayList<Player>();
        this.gameOn = true;
        this.rounds = 0;
        this.playerInPothole = 0;
    }

    public void startGame() {
        startMenu();
        setPlayers();
        while (gameOn) {

            for (Player player : listPlayers) {
                System.out.println("Druk 'enter' om de dobbelsteen te gooien ");
                String userInput = reader.nextLine().toLowerCase().trim();

                if (userInput.equals("" +
                        "")) {
                    if (player.isNotInn()) {
                        if (player.isNotInPrison()) {
                            if (player.isNotPotHole()) {
                                int throwDice = dice.throwDice();
                                player.increasePosition(throwDice);
                                int position = player.getPosition();
                                rules(player, position, throwDice);

                            } else if (this.playerInPothole != player.getNumber() && this.listPlayers.get(player.getNumber()).isInPothole()) {
                                System.out.println(player + "is uit de put!");
                                this.listPlayers.get(player.getNumber()).outOfPothole();
                            } else System.out.println(player + " zit in de put!");

                        } else if (player.countPrisonCount() > 2) {
                            System.out.println(player + " is uit de gevangenis!");
                            this.listPlayers.get(player.getNumber()).outOfPrison();
                        } else System.out.println(player + " zit nog in de gevangenis!");

                    } else if (this.listPlayers.get(player.getNumber()).outOfInn()) {
                        System.out.println(player + " moet een beurt overslaan");
                    }
                }
            }
            rounds++;
            System.out.println(" ");
            System.out.println("Iedereen heeft " + rounds + " keer gedobbeld");
            System.out.println("---------------------------------------");
        }
    }

    public void setPlayers () {
        System.out.print("Kies het aantal spelers: ");
        int numberOfPlayers = Integer.parseInt(reader.nextLine());
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Naam speler: ");
            String name = reader.nextLine();
            this.listPlayers.add(new Player(name, i, true, true, true));
        }
    }

    public void rules(Player player, int position, int dice) {
        if (position == 10 || position == 20 || position == 30 || position == 40 || position == 50 || position == 60) {
            System.out.println(player + " heeft " + dice + " gegooid, en staat op " + position);
            player.increasePosition(dice);
            System.out.println("Bonus stapjes! " + player + " staat nu op plaats " + (player.getPosition()));
            System.out.println("");

        } else if (position == 6) {
            player.setPosition(12);
            System.out.println(player + " heeft " + dice + " gegooid, en staat op " + position + " (BRUG) en mag door naar 12.");

        } else if (position == 19) {
            System.out.println(player + " heeft " + dice + " gegooid, en staat op " + position + " (HERBERG) en moet een beurt overslaan.");
            this.listPlayers.get(player.getNumber()).isInn();

        } else if (position == 31) {
            this.listPlayers.get(player.getNumber()).isInPothole();
            this.playerInPothole = player.getNumber();
            System.out.println(player + " heeft " + dice + " gegooid, en staat op " + position +
                    " (PUT) en moet hier blijven tot de volgende speler komt.");

        } else if (position == 42) {
            System.out.println(player + " heeft " + dice + " gegooid, en staat op " + position + " (DOOLHOF) en moet terug naar 39.");
            player.setPosition(39);

        } else if (position == 53) {
            System.out.println(player + " heeft " + dice + " gegooid, en staat op " + position + " (GEVANGENIS) en moet drie beurten overslaan.");
            this.listPlayers.get(player.getNumber()).isInPrison();
            player.countPrisonCount();

        } else if (position == 59) {
            System.out.println(player + " heeft " + dice + " gegooid, en staat op " + position + " (DOOD). Je gaat terug naar start.");
            player.setPosition(1);

        } else if (position > 63) {
            System.out.println(player + " heeft " + dice + " gegooid en staat op " + position + ". " +
                    player + " heeft teveel ogen gegooid en moet het aantal teveel geworpen ogen terug vanaf 63!");
            player.setPosition(63);
            player.decreasePosition(dice);
            System.out.println(player + " staat nu op " + player.getPosition() + ".");
        }else if (position == 63) {
            System.out.println(player + " heeft " + dice + " gegooid en staat op " + position + "! "
                    + player + " heeft het einde bereikt van Ganzenbord en heeft GEWONNEN!");
            gameOn = false;

        } else System.out.println(player + " heeft " + dice + " gegooid, en staat op " + player.getPosition() + ".");

    }

    public void startMenu(){
        System.out.println("Welkom bij Ganzenbord!!");
        System.out.println("De bedoeling van het spel is om als eerste op hokje 63 uit te komen.");
        System.out.println("Elke speler mag per beurt met de dobbelsteen gooien en zoveel hokjes verplaatsen als dat er ogen worden gegooid.");
        System.out.println("");
        System.out.println("SPELREGELS");
        System.out.println("Het spel heeft een aantal nummers met een speciale betekenis");
        System.out.println("------------------------------------------------------------");
        System.out.println("nummer 6 is de BRUG: je gaat naar 12");
        System.out.println("nummer 19 is de HERBERG: sla 1 beurt over");
        System.out.println("nummer 31 is de PUT: wacht totdat een nieuwe speler in de put komt, pas dan mag je verder");
        System.out.println("nummer 42 is de DOOLHOF: je wordt op 39 geplaatst");
        System.out.println("nummer 52 is de GEVANGENIS: sla 3 beurten over");
        System.out.println("nummer 58 is de DOOD: terug naar start");
        System.out.println("nummer 63 is het eind: wie hier als eerst komt heeft gewonnen!");
        System.out.println("Kom je op 10, 20, 30, 40, 50 of 60 terecht? Dan krijg je het aantal geworpen ogen als bonusstapjes!");
        System.out.println("");
    }
}



