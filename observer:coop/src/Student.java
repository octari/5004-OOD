import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a grade record of a student. It is a subject to be observed. It implements
 * all the methods in the interface Subject
 */
public class Student implements Subject {

  // The name of the student.
  private String name;

  // The gpa of the student.
  private double gpa;

  // A map of credit hours of all the classes.
  // The key is the class name string, value is its credit hour.
  private Map<String, Double> credit;

  // A map of grades of the classes the student have taken.
  // The key is the class name string， value is the grade in letter.
  private Map<String, String> grades;

  // A list of all the observers of this record.
  private List<Observer> observers;

  /**
   * Construct a new grade record of a student with the given name. Initially, all the grades should
   * be 0.
   *
   * @param name the name of the student
   */
  public Student(String name) {
    this.name = name;
    this.gpa = 0;

    credit = new HashMap<>();
    credit.put("CS 5800", 4.0);
    credit.put("CS 5500", 4.0);
    credit.put("CS 5600", 4.0);
    credit.put("CS 5002", 4.0);
    credit.put("CS 5001", 4.0);
    credit.put("CS 5004", 4.0);
    credit.put("CS 5006", 4.0);
    credit.put("CS 5007", 4.0);
    credit.put("CS 5010", 4.0);

    grades = new HashMap<>();
    observers = new ArrayList<>();
  }

  /**
   * Register the given observer so that the observer can monitor this student. Add it to the list
   * of observer and also register this student to the observer.
   *
   * @param obj the observer the given observer.
   */
  @Override
  public void register(Observer obj) {
    obj.register(this);
    observers.add(obj);
  }

  /**
   * Change the grades of the student by adding a new grade or modifying the existed one. After
   * change, notify all the observers. Change occurs when the given grade is different from the
   * existed ones; Otherwise, no update and notify.
   *
   * @param classname the title of the change message
   * @param grade     the corresponding value of the title.
   */
  @Override
  public void change(String classname, String grade) {

    if (!grades.containsKey(classname) || !grades.get(classname).equals(grade)) {
      if (this.grades.containsKey(classname)) {
        this.grades.replace(classname, grade);
      } else {
        this.grades.put(classname, grade);
      }

      gpa = gpaCalculator();
      for (Observer o : observers) {
        // notify the observer.
        o.signal();
      }
    }
  }

  /**
   * Calculate the average gpa of the student. Help method.
   *
   * @return the gpa
   */
  private double gpaCalculator() {
    double gpa = 0;
    double grade = 0;
    for (String s : grades.keySet()) {
      grade = getGrade(s);
      gpa += grade * (credit.get(s) / totalCredit());
    }
    return gpa;
  }

  /**
   * Get corresponding gpa of a class according to the grade in letter.
   *
   * @param s the grade in letter
   * @return the corresponding gpa
   */
  private double getGrade(String s) {
    double grade = 0;
    switch (grades.get(s)) {
      case "A":
        grade = 4.0;
        break;
      case "A-":
        grade = 3.667;
        break;
      case "B+":
        grade = 3.333;
        break;
      case "B":
        grade = 3.0;
        break;
      case "B-":
        grade = 2.667;
        break;
      case "C+":
        grade = 2.333;
        break;
      case " C":
        grade = 2.0;
        break;
      case "C-":
        grade = 1.667;
        break;
      case "D+":
        grade = 1.333;
        break;
      case "D":
        grade = 1.0;
        break;
      case "D-":
        grade = 0.667;
        break;
      case "F":
        grade = 0;
        break;
      default:
        break;
    }
    return grade;
  }


  /**
   * Return the total of credits this student has taken.
   *
   * @return the total credits.
   */
  public double totalCredit() {
    double sum = 0;
    for (String s : grades.keySet()) {
      sum += credit.get(s);
    }
    return sum;
  }

  /**
   * Check whether this student has fulfilled the core requirements: have a gpa 3.0+ in all core
   * courses.
   *
   * @param core Array of the all the cores courses required.
   * @return true if this student is qualified; otherwise, return false.
   */
  public boolean hasFulfilledAllCore(String[] core) {

    //String[] core = {"CS 5004", "CS 5600", "CS 5500", "CS 5800"};
    for (String course : core) {
      if (grades.containsKey(course) && getGrade(course) >= 3.0) {
        continue;
      } else {
        return false;
      }
    }
    return true;
  }


  /**
   * Return this student's gpa.
   *
   * @return the gpa
   */
  public double getGPA() {
    return gpa;
  }

  /**
   * Return the name of this student.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }
}
