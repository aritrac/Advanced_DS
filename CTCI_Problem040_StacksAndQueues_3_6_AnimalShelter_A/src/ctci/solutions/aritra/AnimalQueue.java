package ctci.solutions.aritra;

import java.util.LinkedList;

/*
 * Question: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first out" basis. People must adopt either the oldest based on arrival time
 * of all animals at the shelter or they can select whether they would prefer a dog or a cat and will receive the oldest animal of that type. They cannot select which specific 
 * animal they would like. Create the data structure to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog and dequueCat.
 * You may use the built-in LinkedList data structure
 * 
 * Solution: The approach that is simple, clean and efficient is to simply use separate queues for dogs and cats, and to place them within a wrapper class called AnimalQueue. 
 * We then store some sort of timestamp to mark when each animal was enqueued. When we call dequeue any, we peek at the heads of both the dog and cat and return the oldest.
 */

public class AnimalQueue {
	LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();
	private int order = 0; //acts as timestamp
	
	public void enqueue(Animal a){
		//Order is used like a timestamp, so we can compare order of a dog to a cat
		a.setOrder(order);
		order++;
		
		if(a instanceof Dog) dogs.addLast((Dog)a);
		else if(a instanceof Cat) cats.addLast((Cat)a);
	}
	
	public Animal dequeueAny(){
		//Look at tops of dog and cat queues, and pop the queue with the oldest value
		if(dogs.size() == 0){
			return dequeueCats();
		}else if(cats.size() == 0){
			return dequeueDogs();
		}
		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		if(dog.isOlderThan(cat)){
			return dequeueDogs();
		}else{
			return dequeueCats();
		}
	}
	
	public Dog dequeueDogs(){
		return dogs.poll();
	}
	public Cat dequeueCats(){
		return cats.poll();
	}
	
	public static void main(String[] args) {
		AnimalQueue aq = new AnimalQueue();
		
		aq.enqueue(new Dog("Dog1"));
		aq.enqueue(new Dog("Dog2"));
		aq.enqueue(new Cat("Cat1"));
		aq.enqueue(new Dog("Dog1"));
		
		System.out.println(aq.dequeueAny().name);
		System.out.println(aq.dequeueDogs().name);
		System.out.println(aq.dequeueCats().name);
	}
}

class Dog extends Animal{
	public Dog(String n){
		super(n);
	}
}

class Cat extends Animal{
	public Cat(String n){
		super(n);
	}
}

abstract class Animal{
	private int order;
	protected String name;
	public Animal(String n){
		name = n;
	}
	public void setOrder(int ord){
		order = ord;
	}
	public int getOrder(){
		return order;
	}
	//Compare order of animals to return the older item
	public boolean isOlderThan(Animal a){
		return this.order < a.getOrder();
	}
}
