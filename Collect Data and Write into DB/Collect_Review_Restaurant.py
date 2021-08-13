#!/usr/bin/env python
# coding: utf-8

# In[1]:


# 리뷰 인덱스를 찾는 함수
def get_review_idx(little_menu):
    for i in range(len(little_menu)):
        if little_menu[i].text == '리뷰':
            return i+1


# In[2]:


def collect_review(url):
    from bs4 import BeautifulSoup
    from selenium import webdriver
    import requests
    import time
    import pandas as pd

    options = webdriver.ChromeOptions()
    options.add_experimental_option("excludeSwitches", ["enable-logging"])
    options.add_argument('start-maximized')

    driver = webdriver.Chrome(executable_path = 'C:/Users/WooCheol Kwon/Python_Notebooks/chromedriver.exe', options=options)
    driver.implicitly_wait(1)
#     driver.get('https://map.naver.com/v5/?c=14128298.7324875,4524422.5047163,14,0,0,0,dh')
    driver.get(url)
    driver.implicitly_wait(1)
    time.sleep(2)

    # 리스트 보여지게 클릭
    driver.find_element_by_id('baseMap').click()

    # 음식점 버튼 클릭
    driver.find_element_by_xpath('//*[@id="container"]/shrinkable-layout/div/app-base/bubble-filter/bubble-filter-list/div/ul/li[2]/button').click()
    driver.implicitly_wait(5)

    """
    Selenium은 페이지 조작을 위해 사용하고, BeautifulSoup를 이용해 원하는 텍스트 값을 추출
    """

    """
    res_name: 식당이름
    res_subclass: 분야
    res_reviewcnt: 리뷰 수
    res_score: 식당별점
    res_address: 식당주소
    res_review: 리뷰
    """ 
    res_name = []
    res_subclass = []
    res_score = []
    res_address = []
    res_review = []
    res_review_point = []

    for i in range(1, 51):
        # #document안의 내용이 스크롤 바를 내리지 않으면 숨겨저 있는 구조이므로,
        # 10개마다 새로고침되는 특성을 이용해 스크롤을 내린다.
        if i%10 == 0:
            iframe = driver.find_elements_by_xpath('//*[@id="searchIframe"]')[0]
            driver.switch_to.frame(iframe)
            driver.execute_script("arguments[0].scrollIntoView(true);",driver.find_elements_by_xpath(f'//*[@id ="_pcmap_list_scroll_container"]/ul/li[{i}]/div/a[1]')[0])
            time.sleep(5)
            # 기본 프레임으로 이동
            driver.switch_to.default_content()

        # #document안의 태그를 참조하기 위해 driver 변경
        iframe = driver.find_elements_by_xpath('//*[@id="searchIframe"]')[0]
        driver.switch_to.frame(iframe)
        time.sleep(5)

        driver.find_elements_by_xpath(f'//*[@id ="_pcmap_list_scroll_container"]/ul/li[{i}]/div/a[1]')[0].click()
        time.sleep(5)

        # 기본 프레임으로 이동
        driver.switch_to.default_content()

        iframe2 = driver.find_elements_by_xpath('//*[@id = "entryIframe"]')[0]
        driver.switch_to.frame(iframe2)

        # res_name
        res_name.append(driver.find_elements_by_xpath('//*[@class = "place_section_content"]/div/div/span/span[1]')[0].text)

        # res_subclass
        res_subclass.append(driver.find_elements_by_xpath('//*[@class = "place_section_content"]/div/div/span/span[2]')[0].text)


        # res_score
        tmp_score = driver.find_elements_by_xpath('//*[@class = "place_section_content"]/div/div/div/span/em')
        if len(tmp_score) == 0:
            res_score.append('0')
        else:
            res_score.append(driver.find_elements_by_xpath('//*[@class = "place_section_content"]/div/div/div/span/em')[0].text)

        # res_address
        tmp_address = driver.find_elements_by_xpath('//*[@class = "_2yqUQ"]')
        if len(tmp_address) == 0:
            res_address.append('')
        else:
            res_address.append(driver.find_elements_by_xpath('//*[@class = "_2yqUQ"]')[0].text)

        """
        리뷰 버튼을 찾고 클릭해서 보여줘야 한다.
        """
        # 1) 메뉴에서 리뷰 인덱스를 찾는다.
        little_menu = driver.find_elements_by_xpath('//*[@class="_2MDmw"]/a')
        # 2) 찾은 인덱스로 클릭
        if len(driver.find_elements_by_xpath(f'//*[@class="_2MDmw"]/a[{get_review_idx(little_menu)}]')) < 1:
            little_menu = driver.find_elements_by_xpath('//*[@class="eg-flick-camera"]/a')
            driver.find_elements_by_xpath(f'//*[@class="eg-flick-camera"]/a[{get_review_idx(little_menu)}]')[0].click()
        else:      
            driver.find_elements_by_xpath(f'//*[@class="_2MDmw"]/a[{get_review_idx(little_menu)}]')[0].click()

        # 3) 리뷰가 10개 미만일 경우, 모든 리뷰 클릭
        # 4) 리뷰가 10개 초과일 경우, 모든 리뷰 클릭 후 더보기 클릭 -> 반복
        # 5) 현재 리뷰 10개만 추출하는 것으로 가정하고 코드 작성
        review_cnt = len(driver.find_elements_by_xpath('//*[@class = "_1QS0G "]/li'))

        if review_cnt == 0:
            print("리뷰 없음")
        elif review_cnt < 10:
            for j in range(review_cnt):
                # 리뷰 펼쳐보기 해주기
                try:
                    driver.find_elements_by_xpath("//span[contains(@class,'WoYOw')]")[j].click()
                except:
                    # 후기로 사진만 올리고 글을 작성하지 않은 경우가 있어 pass
                    pass
        else:
            for j in range(review_cnt):
                # 리뷰 펼쳐보기 해주기
                try:
                    driver.find_elements_by_xpath("//span[contains(@class,'WoYOw')]")[j].click()
                except:
                    # 후기로 사진만 올리고 글을 작성하지 않은 경우가 있어 pass
                    pass

                # 더보기 클릭(기능 생략 추후 많은 리뷰를 끌어모으기 위해 추가)

        page_text = BeautifulSoup(driver.page_source, 'html.parser')
        tmp_review = ""
        tmp_review_point = ""
        for li in page_text.find_all("li", {"class": "_2Cv-r"}):
            if li.find("span", {"class": "WoYOw"}):
                tmp_review += li.find("span",{"class": "WoYOw"}).text + "|"
                tmp_review_point += li.find("span", {"class": "_2tObC"}).text + "|"

        res_review.append(tmp_review[:-1])    
        res_review_point.append(tmp_review_point[:-1])
        print(i, '번째 음식점 완료!')

        # 기본 프레임으로 이동
        driver.switch_to.default_content()

    # 데이터를 데이터프레임의 형태로 변환하여 txt 또는 csv 파일로 출력
    df = pd.DataFrame({'res_name': res_name, 'res_subclass': res_subclass, 'res_score': res_score, 'res_address': res_address,'res_review': res_review, 'res_review_point': res_review_point})
    df.to_csv('review.csv', index = None)


# In[ ]:




