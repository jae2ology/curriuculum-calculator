import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// course planner for computer science 

class Course {
    String name;
    int credits;
    List<String> prerequisites;
    boolean elective;

    Course(String name, int credits, List<String> prerequisites, boolean elective){
        this.name = name;
        this.credits = credits;
        this.prerequisites = prerequisites != null ? prerequisites : new ArrayList<>();
        this.elective = elective;
    }

    // determines what a course is and how it is going to be used (specifically for CS major)
}

public class ClassCreator {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        List<Course> allCourses = getAllCourses();
        List<String> completedCourses = new ArrayList<>();
        int totalMajorCredits = 124; // needed to graduate
        
        // input taken courses
        System.out.println("Enter the names of the courses you've completed!:");
        String[] completed = input.nextLine().split(",");
        for (String course : completed) {
            completedCourses.add(course.trim());
            // adds the completed course to the list of completed courses
            // adds it every single time a course is added
        }

        // finds next courses

        List<Course> nextCourses = determineNextCourses(allCourses, completedCourses);
        int totalCreditsForNextSemester = 0; // Tracks credits for next semester

        System.out.println("\n Here's your suggested courses for next semester!");
        for (Course course : nextCourses){
            if (totalCreditsForNextSemester + course.credits <= 16){
                totalCreditsForNextSemester += course.credits;
                System.out.println(course.name + " (" + course.credits + " credits)");
            }
        }

        // remaining credits
        int remainingCredits = calculateRemainingCredits(allCourses, completedCourses, totalMajorCredits);
        System.out.println("\nRemaining Credits for your major!:" + remainingCredits + '.');
    }


    private static List<Course> getAllCourses(){
        // all courses in computer science
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("ENGL 1102", 3, List.of("ENGL 1101"), false));
        courses.add(new Course("ENGL 1101", 3, new ArrayList<>(), false));
        courses.add(new Course("MATH 1113", 3, List.of("MATH 1111"), true));
        courses.add(new Course("MATH 1441", 4, List.of("MATH 1113"), false));
        courses.add(new Course("HIST 1111", 3, new ArrayList<>(), false));
        courses.add(new Course("CORE 2000", 1, new ArrayList<>(), false));
        courses.add(new Course("COMM 1110", 3, new ArrayList<>(), false));
        courses.add(new Course("INTS 2130", 3, new ArrayList<>(), false));
        courses.add(new Course("GEOL 1121", 4, new ArrayList<>(), false));
        courses.add(new Course("STAT 1401", 3, List.of("MATH 1111"), false));
        courses.add(new Course("HIST 2112", 3, new ArrayList<>(), false));
        courses.add(new Course("KINS 1525", 2, new ArrayList<>(), false));
        courses.add(new Course("FYE 1220", 2, new ArrayList<>(), false));
        courses.add(new Course("GEOL 1122", 4, List.of("GEOL 1121"), false));
        courses.add(new Course("ENGL 2100", 3, List.of("ENGL 1102"), false));
        courses.add(new Course("CSCI 1301", 4, List.of("MATH 1113"), false));
        courses.add(new Course("CSCI 1302", 3, List.of("CSCI 1301"), false));
        courses.add(new Course("CSCI 2120", 3, List.of("CSCI 1301", "COMM 1110"), false));
        courses.add(new Course("MATH 2130", 3, List.of("MATH 1111", "CSCI 1301"), false));
        courses.add(new Course("MATH 2242", 4, List.of("MATH 1441"), false));
        courses.add(new Course("MATH 2160", 3, List.of("MATH 2242"), false));
        courses.add(new Course("CSCI 3230", 3, List.of("CSCI 1302", "CSCI 2625"), false));
        courses.add(new Course("CSCI 2503", 3, List.of("CSCI 1302"), false));
        courses.add(new Course("CSCI 3341", 3, List.of("CSCI 2503", "CSCI 3230"), false));
        courses.add(new Course("CSCI 3236", 3, List.of("CSCI 1302", "CSCI 2625"), false));
        courses.add(new Course("CSCI 3432", 3, List.of("CSCI 2503"), false));
        courses.add(new Course("CSCI 4330", 3, List.of("CSCI 3238", "MATH 2160"), false));
        courses.add(new Course("CSCI 3331", 3, List.of("CSCI 3341"), false));
        courses.add(new Course("CSCI 3321", 3, List.of("CSCI 3230"), false));
        courses.add(new Course("CSCI 5332", 3, List.of("STAT 1401", "CSCI 3341"), false));
        courses.add(new Course("CSCI 5431", 3, List.of("CSCI 5332", "CSCI 2120"), false));
        courses.add(new Course("CSCI 5436", 3, List.of("CSCI 3432"), false));
        courses.add(new Course("CSCI 5530", 3, List.of("CSCI 4330", "CSCI 3321", "CSCI 5436"), false));
        courses.add(new Course("MATH 1111", 3, new ArrayList<>(), true));
        courses.add(new Course("CSCI 4439", 3, List.of("CSCI 1302"), true));
        courses.add(new Course("CSCI 5428", 3, List.of("CSCI 5437"), true));
        courses.add(new Course("CSCI 5437", 3, List.of("CSCI 3230", "CSCI 3236"), true));
        courses.add(new Course("POLS 1101", 3, new ArrayList<>(), false));
        courses.add(new Course("ECON 2105", 3, new ArrayList<>(), false));
        courses.add(new Course("PSYC 1101", 3, new ArrayList<>(), true));

        return courses;
    }

    private static List<Course> determineNextCourses(List<Course> allCourses, List<String> completedCourses) {
        List<Course> nextCourses = new ArrayList<>();
        for (Course course : allCourses) {
            boolean canTake = true;
            for (String prereq : course.prerequisites) {
                if (!completedCourses.contains(prereq)) {
                    canTake = false;
                    break;
                }
            }
            if (canTake && !completedCourses.contains(course.name)) {
                nextCourses.add(course);
            }
        }
        return nextCourses;
    }

    private static int calculateRemainingCredits(List<Course> allCourses, List<String> completedCourses, int majorCredits) {
        int completedCredits = 0;
        for (Course course : allCourses) {
            if (!course.elective && completedCourses.contains(course.name)) {
                completedCredits += course.credits;
            }
        }
        return majorCredits - completedCredits;
    }
}
