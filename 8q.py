import json

def isattack(board,r,c):
	for i in range(r):			#to check vertically backward
		if(board[i][c]==1):
			return True

	i=r-1
	j=c-1
	while((i>=0) and (j>=0)):	#check diagonally upward towards left
		if(board[i][j]==1):
			return True
		i=i-1
		j=j-1

	i=r-1
	j=c+1
	while((i>=0) and (j<8)):	#check diagonally upward towards right
		if(board[i][j]==1):
			return True
		i=i-1
		j=j+1

	i=r+1
	j=c+1
	while((i<8) and (j<8)):	#check diagonally downward towards r
		if(board[i][j]==1):
			return True
		i=i+1
		j=j+1

	i=r+1
	j=c-1
	while((i<8) and (j>=0)):	#check diagonally downward towards left
		if(board[i][j]==1):
			return True
		i=i+1
		j=j-1

	return False

def solve(board,row):
	i=0
	while(i<8):			#to check if prev queens are placed in 1...8 location
		if (i!=data["col"]):
			if(row==data["row"]):
				row=row+1
			if(row>7):
				return True
			if(not isattack(board, row, i)):		#row= queen to be placed i = location to place
				board[row][i]=1
				print row,i
				if(row==7):
					return True
				else:
					if(solve(board, row+1)):
						return True
					else:		#to put zero (in case of back tracking)
						print "No position possible to place in row",row+1,"So backtracking required to row ",row
						board[row][i]=0
		i=i+1
	if(i==8):
		return False

def printboard(board):
	for i in range(8):
		for j in range(8):
			print str(board[i][j])+" ",
		print "\n"

board = [[0 for x in range(8)] for x in range(8)]	#init 8*8 mat to 0

if __name__ == '__main__':
	data=[]
	with open('input.json') as f:
		data=json.load(f)

	'''if(data["start"]<0 or data["start"]>7):
		print "Invalid JSON input"
		exit()'''

	board[data["row"]][data["col"]]=1
	if(solve(board, 0)):
		print "Queens problem solved!!!"
		print "Board Configuration:"
		printboard(board)
	else:
		print "Queens problem not solved!!!"



#Filename:Input.json
#{"row":4,"col":4}

