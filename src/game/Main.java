package game;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		// ���� ����
		int people = 5;
		int winningCount = 0;
		int deadWinningCount = 0;
		boolean ifDeadPlayer = false;
		
		// �÷��̾� �� ���� ����
		Player me = new Player("Player");
		Player[] npc = new Player[people];
		ArrayList<Player> deadNpc = new ArrayList<Player>();
		
		// ���� �ʱ�ȭ
		for(int num = 0; num < npc.length; num++) {
			npc[num] = new Player("����");
		}
		
		// ��� ����
		for(int i = 0; i < npc.length; i++){
			
			me.setKoCount(5);
			int rpsPlayer;
			int playerLife = me.getKoCount();
			
			int rpsNpc;
			int npcLife = npc[i].getKoCount();
			
			System.out.println(me.getName() + "��(��) " + npc[i].getName() + (i+1) + "�� ���");
			System.out.println("------------------");
			
			while(true){
				// �÷��̾�� ������ ���������� ���
				rpsPlayer = me.attack();
				System.out.print("�÷��̾��� ���� : ");
				printRps(rpsPlayer);
				
				rpsNpc = npc[i].attack();
				System.out.print("������ ���� : ");
				printRps(rpsNpc);
				
				// ��� ���
				String result = winner(rpsPlayer, rpsNpc);
				System.out.println(result);

				// ��� ��� ó��
				if(result == "�¸�") {
					npcLife--;
					npc[i].setKoCount(npcLife);
				}
				else if(result == "�й�") {
					playerLife--;
					me.setKoCount(playerLife);
				}
				
				System.out.println(me.getName() + "�� ü�� : " + me.getKoCount());
				System.out.println(npc[i].getName() + (i+1) + "�� ü�� : " + npc[i].getKoCount());
				System.out.println("------------------");
				
				// �������� 0�� �Ǿ��� �� ���� Ż��
				if(npcLife == 0 || playerLife == 0) {
					if(npcLife == 0) {
						System.out.println(npc[i].getName() + (i+1) + "�� KO");
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
			
			// �÷��̾� �¸� �� ��� ���
			if(winningCount == npc.length)	{
				System.out.println(me.getName() + "�� ���� �Ǿ����ϴ�!");
				System.out.println("�� �¸� Ƚ�� : " + winningCount);
				System.out.println("------------------");
				break;
				
			// �÷��̾� �й� �� ��� ���
			}
			if(playerLife == 0)	{
				System.out.println(me.getName() + "�� KO");
				System.out.println(me.getName() + "�� ���� ���� ���Ͽ����ϴ�..");
				System.out.println("�¸� Ƚ�� : " + winningCount);
				System.out.println("------------------");
				ifDeadPlayer = true;
				break;
			}
		
		// �𵥵�ŷ ��� ���� ���� ����
		}
		if(winningCount == 0) {
			System.out.println("�÷��̾ �й��Ͽ� ������ ����˴ϴ�.");
			System.out.println("------------------");
		}
		else if(winningCount >= 1 && ifDeadPlayer == true) {
			System.out.println("�÷��̾ �𵥵� ŷ�� �Ǳ� ���Ͽ� ���� �ڵ�� ����մϴ�.");
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
			
			System.out.println(me.getName() + "��(��) " + "���� " + deathNpc.getName() + (i+1) + "�� ���");
			System.out.println("------------------");
			
			while(true){
				// �÷��̾�� ������ ���������� ���
				rpsPlayer = me.attack();
				System.out.print("�÷��̾��� ���� : ");
				printRps(rpsPlayer);
				
				rpsDeathNpc = deathNpc.attack();
				System.out.print("������ ���� : ");
				printRps(rpsDeathNpc);
				
				// ��� ���
				String result = winner(rpsPlayer, rpsDeathNpc);
				System.out.println(result);

				// ��� ��� ó��
				if(result == "�¸�") {
					if(me.getKoCount() == 1)
					{
						playerLife++;
						me.setKoCount(playerLife);
					}
					deathNpcLife--;
					deathNpc.setKoCount(deathNpcLife);
				}
				else if(result == "�й�") {
					playerLife--;
					me.setKoCount(playerLife);
				}
				
				System.out.println(me.getName() + "�� ü�� : " + me.getKoCount());
				System.out.println("���� " + deathNpc.getName() + (i+1) + "�� ü�� : " + deathNpc.getKoCount());
				System.out.println("------------------");
				
				// �������� 0�� �Ǿ��� �� ���� Ż��
				if(deathNpcLife == 0 || playerLife == 0) {
					if(deathNpcLife == 0) {
						System.out.println("���� " + deathNpc.getName() + (i+1) + "�� KO");
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
			
			// �÷��̾� �¸� �� ��� ���
			if(deadWinningCount == deadNpc.size())	{
				System.out.println(me.getName() + "�� �𵥵�ŷ�� �Ǿ����ϴ�!");
				System.out.println("���� �� �¸� Ƚ�� : " + deadWinningCount);
				System.out.println("------------------");
				break;
				
			// �÷��̾� �й� �� ��� ���
			}
			if(playerLife == 0)	{
				System.out.println(me.getName() + "�� KO");
				System.out.println(me.getName() + "�� �𵥵�ŷ�� ���� ���Ͽ����ϴ�..");
				System.out.println("���� �� �¸� Ƚ�� : " + deadWinningCount);
				System.out.println("------------------");
				break;
			}
		}
	}			
}
	
	// ������������ ��³��� ����
	public static void printRps(int rps) {
		switch(rps) {
			case 0 : System.out.println("����");
				break;
			case 1 : System.out.println("����");
				break;
			case 2 : System.out.println("��");
				break;
		}
	}
	
	// ������������ ��� �����
	public static String winner(int rps1, int rps2) {
		String res = "����";
		if(rps1 == rps2) {
			res = "���º�";
			return res;
		}
		else {
			if(rps1 == 0) {
				if(rps2 == 1) {
					res = "�й�";
					return res;
				}
				else if(rps2 == 2) {
					res = "�¸�";
					return res;
				}
			 }
			else if(rps1 == 1) {
				if(rps2 == 0) {
					res = "�¸�";
					return res;
				}
				else if(rps2 == 2) {
					res = "�й�";
					return res;
				}
			}
			else if(rps1 == 2) {
				if(rps2 == 0) {
					res = "�й�";
					return res;
				}
				else if(rps2 == 1) {
					res = "�¸�";
					return res;
				}				
			}
		}
		return res;
	}
	
	// ��� ��� �ð�
	public static void waiting() {
		int i = 1; // i�� 1�� ����
		
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) { }
	}
}
