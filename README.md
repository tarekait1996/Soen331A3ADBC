# Assignment 3 on Contract Programming
## Overview
For this assignment, a Priority Queue abstract data type is implemented. The program will be able to:
  1. insert the pair (element, key) into the priority queue
  2. remove and return the element with the smallest key
  3. return but not remove the element with the smallest key
## Tools 
The environment and tools that were used for this assignment is the Java programming language, the Eclipse IDE, the third party support 'abdc' and the language extension AspectJ. 
## How to run code 
  1. Acquire the code by entering the following command in the directory of choice
  
        ```
        git clone https://github.com/tarekait1996/Soen331A3ADBC.git 
        ```
  
  2. Install [Eclipse](https://www.eclipse.org/downloads/index.php)
  3. Download [AspectJ](https://www.eclipse.org/ajdt/downloads/) plugin
  4. Clone the [abdc](https://github.com/timmolderez/adbc) repo to obtain the **jar** file
  5. Open the project in Eclipse and convert it into an AspectJ project. To do so, 
      * right-click on the project name in **Package Explorer**
      * go on **Configure**
      * click on **Convert to AspectJ Project**
  6. Add the **jar** file to the build path. To do so, 
      * right-click on the project name in **Package Explorer**
      * go on **Properties**
      * click on **AspectJ Build** and go to **Inpath**
      * click on **Add External Jars** and select the the **jar** file (abdc.jar). 
      * click on **OK** to confirm
      * close the **Properties** window 
  7. To run the code, go on *PriorityQueueMain.java* file and click on **Play button** or go on **Run** tab in top menu bar and click on **Run**.
