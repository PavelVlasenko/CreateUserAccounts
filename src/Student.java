public class Student implements Comparable<Student>
{
    private String familyName;
    private String givenNames;
    private String studentNumber;
    private String [] birthday;
    private String userName;
    private String password;

    private String initialsPart;
    private String familyPart;

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
        if (i != 0) return i;

        i = studentNumber.compareTo(o.studentNumber);
        return i;
    }

    public String getFamilyName()
    {
        return familyName;
    }

    public void setFamilyName(String familyName)
    {
        this.familyName = familyName;
    }

    public String getGivenNames()
    {
        return givenNames;
    }

    public void setGivenNames(String givenNames)
    {
        this.givenNames = givenNames;
    }

    public String getStudentNumber()
    {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber)
    {
        this.studentNumber = studentNumber;
    }

    public String[] getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String[] birthday)
    {
        this.birthday = birthday;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getInitialsPart()
    {
        return initialsPart;
    }

    public void setInitialsPart(String initialsPart)
    {
        this.initialsPart = initialsPart;
    }

    public String getFamilyPart()
    {
        return familyPart;
    }

    public void setFamilyPart(String familyPart)
    {
        this.familyPart = familyPart;
    }
}
