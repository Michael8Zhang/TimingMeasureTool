/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.question3;

/**
 *
 * @author michaelzhang
 */
public class MyTestClass {
    public int count;
    public MyTestClass(int st)
    {
        count = st;
    }
    
    public void increase(int st)
    {
        count = count + st;
        //System.out.println("count:"+count);
    }
    
    private void pri_increase(int st, int st1)
    {
        count = count + st + st1;
        //System.out.println("private count: " + count);
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
