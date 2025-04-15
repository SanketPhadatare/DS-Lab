

import java.util.Scanner;

class Rr {
    int id;
    int state; // 0 = active, 1 = inactive
    int index;
}

public class Ring {
    public static void main(String[] args) {
        int i, j, num, temp;
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of process :\n");
        num = in.nextInt();
        Rr[] proc = new Rr[num];

        for (i = 0; i < num; i++) {
            proc[i] = new Rr();
        }

        for (i = 0; i < num; i++) {
            proc[i].index = i;
            System.out.print("Enter the id of process :\n");
            proc[i].id = in.nextInt();
            proc[i].state = 0; // active
        }

        // Sorting process ids
        for (i = 0; i < num - 1; i++) {
            for (j = 0; j < num - i - 1; j++) {
                if (proc[j].id > proc[j + 1].id) {
                    temp = proc[j].id;
                    proc[j].id = proc[j + 1].id;
                    proc[j + 1].id = temp;
                }
            }
        }

        int ch;
        do {
            System.out.println("1.election 2.quit");
            ch = in.nextInt();

            if (ch == 1) {
                System.out.println("Enter the Process number who initialised election :");
                int initiator = in.nextInt();
                initiator--; // make 0-based

                int maxId = proc[initiator].id;
                int current = (initiator + 1) % num;

                System.out.println("Process " + proc[initiator].id + " send message to " + proc[current].id);

                while (current != initiator) {
                    int next = (current + 1) % num;
                    System.out.println("Process " + proc[current].id + " send message to " + proc[next].id);
                    if (proc[current].id > maxId) {
                        maxId = proc[current].id;
                    }
                    current = next;
                }

                System.out.println("process " + maxId + "select as co-ordinator");
            }

        } while (ch != 2);

        System.out.println("Program terminated ...");
        in.close();
    }
}