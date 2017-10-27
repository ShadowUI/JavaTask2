package by.tc.task02.dao;

import by.tc.task02.dao.exeption.DAOException;
import by.tc.task02.dao.constants.DAOConstants;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class XmlFileReader {
	private String xmlFileName;
	private static ArrayList<String> tokensList = new ArrayList<>();
	private static final String PATH = "src/main/resources/task02.xml";

	public XmlFileReader() {}

	public XmlFileReader(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public String getXmlFileName() {
		return xmlFileName;
	}

	public ArrayList<String> readXmlFileContent() throws DAOException {

		try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
			String tagContentBuffer = "";
			while (reader.ready()) {
				String xmlLine = (reader.readLine()).trim();
				if (isDeclarationOrCommentLine(xmlLine))
					continue;
				if(isContainsAnOpenTag(xmlLine)) {
					addOpenTag(xmlLine);
					if(isContainsCloseTag(xmlLine)) {
						addContentBetweenTwoTags(xmlLine);
						addCloseTag(xmlLine);
					} else {
						String lineContent = xmlLine.substring(xmlLine.indexOf(DAOConstants.LAST_BRACKET)+
								DAOConstants.INCLUDING_LAST_BRACKET, xmlLine.length());
						if(!lineContent.isEmpty()) {
							tagContentBuffer += DAOConstants.SPACE + lineContent;
						}
					}
				} else if(isContainsCloseTag(xmlLine)) {
					String lineContent = xmlLine.substring(DAOConstants.BEGIN_OF_LINE,xmlLine.indexOf(DAOConstants.CLOSING_TAG_BRACKET));
					if(!lineContent.isEmpty()) {
						tagContentBuffer += DAOConstants.SPACE + lineContent;
						addContent(tagContentBuffer);
						tagContentBuffer = "";
					}
					addCloseTag(xmlLine);

				} else {
					tagContentBuffer += xmlLine;
				}
			}
			return tokensList;

		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		} catch (FileNotFoundException e) {
			throw new DAOException("File not found!",e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}

	private boolean isDeclarationOrCommentLine(String xmlLine) {
		return ((patternMatch(DAOConstants.XML_DECLARATION,xmlLine) ||
				patternMatch(DAOConstants.COMMENT_TAG,xmlLine)));
	}

	private boolean isContainsAnOpenTag(String xmlLine) {
		return patternMatch(DAOConstants.OPEN_TAG,xmlLine);
	}

	private boolean isContainsCloseTag(String xmlLine) {
		return patternMatch(DAOConstants.CLOSE_TAG,xmlLine);
	}

	private void addOpenTag(String xmlLine) {
		tokensList.add(xmlLine.substring(DAOConstants.BEGIN_OF_LINE,
				xmlLine.indexOf(DAOConstants.LAST_BRACKET)+ DAOConstants.INCLUDING_LAST_BRACKET));
	}

	private void addCloseTag(String xmlLine) {
		tokensList.add(xmlLine.substring(xmlLine.indexOf(DAOConstants.CLOSING_TAG_BRACKET),xmlLine.length()));
	}

	private void addContentBetweenTwoTags(String xmlLine) {
		tokensList.add(xmlLine.substring(xmlLine.indexOf(DAOConstants.LAST_BRACKET)+
				DAOConstants.INCLUDING_LAST_BRACKET,
				xmlLine.indexOf(DAOConstants.CLOSING_TAG_BRACKET,xmlLine.indexOf(DAOConstants.LAST_BRACKET)+
						DAOConstants.INCLUDING_LAST_BRACKET)));
	}

	private void addContent(String tagContentBuffer) {
		tokensList.add(tagContentBuffer);
	}

	private boolean patternMatch(Pattern pattern, String xmlLine) {
		return pattern.matcher(xmlLine).find();
	}
}
