import java.util.Scanner;

public class Template {    
    static Scanner sc = new Scanner(System.in);
    public static boolean isUpper(char c){
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isLower(char c){
        return c >= 'a' && c <= 'z';
    }

    public static boolean isNum(char c){
        return c >= '0' && c <= '9';
    }

    public static int getCharIndex(char c){
        return c - 'A';
    }
    
    public static int countSpaces(String str){
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                count++;
            }
        }
        return count;
    }

    public static int getOperation(String str){
        if(str.indexOf("not", 0)!=-1){
            return 1;
        }
        if(str.indexOf("and", 0)!=-1){
            return 2;
        }
        if(str.indexOf("or", 0)!=-1){
            return 3;
        }
        return 0;
    }

    public static int countCommas(String str){
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ','){
                count++;
            }
        }
        return count;
    }

    public static int[] getNums(String str){
        int[] nums = new int[countCommas(str)+1];
        int count = 0;
        for(int i=0;i<str.length();i++){
            if(isNum(str.charAt(i))){
                nums[count] = str.charAt(i) - '0';
                count++;
            }
        }
        return nums;
    }

    public static int and(int a, int b){
        return a==1 && b==1? 1:0;
    }

    public static int and(int a, int b, int c){
        return and(and(a, b), c);
    }

    public static int or(int a, int b){
        return a==1 || b==1? 1:0;
    }

    public static int or(int a, int b, int c){
        return or(or(a, b), c);
    }

    public static int not(int a){
        return a==1? 0:1;
    }

    public static int operation(int[] nums, int operation){
        if(operation == 1){
            return not(nums[0]);
        }
        if(operation == 2){
            if(nums.length == 2){
                return and(nums[0], nums[1]);
            }
            return and(nums[0], nums[1], nums[2]);
        }
        if(operation == 3){
            if(nums.length == 2){
                return or(nums[0], nums[1]);
            }
            return or(nums[0], nums[1], nums[2]);
        }
        return 0;
    }

    public static String substituteChars(int[] nums, String str){
        String res = "";
        for(int i = 0; i < str.length(); i++){
            if(isUpper(str.charAt(i))){
                res += nums[getCharIndex(str.charAt(i))];
            }else{
                res += str.charAt(i);
            }
        }
        return res;
    }

    public static String process(String str){
        String res = "";
        String buffer = "";
        boolean startedExp = false;
        if(str.length()-countSpaces(str)==1){
            return str;
        }
        for(int i = 0; i < str.length(); i++){
            buffer+=str.charAt(i);
            if(str.charAt(i) == '('){
                startedExp = true;
            }
            if(startedExp && isLower(str.charAt(i))){
                res += buffer.substring(0, buffer.length()-1);
                buffer = "";
                buffer+=str.charAt(i);
                startedExp = false;
            }
            if(startedExp && str.charAt(i) == ')'){
                res += operation(getNums(buffer), getOperation(buffer));
                buffer = "";
                startedExp = false;
            }
            if(!startedExp && str.charAt(i)==','){
                res += buffer;
                buffer = "";
            }
        }
        res += buffer;
        return process(res);
    }

    public static void readLine(){
        int nNums = sc.nextInt();
        
        if(nNums == 0){
            return;
        }
        int[] nums = new int[nNums];
        for(int i = 0; i < nNums; i++){
            nums[i] = sc.nextInt();
        }
        String exp = sc.nextLine();
        exp = substituteChars(nums, exp);
        exp = process(exp);
        System.out.println(exp.charAt(0));
        readLine();
    }

    public static void main(String[] args) {
        readLine();
    }
}