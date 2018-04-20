 #include"iostream"  
  #include"stdlib.h"  
  using namespace std;  
int flag=0;
 class BinarySearch  
  {  
    public: 
int middle;
      int search(int *arr,int s,int lower,int upper)  
      {  
        middle = (lower+upper)/2;  
       if (lower <= upper)  
        {  
          if (arr[middle] == s)  
            return middle;  
          else if (arr[middle] > s)  
            search(arr,s,lower,middle-1);
          else if (arr[middle] < s)
            search(arr,s,middle+1,upper);
  
}
else  {
flag=1;
       cout<<"Number not found";
}      
}  
      void bubblesort(int *arr, const int size)  
      {  
        for(int i=0;i<size;i++)
	{
		for(int j=0;j<size;j++)
		{
			if(arr[j]>arr[j+1])
			{
				int temp;
				temp=arr[j];
				arr[j]=arr[j+1];
				arr[j+1]=temp;

			}
		}
	}
      }  
  }b;  
  int main()  
  {   
    int len,ans=1, s;  
    cout<<"\n\nEnter the number of elements in array: ";  
    cin>>len;  
    int arr[len];  
    cout<<"\nEnter values in array: ";  

    for(int i=0; i<len; i++)
	{  
     	 cin>>arr[i];  
   
	}
   cout<<"\nPrint array values:\n ";  
    for(int i=0; i<len; i++)  
	{
      cout<<arr[i]; 
	cout<<" ";
	 }

    b.bubblesort(arr, len);  
    cout<<"\nSorted array is: \n";  
    for(int i=0; i<len; i++)  
      cout<<arr[i]<<" ";  
    cout<<"]";  
while(ans==1)
{
    cout<<"\n\nEnter the number to be searched: ";  
    cin>>s;  
 int result=b.search(arr,s,0,len);  
if(flag==0)
 cout<<"\n\n"<< s <<"Number present at location: "<< (result+1) <<".\n\n";  
 cout<<"want to search more elements: ";
cin>>ans;
}
     return 0; 
 
  }  

