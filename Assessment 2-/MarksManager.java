
/**
 * Write a description of class MarksManager here.
 *
 * @author Jovin Joseph
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// this is the class for storing the information about each student//
class Student 
    {
        String Firstname;
        String Lastname;
        String id;
        double markNo1;
        double markNo2;
        double markNo3;
        double total;
        
        // this is a constructor to initialize the student data //
        
        public Student(String Firstname, String Lastname, String id, double markNo1, double markNo2, double markNo3)
        {
            this.Firstname=Firstname;
            this.Lastname=Lastname;
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
            return Firstname+ " "+Lastname+ "(" + id + ") - Marks: [" + markNo1 + ", " + markNo2 + ", " + markNo3 + "] Total: " + total;
    
        }
        public double getTotalMarks()
        {
            return total;
        }
    }
    
    //now we move on to the main class to manage students marks
public class MarksManager
{
    private List<Student>students=new ArrayList<>();
    // this is how we read student data from a fie
     public void readFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.startsWith("#") || lineNumber == 1 || lineNumber == 2) continue; 
                // skip comment lines and first two lines because else it is gonna show error due to alphabets

                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    try {
                        // part where we extract the student data from the line
                        String Firstname = parts[0].trim();
                        String Lastname = parts[1].trim();

                        String id = parts[2].trim();

                        // Handle missing or invalid marks
                        double markNo1 = parts.length > 3 ? parseDoubleWithDefault(parts[3].trim(),0.0):0.0;
                        double markNo2 = parts.length > 4 ? parseDoubleWithDefault(parts[4].trim(),0.0):0.0;
                        double markNo3 = parts.length > 5 ? parseDoubleWithDefault(parts[5].trim(),0.0):0.0;

                        // now we add student into the list
                        students.add(new Student(Firstname,Lastname, id, markNo1, markNo2, markNo3));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double parseDoubleWithDefault(String str, double defaultValue) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            // Handle missing or invalid mark by returning 0 or any default value
            return defaultValue;
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
    public void filterStudentByThreshold(double threshold)
    {
        for(Student student:students)
        {
          if(student.getTotalMarks()<threshold)
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
        for(int i= 0; i<n-1; i++)
        {
            for(int j=0; j<n-i-1; j++)
            {
                if(students.get(j).getTotalMarks()>students.get(j+1).getTotalMarks())
                {
                    //here we swap the  students[j]and students[j+1]
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Lowest marks TOP 5:");
        for(int i=0; i<5 && i<students.size(); i++)
        {
            System.out.println(students.get(i));
        
        }
        System.out.println("Highest marks Top 5:");
        for(int i=students.size()- 1; i>=students.size()-5 && i>=0; i--)
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
                    //here we print all the student marks
                    break;
                
                case 2:
                    System.out.print("Enter the threshold:");
                    double threshold=scanner.nextDouble();
                    filterStudentByThreshold(threshold);
                    break;
                
                case 3:
                   print5highestandlowest();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid Choice. Try again.");
                    
            }
        }
    }
    // Next is the main method to run the program
    
    public static void main(String[] args)
    {
        MarksManager manager=new MarksManager(); 
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the filename: ");
        String fileName=scanner.nextLine();
        //reading the filename from user input
        manager.readFromFile(fileName);
        manager.displayMenu();
    }
}