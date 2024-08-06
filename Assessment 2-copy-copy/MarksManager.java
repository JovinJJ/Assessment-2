
/**
 * Write a description of class MarksManager here.
 *
 * @author Jovin Joseph
 * @version (a version number or a date)
 */

import java.io.*; 
import java.util.*;
/* this is the class for storing the information about each student*/
class Student 
    {
        String name;
        String id;
        int markNo1;
        int markNo2;
        int markNo3;
        int total;
        
        // this is a constructor to initialize the student data //
        
        public Student(String name, String id, int markNo1, int markNo2, int markNo3)
        {
            this.name=name;
            this.id=id;
            this.markNo1=markNo1;
            this.markNo2=markNo2;
            this.markNo3=markNo3;
            this.total=markNo1+markNo2+markNo3; 
            
            //here we calculate the total marks//
        }
        
        @Override
        public String toString()
        {
            return name+"("+id=+")-Marks:["+mark1+","+mark2+","+mark3+"]Total:"+total;
        }
    }
    
    //now we move on to the main class to manage students marks
public class MarksManager
{
    private List<Student>students=new ArrayList<>();
    // this is how we read student data from a fie
    public void readFromFile(String fileName)
    {
        try (BufferReader br=new BufferReader(new FileReader(fileName)))
        {
            String line;
            while ((line=br.readLine()) !=null)
            {
                if(line.startsWith("#"))continue;
                //inorder to ignore the comment lines
                String[]parts=line.split(",");
                if(parts.length==5)
                //part where we extract the student data from the line
                {
                    String name= parts[0];
                    String id = parts[1];
                    int markNo1= Integer.parseInt(parts[2]);
                    int markNo2= Integer.parseInt(parts[3]);
                    int markNo3= Integer.parseInt(parts[4]);
                    //now we add student into the list
                    students.add(new Student(name,id,markNo1,markNo2,markNo3));
                }
            } 
            
        }
        catch(IOException e)
            {
                e.printStackTrace();
            }
            
        
    }

    
    //next is the method to print each and every student details
    
    
    