## Traffic Signals Management

This program controls the traffic signals for a four-way intersection. Initially, consider traffic flowing in straight lines only, no turns. The four directions are S(outhbound) and N(orthbound) on Snell Rd; and W(estbound) and E(astbound) on Weaver Rd. The traffic lights should obey the following rules:

1. Cars arrive in each direction on both roads (Snell and Weaver) at the rate of 1 car per second. That is, 4 cars approach the intersection each second.

2. Only one road (Snell or Weaver) can have a "green" light at one time.

3. It is acceptable for both roads to have the "red" light at the same time. Of course, traffic backs up on both roads if this happens.

4. Start by turning on the traffic on Snell Rd "green" in both directions for 3 seconds; then turn it "red" for one second; then turn Weaver "green" for 3 seconds; and then red for one second.

5. When the light turns from red to green at any intersection, it takes the first car 2 seconds to start moving and cross the intersection. Subsequent cars take 1 second each.

6. At the instant the light turns from "green" to "red", a car may not start moving to cross the intersection; whether that car just arrived at the intersection or was waiting at that intersection.

7. The output should be the number of cars that are waiting at the intersection in each direction at each second, for the first 20 seconds. Do not make the program wait 20 seconds to produce the output: this is only a simulation, so print the output when it's ready.


#### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.


#### Prerequisites

Pull the code from GIT and run the main class as "Java Application".
Ensure that JDK and Maven are installed.
Or you can build the executable jar and run it with "java -jar <jar_name> command.

#### How to run

Build using maven
```
mvn clean install
```
If you want to skip the JUnit test cases
```
mvn clean install -DskipTests=true
```
Executable JAR will be generated in the <project-directory>/target
To execute the runnable JAR:
```
java -jar .\traffic-signals-management-1.0.0-SNAPSHOT.jar
```


Sample result:

```
0: N = 0; S = 0; E = 0; W = 0
1: N = 0; S = 0; E = 1; W = 1
2: N = 0; S = 0; E = 2; W = 2
3: N = 0; S = 0; E = 3; W = 3
4: N = 1; S = 1; E = 4; W = 4
5: N = 2; S = 2; E = 5; W = 5
6: N = 3; S = 3; E = 5; W = 5
7: N = 4; S = 4; E = 5; W = 5
8: N = 5; S = 5; E = 6; W = 6
9: N = 6; S = 6; E = 7; W = 7
10: N = 6; S = 6; E = 8; W = 8
11: N = 6; S = 6; E = 9; W = 9
12: N = 7; S = 7; E = 10; W = 10
13: N = 8; S = 8; E = 11; W = 11
14: N = 9; S = 9; E = 11; W = 11
15: N = 10; S = 10; E = 11; W = 11
16: N = 11; S = 11; E = 12; W = 12
17: N = 12; S = 12; E = 13; W = 13
18: N = 12; S = 12; E = 14; W = 14
19: N = 12; S = 12; E = 15; W = 15
20: N = 13; S = 13; E = 16; W = 16

```
Run as "Java Application"
Sample output from console is:
![Alt text](https://github.com/PhaniMangisetti/traffic-signals-management/blob/master/img/EclipseConsoleOutput.JPG?raw=true "Eclipse Console Output")

When the executable JAR is run with "java -jar" command, sample output in command prompt is:
![Alt text](https://github.com/PhaniMangisetti/traffic-signals-management/blob/master/img/sampleOutput.JPG?raw=true "Executable Jar Output")

#### Deployment
Since this is a simple executable JAR, there is no deployment instructions.

#### Built With

Maven - Dependency Management

#### Authors

Phanikumar Mangisetti

#### License

None
