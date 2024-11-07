import java.util.*;

public class FCFS {

    public static void main(String[] args) {
        int n, i, j, k;
        float atat = 0, awt = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];

        for (int m = 0; m < n; m++) {
            System.out.println("Enter the Process id");
            pid[m] = sc.nextInt();
            System.out.println("Enter the Arrival time");
            at[m] = sc.nextInt();
            System.out.println("Enter the Burst time");
            bt[m] = sc.nextInt();
        }


        //swapping...//arrange by bubble sort according to at[]
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (at[j] > at[j + 1]) {
                    int temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;

                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;
                    
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;
                }
            }
        }
        ct[0] = at[0] + bt[0];
        for (i = 1; i < n; i++) {
            if (at[i] > ct[i - 1]) {
                ct[i] = at[i] + bt[i];
            } else {
                ct[i] = ct[i - 1] + bt[i];
            }
        }
        for (int t = 0; t < n; t++) {
            tat[t] = ct[t] - at[t];
            wt[t] = tat[t] - bt[t];
            atat += tat[t];
            awt += wt[t];
        }
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for (k = 0; k < n; k++) {
            System.out.println(pid[k] + "\t" + at[k] + "\t" + bt[k] + "\t" + ct[k] + "\t" + tat[k] + "\t" + wt[k]);
        }
        atat /= n;
        awt /= n;
        System.out.println("Average Turnaround time: " + atat);
        System.out.println("Average Waiting time: " + awt);
        sc.close();
    }
}
/* 
Input:
Enter the number of processes: 
6
Enter the Process id
1
Enter the Arrival time
0
Enter the Burst time
5
Enter the Process id
2
Enter the Arrival time
0
Enter the Burst time
6
Enter the Process id
3
Enter the Arrival time
0
Enter the Burst time
1
Enter the Process id
4
Enter the Arrival time
0
Enter the Burst time
6
Enter the Process id
5
Enter the Arrival time
0
Enter the Burst time
3
Enter the Process id
6
Enter the Arrival time
0
Enter the Burst time
1

Output:
PID	AT	BT	CT	TAT	WT
1	0	5	5	5	0
2	0	6	11	11	5
3	0	1	12	12	11
4	0	6	18	18	12
5	0	3	21	21	18
6	0	1	22	22	21
Average Turnaround time: 14.833333
Average Waiting time: 11.166667


*/