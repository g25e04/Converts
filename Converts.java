import java.util.Scanner;
import java.util.Stack;

public class Converts{
        public static void main(String arg[]){
        Scanner choice = new Scanner(System.in);
        System.out.println("(1)converts(2)get value");
        int choice1 = choice.nextInt();
        if(choice1 == 1)//converts
        {
            Scanner lines = new Scanner(System.in);
            System.out.println("lines?");
            String Fixlines = lines.nextLine();
            char[] fix = new char[Fixlines.length() + 1];// build an array to put the orginal lines.
            for(int k = 0; k < Fixlines.length() ; k++)//put in array
            {
                fix[k] = Fixlines.charAt(k);
            }
            Fixs(fix);
            //lines.close();
        }

        else if(choice1 == 2)
        {
            Scanner lines = new Scanner(System.in);//value
            System.out.println("lines?");
            String  Fixlines = lines.nextLine();
            char[] line = new char[Fixlines.length() + 1];// build an array to put the orginal lines.
            for(int l = 0; l <= Fixlines.length() ; l++)//put in array
            {
                line[l] = Fixlines.charAt(l);
            }
            Values(line);
            lines.close();
        }

        else 
        {
            System.out.println("invalid");
        }
        choice.close();
    }

    public static void Fixs(char[] lines)
    {
        System.out.println("(1)prefix(2)postfix");
        Scanner choices = new Scanner(System.in);
        int choice2 = choices.nextInt();
        if(choice2 == 1)
        {
            int cp = 0;//current priority
            int sp = 0;//stack priority
            Stack Stacks = new Stack();
            Stack out = new Stack();
            char temp;
            for(int ii = lines.length-2 ; ii >= 0 ; ii--)
            {
                switch(lines[ii]){
                    case'(':
                    temp = (char)Stacks.pop();
                        while(temp != ')')
                         {
                            out.push(temp);
                            if(Stacks.empty() == false)
                            temp = (char)Stacks.pop(); 
                         }
                        case ')':
                        case '*':
                        case '/':
                        case '+':
                        case '-':
                        cp = getpostcp(lines[ii]);
                        if(Stacks.empty() == true)
                            {
                                Stacks.push(lines[ii]);
                            }
                            else 
                            {
                                temp = (char)Stacks.pop();
                                sp = getpostsp(temp);
                                if(cp >= sp)
                                {
                                    Stacks.push(temp);
                                    Stacks.push(lines[ii]);
                                }
                                else 
                                {
                                    while(cp < getpostsp(temp)&& Stacks.empty() == false)
                                    {
                                        out.push(temp);
                                        if(Stacks.empty() == false)
                                        temp = (char)Stacks.pop();
                                    }
                                    Stacks.push(lines[ii]);
                                }
                            }
                            break;
                        default:    
                                out.push(lines[ii]);
                    }
            }
            while(Stacks.empty() == false)
            {
                temp = (char)Stacks.pop();
                out.push(temp);
            }
            while(out.empty() == false)
            {
                temp = (char)out.pop();
                System.out.print(temp);
            }
        }

        else if(choice2 == 2)//postfix
        {
            int cp = 0;//current priority
            int sp = 0;//stack priority
            Stack Stacks = new Stack();
            char temp;
            for(int j = 0 ; j < lines.length-1 ; j++)
            {
                
                switch(lines[j]){
                    case')':
                    temp = (char)Stacks.pop();
                        while(temp != '(')
                        {
                            System.out.print(temp);
                            if(Stacks.empty() == false)
                            temp = (char)Stacks.pop(); 
                        }
                        break;

                        case '(':
                        case '*':
                        case '/':
                        case '+':
                        case '-':
                        cp = getpostcp(lines[j]);
                        if(Stacks.empty() == true)
                        {
                            Stacks.push(lines[j]);
                            //System.out.print(" ");
                        }
                        else 
                        {
                            temp = (char)Stacks.pop();
                            sp = getpostsp(temp);
                            if(cp > sp)
                            {
                                Stacks.push(temp);
                                Stacks.push(lines[j]);
                                System.out.print(" ");
                            }
                            else 
              
                            {
                                while(cp <= getpostsp(temp) && Stacks.empty() == false);
                                    {
                                        System.out.print(temp);
                                        if(Stacks.empty() == false)
                                        temp = (char)Stacks.pop();
                                    } 
                                    Stacks.push(lines[j]) ;
                            }
                    }
                        break;
                    default:    
                            System.out.print(lines[j]);
                }
            
            }
            while(Stacks.empty() == false)
                    {
                        temp = (char)Stacks.pop();
                        System.out.print(" " + temp);
                     }
        }
        choices.close();
    }

    public static void Values(char[] lines)
    {

    }

    public static int getpostcp(char x)//evaluate the priority
    {
    int icp = 0;
        switch(x){

            case'(':
                icp = 4;
                break;
            case'*':
            case'/':
                icp = 2;
                break;
            case'+':
            case'-':
                icp = 1;
                break;
            default:
                 break;    
        }return icp;
    }

    public static int getpostsp(char x)//evaluate the priority
    {
    int isp = 0;
        switch(x){

            case'(':
                isp = 0;
                break;
            case'*':
            case'/':
                isp = 2;
                break;
            case'+':
            case'-':
                isp = 1;
                break;
            default:
                break;
        }
                    return isp;
    }
}