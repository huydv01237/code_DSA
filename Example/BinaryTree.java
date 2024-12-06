package Example;

public class BinaryTree {

    // Node class representing each student
    class Node {
        int studentId;
        String name;
        double marks;
        String rank;
        Node left, right;

        // Constructor to initialize the student details
        public Node(int studentId, String name, double marks) {
            this.studentId = studentId;
            this.name = name;
            this.marks = marks;
            this.rank = calculateRank(marks);
            this.left = null;
            this.right = null;
        }

        // Method to calculate the rank based on marks
        public String calculateRank(double marks) {
            if (marks < 5.0) {
                return "Fail";
            } else if (marks >= 5.0 && marks < 6.5) {
                return "Medium";
            } else if (marks >= 6.5 && marks < 7.5) {
                return "Good";
            } else if (marks >= 7.5 && marks < 9.0) {
                return "Very Good";
            } else if (marks >= 9.0 && marks <= 10.0) {
                return "Excellent";
            }
            return "Unknown";
        }
    }

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    // Method to insert a new student
    public void insert(int studentId, String name, double marks) {
        Node newNode = new Node(studentId, name, marks);
        if (root == null) {
            root = newNode;
        } else {
            insertRecursive(root, newNode);
        }
    }

    // Helper method to insert a new node recursively
    private void insertRecursive(Node current, Node newNode) {
        if (newNode.studentId < current.studentId) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                insertRecursive(current.left, newNode);
            }
        } else {
            if (current.right == null) {
                current.right = newNode;
            } else {
                insertRecursive(current.right, newNode);
            }
        }
    }

    // Method to search for a student by studentId
    public Node search(int studentId) {
        return searchRecursive(root, studentId);
    }

    // Helper method for recursive search
    private Node searchRecursive(Node current, int studentId) {
        if (current == null || current.studentId == studentId) {
            return current;
        }
        if (studentId < current.studentId) {
            return searchRecursive(current.left, studentId);
        }
        return searchRecursive(current.right, studentId);
    }

    // Method to delete a student by studentId
    public void delete(int studentId) {
        root = deleteRecursive(root, studentId);
    }

    // Helper method for recursive deletion
    private Node deleteRecursive(Node root, int studentId) {
        if (root == null) {
            return root;
        }
        if (studentId < root.studentId) {
            root.left = deleteRecursive(root.left, studentId);
        } else if (studentId > root.studentId) {
            root.right = deleteRecursive(root.right, studentId);
        } else {
            // Node to be deleted
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // Node with two children
                Node minLargerNode = getMin(root.right);
                root.studentId = minLargerNode.studentId;
                root.name = minLargerNode.name;
                root.marks = minLargerNode.marks;
                root.rank = minLargerNode.rank;
                root.right = deleteRecursive(root.right, minLargerNode.studentId);
            }
        }
        return root;
    }

    // Method to find the minimum node in a subtree
    private Node getMin(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Method for in-order traversal (sorted order by studentId)
    public void inorderTraversal() {
        inorderRecursive(root);
    }

    // Helper method for recursive in-order traversal
    private void inorderRecursive(Node current) {
        if (current != null) {
            inorderRecursive(current.left);
            System.out.println("Student ID: " + current.studentId + ", Name: " + current.name + ", Marks: " + current.marks + ", Rank: " + current.rank);
            inorderRecursive(current.right);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(1, "John Doe", 8.5);
        binaryTree.insert(2, "Jane Smith", 9.2);
        binaryTree.insert(3, "Peter Parker", 6.8);
        binaryTree.insert(4, "Tony Stark", 4.5);

        // Display all students in sorted order by student ID
        binaryTree.inorderTraversal();

        // Search for a student
        Node student = binaryTree.search(2);
        if (student != null) {
            System.out.println("Found: " + student.name + ", Marks: " + student.marks + ", Rank: " + student.rank);
        } else {
            System.out.println("Student not found.");
        }

        // Delete a student by ID
        binaryTree.delete(3);

        // Display again after deletion
        System.out.println("\nAfter deletion:");
        binaryTree.inorderTraversal();
    }
}
