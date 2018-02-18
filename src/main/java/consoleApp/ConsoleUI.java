package consoleApp;

import controllers.ConsoleController;
import gateways.repository.CourseRepositoryMemory;
import gateways.repository.StudentRepositoryMemory;
import unit.core.usecases.dto.GetAllCoursesResponseDTO;
import unit.core.usecases.dto.GetCourseResponseDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class ConsoleUI {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        sampleSeed();

        List<GetCourseResponseDTO> allCourses = getAllCourses();

        do {
            printTitle();
            printMenu(allCourses);

            //
            // Ask studentId
            //
            printAskStudentId();

            try {
                userInput = br.readLine();
            } catch (IOException ioe) {
                break;
            }

            if (userInput.equals("quit")) {
                break;
            }

            String studentId = userInput;

            //
            // Ask course code
            //
            printAskCourseCode();

            try {
                userInput = br.readLine();
            } catch (IOException ioe) {
                break;
            }

            if (userInput.equals("quit")) {
                break;
            }

            String courseCode = userInput;

            // Do stuff
            String registerResponse = ConsoleController.registerCourse(studentId, courseCode);

            printResult(registerResponse);

            try {
                userInput = br.readLine();
            } catch (IOException ioe) {
                break;
            }

        } while (!userInput.equals("quit"));
    }

    private static void sampleSeed() {
        CourseRepositoryMemory.seedDev();
        StudentRepositoryMemory.seedDev();
    }

    private static List<GetCourseResponseDTO> getAllCourses() {
        GetAllCoursesResponseDTO allCoursesResponse = ConsoleController.getAllCourses();
        List<GetCourseResponseDTO> courseList = allCoursesResponse.getCourseList();
        courseList.sort(Comparator.comparing(GetCourseResponseDTO::getName));
        return courseList;
    }

    private static void printAskStudentId() {
        System.out.println();
        System.out.print("Enter your studentId or 'quit': ");
    }

    private static void printTitle() {
        System.out.println("===================================================================================");
        System.out.println("                               Course Registration                                 ");
        System.out.println("===================================================================================");
    }

    private static void printMenu(List<GetCourseResponseDTO> allCourses) {
        System.out.println();
        System.out.println("Select one of the following courses:");
        System.out.println();

        for (GetCourseResponseDTO c : allCourses) {
            System.out.println(String.format("%s - %s - %s - Starts: %s", c.getCode(), c.getName(), c.getDescription(), c.getStartDate()));
        }

        System.out.println("===================================================================================");
    }

    private static void printAskCourseCode() {
        System.out.println();
        System.out.print("Enter course code or 'quit': ");
    }

    private static void printResult(String registerResponse) {
        System.out.println();
        System.out.print("The register result was: ");
        System.out.println(registerResponse);

        System.out.println();
        System.out.println("Press any key to continue...");
    }

}