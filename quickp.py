# -*- coding: utf-8 -*-
"""
Created on Tue Apr 17 14:41:31 2018

@author: Hp
"""

import threading
import xml.etree.ElementTree as X
def part(arr, low, high):
    #pivot, i = arr[high], low
    i=low
    j=high
    mid=(i+j/2)
    pivot=arr[mid]
    while(1):
        while(arr[i]<=pivot and i<j):
            i=i+1
        while(arr[j]>pivot):
            j=j-1
        if(i<j):
            temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        else:
            temp= arr[mid]
            arr[mid] = arr[j]
            arr[j]= temp
            return j
                
    
    '''for j in range(low, high):
        if arr[j] <= pivot:
            arr[i], arr[j] = arr[j], arr[i]
        i = i+1
    arr[i], arr[high] = arr[high], arr[i]
    return i'''

def qsort(arr, low, high):
    if low < high:
        p = part(arr, low, high)
        t1 = threading.Thread(target = qsort, args = (arr, p+1, high))
        t2 = threading.Thread(target = qsort, args = (arr, low, p-1))
        t1.start()
        t2.start()
        t1.join()
        print t1.getName()
        t2.join()
        print t2.getName()

tree = X.parse('input.xml').getroot()
arr=[]
for item in tree.findall('input'):
    arr.append(int(item.get('number')))
    
print arr    
    
qsort(arr, 0, len(arr)-1)
for i in arr:
    print i