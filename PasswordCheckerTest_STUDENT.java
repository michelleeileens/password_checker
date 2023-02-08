
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	
	ArrayList<String> pw1;
	ArrayList<String> pw2;
	
	@Before
	public void setUp() throws Exception {
		pw1 = new ArrayList<String>();
		String [] passwords = {"Password@123","957POI#ert","ivoRy38$NEV","MaLis#45@","Gregory&26","let54","###molly1","CLER456","loweR^^","Crayyy45","City8%$KRR"};
		pw1.addAll(Arrays.asList(passwords));
	}

	@After
	public void tearDown() throws Exception {
		pw1 = pw2 = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Password@123"));
			PasswordCheckerUtility.isValidPassword("let54");
			assertTrue("No length exception",false);
		}
		catch(LengthException e) {
			assertTrue("Threw Length exception",true);
		}
		catch (Exception e) {
			assertTrue("Threw other exception",true);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("CASestudy#45"));
			PasswordCheckerUtility.isValidPassword("###molly1");
			assertTrue("Did not throw NoUpperAlpha exception",false);
		}
		catch(NoUpperAlphaException e) {
			assertTrue("Threw NoUpperAlpha exception",true);
		}
		catch (Exception e) {
			assertTrue("Threw other exception",true);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("HASnoupper*90@"));
			PasswordCheckerUtility.isValidPassword("CLER456");
			assertTrue("Did not throw NoLowerApha exception",false);
		}
		catch(NoLowerAlphaException e) {
			assertTrue("Threw NoLowerAlpha exception",true);
		}
		catch (Exception e) {
			assertTrue("Threw other exception",true);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword("CLER456"));
			PasswordCheckerUtility.isWeakPassword("");
		}
		catch(WeakPasswordException w) {
			assertTrue("Threw weakPassword exception",true);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("PeTe55%AA"));
			PasswordCheckerUtility.isValidPassword("Kate49%BBB");
			assertTrue("did not Throw InvalidSequence exception",false);
		}
		catch(InvalidSequenceException e) {
			assertTrue("Threw InvalidSequence exception",true);
		}
		catch (Exception e) {
			assertTrue("Threw other exception",true);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Im2cool4U@"));
			PasswordCheckerUtility.isValidPassword("ImcoolU#");
			assertTrue("did not throw HasDigit exception",false);
		}
		catch(NoDigitException e) {
			assertTrue("Threw NoDigit exception",true);
		}
		catch (Exception e) {
			assertTrue("Threw other exception",true);
		}
	}
	
	@Test
	public void testValidPasswordNoSpecialChar() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("manNy43$NEV"));
			PasswordCheckerUtility.isValidPassword("manNy43NEV");
			assertTrue("did not throw NoSpecialCharacter exception",false);
		}
		catch(NoSpecialCharacterException e) {
			assertTrue("Threw NoSpecialCharacter exception",true);
		}
		catch (Exception e) {
			assertTrue("Threw other exception",true);
		}
	}
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			PasswordCheckerUtility.isValidPassword("Password@123");
			assertTrue("did not throw an exception",true);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		pw2 = PasswordCheckerUtility.getInvalidPasswords(pw1);
		Scanner in = new Scanner(pw2.get(0));
		assertEquals(in.next(),"let54");
		String comment = in.nextLine().toLowerCase();
		assertTrue(comment.contains("long"));
		
		in = new Scanner(pw2.get(1)); //
		assertEquals(in.next(), "###molly1");
		comment = in.nextLine().toLowerCase();
		assertTrue(comment.contains("uppercase"));
		
		in = new Scanner(pw2.get(2)); 
		assertEquals(in.next(), "CLER456");
		comment = in.nextLine().toLowerCase();
		assertTrue(comment.contains("lowercase"));
		
		in = new Scanner(pw2.get(3)); //
		assertEquals(in.next(), "loweR^^");
		comment = in.nextLine().toLowerCase();
		assertTrue(comment.contains("digit"));
		
		in = new Scanner(pw2.get(4)); //
		assertEquals(in.next(), "Crayyy45");
		comment = in.nextLine().toLowerCase();
		assertTrue(comment.contains("special character"));
		
		in = new Scanner(pw2.get(5)); 
		assertEquals(in.next(), "City8%$KRR");
		comment = in.nextLine().toLowerCase();
		//assertTrue(comment.contains("sequence"));
	}
	
}
