package finpro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class Main2 extends JFrame implements MouseListener,ActionListener{
	//�]�w�ܼ�
    private int w=50,abcw=8,abch=8,bp=20; //w=�C�氪�Aabcw=�]���}�e�Aabch=�]���}��,bp=�]���K��
    private int n,m,x,y,k,b,p,yes,no; //n,m=�C�����s��m ,x,y=���s�Ʀr��m ,k=��e���s�Ҧ� ,b=�]���� ,p=��e��l�� ,yes=�F��� ,no=�F����
    private int xyz[][]; //�C�����s�Ʀr�}�C
    private int t[]={10,-10}; //�C�����s�Ʀr���
    private JButton button[][]; //�C�����s�}�C
    private JButton buttonRE , buttonNext,ps; //RE=�}�l/���ӫ��s ,Next=�U�@�����s ,ps=�ѻ���
    private JTextField sethy,setwx,setbp; //�]�w�C���j�p,�]���K��
    private JLabel l1,l2,l3,story;
    private Container c; 
    
    //�Ϥ����|�]�w
    String icon_path = "images/flag.png"; //�ХܺX�l
	ImageIcon icon = new ImageIcon(icon_path);
	String icon_path2 = "images/monster2.png"; //�]��
	ImageIcon icon2 = new ImageIcon(icon_path2);
	String icon_path3 = "images/boom.png"; //�z��
	ImageIcon icon3 = new ImageIcon(icon_path3);
	String icon_path4 = "images/grass3.png"; //�C���I��
	ImageIcon icon4 = new ImageIcon(icon_path4);
	String icon_path5 = "images/mon_rip.png"; //��쪺�]��
	ImageIcon icon5 = new ImageIcon(icon_path5);
	String icon_path6 = "images/go.png"; //�}�l�C��
	ImageIcon icon6 = new ImageIcon(icon_path6);
   
    public static void main(String args[]){
    Main2 app=new Main2();
    }
    
    //�]�w���s�ʧ@
    public void actionPerformed(ActionEvent e){
    	if(e.getSource()==buttonRE) {
    		this.change();
    		this.re();
    	}else if(e.getActionCommand()=="���ȴ���") {
    		story.setText("<html>�]�k���w�b���a�d�U�F�t���A�i�D�M�h�έ̪���@���X���]���C�Y�O�A�{���Y���a���õ��]���A�A�i�H�����k��d�U�O���A�����έ��̤��n�a��!</html>");
    		buttonNext.setText("���Ȼ���");
    	}else if(e.getActionCommand()=="���Ȼ���") {
    		story.setText("<html>�ھ��]�k�����լd�A�o�ϼ��ó\�h�]���A�����M�h�Ϊ����A�A���ȴN�O���Q�M���]���C�`�N!!!�Ф��n��ʨ��]���A�_�h�N�ް_�z���A���֧A�����������!</html>");
    		buttonNext.setText("���ȴ���");
    	}
    }
    
    public  Main2(){
    	super("�]���y��"); //�إ߼��D�W��
    	c=getContentPane();// ��c�]�w����frame�����e���O
    	c.setLayout(null); //���ϥΪ����t�m�޲z��
    	l1=new JLabel("      �e��(>3):");
    	l1.setBounds(0, w*3, w*2, w);//�]�w��m
	    l2=new JLabel("      ����(>3):");
	    l2.setBounds(0, w*5, w*2, w);
	    l3=new JLabel("         �]���K��(%):");
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
	    buttonNext=new JButton("���ȴ���");
	    buttonNext.setBounds(abcw*w, 0, w*2, w);
	    buttonNext.setBackground(new Color(197,227,209));
	    buttonNext.addActionListener(this); //�ʵ����s
	    buttonRE=new JButton();
	    buttonRE.setBounds(0, w, w*2,w*2);
	    buttonRE.setMargin(new java.awt.Insets(0, 0, 0, 0));
	    buttonRE.setBackground(new Color(197,227,209));
	    buttonRE.addActionListener(this); //�ʵ����s
	    c.add(buttonRE);
	    c.add(ps);
	    c.add(buttonNext);
	    c.add(setwx);
	    c.add(sethy);
	    c.add(setbp);
	    c.add(l1);
	    c.add(l2);
	    c.add(l3);
	    this.re(); //��z�C�����O
	    setLocation(100,100); 
	    setResizable(false); //�������i�ۥѩ�j�Y�p
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //���C���j�p�]�w
    public void change(){
    	for(int i=1;i<=abcw;i++){
    		for(int j=1;j<=abch;j++){
    			button[i][j].setVisible(false);
    		}
    	}
    	//�e���P�K�׳]�w
    	try{
    		abcw=Integer.parseInt(setwx.getText());
    		if(abcw<3){
    			abcw=3;
    			setwx.setText(""+abcw);}
    	}catch(NumberFormatException e){ //�]�w���~�ѨM
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
    
    //�C����������
    public void re(){
    	this.xyz=new int[abcw+2][abch+2];
    	this.button=new JButton[abcw+2][abch+2];
    	n=w*2;
    	m=w*2;
    	//�]�w�D�C����l
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
    	b=abch*abcw*bp/100; //�a�p��
    	p=abch*abcw-b; //���`��l��
    	setSize((abcw+2)*w+15,(abch+2)*w+38);//�վ�����j�p
    	c.setBackground(new Color(207,213,221));
    	ps.setBounds(0, 0, abcw*w, w);//�վ�C�������j�p
    	buttonNext.setBounds(abcw*w, 0, w*2, w);
    	buttonRE.setIcon(icon6);
    	story.setFont(new Font("�з���", Font.PLAIN, 11));
    	story.setText("<html>�]�k���լd��ܳo�ϼ��ó\�h�]���A�����M�h�Ϊ����A�A���ȴN�O���Q�M���]���C�`�N!������ʨ��]���A�_�h�N�ް_�z���A���A�����������!</html>");
    	
    	for(int i=0;i<=abcw+1;i++){
    		for(int j=0;j<=abch+1;j++){
    			if(i==0 || i==abcw+1 || j==0 || j==abch+1){
    				this.xyz[i][j]=200; //��u�Ʀr�]��200
    			}else{
    				this.xyz[i][j]=0; //���t��u�k0
    				button[i][j].setBackground(new Color(173,211,240));
    				button[i][j].setText("");
    			}
    		}
    	}
    	
    	int g;
    	for(int i=1;i<=b;i++){
    		do
    			g = (int)(Math.random() * (abch*abcw));//�H���I�a�p
    		while (xyz[g%abcw+1][g/abcw+1] >= 9);
    		//�]�w���s�Ʀr(�����]����)
    		xyz[g%abcw+1][g/abcw+1]=9;//����
    		xyz[g%abcw+2][g/abcw+1]+=1;//�F
    		xyz[g%abcw+1][g/abcw+2]+=1;//�n
    		xyz[g%abcw+0][g/abcw+1]+=1;//��
    		xyz[g%abcw+1][g/abcw+0]+=1;//�_
    		xyz[g%abcw+2][g/abcw+0]+=1;//�F�_
    		xyz[g%abcw+2][g/abcw+2]+=1;//�F�n
    		xyz[g%abcw+0][g/abcw+2]+=1;//��n
    		xyz[g%abcw+0][g/abcw+0]+=1;//��_
    		//button[g%abcw+1][g/abcw+1].setBackground(Color.red);//�P�_��
    	}
    	for(int i=1;i<=abcw;i++){
    		for(int j=1;j<=abch;j++){
    			if(xyz[i][j]>8){
    				xyz[i][j]=9;
    			}
    			//button[i][j].setText(""+xyz[i][j]);//�P�_��
    		}
    	}
    }
    
    //�z���]�w
    public void boom(){
    	//�]���Хܹ��
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
    				button[i][j].setText("��");
    				button[i][j].setIcon(null);
    				no+=1;}
    			xyz[i][j]+=200;
    		}
    	}
    	buttonRE.setIcon(icon6);
    	story.setFont(new Font("�з���", Font.PLAIN, 15));
    	story.setText("<html>���ȥ���!!<br>����"+yes+"���]��,�Ѿl"+(b-no)+"��</br></html>");
    }
    
    //���\�]�w
    public void win(){
    	buttonRE.setIcon(icon6);
    	story.setFont(new Font("�з���", Font.PLAIN, 20));
    	story.setText("���ߧ�������!!");
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
    		;//���L�F�B�L�ʧ@
    	}else{
    		if(e.getButton()==3){//�k��
    			k=xyz[x][y]/10;//�󴫼Ҧ�
    			xyz[x][y]+=t[(k)%3];
    			
    			//�аO�a�p
    	    	if(xyz[x][y] >= 0 && xyz[x][y] < 10) {
    	    		button[x][y].setText("");
    	    		button[x][y].setIcon(icon4);
    	    	}else if(xyz[x][y] >= 10 && xyz[x][y] < 20) {
    	    		button[x][y].setIcon(icon);
    	    	}
    			if(k==0){ //�аO�]��
    				b-=1;
    				story.setFont(new Font("�з���", Font.PLAIN, 15));
    				story.setText("      �w���Ѿl "+b+" ���]��");}
    			if(k==1){ //�����аO�]��
    				b+=1;
    				story.setFont(new Font("�з���", Font.PLAIN, 15));
    				story.setText("      �w���Ѿl "+b+" ���]��");}
    		}else{
    			if(xyz[x][y]==9){ //����]��
    				this.boom();//�z�F
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
    
    //�S����]��
    public int rerun(int x ,int y){
    	if(xyz[x][y]>9){
    		return 1 ;}
    	else{
    		if(xyz[x][y]==0){//����L�]��
    			story.setFont(new Font("�з���", Font.PLAIN, 15));
    			story.setText("      �w���Ѿl "+b+" ���]��");//��ܦw��
    			button[x][y].setBackground(new Color(173,211,240));
    			button[x][y].setIcon(null);
    			button[x][y].setText(""+xyz[x][y]); //���ܪ��񦳴X���]��
    			xyz[x][y]=200;  //���s�Ʀr�Хܬ�200,��ܤw���L
    			p-=1; //�]����-1
    			//�P���l��ܼƦr����
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
    			story.setFont(new Font("�з���", Font.PLAIN, 15));
    			story.setText("      �w���Ѿl "+b+" ���]��");//��ܦw��
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

