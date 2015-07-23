import java.io.*;
import java.util.Date;

public class CreateUserAccouts
{
    public static void main(String[] args)
    {
        //read inputFile
        if(args.length < 2)
        {
            System.out.println("Missing input file and output file");
            System.exit(0);
        }

        Date startDate = new Date();

        DataProcessor dataProcessor = new DataProcessor(args[0], args[1]);
        dataProcessor.readFile();
        dataProcessor.resolveEqualsNames();
        dataProcessor.writeDataToFile();

        Date endDate = new Date();
        long time = endDate.getTime() - startDate.getTime();
        System.out.println("Calculation takes " + time + " ms");
    }
}

