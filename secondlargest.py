import random
import matplotlib.pyplot as plt
import matplotlib.pyplot as mpatches
'''
Implementations by Sarah Sahibzada, for use in supplementing concepts taught in CSCE 411
and generating a graph for HW 2 
Texas A&M University'''
def test_algorithm(list_of_inputs): #provided in assignment specifications
	nums = list_of_inputs
	largest = nums[0]
	secondlargest = 0
	num = 0
	compcount = 0

	for x in range(1,len(nums)):
		num = nums[x]
		#print num, " = nums[", x, "]"
		compcount += 1
		if num > largest:
			secondlargest = largest
			largest = num
			
		else:
			compcount += 1
			if num > secondlargest:
				secondlargest = num
				
	'''print "-------------------------"
	print list_of_inputs
	print secondlargest, " is second largest "
	print largest, " is largest"
	print compcount, " comparisons so far"
	print "-------------------------"'''
	return compcount

def clear_list(inp_list):
	index = len(inp_list) - 1
	while index >= 0:
		del list[index]
		index -= 1
#tournament algorithm: to fix

'''def tournamentlist_of_inputs):
	nums = list_of_inputs
	next_nums = []
	#print list_of_inputs
	while (len(list_of_inputs) > 2):	
		for x in range(0,len(list_of_inputs)-2):
			#print "(", x, ",", x+1, ")"
			if ((x % 2) != 0):
				continue
			else:
				print "(", x, ",", x+1, ")"
			'''else:
				print list_of_inputs[x], " is being considered"'''
			if list_of_inputs[x] >= list_of_inputs[x+1]:
				#print "appending ", list_of_inputs[x]
				next_nums.append(list_of_inputs[x])
			else:
				next_nums.append(list_of_inputs[x+1])
				#print "appending", list_of_inputs[x+1]

		for x in list_of_inputs:
				list_of_inputs.pop()

		for x in next_nums:
				list_of_inputs.append(x)
				next_nums.pop()
		#print list_of_inputs

	if len(list_of_inputs) == 2:
		#print " in final case "
		if list_of_inputs[0] >= list_of_inputs[1]:
			print list_of_inputs[0]
			return list_of_inputs[0]
		else:
			print list_of_inputs[1]
			return list_of_inputs[1]'''

def test():
	yaxis_worstcase = []
	yaxis_bestcase = []
	for x in range(1,20):
		#random.seed()
		
		shuffled = random.shuffle([y for y in range(0,x)])
		a = test_algorithm([y for y in range(0,x)])
		yaxis_bestcase.append(a)
		b = test_algorithm(list(reversed([y for y in range(0,x)])))
		yaxis_worstcase.append(b)
		#dumb_algorithm(shuffled)
		
		#print [y for y in range(0,x)]
	plt.plot(yaxis_bestcase, 'rs');
	plt.plot(yaxis_worstcase, 'bs');
	#red_patch = mpatches.Patch(color='red', label='Best case (reverse ordered list)')
	#blue_patch = mpatches.Pat
	plt.show()
test()
