import java.util.*;
import java.io.*;

class Tuple {
    String mnemonic, bin_opcode, type;
    int length;

    Tuple() {}

    Tuple(String s1, String s2, String s3, String s4) {
        mnemonic = s1;
        bin_opcode = s2;
        length = Integer.parseInt(s3);
        type = s4;
    }
}

class SymTuple {
    String symbol, ra;
    int value, length;

    SymTuple(String s1, int i1, int i2, String s2) {
        symbol = s1;
        value = i1;
        length = i2;
        ra = s2;
    }
}

class LitTuple {
    String literal, ra;
    int value, length;

    LitTuple() {}

    LitTuple(String s1, int i1, int i2, String s2) {
        literal = s1;
        value = i1;
        length = i2;
        ra = s2;
    }
}

class TwoPassAssembler {
    static int lc;
    static List<Tuple> mot;
    static List<String> pot;
    static List<SymTuple> symtable;
    static List<LitTuple> littable;
    static List<Integer> lclist;
    static Map<Integer, Integer> basetable;
    static PrintWriter out_pass2;
    static PrintWriter out_pass1;
    static int line_no;

    public static void main(String[] args) throws Exception {
        initializeTables();
        System.out.println("====== PASS 1 ======\n");
        pass1();
        System.out.println("\n====== PASS 2 ======\n");
        pass2();
    }

    static void pass1() throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        out_pass1 = new PrintWriter(new FileWriter("output_pass1.txt"), true);
        PrintWriter out_symtable = new PrintWriter(new FileWriter("out_symtable.txt"), true);
        PrintWriter out_littable = new PrintWriter(new FileWriter("out_littable.txt"), true);

        String s;
        while ((s = input.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s, " ", false);
            String[] s_arr = new String[st.countTokens()];
            for (int i = 0; i < s_arr.length; i++) {
                s_arr[i] = st.nextToken();
            }
            if (!searchPot1(s_arr)) {
                searchMot1(s_arr);
                out_pass1.println(s);
            }
            lclist.add(lc);
        }

        int j;
        String output;
        System.out.println("Symbol Table:");
        System.out.println("Symbol\tValue\tLength\tR/A");
        for (SymTuple i : symtable) {
            output = i.symbol;
            for (j = i.symbol.length(); j < 10; j++) output += " ";
            output += i.value;
            for (j = Integer.toString(i.value).length(); j < 7; j++) output += " ";
            output += i.length + "\t" + i.ra;
            System.out.println(output);
            out_symtable.println(output);
        }

        System.out.println("\nLiteral Table:");
        System.out.println("Literal\tValue\tLength\tR/A");
        for (LitTuple i : littable) {
            output = i.literal;
            for (j = i.literal.length(); j < 10; j++) output += " ";
            output += i.value;
            for (j = Integer.toString(i.value).length(); j < 7; j++) output += " ";
            output += i.length + "\t" + i.ra;
            System.out.println(output);
            out_littable.println(output);
        }
    }

    static void pass2() throws Exception {
        line_no = 0;
        out_pass2 = new PrintWriter(new FileWriter("output_pass2.txt"), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("output_pass1.txt")));
        String s;
        System.out.println("Pass 2 input:");
        while ((s = input.readLine()) != null) {
            System.out.println(s);
            StringTokenizer st = new StringTokenizer(s, " ", false);
            String[] s_arr = new String[st.countTokens()];
            for (int i = 0; i < s_arr.length; i++) {
                s_arr[i] = st.nextToken();
            }
            if (!searchPot2(s_arr)) {
                searchMot2(s_arr);
            }
            line_no++;
        }

        System.out.println("\nPass 2 output:");
        input = new BufferedReader(new InputStreamReader(new FileInputStream("output_pass2.txt")));
        while ((s = input.readLine()) != null) {
            System.out.println(s);
        }
    }

    static boolean searchPot1(String[] s) {
        // Implementation for searchPot1
        return false;
    }

    static void searchMot1(String[] s) {
        // Implementation for searchMot1
    }

    static boolean searchPot2(String[] s) {
        // Implementation for searchPot2
        return false;
    }

    static void searchMot2(String[] s) {
        // Implementation for searchMot2
    }

    static void initializeTables() throws Exception {
        symtable = new LinkedList<>();
        littable = new LinkedList<>();
        lclist = new ArrayList<>();
        basetable = new HashMap<>();
        mot = new LinkedList<>();
        pot = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("mot.txt")));
        String s;
        while ((s = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s, " ", false);
            mot.add(new Tuple(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
        }

        br = new BufferedReader(new InputStreamReader(new FileInputStream("pot.txt")));
        while ((s = br.readLine()) != null) {
            pot.add(s);
        }
        Collections.sort(pot);
    }
}

/* 
Input : 
 
PRGAM2 	START 	0 
	USING 	*,15 
	LA 	15,SETUP 
	SR 	TOTAL,TOTAL 
AC 	EQU 	2 
INDEX 	EQU 	3 
TOTAL 	EQU 	4 
DATABASE 	EQU 	13 
SETUP 	EQU 	* 
	USING 	SETUP,15 
	L 	DATABASE,=A(DATA1) 
	USING 	DATAAREA,DATABASE SR
	 	INDEX,INDEX 
LOOP 	L 	AC,DATA1(INDEX) AR
	 	TOTAL,AC 
	A 	AC,=F'5' 
	ST 	AC,SAVE(INDEX) 
	A 	INDEX,=F'4' 
	C 	INDEX,=F'8000' 
	BNE 	LOOP 
	LR 	1,TOTAL 
	BR 	14 
LTORG 
SAVE 	DS 	3F 
DATAAREA 	EQU 	* 
DATA1 	DC 	F'25,26,27' END 
 
 
 
MOT.TXT 
 
LA 01h 4 RX SR 02h 2 RR L 03h 4 RX AR 04h 2 RR A 05h 4 RX 
C 	06h 	4 	RX 
BNE 	07h 	4 	RX 
LR 	08h 	2 	RR 
ST 	09h 	4 	RX 
BR 	15h 	2 	RR 
 
POT.TXT 	 	 	 
 	 	 
START 
END 
LTORG 
DC 
DS 
DROP 
USING 
EQU 
 
*/