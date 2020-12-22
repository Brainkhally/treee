package university;

public class Tree {

    private static class Node {
        int key;
        Object inf;
        Node left;
        Node right;

        /*  public Node(int key)
          {
              this.key=key;
          }
          */
        public Node(int key, Object inf) {
            this.key = key;
            this.inf = inf;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", inf=" + inf +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(int key, Object inf) {
        if (root == null) {
            root = new Node(key, inf);
        } else {
            Node parent = findNode(root, key);
            if (key < parent.key) {
                parent.left = new Node(key, inf);
            } else {
                parent.right = new Node(key, inf);
            }
        }
    }

    public Node findNode(Node current, int key) {
        if (key < current.key) {
            if (current.left == null) {
                return current;
            } else {
                return findNode(current.left, key);
            }
        } else {
            if (current.right == null) {
                return current;
            } else {
                return findNode(current.right, key);
            }
        }
    }

    public Node find(Node current, int key) {
        if (current!=null && key < current.key) {

                return find(current.left, key);
            }
        else {
            if(current!=null && key>current.key)
            {
                return find(current.right,key);
            }
            else
            {
                return  current;
            }
        }

    }

    public boolean checkKey(int key) {
        Node tmp=find(root,key);
        return tmp!=null;
    }

    public Node getParents(Node current, int key) {
        Node temp;
        if (current.key < key) {
            temp = current.right;
        }
        else {
            temp = current.left;
        }

        if (temp == null){
            return null;
        }

        if (temp.key == key) {
            return current;
        }
        else {
            return getParents(temp, key);
        }
    }

    /*реализовать методы обхода дерева: симметричный, прямой, обратный*/

    public void detourSymmetrical(Node current){
        //Рекурсивна функція
        //якщо поточний елемент існує
        if (current != null){
            //виводимо ліве піддерево
            detourSymmetrical(current.left);
            //виводимо поточний елемент
            System.out.print(current.key + ", ");
            //виводимо праве піддерево
            detourSymmetrical(current.right);
        }
    }

    public void detourDirect(Node current){
        //Рекурсивна функція прямого обходу
        //якщо поточний елемент існує:
        if (current != null){
            //виводимо поточний елемент
            System.out.print(current.key + ", ");
            //виводимо ліве піддерево
            detourDirect(current.left);
            //виводимо праве піддерево
            detourDirect(current.right);
        }
    }

    public void detoutReverse(Node current){
        //Рекурсивна функція зворотного обходу
        //якщо поточний елемент існує
        if (current != null){
            //виводимо ліве піддерево
            detoutReverse(current.left);
            //виводимо праве піддерево
            detoutReverse(current.right);
            //виводимо поточний елемент
            System.out.print(current.key + ", ");
        }
    }


    public void deleteNode(int key){
        //дерево пустое
        if (root == null) {return;}

        //Видалення кореня
        if (root.key == key) {
            //root = null
            root = null;
            return;
        }

        Node parent = getParents(root, key);
        //Элемента нет в дереве
        if (parent == null){
            return;
        }

        Node element;
        boolean isRide = false;
        if (parent.left.key == key){
            element = parent.left;
        }
        else {
            element = parent.right;
            isRide = true;
        }

        //Видалення листа
        if (element.left == null && element.right == null){
            if (isRide){ parent.right = null;}
            else {parent.left = null;}
            return;
        }

        //Видалення з одним нащадком
        if (element.left == null || element.right == null){
            //Якщо не маємо лівого нащадка, встановлюємо правий батьківського вузол рівним правому поточному
            //Якщо не маємо правого нащадка, встановлюємо лівий батьківського вузол рівним лівому поточному
            Node tmp = (element.left == null) ? element.right : element.left;
            if (isRide){
                parent.right = tmp;
            }
            else {
                parent.left = tmp;
            }
            return;
        }

        //Алгоритм видалення з двома нащадками
        Node minElement = element.right;
        while (minElement.left != null){
            minElement = minElement.left;
        }

        getParents(element.right, minElement.key).left = minElement.right;
        minElement.right = element.right;
        if (isRide){ parent.right = minElement;}
        else {parent.left = minElement;}
        minElement.left = element.left;
    }
}
