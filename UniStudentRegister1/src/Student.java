    // Student class
public class Student {
    private final String fname;
    private final String sname;
    private final int age;
    private final int stuid;
    private final CourseType course;
    private final String module;


    public Student(
            String fname, String sname, int age, int stuid, CourseType course, String module) {
        this.fname = fname;
        this.sname = sname;
        this.age = age;
        this.stuid = stuid;
        this.course = course;
        this.module = module;
    }
    // Student attribute getters
    public String getFname() {
        return fname;
    }
    public String getSname() {
        return sname;
    }
    public int getAge() {
        return age;
    }
    public int getID() {
        return stuid;
    }
    public CourseType getCourse() {
        return course;
    }
    public String getModule() {
        return module;
    }

    // Layout of student info on register
    public String toString() {
        return  "Name:" + fname + " " + sname + " " +
                " Age:" + age + " " +
                " ID:" + stuid + " " +
                " Course:" + course + " " +
                " Module:" + module;
    }
    // Enumeration for courses
    public enum CourseType {
        CompSci, Psych, Econ, BioMed
    }
}
