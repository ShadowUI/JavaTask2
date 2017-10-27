package by.tc.task02.dao.impl;

import by.tc.task02.dao.XmlParserDAO;
import by.tc.task02.dao.XmlFileReader;
import by.tc.task02.dao.exeption.DAOException;
import by.tc.task02.entity.ComplexTag;
import by.tc.task02.entity.Tag;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParserDAOImpl implements XmlParserDAO {
	private static final Pattern OPEN_TAG = Pattern.compile("[<]([A-z0-9-]+)([ A-z0-9='\"]*)[>]");
	private static final Pattern CLOSE_TAG = Pattern.compile("[<][/]([A-z0-9]+)[>]");
	private static int KEY = 0;
	private static int VALUE = 1;
	private static final String FILE_NAME = "task02.xml";
	private XmlFileReader xmlFileReader = new XmlFileReader(FILE_NAME);
	private Tag root;
	private static Stack<String> lexemesStack = new Stack<>();
	private static Stack<Tag> simpleTagStack = new Stack<>();
	private static Stack<Tag> complexTagStack = new Stack<>();


	public Tag parse() throws DAOException {
		int depth = 0;
		ArrayList<String> xmlFileContent = xmlFileReader.readXmlFileContent();
		for (String lexeme: xmlFileContent) {
			if(isCloseTag(lexeme)) {
				String tagContent;
				String openTag;
				String tagName;
				Tag tag;
				String topStackLexeme = lexemesStack.pop();
				if(!isOpenTag(topStackLexeme)){
					tagContent = topStackLexeme;
					openTag = lexemesStack.pop();

					Matcher m = OPEN_TAG.matcher(openTag);
					m.matches();
					tagName = m.group(1);
					depth--;
					tag = new Tag(tagName,tagContent,depth);
				} else {
					tagContent = "";
					openTag = topStackLexeme;
					Matcher m = OPEN_TAG.matcher(openTag);
					m.matches();
					tagName = m.group(1);
					depth--;
					tag = new ComplexTag(tagName, tagContent, depth);
				}
				Matcher m = OPEN_TAG.matcher(openTag);
				m.matches();
				String attributesLine = m.group(2).trim();
				if(!attributesLine.isEmpty()){
					String[] attributes = attributesLine.split(" ");

					for(int i = 0;i < attributes.length;i++) {
						String[] attributeWithValue = attributes[i].split("=");

						tag.addAttribute(attributeWithValue[KEY],attributeWithValue[VALUE]);
					}
				}
				if(tag instanceof ComplexTag) {
					if(!simpleTagStack.isEmpty()){
						while (!simpleTagStack.isEmpty()){
							((ComplexTag) tag).addTag(simpleTagStack.pop());
						}
						complexTagStack.push(tag);
					} else {
						while (!complexTagStack.isEmpty()){
							((ComplexTag) tag).addTag(complexTagStack.pop());
						}
						complexTagStack.push(tag);
					}
				} else {
					simpleTagStack.push(tag);
				}
			} else {
				lexemesStack.push(lexeme);
				depth++;
			}
		}
		root = complexTagStack.pop();
		return root;
	}

	private boolean isCloseTag(String lexeme) {
		return CLOSE_TAG.matcher(lexeme).find();
	}

	private boolean isOpenTag(String lexeme) {
		return OPEN_TAG.matcher(lexeme).find();
	}
}
