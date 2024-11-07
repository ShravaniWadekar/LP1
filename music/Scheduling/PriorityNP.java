import java.util.Scanner;

public class PriorityNP {

	public static void main(String[] args) {
        int n,i,k;
        float atat = 0,awt = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        int p[] = new int[n];
        int flag[] = new int[n];
     
        for (int m = 0; m < n; m++) {
            System.out.println("Enter the Process id");
            pid[m] = sc.nextInt();
            System.out.println("Enter the Arrival time");
            at[m] = sc.nextInt();
            System.out.println("Enter the Burst time");
            bt[m] = sc.nextInt();
            System.out.println("Enter the priority");
            p[m] = sc.nextInt();
            flag[m] = 0;
        }
        int st = 0;
        int tot = 0;
        while(true) {
        	int min = 99;
         	int c = n;

        	if(tot == n) {
        		break;
        	}

        	for(i = 0; i < n; i++ ) {
        		if(at[i]<=st && flag[i]==0 && p[i]<min) {
        			c=i;
        			min = p[i];
        		}
        	}
        	if(c==n) {
        		st++;
        	}
        	else {
        		ct[c] = st + bt[c];
        		flag[c] = 1;
        		st = ct[c];
        		tot++;		
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
4
Enter the Burst time
5
Enter the priority
4
Enter the Process id
2
Enter the Arrival time
4
Enter the Burst time
10
Enter the priority
2
Enter the Process id
3
Enter the Arrival time
0
Enter the Burst time
2
Enter the priority
6
Enter the Process id
4
Enter the Arrival time
3
Enter the Burst time
1
Enter the priority
5






Enter the Process id
5
Enter the Arrival time
8
Enter the Burst time
4
Enter the priority
1
Enter the Process id
6
Enter the Arrival time
6
Enter the Burst time
2
Enter the priority
3

Output:

PID	AT	BT	CT	TAT	WT
1	4	5	25	21	16
2	4	10	14	10	0
3	0	2	2	2	0
4	3	1	4	1	0
5	8	4	18	10	6
6	6	2	20	14	12
Average Turnaround time: 9.666667
Average Waiting time: 5.6666665
*/