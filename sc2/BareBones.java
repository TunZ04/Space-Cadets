import java.util.*;
import java.io.*;
public class BareBones
{
    public static void interpret(String line)
    {
        Map hm = new HashMap();    //create a hashmap to dynamically deal with variables
        StringTokenizer st = new StringTokenizer(line, ";");    //creates a string tokenizer to separate commands (this allows for more than one statement per line)
        String currstatement;
        while(st.hasMoreTokens())
        {
            currstatement = st.nextToken();
            StringTokenizer word = new StringTokenizer(currstatement, " ");    //splits up the individual command into constituent words
            String cmmdword = word.nextToken();
            String varname;
            if ( word.hasMoreTokens() )
            {
                varname = word.nextToken();
                int currvar = (hm.get(varname) == null) ? 0 : Integer.parseInt((String) hm.get(varname));   //sets an uninitialised variable to 0
                switch(cmmdword)
                {
                    case "clear":
                        hm.put(varname, 0);    //puts the value 0 into the hashmap with the key of the name of the variable
                        break;
                    case "incr":
                        hm.put(varname, currvar++);
                        break;
                    case "decr":
                        hm.put(varname, currvar--);
                        break;
                    case "while":
                        wloop(currstatement);
                        break;
                    default:
                        System.out.println("Invalid Statement");
                }

            }
        }
        System.out.println(hm); //output the list of variables with their values after each command
    }
    public static void wloop(String statement)
    {

    }

    public static void main(String[] args) throws Exception
    {
        File file = new File("input.bb");
        BufferedReader br = new BufferedReader(new FileReader(file));

        System.out.println("Welcome to the Bare Bones interpreter.");
        System.out.println("There are 3 commands available, clear, incr & decr.");
        System.out.println("Each are followed by the name of an integer variable. Clear sets the integer to 0");
        System.out.println("If you incr or decr a variable which hasn't been created, it will be 1 or -1 respectively");
        System.out.println("Additionally, the control sequence while x not y do; accompanied by end; can be used");
        System.out.println("I will first interpret the file input.bb, then you can type in additional commands\n");

        //interpret every line from the input file in turn
        String str;
        while( (str = br.readLine()) != null )
        {
            System.out.println(str);
            interpret(str);
        }

        //after interpreting all the lines in the file, the user can continue to type line-by-line
        Scanner s = new Scanner(System.in);
        String inp;
        while( !(inp = s.nextLine()).equals("exit") )
        {
            interpret(inp);
        }

        System.exit(0); //closes the program when "exit" is typed into the console

    }
}
