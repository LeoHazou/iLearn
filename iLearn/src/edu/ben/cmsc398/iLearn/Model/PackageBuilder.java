/**
 * 
 */
package edu.ben.cmsc398.iLearn.Model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Leo
 * 
 */
public class PackageBuilder {

	/**
	 * Default constructor
	 * 
	 * @param pkgName
	 * @param pkgdescription
	 * @param pkgPrice
	 */
	public PackageBuilder(String pkgName, String pkgdescription, double pkgPrice) {
		this.pkgID = -1;
		this.pkgName = pkgName;
		this.pkgDescription = pkgdescription;
		this.pkgPrice = pkgPrice;
		this.setPublished(false);
		this.setStudyMaterial(false);
		this.setStudyMaterialLink("");
		this.setNumberOfQuestions();
	}

	public void randomizeQuestions() {
		Collections.shuffle(this.questions);
	}

	/**
	 * Custom constructor, for ListAll servlet use only
	 * 
	 * @param pkgID
	 * @param pkgName
	 */
	public PackageBuilder(int pkgID, String pkgName) {
		this.pkgID = pkgID;
		this.pkgName = pkgName;
	}

	/**
	 * Custom constructor, to be used to the edit package functionality
	 * 
	 * @param pkgID
	 */
	public PackageBuilder(int pkgID) {
		this.pkgID = pkgID;
	}

	/**
	 * Private variables
	 */

	private int pkgID;
	private String pkgName;
	private String pkgDescription;
	private ArrayList<Question> questions = new ArrayList<Question>();
	private double pkgPrice;
	private String studyMaterialLink;
	private boolean isStudyMaterial;
	private int numberOfQuestions;
	private boolean isPublished;

	/**
	 * @return the pkgID
	 */
	public int getPkgID() {
		return pkgID;
	}

	/**
	 * @return the pkgName
	 */
	public String getPkgName() {
		return pkgName;
	}

	/**
	 * @return the pkgDescription
	 */
	public String getPkgDescription() {
		return pkgDescription;
	}

	/**
	 * @return the questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * @return the pkgPrice
	 */
	public double getPkgPrice() {
		return pkgPrice;
	}

	/**
	 * @return the studyMaterialLink
	 */
	public String getStudyMaterialLink() {
		return studyMaterialLink;
	}

	/**
	 * @return the isStudyMaterial
	 */
	public boolean isStudyMaterial() {
		return isStudyMaterial;
	}

	/**
	 * @return the numberOfQuestions
	 */
	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	/**
	 * @param pkgID
	 *            the pkgID to set
	 */
	public void setPkgID(int pkgID) {
		this.pkgID = pkgID;
	}

	/**
	 * @param pkgName
	 *            the pkgName to set
	 */
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	/**
	 * @param pkgDescription
	 *            the pkgDescription to set
	 */
	public void setPkgDescription(String pkgDescription) {
		this.pkgDescription = pkgDescription;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(ArrayList<Question> newQuestions) {

		questions.clear();

		for (int i = 0; i < newQuestions.size(); i++) {
			questions.add(newQuestions.get(i));
		}
		setNumberOfQuestions();
	}

	/**
	 * @param pkgPrice
	 *            the pkgPrice to set
	 */
	public void setPkgPrice(double pkgPrice) {
		this.pkgPrice = pkgPrice;
	}

	/**
	 * @param studyMaterialLink
	 *            the studyMaterialLink to set
	 */
	public void setStudyMaterialLink(String studyMaterialLink) {
		this.studyMaterialLink = studyMaterialLink;
	}

	/**
	 * @param isStudyMaterial
	 *            the isStudyMaterial to set
	 */
	public void setStudyMaterial(boolean isStudyMaterial) {
		this.isStudyMaterial = isStudyMaterial;
	}

	/**
	 * @param numberOfQuestions
	 *            the numberOfQuestions to set
	 */
	public void setNumberOfQuestions() {
		this.numberOfQuestions = this.questions.size();

	}

	/**
	 * @return True if the package is published, otherwise false.
	 */
	public boolean isPublished() {
		return isPublished;
	}

	/**
	 * @param isPublished
	 *            the isPublished to set
	 */
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PackageBuilder [pkgID=" + pkgID + ", pkgName=" + pkgName
				+ ", pkgDescription=" + pkgDescription + ", questions="
				+ questions + ", pkgPrice=" + pkgPrice + ", studyMaterialLink="
				+ studyMaterialLink + ", isStudyMaterial=" + isStudyMaterial
				+ ", numberOfQuestions=" + numberOfQuestions + ", isPublished="
				+ isPublished + "]";
	}

}
