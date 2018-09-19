package edu.lu.uni.serval.fixminer.main;

import java.io.File;
import java.util.List;

import edu.lu.uni.serval.bug.fixer.FixMinerFixer;
import edu.lu.uni.serval.config.Configuration;
import edu.lu.uni.serval.fixminer.LiteralReader;
import edu.lu.uni.serval.fixminer.ProjectLiteral;

public class Main {

	public static void main(String[] args) {
		if (args.length != 6) {
			System.out.println("Arguments: <Failed_Test_Cases_File_Path> <Suspicious_Code_Positions_File_Path> <Buggy_Project_Path> <defects4j_Path> <Project_Name> <FL_Metric>");
			System.exit(0);
		}
		Configuration.failedTestCasesFilePath = args[0]; // failedTestCases/
		Configuration.suspPositionsFilePath = args[1];   // BugPositions/
		String buggyProjectsPath = args[2];// "../Defects4JData/"
		String defects4jPath = args[3]; // "../defects4j/"
		String projectName = args[4]; // "Chart_1"
		Configuration.faultLocalizationMetric = args[5];
		System.out.println(projectName);
		fixBug(buggyProjectsPath, defects4jPath, projectName);
	}

	private static void fixBug(String buggyProjectsPath, String defects4jPath, String buggyProjectName) {
		String suspiciousFileStr = Configuration.suspPositionsFilePath;
		
		String dataType = "FixMiner";
		String[] elements = buggyProjectName.split("_");
		String projectName = elements[0];
		int bugId;
		try {
			bugId = Integer.valueOf(elements[1]);
		} catch (NumberFormatException e) {
			System.err.println("Please input correct buggy project ID, such as \"Chart_1\".");
			return;
		}
		
		List<ProjectLiteral> literals = LiteralReader.read(buggyProjectName);
		
		FixMinerFixer fixer = new FixMinerFixer(buggyProjectsPath, projectName, bugId, defects4jPath);
		fixer.metric = Configuration.faultLocalizationMetric;
		fixer.dataType = dataType;
		fixer.literals = literals; // some local data. FIXME: might be useless.
		fixer.suspCodePosFile = new File(suspiciousFileStr);
		if (fixer.minErrorTest == Integer.MAX_VALUE) {
			System.out.println("Failed to defects4j compile bug " + buggyProjectName);
			return;
		}
		
		fixer.fixProcess();
		
		int fixedStatus = fixer.fixedStatus;
		switch (fixedStatus) {
		case 0:
			System.out.println("Failed to fix bug " + buggyProjectName);
			break;
		case 1:
			System.out.println("Succeeded to fix bug " + buggyProjectName);
			break;
		case 2:
			System.out.println("Partial succeeded to fix bug " + buggyProjectName);
			break;
		}
	}

}
