import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PegSolitaire {

    public static int[][] possible = {
            {1, 2, 4}, {1, 3, 6}, {2, 4, 7}, {4, 2, 1}, {6, 3, 1}, {7, 4, 2},
            {2, 5, 9}, {3, 5, 8}, {3, 6, 10}, {9, 5, 2}, {8, 5, 3}, {10, 6, 3},
            {4, 5, 6}, {7, 8, 9}, {8, 9, 10}, {6, 5, 4}, {9, 8, 7}, {10, 9, 8}};
    private final ArrayList<Integer[]> _snapshots;

    public String convertToStars(int x){
        if(x == 1) return "*";
        return ".";
    }

    public void writeToFile(int p, ArrayList<Integer[]> snapshots, FileWriter fileWriter) throws IOException {
        Collections.reverse(snapshots);
        for(int x=0; x < snapshots.size(); x++){
            String s0 = convertToStars(snapshots.get(x)[0]);
            String s1 = convertToStars(snapshots.get(x)[1]);
            String s2 = convertToStars(snapshots.get(x)[2]);
            String s3 = convertToStars(snapshots.get(x)[3]);
            String s4 = convertToStars(snapshots.get(x)[4]);
            String s5 = convertToStars(snapshots.get(x)[5]);
            String s6 = convertToStars(snapshots.get(x)[6]);
            String s7 = convertToStars(snapshots.get(x)[7]);
            String s8 = convertToStars(snapshots.get(x)[8]);
            String s9 = convertToStars(snapshots.get(x)[9]);
            String s = "   " + s0 + "\n" + "  " + s1 + " " + s2 + "\n" + " " + s3 + " " + s4 + " " + s5 + "\n" + s6 + " " + s7 + " " + s8 + " " + s9 + "\n\n";
            fileWriter.write(s);
        }
        fileWriter.write("----------------------- solved(" + p + ") ----------------------- \n\n\n");
    }

    public PegSolitaire(String f) throws IOException {
        FileWriter fileWriter = new FileWriter(f);
        Integer[] ls = new Integer[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        this._snapshots = new ArrayList<Integer[]>();
        for(int x=0; x < 10; x++){
            Integer[] lis = ls.clone();
            lis[x] = 0;
            Integer[] lis_clone = lis.clone();
            boolean comp = gameplay(lis);
            this._snapshots.add(lis_clone);
            if(comp) writeToFile(x,_snapshots, fileWriter);
            this._snapshots.clear();
        }
        fileWriter.close();
    }

    boolean move(int a, int b, int c){
        for(int[]x : possible){
            if(x[0] == a && x[1] == b && x[2] == c) {
                return true;
            }
        }
        return false;
    }

    public boolean gameplay(Integer[] lis){
        List <Integer> l = (List<Integer>) Arrays.asList(lis);
        if(Collections.frequency(l,0) == 9)
        {
            return true;
        }
        else{
            for(int a = 1; a < 11; a++){
                if(lis[a-1] == 0) continue;
                for(int b = 1; b < 11; b++){
                    if(a==b || lis[b-1] == 0) continue;
                    for(int c = 1; c < 11; c++){
                        if(a==c || b==c || lis[c-1] == 1) continue;
                        if(move(a,b,c)){
                            lis[a-1] = 0;
                            lis[b-1] = 0;
                            lis[c-1] = 1;
                            Integer[] lis_copy = lis.clone();
                            boolean p = gameplay(lis);
                            if (p){
                                this._snapshots.add(lis_copy);
                                return true;}
                            else{
                                lis[a-1] = 1;
                                lis[b-1] = 1;
                                lis[c-1] = 0;
                                continue;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
}
