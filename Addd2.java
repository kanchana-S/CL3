package add;

import java.util.Scanner;

public class Addd2 {
    private String number1,number2,s;
    
    Integer n1,n2;
    public void Addd2() {
number1=null;
number2=null;
     
       }
    public String getS() {
        Integer c1;
        Integer c = multiply(n1, n2);
   if((n1>0 && n2<0) || (n1<0 && n2>0))
     
        
     //c1=c;
        s=String.valueOf(-c);
   else
        s=String.valueOf(c);
        n1=0;
        n2=0;
        return s;
        
    }
   
     public int multiply(int n1, int n2)

    {

        
        int[] m = binary(n1,4);
        
        int[] m1 = binary(-n1,4);

        int[] r = binary(n2,4);        

        int[] A = new int[11];

        int[] S = new int[11];

        int[] P = new int[11];        

        for (int i = 0; i < 5; i++)

        {

            A[i] = m[i];

            S[i] = m1[i];

            P[i + 5] = r[i];

        }

        display(A, 'A');

        display(S, 'S');

        display(P, 'P');        

        System.out.println();

 

        for (int i = 0; i < 5; i++)

        {

            if (P[9] == 0 && P[10] == 0);

                // do nothing            

            else if (P[9] == 1 && P[10] == 0)

                add(P, S);                            

            else if (P[9] == 0 && P[10] == 1)

                add(P, A);            

            else if (P[9] == 1 && P[10] == 1);

                // do nothing

 

            rightShift(P);

            display(P, 'P');
            

        }
        if(P[0]==1 && (n2>0 && n2!=16))
            {
                int sum=getDecimal(P,10);
                P=binary(-(sum),10);
                if(P[0]==1)
                {
                    P[0]=0;
                }
                
                 display(P,'P');
                
            }
        else if(P[0]==1 && (n2<0 || n2==16) )
        {
            int sum=getDecimal(P,9);
            
            P=binary(-(sum),9);
            display(P,'P');
        }
        
        
return getDecimal(P,9);
    }

    /** Function to get Decimal equivalent of P **/

    public int getDecimal(int[] B,int loop)

    {
        
        double sum=0;
        double j=0;
        
        {
        
        for(int i=loop;i>=0;i--)
        {
            if(B[i]==1)
            {
                sum=sum+(Math.pow(2, j));
                
            }
            else
            {
                //
            }
            j=j+1;
        }
       
        }
        
        
       
return (int)sum;
    }

    /** Function to right shift array **/

    public void rightShift(int[] A)

    {        

        for (int i = 10; i >= 1; i--)

            A[i] = A[i - 1];        

    }

  
    public void add(int[] A, int[] B)
    {
        int cf =0;
        for(int i=4; i>=0; i--)
        {
            if(A[i]==0 && B[i] ==0)
            {
                if(cf==0)
                A[i]=0;
                else
                {
                    A[i]=1;
                    cf=0;
                }
            }
            else if((A[i]==1 && B[i] ==0) || (A[i]==0 && B[i] ==1))
            {
                if(cf==0)
                    A[i]=1;
                else
                {
                    A[i]=0;
                    cf=1;
                }
            }
            
            else if(A[i]==1 && B[i] ==1)
            {
                if(cf==0)
                {
                    A[i]=0;
                    cf=1;
                }
                else
                {
                    A[i]=1;
                    cf=1;
                }
                
            }
        }
        
    } 
   
public int[] binary(int number,int loop)
{
    int[] bin=new int[11];
    
    int num1,num2,i=0,cf=0,j=0,k=0,m,n;
    
    num2=number;
    
    i=loop;
    while(number!=0)
    {
        bin[i]=Math.abs(number%2);
    number=number/2;
    i=i-1;
    }
    
    while(i>=0)
    {
        bin[i]=0;
        i=i-1;
    }
    
    System.out.println("");
    
   
    
    if(num2<0)
    {
        for(i=0;i<(loop+1);i++)
        {
            if(bin[i]==1)
                bin[i]=0;
            else
                bin[i]=1;
        }
        
        cf=1;
        
        for(i=loop;i>=0;i--)
        {
            if(bin[i]==1 && cf==1)
            {
                cf=1;
                bin[i]=0;
            }
            else if(cf==1)
            {
                bin[i]=1;
                cf=0;
                break;
            }
        }
    }
    
    
    
    return bin;
}

    /** Function to print array **/

    public void display(int[] P, char ch)

    { 

        System.out.print("\n"+ ch +" : ");

        for (int i = 0; i < P.length; i++)

        {

            if (i == 5)

                System.out.print(" ");

            if (i == 10)

                System.out.print(" ");

            System.out.print(P[i]);

        } 

    }

    /**
     * @param s the s to set
     */
    
    /**
     * @return the number1
     */
    
    /**
     * @param number1 the number1 to set
     */
    public void setNumber1(String number1) {
      
        this.number1 = number1;
          n1 = Integer.parseInt(number1);
    }

    /**
     * @return the number2
     */
   
    /**
     * @param number2 the number2 to set
     */
    public void setNumber2(String number2) {
        this.number2 = number2;
          n2= Integer.parseInt(number2);
    }
 
}
