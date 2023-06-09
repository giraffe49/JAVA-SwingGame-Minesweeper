# JAVA-SwingGame-Minesweeper
使用java設計簡易視窗遊戲-踩地雷
### 介紹影片:
https://drive.google.com/file/d/16XsGE7DTEOl7PoKl4iZ4kqVIEY98P4GN/view?usp=sharing
### 遊戲說明:
以類似踩地雷的形式，配合故事設定，讓玩家可以體驗到以智力取勝的樂趣，也能透過運氣闖關成功。用保護王國的故事為主軸，搭配任務挑戰，能展示出平日的學習成果，並融會貫通。
### 程式說明:
我們的遊戲面板中，會有一些可以調整高度、寬度還有控制魔物密度的功能，所以我們設了幾個變數：w=每格高，abcw=魔物陣寬，abch=魔物陣高,bp=魔物密度。接下來是為遊戲設計場景而設的變數：n,m=遊戲按鈕位置 ,x,y=按鈕數字位置 ,k=當前按鈕模式 ,b=魔物數 ,p=當前格子數 ,yes=達對數 ,no=達錯數。為了讓遊戲玩家方便進行，我們還設了RE=開始/重來按鈕 ,Next=下一頁按鈕 ,ps=解說框。

之後為了增加畫面的豐富度與操作性，我們進行了圖片路徑設定，並且使用了if else區分不同按鈕按下後的動作，由於提示框有故事說明與任務提示兩種功能，因此以按鈕上的文字來區分動作。

遊戲視窗的部分，我們先建立了標題名稱，然後把c設定成為frame的內容面板，這裡不使用版面配置管理者，接著設定位置及監視按鈕，並整理遊戲面板，再將視窗設定為不可自由放大縮小，避免跑版。之後進行寬高與密度設定，用於更改遊戲大小,若玩家輸入的大小與密度不符合設定,將會自動設回最小預設值(3,3,20)。
接著是遊戲頁面重整,一開始先建立遊戲數字陣列xyz[][],與遊戲按鈕陣列button[][],然後就可以開始進行主要遊戲格子的設定了，這邊使用了for迴圈來精簡我們的程式碼,然後下方也將地雷數、視窗大小等按照玩家所設定的進行更改，並設定xyz[][]中每格所代表的數字，同樣使用for迴圈並加上if else來使用，這邊我們將邊線的格子都設為200,代表該格子按了之後不會反應，之後開始隨機設定魔物位置，並將魔物位置的格子代表數字設為9，且將魔物周圍格子數字都設為該格9宮格內的魔物數，由於會有格子已經為9但周圍又還有魔物，所以數字會>9，所以最後再使用for迴圈將所有>9的格子設為9。

之後是進行爆炸設定，這邊使用for迴圈進行條件篩選，該格數字與狀態對應為:9=魔物，19=標示成功，9<x<19=標示錯誤，並依照數字改變該格遊戲畫面上的圖示，且在上方跳出「任務失敗!!」的字樣，以及告訴遊戲玩家捕捉了幾隻魔物、剩餘幾隻魔物；相對的也進行了成功設定，畫面將跳出「恭喜完成任務!!」的字樣。

接下來是mouseClicked設定，這邊一樣是用xyz[][]的數字來判斷動作進行，並區分滑鼠左右鍵所代表的動作，右鍵的部分有標示與取消標示兩個動作，左鍵則是一般探查魔物，並設定踩到魔物後該格的畫面變化，若沒有踩到魔物，則是呼叫rerun(x,y)顯示該格附近魔物與更改上方提示。
