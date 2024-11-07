package page_replacement;
import java.util.Arrays;
import java.util.Scanner;

public class FIFO{
    private int[] frames;
    private int frameCount;
    private int pointer;
    private int[] pages;
    private int pageFaults;

    public FIFO(int frameCount, int[] pages) {
        this.frameCount = frameCount;
        this.pages = pages;
        this.frames = new int[frameCount];
        Arrays.fill(frames, -1);
        this.pointer = 0;
        this.pageFaults = 0;
    }

    public void execute() {
        System.out.println("Page Replacement Process using FIFO");
        System.out.println("Frames: " + frameCount);
        System.out.println("Pages: " + Arrays.toString(pages));
        System.out.println("-------------------------");

        for (int page : pages) {
            if (!isPageInFrame(page)) {
                replacePage(page);
                pageFaults++;
            }
            printFrames();
        }

        System.out.println("-------------------------");
        System.out.println("Total Page Faults: " + pageFaults);
    }

    private boolean isPageInFrame(int page) {
        for (int i = 0; i < frameCount; i++) {
            if (frames[i] == page) {
                return true;
            }
        }
        return false;
    }

    private void replacePage(int page) {
        frames[pointer] = page;
        pointer = (pointer + 1) % frameCount;
    }

    private void printFrames() {
        for (int i = 0; i < frameCount; i++) {
            if (frames[i] == -1) {
                System.out.print(" - ");
            } else {
                System.out.print(" " + frames[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter number of Frames: ");
        int frameCount = sc.nextInt();
    	System.out.println("Enter number of pages");
    	int N = sc.nextInt();
        int[] pages = new int[N];
        for(int i = 0; i < N; i++) {
        	System.out.println("Enter the page"+ i);
        	pages[i] = sc.nextInt();
        }
        FIFO fifo = new FIFO(frameCount, pages);
        fifo.execute();
        sc.close();
    }
}


/* 

Output:
Enter number of pages
7
Enter the page0
1
Enter the page1
2
Enter the page2
3
Enter the page3
4
Enter the page4
3
Enter the page5
5
Enter the page6
1
Page Replacement Process using FIFO
Frames: 3
Pages: [1, 2, 3, 4, 3, 5, 1]
-------------------------
 1  -  - 
 1  2  - 
 1  2  3 
 4  2  3 
 4  2  3 
 4  5  3 
 4  5  1 
-------------------------
Total Page Faults: 6

*/