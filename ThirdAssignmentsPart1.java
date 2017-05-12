/**
 * Created by lucy on 27/04/17.
 */
import edu.duke.*;
public class ThirdAssignmentsPart1 {

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

    public String findGene (String dna, int index){
        int startIndex = dna.indexOf("ATG", index);
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

    public StorageResource getAllGenes (String dna){
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        while (true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = (dna.indexOf(currentGene,startIndex)+ currentGene.length());
        }
        return geneList;
    }

    public void testOn (String dna){
        System.out.println("Testing getAllGenes on "+ dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()){
            System.out.println(g);
        }
    }

    public void test(){
        testOn("ABCTATGTCCTGAVVATGTAAVVVATGDDDFFFATGTAGVV");
        testOn("");
        testOn("ATGTAATGATGTTTGGGAAATAGATATGVVTAAVTGAV");
    }
}
