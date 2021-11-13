/***
 * Class to model the Test class which contains the main method
 * @author Udita Agarwal
 * @version 0.1
 * Date of creation: October 18, 2021
 * Last Date Modified: October 20, 2021
 */

import java.util.Stack; // to use the Stack class 
import java.io.FileNotFoundException; // to throw file not found exception
import java.util.PriorityQueue; // to use the Priority Queue class
import java.util.Scanner; // to use the Scanner class
import java.io.File; // to use the File class


public class Test {

    public static void main(String[] args){
        // PART 1
        // creates a Stack of type Integer called postfixExpression
        Stack<Integer> postfixExpression = new Stack<>();
        Scanner keyboard = new Scanner(System.in);
        String userAns = "";

        do{
            // prompts user to enter an expression
            System.out.println("Enter a postfix expression");
            // accepts expression and stores it in a string
            String expression = keyboard.nextLine();
            // uses reg-ex to split the expression wherever there's a space and stores it in an array of Strings called tocken
            String[] tockens = expression.split(" ");

            for(int i = 0; i < tockens.length; i++){
                if(tockens[i].matches("\\d{1,}")){ // if the element in array tocken is an operand, then its parsed into an integer and pushed in the stack
                    postfixExpression.push(Integer.parseInt(tockens[i]));
                } else{ // if the element in the array is an operation, then the top 2 values of the stack are popped and the operation is performed 
                    int op1 = postfixExpression.pop();
                    int op2 = postfixExpression.pop();
                    // performs operation
                    switch(tockens[i]){
                        case "+":
                            postfixExpression.push(op2 + op1);
                            break;

                        case "-":
                            postfixExpression.push(op2 - op1);
                            break;

                        case "*":
                            postfixExpression.push(op2 * op1);
                            break;

                        case "/":
                            postfixExpression.push(op2 / op1);
                            break;
                    }
                }
            }

            int result = postfixExpression.pop();
            // if the stack is empty, the result is printed 
            if(postfixExpression.isEmpty()){
                System.out.println("Result: " + result);
            } else{ // prints the expression is malformed 
                System.out.println("Malformed expression");
            } 

            System.out.println("Do you want to evaluate another postfix expression? (yes/no):");
            userAns = keyboard.nextLine();
        } while(userAns.equals("yes"));


        // PART 2
        // creates a Priority Queue called jobs
        PriorityQueue<Job> jobs = new PriorityQueue<>();
        final double speed = 10000; // stores speed of jobs being printed
        // creates an instance of a file and reads information from it
        File file = new File("jobs.txt");
        try{
            Scanner readFile = new Scanner(file);
            // reads information from the file and stores it in the relevant data members
            while(readFile.hasNext()){
                int id = readFile.nextInt();
                int group = readFile.nextInt();
                long size = readFile.nextLong();
                // creates an instance of the job 
                Job j = new Job(id, group, size);
                // adds job to the priority queue 
                jobs.offer(j);
            }
            readFile.close();
        }
        // catches the file not found exception
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }

        int timeSecs = 0;
        System.out.printf("%-10s\t%-10s\t%-20s\t%-10s", "Job ID", "Job Group", "Size", "Completion Time");
        System.out.println();

        while(!jobs.isEmpty()){
            Job job = jobs.poll(); // removes head of the queue 
            long size = job.getSize(); // stores size of the job
            double time = size/speed; 
            String converted = convert(time); // calls method convert and passes time and stores it in the string converted
            System.out.printf("%-20s\t%-20s\t", job.toString(), converted); // displays the list of jobs in the order processed by the printer and their completion time
            System.out.println();
            
            // splits the converted time in tockens
            String[] tockenTime = converted.split(":");
            
            // converts the time taken in days, hours, mins, secs into seconds 
            for(int i = 0; i < tockenTime.length; i++){
                int convertedMin = Integer.parseInt(tockenTime[i]);
                if(i == 0){
                    timeSecs += convertedMin * 86400;
                } 
                if(i == 1){
                    timeSecs += convertedMin * 3600;
                }
                if(i == 2){
                    timeSecs += convertedMin * 60;
                }
                if(i == 3){
                    timeSecs += convertedMin;
                }
            }
        }
        // calls the method convert to convert the total time taken in seconds into days, hours, mins, secs
        System.out.println("Total printing time: " + convert(timeSecs));
    }

    /***
 	 * converts the time in seconds into time represented in days, hours, mins, secs
 	 * @param time which contains the time in seconds 
 	 * @return a string with the converted time in days, hours, mins, secs
 	 */
    public static String convert(double time){
        String convertedTime = "";
        int days = 00;
        int hours = 00;
        int mins = 00;
        int secs = 00;
       
        String daysString = "00";
        String hoursString = "00";
        String minsString = "00";
        String secsString = "00";
        
        // converts to days
        if(time > 86400){
            days = (int) (time/86400);
            daysString = String.valueOf(days);
            time = time % 86400;
        } 
        // converts to hours
        if(time > 3600){
            hours = (int)(time/ 3600);
            hoursString = String.valueOf(hours);
            time = time % 3600;
        }
        // converts to mins
        if(time > 60){
            mins = (int)(time/60);
            minsString = String.valueOf(mins);
            time = (int)time % 60;
        } 
        secs = (int)Math.round(time);
        secsString = String.valueOf(secs);

        // adds a 0 in front if the length of the string is less than 1
        if(daysString.length() == 1){
            daysString = "0" + daysString;
        }
        if(hoursString.length() == 1){
            hoursString = "0" + hoursString;
        }
        if(minsString.length() == 1){
            minsString = "0" + minsString;
        }
        if(secsString.length() == 1){
            secsString = "0" + secsString;
        }
        convertedTime = daysString + ":" + hoursString + ":" + minsString + ":" + secsString;
        return convertedTime;
    }
    
}
