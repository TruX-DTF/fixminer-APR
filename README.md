# fixminerAPR

## Build

Dependency: clone and install JavaCodeParser

```
git clone https://github.com/AutoProRepair/JavaCodeParser/
cd JavaCodeParser
mvn install -DskipTests
```

Then, install fixminer-APR

```
git clone https://github.com/SerVal-DTF/fixminer-APR/
cd fixminer-APR
mvn install -DskipTests
```



## Run

Usage:

Run the Main class located in https://github.com/fixminer/fixminerAPR/blob/master/src/main/java/edu/lu/uni/serval/fixminer/main/Main.java with the following parameters
  
  Failed_Test_Cases_File_Path
  
    Path to failedTestCases/ 
  https://github.com/fixminer/fixminerAPR/tree/master/FailedTestCases
  
  Suspicious_Code_Positions_File_Path
  
    Path of BugPositions/ 
  https://github.com/fixminer/fixminerAPR/tree/master/BugPositions
  
  Buggy_Project_Path
  
    The repository of buggy project 
    
  defects4j_Path
  
    Path to the defects4j
    
  Project_Name
  
    The defect4j buggy project eg. Chart_1
  
  FL_Metric
    
    
# Fix Templates:

  The fix templates are located 
  
    https://github.com/fixminer/fixminerAPR/tree/master/src/main/java/edu/uni/lu/serval/fixminer/fixtemplate
