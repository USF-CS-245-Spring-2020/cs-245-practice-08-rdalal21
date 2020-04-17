public class BST<T extends Comparable<T>>{
	private class BinaryNode<T>{
		BinaryNode<T> left;
		BinaryNode<T> right;
		T data;
		
		public BinaryNode(T item){
			data=item;
			left=null;
			right=null;
		}
	}
	
	BinaryNode<T> root;
	
	
	//insert method , x:the item to insert
	public void insert(T x){
		root=insert(root, x);
	}

	public BinaryNode<T> insert(BinaryNode<T> node,T x){
		/* If the tree is empty, return a new node */
		if(node==null)
			return new BinaryNode<T>(x);
		/* Otherwise, recur down the tree */
		int compareResult=x.compareTo(node.data);
		if(compareResult<0)
			node.left=insert(node.left,x);
		else if(compareResult>0)
			node.right=insert(node.right,x);
		else
			;//Duplicate, do nothing
		
		/* return the node pointer */
		return node;
	}
	
	
	/*find  method return true if item is found in the BST , false otherwise*/
	public boolean find(T x){
		return find(root,x);
	}
	
	protected boolean find(BinaryNode<T> node,T x){
		if(node==null)
			return false;
		int compareResult=x.compareTo(node.data);
		if(compareResult==0)
			return true;
		else if(compareResult<0)
			return find(node.left,x);
		else
			return find(node.right,x);
	}
	
	
	/*delete method*/
	public void delete(T x){
		root=delete(root,x);
	}

	public BinaryNode<T> delete(BinaryNode<T> node, T x){
		/* If the tree is empty, a base case*/
		if(node==null)
			return node;
		/* Otherwise, recur down the tree */
		int compareResult=x.compareTo(node.data);
		if(compareResult > 0){
			node.right=delete(node.right,x);
			return node;
		}
		else if(compareResult <0){
			node.left=delete(node.left,x);
			return node;
		}
		else{	
			if(node.left==null)
				return node.right;
			else if(node.right==null)
				return node.left;
			else{
				if(node.right.left==null){
					node.data=node.right.data;
					node.right=node.right.right;
				}
				else
					node.data=minValue(node.right);
			}
			return node;
		}
	}
	/* for Node with two children, smallest value utility function*/
	public T minValue(BinaryNode<T> node){
		while (node.left !=null){
			T min=node.left.data;
			node.left=node.left.right;
			return min;
		}
					return minValue(node.left);
	}

	//print method
	public void print(){
		inOrder(root);
	}
	//A utility function to do inorder traversal of BST
	public void inOrder(BinaryNode<T> node){
		if(node!=null){
			inOrder(node.left);
			System.out.print(node.data);
			inOrder(node.right);
		}
	}

}