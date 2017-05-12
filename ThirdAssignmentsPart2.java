import edu.duke.FileResource;
import edu.duke.StorageResource;
import edu.duke.URLResource;

import java.net.URL;

/**
 * Created by lucy on 28/04/17.
 */
public class ThirdAssignmentsPart2 {

    public float cgRatio(String dna) {
        float numberOfC = countSmth(dna, "C");
        int numberOfG = countSmth(dna, "G");
        float ratio = (numberOfC + numberOfG) / dna.length();
        return ratio;
    }

    public int countSmth(String dna, String smth) {
        int startIndex = 0;
        int numberOfSmth = 0;
        while (true) {
            int indexSmth = dna.indexOf(smth, startIndex);
            if (indexSmth == -1) {
                break;
            }
            numberOfSmth = numberOfSmth + 1;
            startIndex = indexSmth + smth.length();
        }
        return numberOfSmth;
    }

    public void testOn(String dna) {
        System.out.println("Testing cgRatio on " + dna);
        float r = cgRatio(dna);
        System.out.println("cgRatio = " + r);
    }

    public int countCTG(String dna) {
        int ctg = countSmth(dna, "CTG");
        return ctg;
    }

    public void testCTG(String dna) {
        System.out.println("Testing countCTG on " + dna);
        int ctg = countCTG(dna);
        System.out.println("Number of CTG is " + ctg);
    }


    public void processGenes(StorageResource sr) {
        StorageResource longGenes = new StorageResource();
        for (String g : sr.data()) {
            if (g.length() > 9) {
                longGenes.add(g);
            }
        }
        int numberOfLongGenes = longGenes.size();
        System.out.println("Number of genes greater then 9 is " + numberOfLongGenes);
        for (String l : longGenes.data()) {
            System.out.println(l);
        }

        StorageResource cGRatioGenes = new StorageResource();
        for (String g : sr.data()) {
            float cgr = cgRatio(g);
            if (cgr > 0.35) {
                cGRatioGenes.add(g);
            }
        }
        int numberOfCgratioGenes = cGRatioGenes.size();
        System.out.println("Number of genes with a CG-ratio greater then 0,35 is " + numberOfCgratioGenes);
        for (String c : cGRatioGenes.data()) {
            System.out.println(c);
        }
        int largestString = 0;
        for (String g : sr.data()) {
            if (g.length() > largestString) {
                largestString = g.length();
            }
            ;
        }
        System.out.println("The length of largest gene is " + largestString);
    }

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public String findGene(String dna, int index) {
        int startIndex = dna.indexOf("ATG", index);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = (dna.indexOf(currentGene, startIndex) + currentGene.length());
        }
        return geneList;
    }

    public void testProcessGenes(String dna) {
        StorageResource sr = getAllGenes(dna);
        System.out.println("Testing ProcessGenes on " + dna);
        processGenes(sr);
    }

    public void test() {
        //           --v--v--vvvv--
       /* testOn("ABCEFGRTCGCGRT");
        testOn("");
        testOn("ASASASRTRTRTDFDFDF");
        testOn("QWERTYUIG");
        testOn("CGCGGCCGGC");

        testCTG("ABCTGABTGCTGCTGCTGAB");
        testCTG("ABABABABCTG");
        testCTG("ABABABABABABA");
        testCTG("CTGCTGCTGGTCTGABCTG");*/

        testProcessGenes("ABCATGTTTAAATGAABCATGTAAAAAATGCCCTGGCGCTAA");
        //                     ---v-----v--v--v---v----------v--
        testProcessGenes("TGAATGAAATGAATGTAAAATGCATGCCGCTAG");
        testProcessGenes("ATGCCGGGCATCTGAAAAATGCCCGGGTTTTAGATGTAAATGCCCTTTGGGAAATGCTACTAA");
        testProcessGenes("ATGTTTAAATAAAGTTAAATGCTAAAATTTCTATAA");
        FileResource fr = new FileResource("brca1line");
        String dna = fr.asString().toUpperCase();
        testProcessGenes(dna);
    }
}
