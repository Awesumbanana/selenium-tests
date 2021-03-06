package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Reply extends BasePageObject {

  @FindBy(css = ".replies-list")
  private List<WebElement> repliesList;

  @FindBy(css = ".reply-upvote")
  private List<WebElement> replyUpvoteButton;

  @FindBy(css = ".replies-list small")
  private List<WebElement> replyVoteCount;

  @FindBy(css = "a.icon")
  private List<WebElement> socialNetworkIcon;

  private WebElement reply;

  Reply(WebElement reply) {
    this.reply = reply;
  }

  public Reply() {
    super();
    reply = null;
  }

  public boolean isPostDetailsListEmpty() {
    return repliesList.isEmpty();
  }

  public boolean isReplyUpvoteButtonVisible(int index) {
    WebElement button = replyUpvoteButton.get(index);
    wait.forElementVisible(button);
    return button.isDisplayed();
  }

  public void clickReplyUpvoteButton(int replyIndex) {
    WebElement button = replyUpvoteButton.get(replyIndex);
    wait.forElementClickable(button, replyIndex);
    button.click();
  }

  public String getReplyVoteCount(int index) {
    WebElement voteArea = replyVoteCount.get(index);
    wait.forElementVisible(voteArea);
    return voteArea.getText();
  }

  public void waitForReplyVoteCountToChange(int replyIndex, String voteCount) {
    WebElement voteArea = replyVoteCount.get(replyIndex);
    wait.forTextNotInElement(voteArea, voteCount);
  }

  /**
   * Wait for the noticeable time lag between vote and vote value change to pass
   */
  public void waitForVoteCountChangeTimeLagToPass() {
    try {
      //This sleep was introduced because of noticeable lag between vote and vote value change
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Log.logError("waitForVoteCountChangeTimeLagToPass", e);
    }
  }

  public boolean hasImage() {
    setShortImplicitWait();
    boolean hasImage = !reply.findElements(By.cssSelector(".post-image-inner-image")).isEmpty();
    restoreDefaultImplicitWait();
    return hasImage;
  }

  public boolean hasOpenGraph() {
    setShortImplicitWait();
    boolean
        hasOpenGraph
        = !reply.findElements(By.cssSelector(".og-container")).isEmpty();
    restoreDefaultImplicitWait();
    return hasOpenGraph;
  }
}
