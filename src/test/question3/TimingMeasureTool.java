/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.question3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author michaelzhang
 */
public class TimingMeasureTool {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String filepath;
        String outputFilepath;
        try {
            if(args.length > 0)
                filepath = TimingMeasureTool.class.getResource("/").getFile() + "test/question3/"+ args[0];
            else filepath = TimingMeasureTool.class.getResource("/").getFile() + "test/question3/MyTestClass";
            
            if(args.length > 1)
                outputFilepath = TimingMeasureTool.class.getResource("/").getFile() + "test/question3/"+ args[1];
            else outputFilepath = TimingMeasureTool.class.getResource("/").getFile() + "test/question3/testoutput.txt";
      
            File file = new File(filepath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
           
            File outfile = new File(outputFilepath);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outfile));
           
            String str = null;
            bufferedReader.readLine();//ignore first line comment
            str = bufferedReader.readLine();//read class name
            
            Class myClass;
            if(args.length == 0)
                myClass = Class.forName("test.question3.MyTestClass");
            else
                myClass = Class.forName(str);
            
            String className = myClass.getName();
            System.out.println(className);
            
            /*while((str=bufferedReader.readLine())!=null){
               
                 //bufferedWriter.write(str);
                 //bufferedWriter.newLine();//return
               
           }*/
           bufferedReader.close();
           //bufferedWriter.close();
            
            Constructor  constructor = myClass.getConstructor(int.class);
            MyTestClass myObject = (MyTestClass)constructor.newInstance(1);
            ArrayList al = new ArrayList();
            
            //Method [] methods = myClass.getMethods();
            Method [] methods = MyTestClass.class.getDeclaredMethods();
            for(Method m : methods){
                System.out.println("method = " + m.getName()+" "+m.getParameterCount()+" ");
                m.setAccessible(true);
                Class []  pType = m.getParameterTypes();
                int index=0;
                for(Class c: pType){
                    //System.out.println("para type " + c.getName());
                    if(c.getName().equalsIgnoreCase("int"))
                        al.add(index,1);                   
                    else if(c.getName().equalsIgnoreCase("long"))                 
                        al.add(index,1l);
                    else if(c.getName().equalsIgnoreCase("string"))
                        al.add(index,"a string");
                    index++;
                }
                
                int count = 0;
                count = m.getParameterCount();
                Object a[] = new Object[count];
                al.toArray(a);
                long start = System.currentTimeMillis();              
                if(1 == count)
                    m.invoke(myObject, a[0]);
                else if(2 == count)
                    m.invoke(myObject,1,1);
                else if(0 == count)
                    m.invoke(myObject, null);
                String str1 = "Method: " + "\""+ m.getName()+ "\" "+" cost time is " + (System.currentTimeMillis()-start) + " ms.";
                System.out.println(str1);
                bufferedWriter.write(str1);
                bufferedWriter.newLine();//return
            }
            bufferedWriter.close();
            
        }catch(FileNotFoundException e){
           System.out.println("File " + args[0] + " not exist !");          
        }
        catch(NoSuchMethodException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
