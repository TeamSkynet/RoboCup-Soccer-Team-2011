import java.io.*;
import java.net.*;
import java.util.*;

public class Field {
	

	public Field(String side) {
		
		if(side.compareTo("l") == 0) {
			this.ftl50 = new Pos(-50, -39);
			this.ftl40 = new Pos(-40, -39);
			this.ftl30 = new Pos(-30, -39);
			this.ftl20 = new Pos(-20, -39);
			this.ftl10 = new Pos(-10, -39);
			this.ft0 = new Pos(0, -39);
			this.ftr10 = new Pos(10, -39);
			this.ftr20 = new Pos(20, -39);
			this.ftr30 = new Pos(30, -39);
			this.ftr40 = new Pos(40, -39);
			this.ftr50 = new Pos(50, -39);
			
			this.frt30 = new Pos(57.5, -30);
			this.frt20 = new Pos(57.5, -20);
			this.frt10 = new Pos(57.5, -10);
			this.fr0 = new Pos(57.5, 0);
			this.frb10 = new Pos(57.5, 10);
			this.frt20 = new Pos(57.5, 20);
			this.frt30 = new Pos(57.5, 30);
			
			this.fbl50 = new Pos(-50, 39);
			this.fbl40 = new Pos(-40, 39);
			this.fbl30 = new Pos(-30, 39);
			this.fbl20 = new Pos(-20, 39);
			this.fbl10 = new Pos(-10, 39);
			this.fb0 = new Pos(0, 39);
			this.fbr10 = new Pos(10, 39);
			this.fbr20 = new Pos(20, 39);
			this.fbr30 = new Pos(30, 39);
			this.fbr40 = new Pos(40, 39);
			this.fbr50 = new Pos(50, 39);
			
			this.flt30 = new Pos(-57.5, 30);
			this.flt20 = new Pos(-57.5, 20);
			this.flt10 = new Pos(-57.5, 10);
			this.fl0 = new Pos(-57.5, 0);
			this.flb10 = new Pos(-57.5, -10);
			this.flt20 = new Pos(-57.5, -20);
			this.flt30 = new Pos(-57.5, -30);
			
			
			this.flt = new Pos(-52.5, -34);
			this.fct = new Pos(0, -34);
			this.frt = new Pos(52.5, -34);
			this.flb = new Pos(-52.5, 34);
			this.fcb = new Pos(0, 34);
			this.frb = new Pos(52.5, 34);
			
			this.fplt = new Pos(-36, -20.16);
			this.fplc = new Pos(-36, 0);
			this.fplb = new Pos(-36, 20.16);
			this.fglt = new Pos(-52.5, -7.01);
			this.fglb = new Pos(-52.5, 7.01);
			
			this.fprt = new Pos(36, -20.16);
			this.fprc = new Pos(36, 0);
			this.fprb = new Pos(36, 20.16);
			this.fgrt = new Pos(52.5, -7.01);
			this.fgrb = new Pos(52.5, 7.01);
			
			this.fc = new Pos(0, 0);
			
			this.gl = new Pos(-52.5, 0);
			this.gr = new Pos(52.5, 0);
			
			
			
		}
		else {
			this.ftl50 = new Pos(50, 39);
			this.ftl40 = new Pos(40, 39);
			this.ftl30 = new Pos(30, 39);
			this.ftl20 = new Pos(20, 39);
			this.ftl10 = new Pos(10, 39);
			this.ft0 = new Pos(0, 39);
			this.ftr10 = new Pos(-10, 39);
			this.ftr20 = new Pos(-20, 39);
			this.ftr30 = new Pos(-30, 39);
			this.ftr40 = new Pos(-40, 39);
			this.ftr50 = new Pos(-50, 39);
			
			this.frt30 = new Pos(-57.5, 30);
			this.frt20 = new Pos(-57.5, 20);
			this.frt10 = new Pos(-57.5, 10);
			this.fr0 = new Pos(-57.5, 0);
			this.frb10 = new Pos(-57.5, -10);
			this.frt20 = new Pos(-57.5, -20);
			this.frt30 = new Pos(-57.5, -30);
			
			this.fbl50 = new Pos(50, -39);
			this.fbl40 = new Pos(40, -39);
			this.fbl30 = new Pos(30, -39);
			this.fbl20 = new Pos(20, -39);
			this.fbl10 = new Pos(10, -39);
			this.fb0 = new Pos(0, -39);
			this.fbr10 = new Pos(-10, -39);
			this.fbr20 = new Pos(-20, -39);
			this.fbr30 = new Pos(-30, -39);
			this.fbr40 = new Pos(-40, -39);
			this.fbr50 = new Pos(-50, -39);
			
			this.flt30 = new Pos(57.5, -30);
			this.flt20 = new Pos(57.5, -20);
			this.flt10 = new Pos(57.5, -10);
			this.fl0 = new Pos(57.5, 0);
			this.flb10 = new Pos(57.5, 10);
			this.flt20 = new Pos(57.5, 20);
			this.flt30 = new Pos(57.5, 30);
			
			
			this.flt = new Pos(52.5, 34);
			this.fct = new Pos(0, 34);
			this.frt = new Pos(-52.5, -34);
			this.flb = new Pos(52.5, -34);
			this.fcb = new Pos(0, -34);
			this.frb = new Pos(-52.5, -34);
			
			this.fplt = new Pos(36, 20.16);
			this.fplc = new Pos(36, 0);
			this.fplb = new Pos(36, -20.16);
			this.fglt = new Pos(52.5, 7.01);
			this.fglb = new Pos(52.5, -7.01);
			
			this.fprt = new Pos(-36, 20.16);
			this.fprc = new Pos(-36, 0);
			this.fprb = new Pos(-36, -20.16);
			this.fgrt = new Pos(-52.5, 7.01);
			this.fgrb = new Pos(-52.5, -7.01);
			
			this.fc = new Pos(0, 0);
			
			this.gl = new Pos(52.5, 0);
			this.gr = new Pos(-52.5, 0);
		}
		
		
		
	}
	
	
	public Pos ftl50;
	public Pos ftl40;
	public Pos ftl30;
	public Pos ftl20;
	public Pos ftl10;
	public Pos ft0;
	public Pos ftr10;
	public Pos ftr20;
	public Pos ftr30;
	public Pos ftr40;
	public Pos ftr50;
	
	public Pos fbl50;
	public Pos fbl40;
	public Pos fbl30;
	public Pos fbl20;
	public Pos fbl10;
	public Pos fb0;
	public Pos fbr10;
	public Pos fbr20;
	public Pos fbr30;
	public Pos fbr40;
	public Pos fbr50;
	
	public Pos frt30;
	public Pos frt20;
	public Pos frt10;
	public Pos fr0;
	public Pos frb10;
	public Pos frb20;
	public Pos frb30;
	
	public Pos flt30;
	public Pos flt20;
	public Pos flt10;
	public Pos fl0;
	public Pos flb10;
	public Pos flb20;
	public Pos flb30;

	
	public Pos flt;
	public Pos fct;
	public Pos frt;
	public Pos flb;
	public Pos fcb;
	public Pos frb;
	
	public Pos fplt;
	public Pos fplc;
	public Pos fplb;
	public Pos fglt;
	public Pos fglb;
	
	public Pos fprt;
	public Pos fprc;
	public Pos fprb;
	public Pos fgrt;
	public Pos fgrb;
	
	public Pos fc;
	
	public Pos gl;
	public Pos gr;
	
	
	
	
}
