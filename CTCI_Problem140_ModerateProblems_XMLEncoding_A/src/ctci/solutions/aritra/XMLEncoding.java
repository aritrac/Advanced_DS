package ctci.solutions.aritra;

import java.util.ArrayList;

/*
 * Question: Since XML is very verbose, you are given a way of encoding it where each tag gets mapped to a predefined integer value. The language/grammar is as follows
 * 
 * Element   --> Tag Attributes END Children END
 * Attribute --> Tag Value
 * END       --> 0
 * Tag       --> Some predefined mapping to int
 * Value     --> string value
 * 
 * For example, the following XML might be converted into the compressed string below (assuming a mapping of family -> 1, person ->2, firstName -> 3, lastName -> 4, state -> 5)
 * 
 * <family lastName="McDowell" state="CA">
 * 	<person firstName="Gayle">Some Message</person>
 * <family>
 * 
 * Becomes:
 * 
 * 1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0
 * 
 * Solution: Since we know the element will be passed in as an Element and Attribute, our code is reasonably simple. We can implement this by applying a tree-like approach.
 * We repeatedly call encode() on parts of the XML structure, handling the code in slightly different ways depending on the type of the XML element.
 */

public class XMLEncoding {
	void encode(Element root, StringBuilder sb){
		encode(root.getNameCode(), sb);
		for(Attribute a : root.attributes){
			encode(a,sb);
		}
		encode("0",sb);
		if(root.value != null && root.value != ""){
			encode(root.value, sb);
		}else{
			for(Element e : root.children){
				encode(e, sb);
			}
		}
		encode("0",sb);
	}
	
	void encode(String v, StringBuilder sb){
		sb.append(v);
		sb.append(" ");
	}
	
	void encode(Attribute attr, StringBuilder sb){
		encode(attr.getTagCode(), sb);
		encode(attr.value, sb);
	}
	
	String encodeToString(Element root){
		StringBuilder sb = new StringBuilder();
		encode(root, sb);
		return sb.toString();
	}
}

class Element {
	public ArrayList<Attribute> attributes;
	public ArrayList<Element> children;
	public String name;
	public String value;
	
	public Element(String n) {
		name = n;
		attributes = new ArrayList<Attribute>();
		children = new ArrayList<Element>();
	}
	
	public Element(String n, String v) {
		name = n;
		value = v;
		attributes = new ArrayList<Attribute>();
		children = new ArrayList<Element>();
	}	
	
	public String getNameCode() {
		if (name == "family") {
			return "1";
		} else if (name == "person") {
			return "2";
		} else if (name == "firstName") {
			return "3";
		} else if (name == "lastName") {
			return "4";
		} else if (name == "state") {
			return "5";
		} 
		return "--";
	}
	
	public void insert(Attribute attribute) {
		attributes.add(attribute);
	}
	
	public void insert(Element child) {
		children.add(child);
	}
}

class Attribute {
	public String tag;
	public String value;
	public Attribute(String t, String v) {
		tag = t;
		value = v;
	}
	
	public String getTagCode() {
		if (tag == "family") {
			return "1";
		} else if (tag == "person") {
			return "2";
		} else if (tag == "firstName") {
			return "3";
		} else if (tag == "lastName") {
			return "4";
		} else if (tag == "state") {
			return "5";
		} 
		return "--";
	}
}
