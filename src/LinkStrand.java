public class LinkStrand implements IDnaStrand{
	
	/**
	 * internal Node class
	 */
	private class Node{
		String info;
		Node next;
		
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	
	/**
	 * instance variables
	 */
	private Node myFirst, myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;
	
	/**
	 * Constructor 1
	 */
	public LinkStrand() {
		this("");
	}
	
	/**
	 * Constructor 2
	 * @param s
	 */
	public LinkStrand(String s) {
		initialize(s);
	}

	@Override
	public long size() {
		return mySize;
	}

	/**
	 * sets myFirst and myLast
	 */
	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
		mySize = source.length();
		myAppends = 0;
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst; 
	}

	/**
	 * returns new instance of LinkStrand
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	/**
	 * updates myAppends by 1
	 * updates size by the size of dna
	 * repoints myLast to the last node (node that just got appended)
	 * myFirst stays the same 
	 */
	@Override
	public IDnaStrand append(String dna) {
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize += dna.length();
		myAppends +=1;
		return this;
	}
	/**
	 * returns string of all nodes of linked list 
	 * using StringBuilder
	 */
	public String toString() {
		Node traveller = myFirst;
		StringBuilder str = new StringBuilder();
		while (traveller!=null) {
			str.append(traveller.info);
			traveller = traveller.next;
		}
		return str.toString();
	}
	
	/**
	 * creates new LinkStrand reversed 
	 * reverses the string in each node 
	 * reverses the linked list
	 * returns LinkStrand reversed
	 */
	@Override
	public IDnaStrand reverse() {
		StringBuilder reversing = new StringBuilder(myFirst.info);
		LinkStrand reversed = new LinkStrand(reversing.reverse().toString());
		Node traveller = myFirst;
		while ((traveller=traveller.next)!=null) {
			StringBuilder reverseFirst = new StringBuilder(traveller.info);
			Node newNode = new Node(reverseFirst.reverse().toString());
			newNode.next = reversed.myFirst;
			reversed.myFirst = newNode;
		}
		reversed.mySize = mySize;
		reversed.myAppends = myAppends; 
		return reversed;
	}

	/**
	 * return myAppends
	 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}

	/**
	 * keeps track of myIndex for a more efficient implementation fo charAt
	 * resets myIndex, myLocalIndex, and myCurrent if myIndex>index
	 */
	@Override
	public char charAt(int index) {
		if(index>=mySize || index<0) {
			throw new IndexOutOfBoundsException(index+"is not a valid index");
		}
		if(myIndex>index) {
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
		
		while(myIndex!=index) {
			myIndex+=1; 
			myLocalIndex+=1;
			if(myLocalIndex>=myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent = myCurrent.next;
			}
		}	
		return myCurrent.info.charAt(myLocalIndex);
	}

}
