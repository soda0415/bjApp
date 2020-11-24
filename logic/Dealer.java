/**
 *ディーラー処理BL
 */
package logic;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {
	//クラス変数
	public int[] cardList;
	public int score = 0;
	public ArrayList<Integer> cardPool;
	public ArrayList<Integer> scoreList = new ArrayList<Integer>();
	public int[] scoreCount = new int[22];//0から21までの添え字

	//コンストラクター
	public Dealer(int[] cardList) {
		super();
		this.cardList = cardList;
		this.cardPool = new ArrayList<Integer>() {{
			for (int i=0;i<=12;i++) {
				for (int j=0;j<cardList[i];j++) {
					//絵柄のトランプは10として扱う
					if(i<10) {
						add(Integer.valueOf(i+1));
					}else {
						add(Integer.valueOf(10));
					}
				}
		    }
		}};
	}
	//最終スコア集計メソッド
	public void scoreAggregate (int score) {
		scoreList.add(score);
		scoreCount[score]= scoreCount[score] + 1;
	}

	//一定回数 (count) カードを引き、スコア計算
	//scoreList 配列に結果を格納
	public void operate(int count) {
		int score =0;
		int k = 0;
		//Aを引いたかチェックするフラグ
		boolean A_flg = false;
		//一回目シャッフル
		Collections.shuffle(cardPool);
		for (int i=0;i<count;i++) {
			//System.out.println(cardPool.get(k));
			//Aを引いた場合は、さらに条件分岐
			if(cardPool.get(k)==1) {
				A_flg = true;
			    score = score + 11;
			}else {
			//A以外を引いた場合は、普通に合計
				score = score + cardPool.get(k);
			}
			k=k+1;

			//バーストの条件分岐
			if(score >21) {
				//Aを引いていてバーストした場合、11を1として再計算する。
				if(A_flg) {
					score = score -10;
					A_flg = false;
				}else {
					//バーストの場合、最終スコアを0とする
					scoreAggregate(0);
					Collections.shuffle(cardPool);
					//初期化処理
					score = 0;
					k=0;
					A_flg = false;
				}
			}
			//17～21になった時点で終了
			if(17 <= score && score <= 21) {
				scoreAggregate(score);
				Collections.shuffle(cardPool);
				//初期化処理
				score = 0;
				k=0;
				A_flg = false;
			}
		}
	}

}
