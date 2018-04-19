def bsearch(a,s,beg,last):
	if(beg<last):
		mid=int((beg+last)/2)
		#print mid
		if(a[mid]==s):
			print"Element found at ",+ mid
			
		elif(s<a[mid]):
			return bsearch(a,s,beg,mid)
		else:
			return bsearch(a,s,mid+1,last)
	else:
		print"Element not found"


n=int(raw_input("\nEnter number of elements"))
data = []
for i in range(0, n):
    x = int(raw_input('Enter the numbers into the array: '))
    data.append(x)
print(data)
for i in range(0, n):
	for j in range(0, n):
		if(data[i]<data[j]):
			data[i]=data[i]+data[j]
			data[j]=data[i]-data[j]
			data[i]=data[i]-data[j]
print data
ans='1'
while(ans=='1'):
	print"\nEnter element to be searched: \n"
	s=int(input())
	bsearch(data,s,0,n)
	print"\nWant to search more elements?\n"
	ans=raw_input()


