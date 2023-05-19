package finpro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class Main2 extends JFrame implements MouseListener,ActionListener{
	//設定變數
    private int w=50,abcw=8,abch=8,bp=20; //w=每格高，abcw=魔物陣寬，abch=魔物陣高,bp=魔物密度
    private int n,m,x,y,k,b,p,yes,no; //n,m=遊戲按鈕位置 ,x,y=按鈕數字位置 ,k=當前按鈕模式 ,b=魔物數 ,p=當前格子數 ,yes=達對數 ,no=達錯數
    private int xyz[][]; //遊戲按鈕數字陣列
    private int t[]={10,-10}; //遊戲按鈕數字更改
    private JButton button[][]; //遊戲按鈕陣列
    private JButton buttonRE , buttonNext,ps; //RE=開始/重來按鈕 ,Next=下一頁按鈕 ,ps=解說框
    private JTextField sethy,setwx,setbp; //設定遊戲大小,魔物密度
    private JLabel l1,l2,l3,story;
    private Container c; 
    
    //圖片路徑設定
    String icon_path = "images/flag.png"; //標示旗子
	ImageIcon icon = new ImageIcon(icon_path);
	String icon_path2 = "images/monster2.png"; //魔物
	ImageIcon icon2 = new ImageIcon(icon_path2);
	String icon_path3 = "images/boom.png"; //爆炸
	ImageIcon icon3 = new ImageIcon(icon_path3);
	String icon_path4 = "images/grass3.png"; //遊戲背景
	ImageIcon icon4 = new ImageIcon(icon_path4);
	String icon_path5 = "images/mon_rip.png"; //抓到的魔物
	ImageIcon icon5 = new ImageIcon(icon_path5);
	String icon_path6 = "images/go.png"; //開始遊戲
	ImageIcon icon6 = new ImageIcon(icon_path6);
   
    public static void main(String args[]){
    Main2 app=new Main2();
    }
    
    //設定按鈕動作
    public void actionPerformed(ActionEvent e){
    	if(e.getSource()==buttonRE) {
    		this.change();
    		this.re();
    	}else if(e.getActionCommand()=="任務提示") {
    		story.setText("<html>魔法部已在此地留下了暗號，告訴騎士團們附近共有幾隻魔物。若是你認為某塊地潛藏著魔物，你可以單擊右鍵留下記號，提醒團員們不要靠近!</html>");
    		buttonNext.setText("任務說明");
    	}else if(e.getActionCommand()=="任務說明") {
    		story.setText("<html>根據魔法部的調查，這區潛藏許多魔物，身為騎士團長的你，任務就是順利清除魔物。注意!!!請不要驚動到魔物，否則將引起爆炸，祝福你能夠完成任務!</html>");
    		buttonNext.setText("任務提示");
    	}
    }
    
    public  Main2(){
    	super("魔物獵手"); //建立標題名稱
    	c=getContentPane();// 把c設定成為frame的內容面板
    	c.setLayout(null); //不使用版面配置管理者
    	l1=new JLabel("      寬度(>3):");
    	l1.setBounds(0, w*3, w*2, w);//設定位置
	    l2=new JLabel("      高度(>3):");
	    l2.setBounds(0, w*5, w*2, w);
	    l3=new JLabel("         魔物密度(%):");
	    l3.setBounds(w*2, w, w*3, w);
	    setwx=new JTextField("8");
	    setwx.setBounds(0,w*4, w*2, w);
	    sethy=new JTextField("8");
	    sethy.setBounds(0, w*6, w*2, w);
	    setbp=new JTextField("20");
	    setbp.setBounds(w*5, w, w*2, w);
	    ps=new JButton();
	    ps.setBounds(0, 0, abcw*w, w);
	    ps.setBackground(new Color(197,210,227));
	    ps.setLayout(new BorderLayout());
	    story = new JLabel("");
	    ps.add(story, BorderLayout.NORTH);
	    buttonNext=new JButton("任務提示");
	    buttonNext.setBounds(abcw*w, 0, w*2, w);
	    buttonNext.setBackground(new Color(197,227,209));
	    buttonNext.addActionListener(this); //監視按鈕
	    buttonRE=new JButton();
	    buttonRE.setBounds(0, w, w*2,w*2);
	    buttonRE.setMargin(new java.awt.Insets(0, 0, 0, 0));
	    buttonRE.setBackground(new Color(197,227,209));
	    buttonRE.addActionListener(this); //監視按鈕
	    c.add(buttonRE);
	    c.add(ps);
	    c.add(buttonNext);
	    c.add(setwx);
	    c.add(sethy);
	    c.add(setbp);
	    c.add(l1);
	    c.add(l2);
	    c.add(l3);
	    this.re(); //整理遊戲面板
	    setLocation(100,100); 
	    setResizable(false); //視窗不可自由放大縮小
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //更改遊戲大小設定
    public void change(){
    	for(int i=1;i<=abcw;i++){
    		for(int j=1;j<=abch;j++){
    			button[i][j].setVisible(false);
    		}
    	}
    	//寬高與密度設定
    	try{
    		abcw=Integer.parseInt(setwx.getText());
    		if(abcw<3){
    			abcw=3;
    			setwx.setText(""+abcw);}
    	}catch(NumberFormatException e){ //設定錯誤解決
    		abcw=3;
    		setwx.setText(""+abcw);}
    	try{
    		abch=Integer.parseInt(sethy.getText());
    		if(abch<3 ){
    			abch=3;
    			sethy.setText(""+abch);}
    	}catch(NumberFormatException e){
    		abch=3;
    		sethy.setText(""+abch);}    
    	try{
    		bp=Integer.parseInt(setbp.getText());
    		if(bp<0 || bp>100){
    			bp=20; 
    			setbp.setText(""+bp);}
    	}catch(NumberFormatException e){
    		bp=20;
    		setbp.setText(""+bp);}
    }
    
    //遊戲頁面重整
    public void re(){
    	this.xyz=new int[abcw+2][abch+2];
    	this.button=new JButton[abcw+2][abch+2];
    	n=w*2;
    	m=w*2;
    	//設定主遊戲格子
    	for(int i=1;i<=abcw;i++){
    		for(int j=1;j<=abch;j++){
    			button[i][j]=new JButton();
    			button[i][j].setLocation(n, m);
    			button[i][j].setSize(w,w);
    			button[i][j].setIcon(icon4);
    			c.add(button[i][j]);
    			button[i][j].addMouseListener(this);
    			m+=w;
    		}
    		n+=w;
    		m=w*2;
    	}
    	b=abch*abcw*bp/100; //地雷數
    	p=abch*abcw-b; //正常格子數
    	setSize((abcw+2)*w+15,(abch+2)*w+38);//調整視窗大小
    	c.setBackground(new Color(207,213,221));
    	ps.setBounds(0, 0, abcw*w, w);//調整遊戲說明大小
    	buttonNext.setBounds(abcw*w, 0, w*2, w);
    	buttonRE.setIcon(icon6);
    	story.setFont(new Font("標楷體", Font.PLAIN, 11));
    	story.setText("<html>魔法部調查顯示這區潛藏許多魔物，身為騎士團長的你，任務就是順利清除魔物。注意!不能驚動到魔物，否則將引起爆炸，祝你能夠完成任務!</html>");
    	
    	for(int i=0;i<=abcw+1;i++){
    		for(int j=0;j<=abch+1;j++){
    			if(i==0 || i==abcw+1 || j==0 || j==abch+1){
    				this.xyz[i][j]=200; //邊線數字設為200
    			}else{
    				this.xyz[i][j]=0; //不含邊線歸0
    				button[i][j].setBackground(new Color(173,211,240));
    				button[i][j].setText("");
    			}
    		}
    	}
    	
    	int g;
    	for(int i=1;i<=b;i++){
    		do
    			g = (int)(Math.random() * (abch*abcw));//隨機埋地雷
    		while (xyz[g%abcw+1][g/abcw+1] >= 9);
    		//設定按鈕數字(附近魔物數)
    		xyz[g%abcw+1][g/abcw+1]=9;//本位
    		xyz[g%abcw+2][g/abcw+1]+=1;//東
    		xyz[g%abcw+1][g/abcw+2]+=1;//南
    		xyz[g%abcw+0][g/abcw+1]+=1;//西
    		xyz[g%abcw+1][g/abcw+0]+=1;//北
    		xyz[g%abcw+2][g/abcw+0]+=1;//東北
    		xyz[g%abcw+2][g/abcw+2]+=1;//東南
    		xyz[g%abcw+0][g/abcw+2]+=1;//西南
    		xyz[g%abcw+0][g/abcw+0]+=1;//西北
    		//button[g%abcw+1][g/abcw+1].setBackground(Color.red);//判斷用
    	}
    	for(int i=1;i<=abcw;i++){
    		for(int j=1;j<=abch;j++){
    			if(xyz[i][j]>8){
    				xyz[i][j]=9;
    			}
    			//button[i][j].setText(""+xyz[i][j]);//判斷用
    		}
    	}
    }
    
    //爆炸設定
    public void boom(){
    	//魔物標示對錯
    	yes=0;
    	no=0;
    	for(int i=1;i<=abcw;i++){
    		for(int j=1;j<=abch;j++){
    			if(xyz[i][j]==9 ){
    				button[i][j].setBackground(new Color(168,167,235));
    				button[i][j].setText(null);
    				button[i][j].setIcon(icon2);
    			}
    			if(xyz[i][j]==19){
    				button[i][j].setBackground(new Color(230,195,195));
    				button[i][j].setIcon(icon5);
    				yes+=1;}
    			if(xyz[i][j]<19 && xyz[i][j]>9){
    				button[i][j].setBackground(new Color(210, 87, 113));
    				button[i][j].setText("錯");
    				button[i][j].setIcon(null);
    				no+=1;}
    			xyz[i][j]+=200;
    		}
    	}
    	buttonRE.setIcon(icon6);
    	story.setFont(new Font("標楷體", Font.PLAIN, 15));
    	story.setText("<html>任務失敗!!<br>捕捉"+yes+"隻魔物,剩餘"+(b-no)+"隻</br></html>");
    }
    
    //成功設定
    public void win(){
    	buttonRE.setIcon(icon6);
    	story.setFont(new Font("標楷體", Font.PLAIN, 20));
    	story.setText("恭喜完成任務!!");
    	for(int i=1;i<=abcw;i++){
    		for(int j=1;j<=abch;j++){
    			if(xyz[i][j]%10==9){
    				button[i][j].setBackground(new Color(230,195,195));
    				button[i][j].setIcon(icon5);
    			}
    			xyz[i][j]+=200;
    		}
    	}
    }
    
    public void mouseClicked(MouseEvent e) {
    	x=(this.getMousePosition().x-3-w*2)/w+1;
    	y=(this.getMousePosition().y-29-w*2)/w+1;
    	if(xyz[x][y]>100){
    		;//按過了、無動作
    	}else{
    		if(e.getButton()==3){//右鍵
    			k=xyz[x][y]/10;//更換模式
    			xyz[x][y]+=t[(k)%3];
    			
    			//標記地雷
    	    	if(xyz[x][y] >= 0 && xyz[x][y] < 10) {
    	    		button[x][y].setText("");
    	    		button[x][y].setIcon(icon4);
    	    	}else if(xyz[x][y] >= 10 && xyz[x][y] < 20) {
    	    		button[x][y].setIcon(icon);
    	    	}
    			if(k==0){ //標記魔物
    				b-=1;
    				story.setFont(new Font("標楷體", Font.PLAIN, 15));
    				story.setText("      預估剩餘 "+b+" 隻魔物");}
    			if(k==1){ //取消標記魔物
    				b+=1;
    				story.setFont(new Font("標楷體", Font.PLAIN, 15));
    				story.setText("      預估剩餘 "+b+" 隻魔物");}
    		}else{
    			if(xyz[x][y]==9){ //踩到魔物
    				this.boom();//爆了
    				button[x][y].setBackground(new Color(226,65,94));  
    				button[x][y].setIcon(icon3);
    				button[x][y].setText(null);
    			}else{    
    				this.rerun(x, y);
    			}
    		}
    	}
    	if(p<1)
    		this.win();
    }
    
    //沒踩到魔物
    public int rerun(int x ,int y){
    	if(xyz[x][y]>9){
    		return 1 ;}
    	else{
    		if(xyz[x][y]==0){//附近無魔物
    			story.setFont(new Font("標楷體", Font.PLAIN, 15));
    			story.setText("      預估剩餘 "+b+" 隻魔物");//顯示安全
    			button[x][y].setBackground(new Color(173,211,240));
    			button[x][y].setIcon(null);
    			button[x][y].setText(""+xyz[x][y]); //提示附近有幾隻魔物
    			xyz[x][y]=200;  //按鈕數字標示為200,表示已按過
    			p-=1; //魔物數-1
    			//周圍格子顯示數字提示
    			this.rerun(x-1, y-1);
    			this.rerun(x, y-1);
    			this.rerun(x+1, y-1);
    			this.rerun(x-1, y);
    			this.rerun(x+1, y);
    			this.rerun(x-1, y+1);
    			this.rerun(x, y+1);
    			this.rerun(x+1, y+1);
    			return 1;
    		}else{
    			story.setFont(new Font("標楷體", Font.PLAIN, 15));
    			story.setText("      預估剩餘 "+b+" 隻魔物");//顯示安全
    			button[x][y].setBackground(new Color(173,211,240));
    			button[x][y].setIcon(null);
    			button[x][y].setText(""+xyz[x][y]);
    			xyz[x][y]=200;
    			p-=1;
    			return 1;
    		}
    	}
    }
    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}

