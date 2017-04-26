/**
 * Created by lucy on 06/04/17.
 */
public class Part3 {
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
    
    public void printAllGenes (String dna){
        int startIndex = 0;
        while (true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = (dna.indexOf(currentGene,startIndex)+ currentGene.length());
        }
    }

    public int countGenes (String dna){
        int startIndex = 0;
        int numberOfGenes = 0;
        while (true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            numberOfGenes = numberOfGenes+1;
            startIndex = (dna.indexOf(currentGene,startIndex)+ currentGene.length());
        }
        return numberOfGenes;
    }

    public void testCountGenes(){
        String dna = "ACATGCCCTGAGGATGTTTTCGTAAATGATGTAGCCATG";
        int howMany = countGenes(dna);
        if (howMany!=3){
            System.out.println("error on 3");
        }
        System.out.println("The number of genes in "+ dna+ " is "+howMany);

        dna = "AAACCCTTTTGA";
        howMany = countGenes(dna);
        if(howMany!=0){
            System.out.println("error on 0");
        }
        System.out.println("The number of genes in "+ dna+ " is "+howMany);

        dna = "ATGTGAcccATGTTTATCTAAggATGTCCGGGAAATAGaaaATGTAGaaggccATGATGATGTAAcgATGCCCTGACGATGA";
        howMany = countGenes(dna);
        if(howMany!=6){
            System.out.println("error on 6");
        }
        System.out.println("The number of genes in "+ dna+ " is "+howMany);
    }
}
