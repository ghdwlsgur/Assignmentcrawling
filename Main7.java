package com.koreait.crawling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main7 {
	public static void main(String[] args) {
		String DRIVER_ID = "webdriver.chrome.driver";
		String DRIVER_PATH = "C:/Users/redma/HJH/JSP/chromedriver.exe";
		System.setProperty(DRIVER_ID, DRIVER_PATH);
		WebDriver driver = new ChromeDriver();
		// 바나프레소 주소
		String base_url = "https://www.banapresso.com/store";
		
		try {
			driver.get(base_url);			// 크롬드라이버 주소 연결
			int pageNum = 1;				// 페이지번호 -> 1 2 3 4 5 	> (NextButton)
											// pageNum -> 1 2 3 4 5
											// 페이지번호 -> 6 7 8 9 10
											// pageNum -> 1 2 3 4 5
			boolean checkNum = true;		// pageNum이 5일때 NextButton 눌러주기 (pageNum이 5면 false)
											//							   	  (5가 아니면 true) 
			
			while(pageNum < 6) {				
				Thread.sleep(1000);	// 1초 대기
				List<WebElement> elements = driver.findElements(By.className("store_name_map"));	// class 이름이 store_name_map인 요소 elemets 변수에 저장
				for(WebElement el : elements) {					
					String[] store = el.getText().split("\n");
					System.out.println("지점명"+store[1]);
					System.out.println("지점주소"+store[2]);
					System.out.println("---------------------");
				}				
						
				// pageNum이 5일때, 다음장으로 넘기기
				if(pageNum % 5 == 0) {
					checkNum = false;
				}			
				pageNum++;			
				
				// 다음쪽
				if(checkNum) {							
					WebElement nextPage = driver.findElement(By.cssSelector("div.pagination > ul > li:nth-child("+pageNum+") > a"));
					nextPage.click();
				// 다음페이지
				}else {
					try {
						WebElement nextButton = driver.findElement(By.cssSelector("span.btn_page > a"));
						nextButton.click();			// 다음페이지로 이동하는 버튼 클릭!
						pageNum = 1;				// 다음페이지로 이동하였으니 다시 1부터 시작!
						checkNum = true; 			// 다음페이지로 이동하였으니 다시 checkNum = true로 줘서 if문 실행도록 하겠습니다. (다음쪽)
					}catch(Exception e) {
						break;
					}
				}									
			}			
		}catch(Exception e) {
			System.out.println("프로그램 종료");
		}
		
		

	}

}


