# Excel Demo Program   
---

java swing을 이용하여, Excel기능을 일부 구현한 프로그램  
## 구현기능

* 프로그램이 실행되면, 가로 610, 세로 588인 크기 조절 가능한 창이 나오도록 생성자 구현
row는 (0~99) , column(A-Z)까지 생성하며, 셀이 선택되었을 때, row header의 숫자가 진하게 표시되고, 색상이 변경된다.

* File메뉴를 클릭시 New, Open, Save, Exit 기능을 제공  

* New -> 클릭시 새 창이 생성하며, 기존 창은 제거된다. 
* Open -> csv 파일을 읽을 수 있으며, 파일을 정보대로 엑셀 프로그램에 mapping되어 open된다.   
* Save -> 파일을 저장할 수 있게 경로창이 나오며, csv형태로 파일이 저장된다.  
* Exit -> 프로그램 종료   

* Fomular 메뉴 클릭시 SUM, AVERAGE, COUNT, MIN, MAX 함수 기능 제공을 한다.
  결과를 출력할 셀을 클릭후, 함수를 클릭하면 셀 범위를 지정( 셀이름1:셀이름2 )해주어 연산 결과를 확인할 수 있다.
  
## 리뷰
readme를 쓰면서 오랜만에 동작을 시켜보니, 함수 실행에서 예외처리가 되어 있지 않는 것을 확인  
당시에 프로젝트 명세에 만들 수 있는 method가 3개로 제한되어, 대부분의 기능을 생성자에 넣게 되어 많은 불만이 있었던 기억이 난다.  
수업시간에 Java 문법만 공부하다 갑자기 혼자서 오라클 문서를 보며 힘들게 구현했지만, 혼자 만든 결과물을 들고 노는 재미가 있었던 프로젝트 과제 


