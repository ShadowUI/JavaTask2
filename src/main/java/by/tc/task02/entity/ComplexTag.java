package by.tc.task02.entity;

import java.util.ArrayList;

public class ComplexTag extends Tag{
	private ArrayList<Tag> contentTags;

	public ComplexTag() {

	}

	public ComplexTag(String name, String content, int depth) {
		super(name, content, depth);
		this.contentTags = new ArrayList<Tag>();
	}

	public ArrayList<Tag> getContentTags() {
		return contentTags;
	}

	public void setContentTags(ArrayList<Tag> contentTags) {
		this.contentTags = contentTags;
	}

	public Tag getTag(int index) {
		return getContentTags().get(index);
	}

	public void addTag(Tag tag) {
		getContentTags().add(tag);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		ComplexTag that = (ComplexTag) o;

		return contentTags != null ? contentTags.equals(that.contentTags) : that.contentTags == null;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (contentTags != null ? contentTags.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		String result = super.toString()+
				"    ";
		for (Tag a: getContentTags()) {
			result += a.toString();
		}
		return result;
	}
}
