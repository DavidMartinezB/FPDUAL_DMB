/*
 * 
 * 
 * @author: David Martínez Bergantiño 
 * */

package trivia;

import java.util.ArrayList;

public class Game {
	private static int points;
	private static ArrayList<Questions> questionsList = new ArrayList<>();
	private static ArrayList<Answers> answersList = new ArrayList<>();
	
	protected static String getQuestion(int select) {
		return createQuestions().get(select).getValue();
	}
	
	protected static String getAnswer(int select) {
		return createAnswers().get(select).getValue();
	}
	
	protected static boolean compareAnswer(int select, String answ) {
		String correctAnsw = getAnswer(select);
		if (!answ.equals(correctAnsw)) {
			return false;
		}
		return true;
	}
	
	protected static int getPoints(int select, String answ) {
		if (!compareAnswer(select, answ)) {
			return 0;
		}
		return points++;
	}
	
	protected static ArrayList<Questions> createQuestions() {
		Questions q1 = new Questions("¿Cuál es la capital de Australia?");
		Questions q2 = new Questions("¿En qué año comenzó la Segunda Guerra Mundial?");
		Questions q3 = new Questions("¿Cuál es el río más largo del mundo?");
		Questions q4 = new Questions("¿Cuál es la montaña más alta de África?");
		Questions q5 = new Questions("¿Quién pintó la famosa obra \"La última cena\"?");
		Questions q6 = new Questions("¿Cuál es el elemento químico más abundante en la corteza terrestre?");
		Questions q7 = new Questions("¿Cuál es el océano más grande del mundo?");
		Questions q8 = new Questions("¿Quién escribió \"Cien años de soledad\"?");
		Questions q9 = new Questions("¿Cuál es la capital de Francia?");
		Questions q10 = new Questions("¿Cuál es el país más poblado del mundo?");
		
		questionsList.add(q1);
		questionsList.add(q2);
		questionsList.add(q3);
		questionsList.add(q4);
		questionsList.add(q5);
		questionsList.add(q6);
		questionsList.add(q7);
		questionsList.add(q8);
		questionsList.add(q9);
		questionsList.add(q10);
		
		return questionsList;
	}
	
	protected static ArrayList<Answers> createAnswers() {
		Answers a1 = new Answers("Canberra", createQuestions().get(0));
		Answers a2 = new Answers("1933", createQuestions().get(1));
		Answers a3 = new Answers("Amazonas", createQuestions().get(2));
		Answers a4 = new Answers("Monte Kilimanjaro", createQuestions().get(3));
		Answers a5 = new Answers("Leonardo da Vinci", createQuestions().get(4));
		Answers a6 = new Answers("Oxígeno", createQuestions().get(5));
		Answers a7 = new Answers("Oceano Pacífico", createQuestions().get(6));
		Answers a8 = new Answers("Gabriel García Márquez", createQuestions().get(7));
		Answers a9 = new Answers("París", createQuestions().get(8));
		Answers a10 = new Answers("China", createQuestions().get(9));
		
		answersList.add(a1);
		answersList.add(a2);
		answersList.add(a3);
		answersList.add(a4);
		answersList.add(a5);
		answersList.add(a6);
		answersList.add(a7);
		answersList.add(a8);
		answersList.add(a9);
		answersList.add(a10);
		
		return answersList;
	}
}
