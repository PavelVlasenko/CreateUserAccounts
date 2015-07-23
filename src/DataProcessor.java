import java.io.*;
import java.util.*;

/**
 * Parse input file and write data to the output file.
 * Resolve equals usernames.
 *
 */
public class DataProcessor
{
    File inputFile;
    File outputFile;
    private Set<Student> studentsSet = new TreeSet<Student>();

    public DataProcessor(String inputFilePath, String outputFilePath)
    {
        inputFile = new File(inputFilePath);
        outputFile = new File(outputFilePath);
    }

    public void readFile()
    {
        BufferedReader br = null;

        try
        {
            System.out.println("...Start parsing input file");
            br = new BufferedReader(new FileReader(inputFile));

            String str;
            Student student;

            while((str = br.readLine()) != null)
            {
                LinkedList<String> rows = new LinkedList<String>();
                student = new Student();

                while (str !=null && !str.equals(""))
                {
                    rows.add(str);
                    str = br.readLine();
                }

                student.setStudentNumber(rows.get(0));
                String[] arrayNames = rows.get(1).split(", ");
                student.setFamilyName(arrayNames[0].trim());
                student.setGivenNames(arrayNames[1].trim());
                student.setBirthday(rows.getLast().split(" "));

                student.calculateUsername();
                student.calculatePassword();
                studentsSet.add(student);
            }

            System.out.println(studentsSet.size() + " Students objects was created");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeDataToFile()
    {
        System.out.println("...Start write to output file");
        PrintWriter out = null;
        try
        {
            if(!outputFile.exists())
            {
                outputFile.createNewFile();
            }
            out = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(outputFile)), "UTF-8"));

            Iterator<Student> iter = studentsSet.iterator();
            while(iter.hasNext())
            {
                Student student = iter.next();
                out.println("Family Name: " + student.getFamilyName());
                out.println("Given names: " + student.getGivenNames());
                out.println("Student number: " + student.getStudentNumber());
                out.println("Username: " + student.getUserName());
                out.println("Password: " + student.getPassword());
                if(iter.hasNext())
                {
                    //empty row
                    out.println("");
                }
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public void resolveEqualsNames()
    {
        Iterator<Student> it = studentsSet.iterator();
        Student currentStudent = it.next();
        String currentUserName = currentStudent.getUserName();
        int repeatCount = 0;
        while(it.hasNext())
        {
            Student nextStudent = it.next();
            if(currentUserName.equals(nextStudent.getUserName()))
            {
                if(repeatCount == 0)
                {
                    currentUserName = new String(currentUserName);
                    currentStudent.renameUserNameWithNumber(++repeatCount);
                }
                nextStudent.renameUserNameWithNumber(++repeatCount);
            }

            else
            {
                currentStudent = nextStudent;
                currentUserName = nextStudent.getUserName();
                repeatCount = 0;
            }
        }
    }
}
