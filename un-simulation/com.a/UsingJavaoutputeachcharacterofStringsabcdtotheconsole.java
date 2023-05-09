// Using Java8, output each character of String s = "a,b,c,d" to the console
. Keep in mind that characters are always displayed in UTF-8 encoding, regardless of locale or character set.
public class Main{
    public static void main(String[] args){
        for(char c: "a,b,c,d".toCharArray()){
            System.out.println(c);
        }
    }
}