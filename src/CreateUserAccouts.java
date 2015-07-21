import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class CreateUserAccouts
{
    private static Set<Student> studentsSet = new TreeSet<Student>();

    public static void main(String[] args)
    {
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
                    case 4: student.birthday = str.split(" ");
                            student.calculateUsername();
                            student.calculatePassword();
                            studentsSet.add(student);
                            break;
                }
                rowCount++;
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

        //find equals usernames
        Iterator<Student> it = studentsSet.iterator();
        Student currentStudent = it.next();
        String currentUserName = currentStudent.userName;
        int repeatCount = 0;
        while(it.hasNext())
        {
            Student nextStudent = it.next();
            if(currentUserName.equals(nextStudent.userName))
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
                currentUserName = nextStudent.userName;
                repeatCount = 0;
            }
        }
        System.out.println(studentsSet);
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

    String initialsPart;
    String familyPart;

    public void calculatePassword()
    {
        String month = birthday[1].length() > 3 ? birthday[1].substring(0, 3) : birthday[1];
        password =  month + birthday[0] + studentNumber.substring(4);
    }

    public void calculateUsername()
    {
        familyPart = familyName.replace(" ", "").toLowerCase();
        String [] initialsArray = givenNames.toLowerCase().split(" ");
        StringBuffer initials = new StringBuffer();
        for (String st : initialsArray)
        {
            initials.append(st.charAt(0));
        }
        initialsPart = initials.toString();
        userName = initialsPart + familyPart;
    }

    public void renameUserNameWithNumber(int num)
    {
        userName = initialsPart + num + familyPart;
    }

    @Override
    public int compareTo(Student o)
    {
        int i = familyName.compareTo(o.familyName);
        if (i != 0) return i;

        i = givenNames.compareTo(o.givenNames);
        return i;
    }
}