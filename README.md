Author: jhoffm12

<h1>Overview</h1>
This project is a basic implementation of a genetic algorithm to find a close-to-optimized solution
to the classic Traveling Salesman Problem (TSP). It is well-known that optimization version of the TSP is NP-hard
so this genetic algorithm aims to find a solution that is pretty close to the "optimal" solution.

<h1>Building the project</h1>
Navigate to the root directory and run the command:<br>
$ gradle build <br>
If you do not have gradle on your machine then clearly you must square that away beforehand.

<h1>Running the project</h1>
The GA depends on several parameters from the command line to function so, to execute, run the following command from the project root directory:

$ gradle run -Dexec.args="[inputFile] [crossoverProbability] [mutationProbability] [elitismProbability] [populationSize] [tournamentSize] [numberOfGenerations] [crossoverMode]"

[inputFile] (type: String) - the formatted input file of destinations (see "Input Automation" below for format details)<br>
[crossoverProbability] (type: double) - GA parameter that determines the probability offspring is created on a run<br>
[mutationProbability] (type: double) - GA paramter that determines the probability a route is mutated<br>
[elitismProbability] (type: double) - GA parameter that determines if elitism is applied<br>
[populationSize] (type: int) - specifies the number of routes to be used in the population<br>
[tournamentSize] (type: int) - specifies the number of routes to be selected for a tournament<br>
[numberOfGenerations] (type: int) - specifies the number of generations the population will be evolved into before terminating<br>
[crossoverMode] (type: int) - specifies which crossover method is used (1 for my own naive approach, 2 for PMX crossover)<br>

<h3>Crossover Variations</h3>
Currently, there are 2 options for applying the crossover operation in this GA.

1. My naive approach picks an index in parent 1 and copies every destination at and after that point into the same position
   in the child. Then the destinations that are not already in the child are inserted in order found from parent 2.
   
2. An explanation of the second option, the PMX Crossover, can be found here http://www.rubicite.com/Tutorials/GeneticAlgorithms/CrossoverOperators/PMXCrossoverOperator.aspx/

<h1>Input Automation</h1>
To set up a context of destinations for the traveling salesman, there is a very simple format to the input files that
will be fed into the project. The first line of the file is the number of destinations, N, in the file. Following that are
N lines of a pair of coordinates X Y on each line representing the location of the destination.

Feel free to hand write your own destination sets, but located in tsalesman-ga/inputfile-automation/ is a python script
that will randomly generate a user specified amount of destinations into a specified file name.
You can run this script by navigating to the root directory of the project and running:

$ python src/inputfile-automation/input_generator.py


<br>
<br>
I'll admit, I'm not perfect and am very new to the world of genetic algorithms so any suggestions are welcome. Thanks!

Jax
