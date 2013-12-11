package com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class AlmostTherePageObject extends BasePageObject {

	@FindBy(xpath="//h2[contains(text(), 'Almost there')]")
	private WebElement almostThereText;
	@FindBy(css="h1.wordmark a[href='/Wikia']")
	private WebElement wikiaWordmark;
	@FindBy(css="input.link[value='Send me another confirmation email']")
	private WebElement sendAnotherMail;
	@FindBy(css="a.change-email-link")
	private WebElement changeMyEmail;

	public AlmostTherePageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyAlmostTherePage() {
		waitForElementByElement(almostThereText);
		waitForElementByElement(sendAnotherMail);
		waitForElementByElement(changeMyEmail);
	}

	private String getActivationLinkFromMail(String email, String password) {
		String www = MailFunctions.getActivationLinkFromMailContent(MailFunctions.getFirstMailContent(email, password));
		www = www.replace("=", "");
		PageObjectLogging.log("getActivationLinkFromMail", "activation link is visible in email content: "+www, true);
		return www;
	}

	public ConfirmationPageObject enterActivationLink(String email, String password) {
		getUrl(getActivationLinkFromMail(email, password));
		PageObjectLogging.log("enterActivationLink", "activation page is displayed", true, driver);
		return new ConfirmationPageObject(driver);
	}

}
