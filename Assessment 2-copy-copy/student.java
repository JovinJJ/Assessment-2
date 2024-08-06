
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
            this.total=markNo1+markNo2+markNo3; //here we calculate the total marks//
        }
        
        
public class MarksManager
{