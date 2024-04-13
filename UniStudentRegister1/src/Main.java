import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>(Arrays.asList(
                new Student("John", "Doe", 20, 45025, Student.CourseType.Econ, "BS2549"),
                new Student("Saffron", "Yancy", 19, 64856, Student.CourseType.BioMed, "BI1002"),
                new Student("Edytha", "Jeb", 20, 95687, Student.CourseType.CompSci, "CI2043"),
                new Student("Sharon", "Vernon", 21, 36578, Student.CourseType.CompSci, "CI3015"),
                new Student("Flo", "Denholm", 22, 47834, Student.CourseType.Psych, "PSY3013"),
                new Student("Florrie", "Huxley", 21, 67845, Student.CourseType.BioMed, "BI3001"),
                new Student("Sylas", "Judd", 20, 73967, Student.CourseType.Psych, "PSY2030"),
                new Student("Cassandra", "Merideth", 20, 27849, Student.CourseType.Econ, "BS1652"),
                new Student("Jacelyn", "Verity", 19, 45806, Student.CourseType.Econ, "BS1652")));

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Options user chooses from
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Register a new student");
            System.out.println("2. View the student register");
            System.out.println("3. Search for students");
            System.out.println("4. Remove a student");
            System.out.println("5. Sort the student list");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    studentList = registerStudent(studentList, scanner);
                    break;
                case 2:
                    displayStudentRegister(studentList);
                    break;
                case 3:
                    searchForStudents(studentList, scanner);
                    break;
                case 4:
                    studentList = removeStudent(studentList, scanner);
                    break;
                case 5:
                    studentList = sortStudentList(studentList, scanner);
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

        // New student reg
    private static List<Student> registerStudent(List<Student> studentList, Scanner scanner) {
        System.out.println("Enter the student's first name:");
        String fname = scanner.next();
        System.out.println("Enter the student's surname:");
        String sname = scanner.next();
        System.out.println("Enter the student's age:");
        int age = scanner.nextInt();
        System.out.println("Enter the student's student id:");
        int stuid = scanner.nextInt();
        System.out.println("Enter the student's course:");
        Student.CourseType course = Student.CourseType.valueOf(scanner.next());
        System.out.println("Enter the student's module:");
        String module = scanner.next();

        Student newStudent = new Student(fname, sname, age, stuid, course, module);
        List<Student> newStudentList = new ArrayList<>(studentList);
        newStudentList.add(newStudent);
        // Confirm new student reg success
        System.out.println("New student registered successfully:\n" + newStudent);
        return newStudentList;
    }
        // Display reg
    private static void displayStudentRegister(List<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("Student register is empty.");
        } else {
            System.out.println("Student Register:");
            studentList.forEach(System.out::println);
        }
    }
        // Search for students with name/id/course/module
    private static void searchForStudents(List<Student> studentList, Scanner scanner) {
        System.out.println("What would you like to search by?");
        System.out.println("1. Name");
        System.out.println("2. ID");
        System.out.println("3. Course");
        System.out.println("4. Module");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                searchByName(studentList, scanner);
                break;
            case 2:
                searchById(studentList, scanner);
                break;
            case 3:
                searchByCourse(studentList, scanner);
                break;
            case 4:
                searchByModule(studentList, scanner);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }
        // Query students by name
    private static void searchByName(List<Student> studentList, Scanner scanner) {
        System.out.println("Enter the student's first name:");
        String fname = scanner.nextLine();
        System.out.println("Enter the student's last name:");
        String sname = scanner.nextLine();

        List<Student> filteredStudents = studentList.stream()
                .filter(student -> student.getFname().equalsIgnoreCase(fname) || student.getSname().equalsIgnoreCase(sname))
                .collect(Collectors.toList());

        displaySearchResult(filteredStudents);
    }
        // Query students by id
    private static void searchById(List<Student> studentList, Scanner scanner) {
        System.out.println("Enter the student's ID:");
        int id = scanner.nextInt();

        List<Student> filteredStudents = studentList.stream()
                .filter(student -> student.getID() == id)
                .collect(Collectors.toList());

        displaySearchResult(filteredStudents);
    }
        // Query students by course
    private static void searchByCourse(List<Student> studentList, Scanner scanner) {
        System.out.println("Enter the student's course:");
        String course = scanner.nextLine();

        List<Student> filteredStudents = studentList.stream()
                .filter(student -> student.getCourse().toString().equalsIgnoreCase(course))
                .collect(Collectors.toList());

        displaySearchResult(filteredStudents);
    }
        // Query students by module
    private static void searchByModule(List<Student> studentList, Scanner scanner) {
        System.out.println("Enter the student's module:");
        String module = scanner.nextLine();

        List<Student> filteredStudents = studentList.stream()
                .filter(student -> student.getModule().equalsIgnoreCase(module))
                .collect(Collectors.toList());

        displaySearchResult(filteredStudents);
    }

    private static void displaySearchResult(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Search Result:");
            students.forEach(System.out::println);
        }
    }
        // Remove student by student id and confirm / else inform user
    private static List<Student> removeStudent(List<Student> studentList, Scanner scanner) {
        System.out.println("Enter the student's ID to remove:");
        int idToRemove = scanner.nextInt();

        List<Student> newStudentList = studentList.stream()
                .filter(student -> student.getID() != idToRemove)
                .collect(Collectors.toList());

        if (newStudentList.size() == studentList.size()) {
            System.out.println("Student with ID " + idToRemove + " not found.");
        } else {
            System.out.println("Student with ID " + idToRemove + " has been removed.");
        }

        return newStudentList;
    }
        // Sort student list / default = alphabetical surname
    private static List<Student> sortStudentList(List<Student> studentList, Scanner scanner) {
        System.out.println("How would you like to sort the student list?");
        System.out.println("1. By First Name");
        System.out.println("2. By ID");
        System.out.println("3. By Course");
        System.out.println("4. By Module");

        int choice = scanner.nextInt();

        Comparator<Student> comparator = switch (choice) {
            case 1 -> Comparator.comparing(Student::getFname);
            case 2 -> Comparator.comparingInt(Student::getID);
            case 3 -> Comparator.comparing(Student::getCourse);
            case 4 -> Comparator.comparing(Student::getModule);
            default -> {
                System.out.println("Invalid choice. Sorting by default (surname)");
                yield Comparator.comparing(Student::getSname);
            }
        };

        List<Student> sortedList = studentList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        System.out.println("Student list sorted successfully.");
        return sortedList;
    }
}


