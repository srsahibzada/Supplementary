
import java.math.BigInteger;
/*
	Karatsuba's Multiplication Algorithm
	Implemented by Sarah Sahibzada for use in CSCE 411: Design and Analysis of Algorithms
	Input verification for HW 2 in main
	Texas A&M University
*/
class karatsuba {
BigInteger base; //for arbitrary 
static final BigInteger TEN = new BigInteger("10"); //need only test against b = 10
static final BigInteger ERROR = new BigInteger("-1");
//constructor sets up the basics for a karatsuba's problem.
//initialize a karatsuba object with x, y
public karatsuba(BigInteger base) {
	this.base = base;
}

public static int lengthOfArg(BigInteger arg) {
	String digits = arg.toString();
	return digits.length();
}


public static BigInteger leftShiftByTen(BigInteger arg, int amount) {
	String num = arg.toString();
	while (amount != 0) {
		num += "0";
		amount -= 1;
	}
	return new BigInteger(num);
}

public static BigInteger rightShiftByTen(BigInteger arg, int amount) {
	
	if (amount > (arg.toString()).length()) {
		return ERROR; // we only want to deal with integers
	}
	else {
		while (amount != 0) {
			arg = arg.divide(TEN);
			amount -= 1;
		}
	}
	return arg;
}



public BigInteger kmultiply(BigInteger x, BigInteger y) {

	int len1 = lengthOfArg(x);
	int len2 = lengthOfArg(y);
	System.out.println(x.toString());
	System.out.println(y.toString());
	int maxLength = (Math.max(len1, len2));
	System.out.println("Initially, max length is " + maxLength);

	if (maxLength <= 2) {
		System.out.println(" small number alert "); //because 4-digit input
		System.out.println((x.multiply(y)).toString());
		return x.multiply(y); //base case
	}
	else {
		System.out.println("recursive call");
		// x = a + B^k b and y = x + B^k d
		maxLength = (maxLength / 2) + (maxLength % 2); //ceiling functions
		//System.out.println(" Max length in this call is " + maxLength);
		//BigInteger multiplier = base.pow(halfLength); // B^k where k is the smaller part
		//System.out.println(multiplier.toString() + " is the multiplier");
		BigInteger b = rightShiftByTen(x,maxLength);
		System.out.println(b.toString() + " is b");
		BigInteger a = x.subtract(leftShiftByTen(b,maxLength));
		System.out.println(a.toString() + " is a");
		BigInteger d = rightShiftByTen(y,maxLength);
		System.out.println(d.toString() + " is d");
		BigInteger c = y.subtract(leftShiftByTen(d,maxLength));
		System.out.println(c.toString() + " is c");
		BigInteger aPlusb = a.add(b);
		BigInteger cPlusd = c.add(d);


		//using kmult, not BigInteger mult
		BigInteger z0 = this.kmultiply(a,c);
		BigInteger z1 = this.kmultiply(aPlusb,cPlusd);
		BigInteger z2 = this.kmultiply(b,d);
		BigInteger z2ShiftLeftHalf = leftShiftByTen(z2,maxLength);
		BigInteger z2ShiftLeftWhole = leftShiftByTen(z2,2*maxLength);
		BigInteger zSubtraction = z1.subtract(z0).subtract(z2);
		BigInteger subLeftShift = leftShiftByTen(zSubtraction, maxLength);

		return z0.add(subLeftShift).add(z2ShiftLeftWhole);



}
}
public static void main(String[] args) {
	karatsuba kBaseTen = new karatsuba(new BigInteger("10"));


	System.out.println(" Karatsuba object kBaseTen has base 10. Multiplication with kMultiply() is shown below.");
	BigInteger product2 = kBaseTen.kmultiply(new BigInteger("1234"), new BigInteger("9867"));
	System.out.println(product2.toString());
	System.out.println(((new BigInteger("1234")).multiply(new BigInteger("9867"))).toString());



}
}
