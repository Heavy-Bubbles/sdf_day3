package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        // read string/content from argument passed in when running the app
        // eg. java sg.edu.nus.iss/App arg[0], arg[1], arg[2]
        // eg. java sg.edu.nus.iss/App "c:\data" myfile.txt
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("Argument" + i +": " + args[i]);
            }
        } else {
            System.out.println("You have not passed in any arguments!");
        }
        

        String dirPath = args [0];
        String fileName = args [1];
        String dirPathFileName = dirPath + File.separator + fileName;

        // use file object to check if directory exists
        // create the directory if it doesnt exist
        // Slide 3
        File newDirectory = new File(dirPath);
        if (newDirectory.exists()) {
            System.out.println("Directory " + dirPath + " already exists!");
        } else {
            newDirectory.mkdir();
    }
        // check whether a file exists in a directory
        // if it doesn't create a file
        File fileData = new File(dirPathFileName);

        if (fileData.exists()) {
            System.out.println(dirPathFileName + "already exists!");
        } else {
                fileData.createNewFile();
        }

        String content = "I want to go home and sleep";
        String content2 = "Morpeko Hangry";

        FileWriter fileWriter = new FileWriter(dirPathFileName, true);
        fileWriter.write(content);
        fileWriter.write("\n");
        fileWriter.write(content2);
        fileWriter.flush();
        fileWriter.close();

        // another example - a type of decorator pattern
        String content3 = "lazy day";
        FileWriter fw2 = new FileWriter(dirPathFileName, true);
        BufferedWriter bw = new BufferedWriter(fw2);
        bw.append(content3);

        bw.flush();
        bw.close();
        fw2.close();
        // close in reversed order

        //anothere example
        String content4 = "hi";
        FileOutputStream fos = new FileOutputStream(dirPathFileName, true);
        byte[] byteContent = content4.getBytes();
        fos.write(byteContent);
        fos.flush();
        fos.close();

        //another example - with decoration pattern
        String content5 = "no";
        FileOutputStream fos2 = new FileOutputStream(dirPathFileName, true);
        DataOutputStream dos2 = new DataOutputStream(fos2);
        dos2.writeBytes(content5);
        dos2.flush();
        dos2.close();
        fos2.close(); 

        //reading from file
        File file2 = new File(dirPath + File.separator + fileName);
        FileReader fr = new FileReader(file2);
        int dataRead = fr.read();
        while (dataRead!= -1) {
            System.out.print((char) dataRead);
            dataRead = fr.read();
        }
        fr.close();

        // another example - using bufferedReader
        FileReader fr2 = new FileReader(file2);
        BufferedReader br = new BufferedReader(fr2);
        //reference a file reader
        String line = "";
        line = br.readLine();

        while (line !=null){
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
        fr2.close();

        // another example - FileInputStream and DataInputStream - a type of decorator
        FileInputStream fis = new FileInputStream(file2);
        DataInputStream dis = new DataInputStream(fis);
        //String result = dis.readUTF();
        //System.out.println(result);
        int disData = dis.read();

        // != -1 is while it is not end of file
        while (disData != -1) {
            System.out.print((char) disData);
            
            disData = dis.read();
        }
        dis.close();
        fis.close();
    }

}
