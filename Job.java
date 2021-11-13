
/***
 * Class to model the entity Job which implements the Comparable interface
 * @author Udita Agarwal
 * @version 0.1
 * Date of creation: October 18, 2021
 * Last Date Modified: October 21, 2021
 */


import java.util.Comparator; // to use the Comparable interface


public class Job implements Comparable<Job>{
    // data members 
    private int id;
    private int group;
    private long size; 

     /***
	 * Default constructor
	 * @param id which has the job id
     * @param group which has the job group
     * @param size which has the job size 
	 * Initializes data members to the values passed 
	 */
    public Job(int id, int group, long size){
        this.id = id;
        this.group = group;
        this.size = size;
    }

    /***
 	 * Getter for the job id
 	 * @param no parameters
 	 * @return the value of the data member id
 	 */
    public int getId(){
        return id;
    }

    /***
 	 * Getter for the job group
 	 * @param no parameters
 	 * @return the value of the data member group
 	 */
    public int getGroup(){
        return group;
    }

    /***
 	 * Getter for the job size
 	 * @param no parameters
 	 * @return the value of the data member size
 	 */
    public long getSize(){
        return size;
    }

    /***
 	 * Prints information related to job in a formatted string with the id, group, and size 
     * Converts the size of printing jobs in bytes/KB/MB/GB
 	 * no parameters
 	 * @return formatted string with relevant information
 	 */
    public String toString(){
        String output = String.format("%-10d\t%-10d\t", id, group);
        double convertedSize = size;
        if(convertedSize > 1000000000){ // converts in GB
            convertedSize = convertedSize / 1000000000;
            output += String.format("%10.1fGB", convertedSize);
        } else if(convertedSize > 1000000){ // converts in MB
            convertedSize = convertedSize / 1000000;
            output += String.format("%10.1fMB", convertedSize);
        } else if(convertedSize > 1000){ // converts in KB
            convertedSize = convertedSize / 1000;
            output += String.format("%10.1fKB", convertedSize);
        } else{ // in bytes
            output += String.format("%-10d bytes", convertedSize);
        }
        return output;
    }
    
    
    /***
 	 * Compares this instance of job to the job passed on basis of its group and returns whether it's greater or smaller or equal to it
 	 * @param j the job this instance needs to be compared to
 	 * @return 0 if they're equal, less than 0 if its less than the job object being passed and more than 0 if its greater than the job object being passed
 	 */    
    public int compareTo(Job j){
        Integer i1 = this.group;
        Integer i2 = j.group;
        return i1.compareTo(i2);
    }    
}
