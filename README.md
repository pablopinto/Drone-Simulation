Drone Simulation

Scenario
There are two automatic drones that fly around London and report on traffic conditions. When a drone flies over a tube station, it assesses what the traffic condition is like in the area, and reports on it.


How it works

A simulation that has one dispatcher and two drones. Each drone "moves" independently on different processes. The dispatcher sends the coordinates to each drone detailing where the drone's next position should be. The dispatcher is also responsible for terminating the program. 
When the drone receives a new coordinate, it moves, checks if there is a tube station in the area, and if so, reports on the traffic conditions there. 
