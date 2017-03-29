/**
 * Created by lucy on 28/03/17.
 */
public class Part1 {

    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while (currIndex !=-1){
            int diff = currIndex-startIndex;
            if(diff%3==0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
     return dna.length();
    }

    public void testFindStopCodon (){
        //            012345678901234567890123456789012345678901234
        String dna = "XXXYYYZZZTAAXXXYYYZTAAXXTAAXXXTGAYYYTAAZZZXXX";
        int testIndex = findStopCodon(dna,0, "TAA");
        if(testIndex!=9){
            System.out.println("error on 9");
        }
        testIndex = findStopCodon(dna, 9, "TAA");
        if(testIndex!=24){
            System.out.println("error on 24");
        }
        testIndex= findStopCodon(dna,24,"TAA");
        if(testIndex!=36){
            System.out.println("error on 36");
        }
        testIndex = findStopCodon(dna, 36, "TAA");
        if(testIndex!=45){
            System.out.println("error on 45");
        }
        testIndex = findStopCodon(dna,0, "TGA");
        if(testIndex!=30){
            System.out.println("error on 30");
        }

        System.out.println("Test finished");
    }

    public String findGene (String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex==-1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex,"TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex,tgaIndex));
        if(minIndex==dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }

    public void testFindGene(){
        //            01234567890123456789
        String dna = "xxxATGyyyzzzTAA";
        String gene = findGene(dna);
        if(gene.equalsIgnoreCase("ATGyyyzzzTAA")) {
            System.out.println("DNA string is " + " " + dna);
            System.out.println("Gene is " + " " + gene);
        }
        else {
            System.out.println("error 1 (TAA)");
        }

        dna = "GTTCAATAGCGGTAATTTCCC";
        gene = findGene(dna);
        if(!gene.equalsIgnoreCase("")){
            System.out.println("error 2 (no ATG)");
        }
        System.out.println("DNA string is"+" "+ dna);
        System.out.println("Gene is "+ gene);

        dna = "ATGxxxyyyzTAAxxTAGzzzTGAxxxTAA";
        gene = findGene(dna);
        if(!gene.equalsIgnoreCase("ATGxxxyyyzTAAxxTAG")) {
            System.out.println("error 3 (multiple stop codons)");
        }
        System.out.println("DNA string is"+" "+ dna);
        System.out.println("Gene is "+ gene);

        dna = "ATGTTTCCCAAAGTACTACTAA";
        gene = findGene(dna);
        if(!gene.equalsIgnoreCase("")){
            System.out.println("error 4 (no stopCodon)");
        }
        System.out.println("DNA string is"+" "+ dna);
        System.out.println("Gene is "+ gene);
    }

    public void printAllGenes (String dna){
        int startIndex = 0;
        while (true){
            String currentGene = findGene(dna);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = (currentGene.length() + dna.indexOf(currentGene,startIndex));
        }
    }

    public void testOn (String dna){
        System.out.println("Testing printAllGenes on "+ dna);
        printAllGenes(dna);
    }

    public void test(){
        testOn("xxxATGyyyzzzTGAxxATGxxxyTAAzzTAAxxxTAGzz");
        testOn("xxxAGGyyyAAAzzz");
        testOn("");
        testOn("ATGxxxyyyTGGzzzTcc");
    }
}
