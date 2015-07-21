import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class CreateUserAccouts
{
    private static Set<Student> studentsSet = new TreeSet<Student>();

    public static void main(String[] args) {
        //read file
        File file = new File("/home/pvlasenko/Documents/Upwork/test.txt");
        BufferedReader br = null;

        try
        {
            br = new BufferedReader(new FileReader(file));

            String str;
            Student student = new Student();
            int rowCount = 0;

            while((str = br.readLine()) != null)
            {
                if(rowCount == 6)
                {
                    studentsSet.add(student);
                    student = new Student();
                    rowCount = 0;
                }
                switch (rowCount)
                {
                    case 0: student.studentNumber = str; break;
                    case 1: String[] arrayNames = str.split(", ");
                            student.familyName = arrayNames[0].trim();
                            student.givenNames = arrayNames[1].trim();
                            break;
                    case 4: student.birthday = str.split(" ");break;
                }
                rowCount++;
            }
            studentsSet.add(student);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        for (Student st : studentsSet)
        {
            st.calculatePassword();
        }
    }

    private static void calculateUserName(TreeSet<Student> set)
    {
        username
        for()

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

    public void calculatePassword()
    {
        String month = birthday[1].length() > 3 ? birthday[1].substring(0, 3) : birthday[1];
        password =  month + birthday[0] + studentNumber.substring(4);
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