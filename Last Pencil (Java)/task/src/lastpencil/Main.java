package lastpencil;
import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        int num = getNumberOfPencils();
        String name = getFirstPlayerName();

        while(num>0){
            String emptyStr = "";
            for(int i=0; i<num; i++){
                emptyStr += "|";
            }
            System.out.println(emptyStr);
            System.out.println(name+"'s turn");


            int useNum;
            if (name.equals("Jack")) {
                useNum = calculateJackMove(num);
                System.out.println(useNum);
            } else {
                useNum = getUserMove(num);
            }
            num = num - useNum;


            if(name.equals("Jack")){
                name ="John";
            }
            else{
                name="Jack";
            }
        }
        System.out.println(name+" won!");
    }

    public static int getNumberOfPencils(){
        int num = 0;
        boolean flag = false;
        System.out.println("How many pencils would you like to use:");
        do{
            Scanner scanner = new Scanner(System.in);
            String numStr = scanner.nextLine();
            try{
                num = Integer.parseInt(numStr);
                if(num==0){
                    System.out.println("The number of pencils should be positive");
                }
                else if(num<0){
                    System.out.println("The number of pencils should be numeric");
                }
                else{
                    flag=true;
                }
            }catch(Exception e){
                System.out.println("The number of pencils should be numeric");
            }
        } while(!flag);
        return num;
    }

    public static String getFirstPlayerName() {
        boolean nameCheck = false;
        System.out.println("Who will be the first (John, Jack)");
        String name;
        do {
            Scanner a = new Scanner(System.in);
            name = a.nextLine();
            if (!(name.equals("Jack") || name.equals("John"))) {
                System.out.println("Choose between John and Jack");
            } else {
                nameCheck = true;
            }
        } while (!nameCheck);
        return name;
    }

    private static int getUserMove(int num) {
        boolean useNumCheck = false;
        int useNum = 0;
        do{
            Scanner a = new Scanner(System.in);
            String useNumStr = a.nextLine();
            try {
                useNum = Integer.parseInt(useNumStr);
                if(!(useNum==1 || useNum==2 || useNum==3)){
                    System.out.println("Possible values: '1', '2' or '3'");
                }
                else if(useNum>num){
                    System.out.println("Too many pencils were taken");
                }
                else{
                    useNumCheck = true;
                }
            }
            catch(Exception e){
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }while(!useNumCheck);
        return useNum;
    }

    private static int calculateJackMove(int num) {
        if (isBotInLosingPosition(num)) {
            return calculateLosingMove(num);
        } else {
            return calculateWinningMove(num);
        }
    }

    private static boolean isBotInLosingPosition(int num)
    {
        return num % 4 == 1;
    }

    private static int calculateWinningMove(int num) {
        if(num%4 == 0){
            return 3;
        }
        if(num%4 == 3){
            return 2;
        }
        if(num%4 == 2){
            return 1;
        }
        return 0;
    }

    private static int calculateLosingMove(int num) {
        if(num == 1){
            return 1;
        }
        else{
            Random random = new Random();
            return random.nextInt(3) + 1;
        }
    }

}
