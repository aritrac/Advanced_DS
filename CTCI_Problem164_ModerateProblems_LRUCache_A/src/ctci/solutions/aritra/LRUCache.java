package ctci.solutions.aritra;

import java.util.HashMap;

/*
 * Question: Design and build a 'least recently used' cache, which evicts the least recently used item. The cache should map from keys to values (allowing you to insert
 * and retrieve a value associated with a particular key) and be initialized with a max size. When it is full, it should evict the least recently used item. You can assume the keys
 * are integers and the values are strings.
 * 
 * Solution: For this we could use a linked list, ordered by the most recently used. This would make it easy to mark an item as the most recently used (just put it in the front of the list)
 * or to remove the least recently used item(remove the end).
 * Unfortunately, this does not offer a quick way to look up an item by its key. We could iterate through the linked list and find the item by key. But this could get
 * very slow. So we need to use a HashMap for retrieval and a linked list for ordering.
 * The linked list looks as it did in the earlier example, but now it's a doubly linked list. This allows us to easily remove an element from the middle of the linked list.
 * The hash table now maps to each linked list node rather than the value.
 * 
 * The algorithm now operates as follows:
 * Inserting Key, Value pair : Create a linked list node with key value. Insert into head of linked list. Insert key -> node mapping into hash table.
 * Retrieving Value by Key: Look up node in hash table and return value. Update most recently used item.
 * Finding Least Recently Used: Least recently used item will be found at the end of the linked list.
 * Updating Most Recently Used: Move node to front of linked list. Hash table does not need to be updated.
 * Eviction: Remove tail of linked list. Get key from linked list node and remove key from hash table.
 * 
 * The code below implements these classes and algorithms.
 */

public class LRUCache {
	private int maxCacheSize;
	private HashMap<Integer, LinkedListNode> map = new HashMap<Integer, LinkedListNode>();
	private LinkedListNode listHead = null;
	private LinkedListNode listTail = null;
	
	public LRUCache(int maxSize){
		maxCacheSize = maxSize;
	}
	
	//Get value for key and mark as most recently used
	public String getValue(int key){
		LinkedListNode item = map.get(key);
		if(item == null)
			return null;
		//Move to front of list to mark as most recently used
		if(item != listHead){
			removeFromLinkedList(item);
			insertAtFrontOfLinkedList(item);
		}
		return item.value;
	}
	
	//Remove node from linked list
	private void removeFromLinkedList(LinkedListNode node){
		if(node == null)
			return;
		if(node.previous != null)
			node.previous.next = node.next;
		if(node.next != null)
			node.next.previous = node.previous;
		if(node == listTail)
			listTail = node.previous;
		if(node == listHead)
			listHead = node.next;
	}
	
	//Insert node at front of linked list
	private void insertAtFrontOfLinkedList(LinkedListNode node){
		if(listHead == null){
			listHead = node;
			listTail = node;
		}else{
			listHead.previous = node;
			node.next = listHead;
			listHead = node;
		}
	}
	
	//Remove key/value pair from cache, deleting from hashtable and linked list
	public boolean removeKey(int key){
		LinkedListNode node = map.get(key);
		removeFromLinkedList(node);
		map.remove(key);
		return true;
	}
	
	//Puts key/value pair in cache. Removes old value for key if necessary. Inserts pair into linked list and hash table
	public void setKeyValue(int key, String value){
		//Remove if already there
		removeKey(key);
		
		//If full, remove least recently used item from cache
		if(map.size() >= maxCacheSize && listTail != null){
			removeKey(listTail.key);
		}
		
		//Insert new node.
		LinkedListNode node = new LinkedListNode(key, value);
		insertAtFrontOfLinkedList(node);
		map.put(key, node);
	}
}

class LinkedListNode{
	public String value;
	public Integer key;
	public LinkedListNode next;
	public LinkedListNode previous;
	
	public LinkedListNode(String x){
		this.value = x;
	}
	
	public LinkedListNode(Integer key, String value){
		this.key = key;
		this.value = value;
	}
}
