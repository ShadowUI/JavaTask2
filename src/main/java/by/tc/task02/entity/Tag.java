package by.tc.task02.entity;

import java.util.HashMap;

public class Tag {
	private String name;
	private String content;
	private int depth;
	private HashMap<String,String> attributes;

	public Tag() {}

	public Tag(String name, String content, int depth) {
		this.name = name;
		this.content = content;
		this.depth = depth;
		this.attributes = new HashMap<String, String>();
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}
	public String getAttribute(String key) {
		return getAttributes().get(key);
	}
	public void addAttribute(String key, String value) {
		getAttributes().put(key, value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tag tag = (Tag) o;

		if (depth != tag.depth) return false;
		if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
		if (content != null ? !content.equals(tag.content) : tag.content != null) return false;
		return attributes != null ? attributes.equals(tag.attributes) : tag.attributes == null;

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + depth;
		result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return name +attributes+ '\n' + "  " +
				 content + '\n';
	}
}
