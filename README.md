# LLAPSearch: AI Search Algorithm Agent

## Overview

LLAPSearch is an AI search algorithm agent designed to solve problems using various search strategies. The agent takes an initial state and a specified strategy to find the sequence of actions required to reach the goal state. The application supports multiple search strategies, including Breadth-First Search (BFS), Depth-First Search (DFS), Iterative Deepening Search (IDS), Uniform Cost Search (UFS), Greedy Search (GR1, GR2), and A* Search (AS1, AS2).

## Features

- **Multiple Search Strategies**: Supports BFS, DFS, IDS, UFS, GR1, GR2, AS1, and AS2.
- **Performance Metrics**: Measures and displays time taken, memory usage, and CPU usage for each search strategy.
- **Visualization**: Option to visualize the search process.
- **Customizable Initial States**: Allows users to define various initial states for testing.

## Usage

### Defining Initial States

Initial states are defined as strings with the following format:
```
initialProsperity;
initialFood, initialMaterials, initialEnergy;
unitPriceFood, unitPriceMaterials, unitPriceEnergy;
amountRequestFood, delayRequestFood;
amountRequestMaterials, delayRequestMaterials;
amountRequestEnergy, delayRequestEnergy;
priceBUILD1, foodUseBUILD1, materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1;
priceBUILD2, foodUseBUILD2, materialsUseBUILD2, energyUseBUILD2, prosperityBUILD2;
```

### Example Initial States

```java
String initialState0 = "17;49,30,46;7,57,6;7,1;20,2;29,2;350,10,9,8,28;408,8,12,13,34;";
String initialState1 = "50;12,12,12;50,60,70;30,2;19,2;15,2;300,5,7,3,20;500,8,6,3,40;";
```

### Running Search Algorithms

To run a specific search algorithm, use the `runAlgorithm` method:

```java
runAlgorithm("BF", initialState0);
runAlgorithm("DF", initialState1);
```

### Output

The output will display the following metrics for each algorithm:
- Time taken
- Memory usage
- CPU usage
- Plan (sequence of actions)

## Search Strategies

- **BF**: Breadth-First Search
- **DF**: Depth-First Search
- **ID**: Iterative Deepening Search
- **UC**: Uniform Cost Search
- **GR1**: Greedy Search 1
- **AS1**: A* Search 1
- **GR2**: Greedy Search 2
- **AS2**: A* Search 2
