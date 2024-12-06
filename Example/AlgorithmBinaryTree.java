package Example;

public class AlgorithmBinaryTree {

    static class Student {
        int student_id;
        String name;
        double marks;
        String rank;
        Student left, right;

        // Constructor to initialize the student
        public Student(int student_id, String name, double marks) {
            this.student_id = student_id;
            this.name = name;
            this.marks = marks;
            this.rank = calculateRank();
            this.left = null;
            this.right = null;
        }

        // Method to calculate rank based on marks
        public String calculateRank() {
            if (this.marks < 5.0) {
                return "Fail";
            } else if (this.marks >= 5.0 && this.marks < 6.5) {
                return "Medium";
            } else if (this.marks >= 6.5 && this.marks < 7.5) {
                return "Good";
            } else if (this.marks >= 7.5 && this.marks < 9.0) {
                return "Very Good";
            } else {
                return "Excellent";
            }
        }
    }

    static class BinaryTree {
        private Student root;

        public BinaryTree() {
            this.root = null;
        }

        // Insert a student into the tree
        public void insert(Student student) {
            if (student == null) {
                System.out.println("Error: Cannot insert a null student.");
                return;
            }
            if (search(student.student_id) != null) {
                System.out.println("Error: Student with ID " + student.student_id + " already exists.");
                return;
            }
            if (root == null) {
                root = student;
            } else {
                insertRec(root, student);
            }
        }

        private void insertRec(Student node, Student student) {
            if (student.student_id < node.student_id) {
                if (node.left == null) {
                    node.left = student;
                } else {
                    insertRec(node.left, student);
                }
            } else if (student.student_id > node.student_id) {
                if (node.right == null) {
                    node.right = student;
                } else {
                    insertRec(node.right, student);
                }
            }
        }

        // Delete a student by ID
        public void delete(int student_id) {
            if (search(student_id) == null) {
                System.out.println("Error: Student with ID " + student_id + " does not exist.");
                return;
            }
            root = deleteRec(root, student_id);
            System.out.println("Student with ID " + student_id + " has been deleted.");
        }

        private Student deleteRec(Student node, int student_id) {
            if (node == null) {
                return null;
            }
            if (student_id < node.student_id) {
                node.left = deleteRec(node.left, student_id);
            } else if (student_id > node.student_id) {
                node.right = deleteRec(node.right, student_id);
            } else {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                }
                node.student_id = minValue(node.right).student_id;
                node.right = deleteRec(node.right, node.student_id);
            }
            return node;
        }

        private Student minValue(Student node) {
            Student current = node;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        // Search for a student by ID
        public Student search(int student_id) {
            Student result = searchRec(root, student_id);
            if (result == null) {
                System.out.println("Error: Student with ID " + student_id + " not found.");
            }
            return result;
        }

        private Student searchRec(Student node, int student_id) {
            if (node == null || node.student_id == student_id) {
                return node;
            }
            if (student_id < node.student_id) {
                return searchRec(node.left, student_id);
            }
            return searchRec(node.right, student_id);
        }

        // Traverse the tree in-order
        public void traverseInOrder() {
            if (root == null) {
                System.out.println("Error: The tree is empty.");
                return;
            }
            traverseInOrderRec(root);
        }

        private void traverseInOrderRec(Student node) {
            if (node != null) {
                traverseInOrderRec(node.left);
                System.out.println("Student ID: " + node.student_id + ", Name: " + node.name + ", Marks: " + node.marks + ", Rank: " + node.rank);
                traverseInOrderRec(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Test cases
        tree.insert(new Student(1, "Alice", 8.5));
        tree.insert(new Student(2, "Bob", 6.0));
        tree.insert(new Student(3, "Charlie", 4.5));

        tree.traverseInOrder();

        // Search for a student
        tree.search(2); // Found
        tree.search(10); // Not found

        // Delete a student
        tree.delete(3); // Deleted
        tree.delete(10); // Error

        tree.traverseInOrder();
    }
}

