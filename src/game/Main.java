package game;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		// 변수 설정
		int people = 5;
		int winningCount = 0;
		int deadWinningCount = 0;
		boolean ifDeadPlayer = false;
		
		// 플레이어 및 상대방 설정
		Player me = new Player("Player");
		Player[] npc = new Player[people];
		ArrayList<Player> deadNpc = new ArrayList<Player>();
		
		// 상대방 초기화
		for(int num = 0; num < npc.length; num++) {
			npc[num] = new Player("상대방");
		}
		
		// 대결 시작
		for(int i = 0; i < npc.length; i++){
			
			me.setKoCount(5);
			int rpsPlayer;
			int playerLife = me.getKoCount();
			
			int rpsNpc;
			int npcLife = npc[i].getKoCount();
			
			System.out.println(me.getName() + "과(와) " + npc[i].getName() + (i+1) + "의 대결");
			System.out.println("------------------");
			
			while(true){
				// 플레이어와 상대방의 가위바위보 대결
				rpsPlayer = me.attack();
				System.out.print("플레이어의 공격 : ");
				printRps(rpsPlayer);
				
				rpsNpc = npc[i].attack();
				System.out.print("상대방의 공격 : ");
				printRps(rpsNpc);
				
				// 대결 결과
				String result = winner(rpsPlayer, rpsNpc);
				System.out.println(result);

				// 대결 결과 처리
				if(result == "승리") {
					npcLife--;
					npc[i].setKoCount(npcLife);
				}
				else if(result == "패배") {
					playerLife--;
					me.setKoCount(playerLife);
				}
				
				System.out.println(me.getName() + "의 체력 : " + me.getKoCount());
				System.out.println(npc[i].getName() + (i+1) + "의 체력 : " + npc[i].getKoCount());
				System.out.println("------------------");
				
				// 라이프가 0이 되었을 시 루프 탈출
				if(npcLife == 0 || playerLife == 0) {
					if(npcLife == 0) {
						System.out.println(npc[i].getName() + (i+1) + "의 KO");
						System.out.println("------------------");
						deadNpc.add(npc[i]);
						winningCount++;
						break;
					}
					else if(playerLife == 0) {
						break;
					}
					break;
				}
				
				waiting();
			}
			
			// 플레이어 승리 시 결과 출력
			if(winningCount == npc.length)	{
				System.out.println(me.getName() + "가 왕이 되었습니다!");
				System.out.println("총 승리 횟수 : " + winningCount);
				System.out.println("------------------");
				break;
				
			// 플레이어 패배 시 결과 출력
			}
			if(playerLife == 0)	{
				System.out.println(me.getName() + "의 KO");
				System.out.println(me.getName() + "은 왕이 되지 못하였습니다..");
				System.out.println("승리 횟수 : " + winningCount);
				System.out.println("------------------");
				ifDeadPlayer = true;
				break;
			}
		
		// 언데드킹 대결 조건 적합 판정
		}
		if(winningCount == 0) {
			System.out.println("플레이어만 패배하여 게임이 종료됩니다.");
			System.out.println("------------------");
		}
		else if(winningCount >= 1 && ifDeadPlayer == true) {
			System.out.println("플레이어가 언데드 킹이 되기 위하여 죽은 자들과 대결합니다.");
			System.out.println("------------------");
			
			for(int i = 0; i < deadNpc.size(); i++){
			
			if(i == 0) {
				me.setKoCount(2);
			}
			
			int rpsPlayer;
			int playerLife = me.getKoCount();
			
			int rpsDeathNpc;
			Player deathNpc = deadNpc.get(i);
			deathNpc.setKoCount(1);
			int deathNpcLife = deathNpc.getKoCount();
			
			System.out.println(me.getName() + "과(와) " + "죽은 " + deathNpc.getName() + (i+1) + "의 대결");
			System.out.println("------------------");
			
			while(true){
				// 플레이어와 상대방의 가위바위보 대결
				rpsPlayer = me.attack();
				System.out.print("플레이어의 공격 : ");
				printRps(rpsPlayer);
				
				rpsDeathNpc = deathNpc.attack();
				System.out.print("상대방의 공격 : ");
				printRps(rpsDeathNpc);
				
				// 대결 결과
				String result = winner(rpsPlayer, rpsDeathNpc);
				System.out.println(result);

				// 대결 결과 처리
				if(result == "승리") {
					if(me.getKoCount() == 1)
					{
						playerLife++;
						me.setKoCount(playerLife);
					}
					deathNpcLife--;
					deathNpc.setKoCount(deathNpcLife);
				}
				else if(result == "패배") {
					playerLife--;
					me.setKoCount(playerLife);
				}
				
				System.out.println(me.getName() + "의 체력 : " + me.getKoCount());
				System.out.println("죽은 " + deathNpc.getName() + (i+1) + "의 체력 : " + deathNpc.getKoCount());
				System.out.println("------------------");
				
				// 라이프가 0이 되었을 시 루프 탈출
				if(deathNpcLife == 0 || playerLife == 0) {
					if(deathNpcLife == 0) {
						System.out.println("죽은 " + deathNpc.getName() + (i+1) + "의 KO");
						System.out.println("------------------");
						deadWinningCount++;
						break;
					}
					else if(playerLife == 0) {
						break;
					}
					break;
				}
				
				waiting();
			}
			
			// 플레이어 승리 시 결과 출력
			if(deadWinningCount == deadNpc.size())	{
				System.out.println(me.getName() + "가 언데드킹이 되었습니다!");
				System.out.println("죽음 후 승리 횟수 : " + deadWinningCount);
				System.out.println("------------------");
				break;
				
			// 플레이어 패배 시 결과 출력
			}
			if(playerLife == 0)	{
				System.out.println(me.getName() + "의 KO");
				System.out.println(me.getName() + "은 언데드킹이 되지 못하였습니다..");
				System.out.println("죽음 후 승리 횟수 : " + deadWinningCount);
				System.out.println("------------------");
				break;
			}
		}
	}			
}
	
	// 가위바위보의 출력내용 변경
	public static void printRps(int rps) {
		switch(rps) {
			case 0 : System.out.println("가위");
				break;
			case 1 : System.out.println("바위");
				break;
			case 2 : System.out.println("보");
				break;
		}
	}
	
	// 가위바위보의 대결 결과값
	public static String winner(int rps1, int rps2) {
		String res = "에러";
		if(rps1 == rps2) {
			res = "무승부";
			return res;
		}
		else {
			if(rps1 == 0) {
				if(rps2 == 1) {
					res = "패배";
					return res;
				}
				else if(rps2 == 2) {
					res = "승리";
					return res;
				}
			 }
			else if(rps1 == 1) {
				if(rps2 == 0) {
					res = "승리";
					return res;
				}
				else if(rps2 == 2) {
					res = "패배";
					return res;
				}
			}
			else if(rps1 == 2) {
				if(rps2 == 0) {
					res = "패배";
					return res;
				}
				else if(rps2 == 1) {
					res = "승리";
					return res;
				}				
			}
		}
		return res;
	}
	
	// 출력 대기 시간
	public static void waiting() {
		int i = 1; // i는 1초 기준
		
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) { }
	}
}
