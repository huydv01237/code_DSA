package Example;

public class AIgorithmBinaryTree {

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
        public void insert(Student student) {
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
            root = deleteRec(root, student_id);
        }

        private Student deleteRec(Student node, int student_id) {
            if (node == null) {
                return node;
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
            return searchRec(root, student_id);
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
        // 
}