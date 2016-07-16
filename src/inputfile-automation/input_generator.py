#!/usr/local/bin/python

# Above can be changed to wherever python is located on the machine

# NOTE: Run from root directory of project in order for file path to work

import random

CITY_LIMITS = 400
filename = raw_input("Enter desired inputfile name: ")
numDest = input("Enter number of cities for file: ")

outFile = open("src/inputfile-automation/inputfiles/" + filename, "w")

outFile.write(str(numDest) + "\n")

for i in range(0,numDest):
    xCoord = random.randint(0, CITY_LIMITS)
    yCoord = random.randint(0, CITY_LIMITS)
    outFile.write("%d %d\n" % (xCoord, yCoord))

outFile.close()


