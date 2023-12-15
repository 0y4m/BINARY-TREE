import java.util.Scanner;
import java.util.InputMismatchException;

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int key) {
        root = insert(root, key);
        System.out.println("-------------------------------------------");
        System.out.println("Inserted: " + key);
        System.out.println("Tree after insert:");
        System.out.print("Inorder: ");
        inorder();
        System.out.print("Postorder: ");
        postorder();
        System.out.print("Pretorder: ");
        preorder();
        System.out.println("-------------------------------------------");
    }

    private Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
            // Locating where the number  is inserted in the binary tree....
            System.out.println("-------------------------------------------");
            System.out.println("Inserted " + key + " to the left of Node " + root.data);
        } else if (key > root.data) {
            root.right = insert(root.right, key);
            System.out.println("-------------------------------------------");
            System.out.println("Inserted " + key + " to the right of Node " + root.data);
        }
        return root;
    }

    public void delete(int key) {
        root = del(root, key);
        System.out.println("-------------------------------------------");
        System.out.println("Deleted: " + key);
        System.out.println("Tree after delete:");
        System.out.print("Inorder: ");
        inorder();
        System.out.print("Postorder: ");
        postorder();
        System.out.print("Preorder: ");
        preorder();
        System.out.println("-------------------------------------------");
    }

    private Node del(Node root, int key) {
        if (root == null) {
            System.out.println("-------------------------------------------");
            System.out.println("Node with value " + key + " not found in the binary for delete");
            return root;
        }
    
        if (key < root.data) {
            root.left = del(root.left, key);
            
        } else if (key > root.data) {
            root.right = del(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
    
            root.data = findValue(root.right);
    
            root.right = del(root.right, root.data);
            
        }
    
        return root;
    }
    

    private int findValue(Node root) {
        int val = root.data;
        while (root.left != null) {
            val = root.left.data;
            root = root.left;
        }
        return val;
    }

    public boolean search(int key) {
        return srch(root, key);
    }

    private boolean srch(Node root, int key) {
        if (root == null || root.data == key)
            return root != null;

        if (key < root.data)
            return srch(root.left, key);
        else
            return srch(root.right, key);
    }

    public void inorder() {
        in(root);
        System.out.println();
    }

    private void in(Node root) {
        if (root != null) {
            in(root.left);
            System.out.print(root.data + " ");
            in(root.right);
        }
    }

    public void preorder() {
        pre(root);
        System.out.println();
    }

    private void pre(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            pre(root.left);
            pre(root.right);
        }
    }

    public void postorder() {
        post(root);
        System.out.println();
    }

    private void post(Node root) {
        if (root != null) {
            post(root.left);
            post(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scan = new Scanner(System.in);

        int size = 0;
        while (size <= 0) {
            try {
                System.out.println("==========================");
                System.out.print("Enter the number of nodes: ");
                size = scan.nextInt();
                if (size <= 0) {
                    System.out.println("Please enter an integer greater than 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scan.next();
            }
        }
        System.out.println("==========================");

        System.out.println("Insert the values of nodes:");

        for (int i = 0; i < size; i++) {
            while (true) {
                try {
                    System.out.print("Enter an element: ");
                    int element = scan.nextInt();
                    tree.insert(element);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    scan.next();
                }
            }
        }

        System.out.println("Inorder:");
        tree.inorder();
        System.out.println("Postorder:");
        tree.postorder();
        System.out.println("Preorder:");
        tree.preorder();

        while (true) {
            try {
                System.out.println("==============================================================================");
                System.out.print("Choose an operation ([1] = insert | [2] = delete | [3] = search | [4] = exit): ");
                int op = scan.nextInt();
                System.out.println("==============================================================================");

                if (op == 4) {
                    break;
                }

                switch (op) {
                    case 1:
                        System.out.println("Enter a value to insert:");
                        int val = scan.nextInt();
                        tree.insert(val);
                        break;
                    case 2:
                        System.out.println("Enter a value to delete:");
                        int valueToDelete = scan.nextInt();
                        tree.delete(valueToDelete);
                        break;
                    case 3:
                        System.out.println("Enter the value to search:");
                        int valueToSearch = scan.nextInt();
                        boolean isFound = tree.search(valueToSearch);
                        System.out.println("Node with value " + valueToSearch + " found: " + isFound);
                        break;
                    default:
                        System.out.println("Invalid operation. Please choose [1] = [2] = [2] = [4].");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scan.next();
            }
        }

        scan.close();
    }
}