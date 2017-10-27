package by.tc.task02.main;

import by.tc.task02.entity.ComplexTag;
import by.tc.task02.entity.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoPrinter {
	public static void print(ComplexTag root) {
		print(root, " ");
	}

	private static void print(ComplexTag element, String spaces) {
		System.out.println(spaces + "name:" + element.getName());
		printAttributes(spaces, element);
		System.out.println(spaces + "nodes:");
		printNodes(element, spaces);
	}

	private static void printNodes(Tag element, String spaces) {
		List<Tag> tags = ((ComplexTag)element).getContentTags();
		if (!tags.isEmpty()) {
			for (int i = tags.size()-1; i>0; i--) {
				Tag tag = tags.get(i);
				if (tag instanceof ComplexTag) {
					printElem(spaces, (ComplexTag) tag);
				} else {
					System.out.println(spaces + "      " + tag.getContent());
				}
			}
		}
	}

	private static void printElem(String space, ComplexTag tag) {
		String spaceNew = space + "      ";
		print(tag, spaceNew);
	}

	private static void printAttributes(String space, ComplexTag element) {
		if (!(element.getAttributes()).isEmpty()) {
			System.out.println(space + "attributes:");
			printAttributes(element, space);
		}
	}

	private static void printAttributes(ComplexTag element, String space) {
		HashMap<String,String> attributes = element.getAttributes();
		for (Map.Entry<String,String> attribute : attributes.entrySet()) {
			System.out.println(space + "   " + attribute.getKey() + "=\"" + attribute.getValue() + "\"");
		}
	}
}

