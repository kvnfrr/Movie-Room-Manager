import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rowNum = 0;
        int seatNum = 0;
        int totalIncome = 0;
        int[] currentIncome = {0};
        int[] purchasedTickets = {0};

        System.out.println("Enter the number of rows:");
        int numOfRows = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = in.nextInt();

        boolean[][] seats = new boolean[numOfRows][seatsPerRow];
        int totalSeats = numOfRows * seatsPerRow;
        totalIncome = calculateTotalIncome(numOfRows, seatsPerRow);

        while(true) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            switch (in.next()) {
                case "1":
                    showSeats(seats);
                    continue;
                case "2":
                    buyTicket(in, seats, totalSeats, currentIncome, purchasedTickets);
                    continue;
                case "3":
                    showStatistics(purchasedTickets[0], totalSeats, currentIncome[0], totalIncome);
                    continue;
                case "0":
                    return;
            }
        }
    }

    public static void showSeats(boolean[][] seats) {
        System.out.println("\nCinema: ");
        System.out.print("  ");
        for(int i = 1; i <= seats[0].length; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int j = 0; j <= seats.length - 1; j++){
            System.out.print((j + 1) + " ");
            for(int k = 0; k <= seats[0].length - 1; k++){
                System.out.print((!seats[j][k]? "S" : "B") + " ");
            }
            System.out.println();
        }
    }

    public static void buyTicket(Scanner in, boolean[][] seats, int totalSeats, int[] currentIncome, int[] purchasedTickets) {
        int rowNum, seatNum;
        while (true) {
            System.out.println("\nEnter a row number:");
            rowNum = in.nextInt() - 1;
            System.out.println("Enter a seat number in that row:");
            seatNum = in.nextInt() - 1;

            if (rowNum < 0 || rowNum >= seats.length || seatNum < 0 || seatNum >= seats[0].length) {
                System.out.println("\nWrong input!");
                continue;
            }

            if (seats[rowNum][seatNum]) {
                System.out.println("\nThat ticket has already been purchased!");
            } else {
                int ticketPrice = calculateTicketPrice(seats.length, rowNum);
                seats[rowNum][seatNum] = true;
                currentIncome[0] += ticketPrice;
                purchasedTickets[0]++;
                System.out.println("Ticket price: $" + ticketPrice);
                break;
            }
        }
    }

    public static void showStatistics(int purchasedTickets, int totalSeats, int currentIncome, int totalIncome) {
        double occupancyPercentage = (double) purchasedTickets / totalSeats * 100;
        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", occupancyPercentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static int calculateTicketPrice(int numRows, int rowNum) {
        int totalSeats = numRows * 10;
        if (totalSeats <= 60 || rowNum < numRows / 2) {
            return 10;
        } else {
            return 8;
        }
    }

    public static int calculateTotalIncome(int numOfRows, int seatsPerRow) {
        int totalSeats = numOfRows * seatsPerRow;
        int totalIncome;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            int frontHalfRows = numOfRows / 2;
            int backHalfRows = numOfRows - frontHalfRows;
            totalIncome = (frontHalfRows * seatsPerRow * 10) + (backHalfRows * seatsPerRow * 8);
        }
        return totalIncome;
    }
}