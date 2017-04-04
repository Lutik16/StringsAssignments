/**
 * Created by lucy on 04/04/17.
 */
public class Part2 {

    public int howMany (String stringa, String stringb){
        int occure = 0;
        int currentIndex = 0;
        while (true){
            int startIndex= stringb.indexOf(stringa, currentIndex);
            if(startIndex==-1){
                break;
            }
            occure = occure + 1;
            currentIndex = startIndex + stringa.length();
        }
        return occure;
    }

    public void testHowMany (){
        String stringb ="ABCTABCBABT";
        int test = howMany("AB", stringb);
        if (test!=3){
            System.out.println("error on AB");
        }
        System.out.println("Occurrence AB in "+ stringb +" is "+ test);

        test = howMany("BC", stringb);
        if (test !=2){
            System.out.println("error on BC");
        }
        System.out.println("Occurence BC in "+ stringb+ " is "+test);

        test = howMany(" ", stringb);
        if (test!=0){
            System.out.println("error on space");
        }
        System.out.println("Occurence SPACE in "+stringb+ " is "+test);
    }
}
