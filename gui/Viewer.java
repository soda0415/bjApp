package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logic.Dealer;

public class Viewer extends JFrame implements ActionListener{
	JLabel result;

	JTextField[] tf=new JTextField[13];
	JTextField times;

	public static void main(String[] args) {
		Viewer frame = new Viewer("BLACK JACK シミュレーター");
		frame.setVisible(true);
	}

	Viewer(String title){
	    setTitle(title);
	    setBounds(100, 200, 500, 500);
	    //閉じるボタン追加
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JButton button = new JButton("計算開始");
	    button.addActionListener(this);JPanel p = new JPanel();
	    p.setLayout(new GridLayout(8,10));

	    //トランプの枚数入力フォーム作成
	    for(int i=0;i<13;i++){
            	tf[i]=new JTextField("24",3);
            	p.add(new JLabel(i+1+"の残り枚数:", SwingConstants.LEFT));
            	p.add(tf[i]);
            }
	    p.add(new JLabel("ドロー回数", SwingConstants.LEFT));
	    times = new JTextField("100000",10);
	    p.add(times);
	    p.add(button);

	    JLabel label1 = new JLabel("山札のトランプの残り枚数を入力して下さい。");
	    result = new JLabel();

	    Container contentPane = getContentPane();
	    contentPane.add(label1, BorderLayout.NORTH);
	    contentPane.add(p, BorderLayout.SOUTH);
	    contentPane.add(result, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e){
		int[] cardList= new int[13];
		for(int i=0;i<13;i++) {
			cardList[i]=Integer.parseInt(tf[i].getText());
		}
		Dealer dl = new Dealer(cardList);
		dl.operate(Integer.parseInt(times.getText()));
		//System.out.println(dl.scoreList);
		result.setText("<html><body>ゲーム回数 : " + String.valueOf(dl.scoreList.size()) + " 回" + "<br/>"+ "<br/>"
		+ "21 出現回数： " + String.valueOf(dl.scoreCount[21]) + " 回"+"　　　確率： <font color=\"#ff4500\">"+(String.format("%.2f", (double)dl.scoreCount[21]/dl.scoreList.size()*100))+" % </font>"+"<br/>"
		+ "20 出現回数： " + String.valueOf(dl.scoreCount[20]) + " 回"+"　　　確率： <font color=\"#ff4500\">"+(String.format("%.2f", (double)dl.scoreCount[20]/dl.scoreList.size()*100))+" % </font>"+"<br/>"
		+ "19 出現回数： " + String.valueOf(dl.scoreCount[19]) + " 回"+"　　　確率： <font color=\"#ff4500\">"+(String.format("%.2f", (double)dl.scoreCount[19]/dl.scoreList.size()*100))+" % </font>"+"<br/>"
		+ "18 出現回数： " + String.valueOf(dl.scoreCount[18]) + " 回"+"　　　確率： <font color=\"#ff4500\">"+(String.format("%.2f", (double)dl.scoreCount[18]/dl.scoreList.size()*100))+" % </font>"+"<br/>"
		+ "17 出現回数： " + String.valueOf(dl.scoreCount[17]) + " 回"+"　　　確率： <font color=\"#ff4500\">"+(String.format("%.2f", (double)dl.scoreCount[17]/dl.scoreList.size()*100))+" % </font>"+"<br/>"
		+ "バースト回数： " + String.valueOf(dl.scoreCount[0]) + " 回"+"　　　確率： <font color=\"#ff4500\">"+(String.format("%.2f", (double)dl.scoreCount[0]/dl.scoreList.size()*100))+" % </font>"+"<br/>"
				+ "</body></html>");
	}
}
