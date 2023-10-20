import java.util.*;
import java.io.*;

public class BareBones
{
    private static ArrayList<String> FILESTRINGS = new ArrayList<>();
    private static Map<String, Integer> VARS = new HashMap<>();
    private static Stack<Integer> PTRSTACK = new Stack();
    private static Stack<String> VARNAMESTACK = new Stack();
    //I really should make a data structure to combine both stacks. I didn't want to make a new class though.
    //I could then easily add a compare value, so it doesn't have to be 0

    private static String cmmdword;
    private static String varname;


    public static void main(String[] args) throws IOException
    {
        BufferedReader b = new BufferedReader(new FileReader("input.bb"));
        Scanner s = new Scanner(System.in);

        //this adds all the commands (separated by ';') to the FILESTRINGS array
        String file = "";
        String line = ""; while ( (line = b.readLine()) != null) { file += line; }
        StringTokenizer statements = new StringTokenizer(file, ";");
        while (statements.hasMoreTokens()) { FILESTRINGS.add(statements.nextToken()); }

        System.out.println("*************************************\nWelcome to the BareBones interpreter\n" +
                "*************************************\n");

        String input = "";
        int cmmdptr = 0;
        while (!input.equals("exit"))
        {
            //this enables the user to input their own code in console after the file has been exhausted
            if (cmmdptr == FILESTRINGS.size()){
                input = s.nextLine();
                FILESTRINGS.add(input);  //note, user must input 1 command per line without ';' when using console
            }
            StringTokenizer command = new StringTokenizer(FILESTRINGS.get(cmmdptr), " ");
            try { cmmdword = command.nextToken(); }
            catch(Exception e) { System.out.println("Error: no command word"); }
            try { varname = command.nextToken(); }
            catch(Exception e) { if (cmmdword.equals("exit")) System.out.println("Error: no variable name"); }

            int currvar = (VARS.get(varname) == null) ? 0 : VARS.get(varname);
            switch (cmmdword){
                case "clear" -> VARS.put(varname, 0);
                case "incr" -> VARS.put(varname, currvar + 1);
                case "decr" -> VARS.put(varname, currvar - 1);
                case "while" ->{
                    PTRSTACK.push(cmmdptr);
                    VARNAMESTACK.push(varname);
                }
                case "end" ->
                {
                    if (VARS.get(VARNAMESTACK.peek()) == 0){
                        PTRSTACK.pop();
                        VARNAMESTACK.pop();
                    } else { cmmdptr = PTRSTACK.peek(); }
                }
                case "exit" -> {}
                default -> System.out.println("Invalid Command");
            }
            System.out.println(FILESTRINGS.get(cmmdptr));
            if (!cmmdword.equals("end") & !cmmdword.equals("while")){ System.out.println(VARS + "\n"); }
            cmmdptr += 1;
        }
    }
}
