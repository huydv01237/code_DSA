package exampleasm;
class StudentNode {
    int studentId;
    String studentName;
    double studentMarks;
    String studentRank;
    StudentNode left, right;

    // Constructor to create a new node
    public StudentNode(int studentId, String studentName, double studentMarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMarks = studentMarks;
        this.studentRank = calculateRank(studentMarks);
        this.left = this.right = null;
    }

    // Method to calculate the rank based on marks
    private String calculateRank(double marks) {
        if (marks < 5.0) return "Fail";
        else if (marks < 6.5) return "Medium";
        else if (marks < 7.5) return "Good";
        else if (marks < 9.0) return "Very Good";
        else return "Excellent";
    }
}

// Binary Search Tree (BST) class
class StudentBST {
    StudentNode root;

    // Constructor
    public StudentBST() {
        root = null;
    }

    // Method to add a new student node
    public void addStudent(int studentId, String studentName, double studentMarks) {
        root = addRecursive(root, studentId, studentName, studentMarks);
    }

    private StudentNode addRecursive(StudentNode node, int studentId, String studentName, double studentMarks) {
        if (node == null) {
            return new StudentNode(studentId, studentName, studentMarks);
        }

        if (studentId < node.studentId) {
            node.left = addRecursive(node.left, studentId, studentName, studentMarks);
        } else if (studentId > node.studentId) {
            node.right = addRecursive(node.right, studentId, studentName, studentMarks);
        }

        return node;
    }

    // Method to search a student by their ID
    public StudentNode searchStudent(int studentId) {
        return searchRecursive(root, studentId);
    }

    private StudentNode searchRecursive(StudentNode node, int studentId) {
        if (node == null || node.studentId == studentId) {
            return node;
        }

        if (studentId < node.studentId) {
            return searchRecursive(node.left, studentId);
        } else {
            return searchRecursive(node.right, studentId);
        }
    }

    // Method to delete a student by their ID
    public void deleteStudent(int studentId) {
        root = deleteRecursive(root, studentId);
    }

    private StudentNode deleteRecursive(StudentNode node, int studentId) {
        if (node == null) {
            return null;
        }

        if (studentId < node.studentId) {
            node.left = deleteRecursive(node.left, studentId);
        } else if (studentId > node.studentId) {
            node.right = deleteRecursive(node.right, studentId);
        } else {
            // Node to be deleted found
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.studentId = findMin(node.right).studentId;
            node.right = deleteRecursive(node.right, node.studentId);
        }

        return node;
    }

    private StudentNode findMin(StudentNode node) {
        return node.left == null ? node : findMin(node.left);
    }

    // Method to display all students in in-order traversal (sorted order)
    public void displayStudents() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(StudentNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println("ID: " + node.studentId + ", Name: " + node.studentName + ", Marks: " + node.studentMarks + ", Rank: " + node.studentRank);
            inOrderTraversal(node.right);
        }
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        StudentBST studentTree = new StudentBST();

        // Add some students
        studentTree.addStudent(1001, "John Doe", 8.5);
        studentTree.addStudent(1002, "Jane Smith", 6.0);
        studentTree.addStudent(1003, "Emily Davis", 9.5);
        studentTree.addStudent(1004, "Michael Brown", 4.5);
        studentTree.addStudent(1005, "Sarah Johnson", 7.2);

        // Display all students (in sorted order by ID)
        System.out.println("All Students:");
        studentTree.displayStudents();

        // Search for a student by ID
        System.out.println("\nSearch for student with ID 1003:");
        StudentNode student = studentTree.searchStudent(1003);
        if (student != null) {
            System.out.println("Found: " + student.studentName + ", Marks: " + student.studentMarks + ", Rank: " + student.studentRank);
        } else {
            System.out.println("Student not found.");
        }

        // Delete a student by ID
        System.out.println("\nDeleting student with ID 1004:");
        studentTree.deleteStudent(1004);

        // Display all students after deletion
        System.out.println("\nAll Students after deletion:");
        studentTree.displayStudents();
    }
}
