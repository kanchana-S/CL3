 #include"iostream"  
  #include"stdlib.h"  
  using namespace std;  
 class BinarySearch  
  {  
    public:  
      int search(int *arr, const int len, const int s)  
      {  
        int lower = 0;  
        int upper = len - 1;  
        int middle = (lower+upper)/2;  
       while (lower <= upper)  
        {  
          if (arr[middle] == s)  
            return middle;  
          else if (arr[middle] > s)  
            upper = middle-1;  
          else  
            lower = middle+1;  
          middle = (lower + upper)/2;  
        }  
        return -1;  
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
    cout<<"\n\n^_^_^_^_^_^_^_^_^_^_^_^ Binary Search _^_^_^_^_^_^_^_^_^_^_^";  
    int len,ans=1, s;  
    cout<<"\n\nEnter size of an array: ";  
    cin>>len;  
    int arr[len];  
    cout<<"\nEnter elements: ";  
    for(int i=0; i<len; i++)  
      cin>>arr[i];  
    b.bubblesort(arr, len);  
    cout<<"\nSorted array: [ ";  
    for(int i=0; i<len; i++)  
      cout<<arr[i]<<" ";  
    cout<<"]";  
while(ans==1)
{
    cout<<"\n\nEnter element to be searched: ";  
    cin>>s;  
    int result = b.search(arr, len, s);  
     if(result != -1)  
       cout<<"\n\n"<< s <<" successfully found in the array at index "<< result <<".\n\n";  
    else  
       cout<<"\n\nOops. :( Element "<< s <<" is not found in the given array.\n\n";  
cout<<"Press 1 to continue";
cin>>ans;
}
     return 0; 
 
  }  

