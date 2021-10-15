package spring.mvc;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import spring.mvc.data.Entity;
import spring.mvc.data.EntityRepository;

/**
 * テスト:CRUD) Entityデータベース
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SBPracticeTest {
	@Autowired
	protected EntityRepository entityRepo;

	/**
	 * Entity CRUDテスト
	 */
	@Test
	public void crudEntity() {
		/*
		 * テスト用エンティティ
		 */
		int key = 1;
		String strDate = "2020-04-01";
		Date value = Date.valueOf(strDate);
		Entity entity = new Entity(key, value);
		/*
		 * CREATEテスト
		 */
		entityRepo.save(entity);

		/*
		 * READテスト
		 */
		Assert.isTrue((entityRepo.findById(key)).isPresent(), "Entity READ ONE");
		Assert.isTrue(!entityRepo.findAll().isEmpty(), "Entity READ ALL");

		/*
		 * UPDATEテスト
		 */
		strDate = "2021-04-01";
		value = Date.valueOf(strDate);
		entity.setValue(value);
		entityRepo.save(entity);
		Assert.isTrue(entityRepo.findById(key).get().getValue().equals(value), "Entity UPDATED");

		/*
		 * DELETEテスト
		 */
		entityRepo.delete(entity);
		Assert.isNull(entityRepo.findById(entity.getKey()).orElse(null), "Entity DELETED");
	}
	
	@Test
	public void pageTransition() {

		//	VM引数 -Dwebdriver.gecko.driver="D:/work/geckodriver-v0.30.0-win64/geckodriver.exe"
		//  test.propertiesで指定している
		Properties settings = System.getProperties();
	    try(FileInputStream in = new FileInputStream("src/test/resources/test.properties")) {
	    	settings.load(in);
	    	in.close();
		}catch(IOException e) {
			fail("test.properties not exists");
		}
	    // Firefoxドライバーインスタンスを作成する
	    WebDriver driver = new FirefoxDriver();
	    // ホームページを開く
	    String home = settings.getProperty("server.url");
		driver.get(home);
		driver.manage().window().setSize(new Dimension(1280, 1024));
		// 登録画面へ遷移する
		driver.findElement(By.xpath("//a[contains(@href, 'entity')]")).click();
		
		/**
		 * 登録画面(entity.html)でEntityを登録する
		 */
		int key = 1;
		String strDate = "2020-03-25";
		Date value = Date.valueOf(strDate);
		
		WebElement elem;
		elem = driver.findElement(By.xpath("//input[@id='key']"));
		elem.clear();
		elem.sendKeys(String.valueOf(key));
		elem = driver.findElement(By.xpath("//input[@id='value']"));
		elem.clear();
		elem.sendKeys(strDate);
		driver.findElement(By.xpath("//input[@value='登録']")).click();
		// ホームページへ戻る
		driver.findElement(By.xpath("//input[@value='HOME']")).click();
		Assert.isTrue(home.equals(driver.getCurrentUrl().substring(0, home.length())), "Return Home");
		
		// EntityがDBへ登録されたことを確認する
		Optional<Entity> expected = entityRepo.findById(key);		
		Assert.isTrue(expected.isPresent() && expected.get().getValue().equals(value) , "Entity entried");
		
		/**
		 * 一覧
		 */
		driver.findElement(By.xpath("//a[contains(@href, 'entityList')]")).click();
		driver.findElement(By.xpath("//input[@value='HOME']")).click();
		Assert.isTrue(home.equals(driver.getCurrentUrl().substring(0, home.length())), "Return Home");
	
		try {
		Thread.sleep(2000);
		} catch (InterruptedException e) {
			fail("sleep fail");
		}
	
	    // ブラウザを閉じる
	    driver.quit();
	}
}
