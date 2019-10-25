import java.util.*;

public class BST<T> {

	Node root;

	class Node<T> {

		Comparable data;
		Node left;
		Node right;

		public Node(Comparable item) {
			data = item;
		}
}


	public boolean find(Comparable item) {
		return find(item, root);
		//Return true if item is found in BST, false otherwise
	}

	public boolean find(Comparable item, Node node) { 
		//base case --> check if empty
		if(node == null) {
			System.out.println("Empty.");
			return false;
		}

		if(item == node.data)
			return true;

		//check through left + right node recursively
		if(node.data.compareTo(item) < 0) //if target is LESS than the root, search left
			return find(item, node.left);
		else
			return find(item, node.right);

	}

	public void insert(Comparable item) { //insert item, keeping duplicates in their own nodes
		root = insert(item,root); //starting from root, search till we hit a leaf node
	}

	public Node insert(Comparable item, Node node) {
		if(node == null) //Base case: if tree is empty, return a new node
			return new Node(item);

		if(node.data==item)
			return node;

		if(item.compareTo(node.data) < 0) {
			node.right = insert(item,node.right);
			return node;
		}
		else {
			node.left = insert(item,node.left);
			return node;
		}
	}

	public void print() { //Print items in order using println
		print(root);
	}

	public void print(Node node) {
		if(node == null) {
			System.out.println("Empty.");
		}
		else {
			print(node.left);
			System.out.println(node.data);
			print(node.right);
		}	

	}

	public void delete(Comparable item) {
		//Delete first instance of item from BST
		root = delete(item,root);
	}

	public Node delete(Comparable item, Node node) {
		if(node == null)
			return null;

		if(item.compareTo(node.data)==0) { //check if node has children
			if(node.left == null)
				return node.right; //return right child
			else if(node.right == null)
				return node.left;
			else if(node.right.left == null) {
				node.data = node.right.data; //replace it with right child so it gets deleted ('adopt' right child)
				node.right = node.right.right;
				return node;
			}
			else {
				node.data = remove_smallest(node.right);
				return node;
			}

		}

		else if(node.data.compareTo(item) > 0)
				node.left = delete(item, node.left);
		else
			node.right = delete(item,node.right);
		return node;
	}

	Comparable remove_smallest(Node node) {
		if(node.left.left == null) { //if the node's left has no left child
			Comparable smallest = node.left.data;
			node.left = node.left.right; //left most thing on right child
			return smallest;
		}
		else {
			return remove_smallest(node.left);
		}
	}
}