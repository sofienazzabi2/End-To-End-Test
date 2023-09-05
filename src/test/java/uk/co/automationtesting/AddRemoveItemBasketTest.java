package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import pageObjects.HomePage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductpage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class)
public class AddRemoveItemBasketTest extends BasePage {

	public AddRemoveItemBasketTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest
	public void setup() throws IOException {
		driver = getDriver();
		driver.get(getUrl());
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver = null;
	}

	@Test
	public void addRemoveItem() throws IOException {
		HomePage home = new HomePage(driver);
		home.getCookie().click();
		home.getTestStoreLink().click();

		ShopHomepage shopHome = new ShopHomepage(driver);
		shopHome.getProdOne().click();

		ShopProductpage shopProd = new ShopProductpage(driver);
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();

		ShopContentPanel cPanel = new ShopContentPanel(driver);
		cPanel.getContinueShopBtn().click();

		shopProd.getHomepageLink().click();

		shopHome.getProdTwo().click();;

		shopProd.getAddToCartBtn().click();

		cPanel.getCheckoutBtn().click();

		ShoppingCart cart = new ShoppingCart(driver);
		cart.getDeleteItemTwo().click();
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.invisibilityOf(cart.getDeleteItemTwo()));
		Assert.assertEquals(cart.getTotalAmount().getText(),"$45.24");
		
		

	}

}
