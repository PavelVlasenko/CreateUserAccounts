import java.io.*;
import java.util.TreeSet;

public class CreateUserAccouts
{
    private static TreeSet<Student> students;


    public static void main(String[] args) {
        //read file
        File file = new File("/home/pvlasenko/Documets/Upwork/test.txt");
        BufferedReader br = null;

        try
        {
            br = new BufferedReader(new FileReader(file));

            String str;
            Student student = new Student();
            int rowCount = 0;

            while((str = br.readLine()) != null)
            {
                if(rowCount == 5)
                {
                    student = new Student();
                }
                switch (rowCount)
                {
                    case 0: student.studentNumber = str; break;
                    case 1: String[] arrayNames = str.split(", ");
                            student.familyName = arrayNames[0];
                            student.givenNames = arrayNames[1];
                            break;
                    case 4: student.birthday = str.split(" ");
                    default: rowCount++;

                }


            }
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

}

class Student implements Comparable<Student>
{
    String familyName;
    String givenNames;
    String studentNumber;
    String [] birthday;
    String userName;
    String password;

    public void calculateUsernamePassword()
    {
        //calculateUsername

    }

    @Override
    public int compareTo(Student o)
    {
        int i = familyName.compareTo(o.familyName);
        if (i != 0) return i;

        i = givenNames.compareTo(o.givenNames);
        return i;
    }

    @Override
    public String toString()
    {
        return "asdaasdasd";
    }
}