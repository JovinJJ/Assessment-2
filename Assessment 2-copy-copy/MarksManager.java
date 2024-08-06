
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
    public void printStudentMarks()
    {
        for(Student student:students)
        {
            System.out.println(student);
        
        }
    }
    
    //To filter and print students whos marks are below a threshold we do this://
    public void filterStudentByThreshold(int threshold)
    {
        for(Student student:students)
        {
          if(student.total<threshold)
          {
            System.out.println(student);
          }
        }
    }
    
    // here we sort the students by total marks using the bubble sort method and print the top 5 highest and lowest mark
    public void print5highestandlowest()
    {
        //using the bubble sort 
        int n=students.size();
        for(int i= 0 ;i< n-1;i++)
        {
            for(int j=0;j<n-i-1;j++)
            {
                if(students.get(j).total>students.get(j+1).total)
                {
                    //here we swap the  students[j]and students[j+1]
                }
            }
        }
        System.out.println("Lowest marks TOP 5:");
        for(int i=0;i<5 && i<students.size(); i++)
        {
            System.out.println(students.get(i));
        
        }
        System.out.println("Highest marks Top 5:");
        for(int i=students.size()- 1; i>=students.size()-5 && i>=0;i--)
        {
            System.out.println(students.get(i));
        }
    }
    //now we display the menu for the interaction
    public void displayMenu()
    {
        Scanner scanner=new Scanner(System.in);
        while(true)
        {
            System.out.println("\nMenu:");
            System.out.println("1. Print the Student Marks");
            System.out.println("2. Filter the students by threshold");
            System.out.println("3. Print Top 5 Highest and Lowest Marks:");
            System.out.println("4. exit:");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                    printStudentMarks();
                    
            }
            }
        }
    }