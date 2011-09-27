//package parser;

public class ParserTest {

	public static void main(String[] args) {

		String input = "(see 0 ((f c) 44.7 23) ((f c t) 44.7 -22) ((f r t) 95.6 -10) ((f r b) 106.7 29) ((f g r b) 96.5 14) ((g r) 95.6 10) ((f g r t) 94.6 6) ((f p r b) 85.6 26) ((f p r c) 79 13) ((f p r t) 77.5 -2) ((f p l t) 6.2 -29 0 0) ((f t 0) 47 -28) ((f t r 10) 55.7 -23) ((f t r 20) 65.4 -20) ((f t r 30) 74.4 -17) ((f t r 40) 83.9 -15) ((f t r 50) 93.7 -13) ((f t l 10) 38.1 -35) ((f b r 20) 83.1 42) ((f b r 30) 90.9 38) ((f b r 40) 98.5 35) ((f b r 50) 107.8 32) ((f r 0) 100.5 10) ((f r t 10) 99.5 4) ((f r t 20) 98.5 -2) ((f r t 30) 99.5 -7) ((f r b 10) 102.5 15) ((f r b 20) 105.6 21) ((f r b 30) 109.9 25) ((b) 44.7 23) ((p) 49.4 39) ((l r) 93.7 90))";

		Memory newMem = new Memory();
		ObjInfo newInfo = new ObjInfo();
		Parser p = new Parser(input, newMem);
		
		
		
		for(int i = 0; i < newMem.ObjMem.getSize(); i++) {
			newInfo = newMem.ObjMem.getObj(i);
			System.out.println("Name: " + newInfo.getObjName());
			System.out.println("Distance: " + newInfo.getDistance());
			System.out.println("");
		}
		
		System.out.println(newMem.ObjMem.getObj("ball").getDirection());
		
	}

}
