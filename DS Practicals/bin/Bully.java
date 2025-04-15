

import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[6]; // index 1 to 5
    static int coordinator;

    public static void up(int up) {
        if (state[up]) {
            System.out.println("Process " + up + " is already up.");
        } else {
            state[up] = true;
            System.out.println("Process " + up + " is up.");
            System.out.println("Process " + up + " held election.");

            for (int i = up + 1; i <= 5; i++) {
                System.out.println("Election message sent from process " + up + " to process " + i);
            }

            for (int i = 5; i >= 1; i--) {
                if (state[i]) {
                    coordinator = i;
                    break;
                }
            }

            System.out.println("Coordinator is: " + coordinator);
        }
    }

    public static void down(int down) {
        if (!state[down]) {
            System.out.println("Process " + down + " is already down.");
        } else {
            state[down] = false;
            System.out.println("Process " + down + " is down.");
        }
    }

    public static void mess(int mess) {
        if (!state[mess]) {
            System.out.println("Process " + mess + " is down.");
        } else {
            if (!state[coordinator]) {
                System.out.println("Process " + mess + " held election.");

                for (int i = mess + 1; i <= 5; i++) {
                    System.out.println("Election message sent from process " + mess + " to process " + i);
                }

                for (int i = 5; i >= 1; i--) {
                    if (state[i]) {
                        coordinator = i;
                        break;
                    }
                }

                System.out.println("Coordinator message sent from process " + coordinator + " to all.");
            } else {
                System.out.println("Message sent to coordinator from process " + mess);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= 5; i++) {
            state[i] = true;
        }

        coordinator = 5;
        System.out.println("5 active processes are: p1 p2 p3 p4 p5");
        System.out.println("Process 5 is coordinator");

        int choice;
        do {
            System.out.println("\n1. Up a process\n2. Down a process\n3. Send a message\n4. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Bring up which process?");
                    int up = sc.nextInt();
                    if (up >= 1 && up <= 5)
                        up(up);
                    else
                        System.out.println("Invalid process ID.");
                    break;
                case 2:
                    System.out.println("Bring down which process?");
                    int down = sc.nextInt();
                    if (down >= 1 && down <= 5)
                        down(down);
                    else
                        System.out.println("Invalid process ID.");
                    break;
                case 3:
                    System.out.println("Which process will send message?");
                    int mess = sc.nextInt();
                    if (mess >= 1 && mess <= 5)
                        mess(mess);
                    else
                        System.out.println("Invalid process ID.");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}