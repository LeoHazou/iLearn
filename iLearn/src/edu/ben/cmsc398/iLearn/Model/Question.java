/**
 * 
 */
package edu.ben.cmsc398.iLearn.Model;

/**
 * @author Leo
 *
 */
public class Question {

	/**
	 * Default constructor
	 * @param thirdWrongAns 
	 * @param secondWrongAns 
	 * @param firstWrongAns 
	 * @param correctAns 
	 * @param question 
	 * @param explanation
	 */
	public Question(String question, String correctAns, String firstWrongAns, String secondWrongAns, String thirdWrongAns, String explanation) 
	{
		this.question = question;
		this.correctAns = correctAns;
		this.firstWrongAns = firstWrongAns;
		this.secondWrongAns = secondWrongAns;
		this.thirdWrongAns = thirdWrongAns;
		this.explanation = explanation;
	}
	
	/**
	 * Custom constructor, used for retrieving questions for edit package functionality
	 */
	public Question(){}
	
	/**
	 * private variables
	 */
	private int ID;
	private String question;
	private String correctAns;
	private String firstWrongAns;
	private String secondWrongAns;
	private String thirdWrongAns;
	private String explanation;
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @return the correctAns
	 */
	public String getCorrectAns() {
		return correctAns;
	}
	/**
	 * @return the firstWrongAns
	 */
	public String getFirstWrongAns() {
		return firstWrongAns;
	}
	/**
	 * @return the secondWrongAns
	 */
	public String getSecondWrongAns() {
		return secondWrongAns;
	}
	/**
	 * @return the thirdWrongAns
	 */
	public String getThirdWrongAns() {
		return thirdWrongAns;
	}
	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @param correctAns the correctAns to set
	 */
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	/**
	 * @param firstWrongAns the firstWrongAns to set
	 */
	public void setFirstWrongAns(String firstWrongAns) {
		this.firstWrongAns = firstWrongAns;
	}
	/**
	 * @param secondWrongAns the secondWrongAns to set
	 */
	public void setSecondWrongAns(String secondWrongAns) {
		this.secondWrongAns = secondWrongAns;
	}
	/**
	 * @param thirdWrongAns the thirdWrongAns to set
	 */
	public void setThirdWrongAns(String thirdWrongAns) {
		this.thirdWrongAns = thirdWrongAns;
	}
	/**
	 * @param explanation the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [question=" + question + ", correctAns=" + correctAns
				+ ", firstWrongAns=" + firstWrongAns + ", secondWrongAns="
				+ secondWrongAns + ", thirdWrongAns=" + thirdWrongAns
				+ ", explanation=" + explanation + "]";
	}
	
}
