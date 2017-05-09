/**
 * Created by lucy on 28/04/17.
 */
public class ThirdAssignmentsPart2 {

    public float cgRatio (String dna){
        float numberOfC = countSmth(dna, "C");
        int numberOfG = countSmth(dna, "G");
        float ratio = (numberOfC+numberOfG)/dna.length();
        return ratio;
    }

    public int countSmth (String dna, String smth){
        int startIndex=0;
        int numberOfSmth =0;
        while (true){
            int indexSmth = dna.indexOf(smth, startIndex);
            if (indexSmth==-1){
                break;
            }
            numberOfSmth = numberOfSmth+1;
            startIndex = indexSmth+smth.length();
        }
        return numberOfSmth;
    }

    public void testOn (String dna){
        System.out.println("Testing cgRatio on "+ dna);
        float r = cgRatio(dna);
        System.out.println("cgRatio = "+ r);
    }

    public int countCTG (String dna){
        int ctg = countSmth(dna, "CTG");
        return ctg;
    }

    public void testCTG (String dna){
        System.out.println("Testing countCTG on "+ dna);
        int ctg = countCTG(dna);
        System.out.println("Number of CTG is "+ ctg);
    }

    public void test(){
        //           --v--v--vvvv--
        testOn("ABCEFGRTCGCGRT");
        testOn("");
        testOn("ASASASRTRTRTDFDFDF");
        testOn("QWERTYUIG");
        testOn("CGCGGCCGGC");

        testCTG("ABCTGABTGCTGCTGCTGAB");
        testCTG("ABABABABCTG");
        testCTG("ABABABABABABA");
        testCTG("CTGCTGCTGGTCTGABCTG");
    }
}
