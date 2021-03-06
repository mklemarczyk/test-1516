package ug.lab.project3;

import static org.junit.Assert.*;

import org.jbehave.core.annotations.*;

public class MySteps{

	private Boolean lastOperationResult;
	private Integer lastOperationNumber;
	private Exception lastOperationException;
	private BinaryTree tree;

	@BeforeScenario
	public void beforeEachScenario() {
	    tree = new BinaryTree();
	}

	@AfterScenario
	public void afterAnyScenario() {
		tree = null;
	}

	@AfterScenario(uponOutcome=AfterScenario.Outcome.SUCCESS)
	public void afterSuccessfulScenario() {
	    System.out.println("Super - dziala");
	}
	     
	@AfterScenario(uponOutcome=AfterScenario.Outcome.FAILURE)
	public void afterFailedScenario() {
		System.out.println("Zepsute to..");
	}


	@When("getting number")
	public void whenGettingNumber(){
		lastOperationException = null;
		lastOperationNumber = null;
		try{
			lastOperationNumber = tree.getNumber();
		}catch(Exception ex){
			lastOperationException = ex;
		}
	}
	
	@Then("operation should throw an exception")
	public void thenOperationShouldThrowAnException() {
		assertNotNull(lastOperationException);
	}

	@Then("operation should not throw an exception")
	public void thenOperationShouldNotThrowAnException() {
		assertNull(lastOperationException);
	}

	@Then("operation should return a number $n")
	public void thenOperationShouldReturnNumber(@Named("n") int n) {
		assertNotNull(lastOperationNumber);
		assertEquals(n, lastOperationNumber.longValue());
	}

	@When("navigate to parent")
	public void whenNavigateToParent(){
		lastOperationResult = tree.moveUp();
	}

	@When("navigate to left child")
	@Alias("go to left child")
	public void whenNavigateToLeftChild(){
		lastOperationResult = tree.moveLeft();
	}

	@When("{navigate|go} to right child")
	public void whenNavigateToRightChild(){
		lastOperationResult = tree.moveRight();
	}

	@Then("operation should return a boolean $value")
	public void thenOperationShouldReturn(@Named("value") boolean value){
		assertEquals(value, lastOperationResult);
	}

	@When("add a number $n")
	public void whenAddANumber(@Named("n") int n) {
		tree.add(n);
	}

	@Then("root should be equal to $n")
	public void thenRootShouldBeEqualTo(@Named("n") int n) {
		assertEquals(n, tree.getNumber());
	}

	@Then("left child should be equal to $n")
	public void thenLeftChildShouldBeEqualTo(@Named("n") int n) {
		assertTrue(tree.moveLeft());
		assertEquals(n, tree.getNumber());
		assertTrue(tree.moveUp());
	}

	@Then("right child should be equal to $n")
	public void thenRightChildShouldBeEqualTo(@Named("n") int n) {
		assertTrue(tree.moveRight());
		assertEquals(n, tree.getNumber());
		assertTrue(tree.moveUp());
	}

	@Then("left child should be empty")
	public void thenLeftChildShouldBeEmpty() {
		assertFalse(tree.moveLeft());
	}

	@Then("right child should be empty")
	public void thenRightChildShouldBeEmpty() {
		assertFalse(tree.moveRight());
	}

	@When("delete a number $n")
	public void whenDeleteANumber(@Named("n") int n) {
		lastOperationResult = tree.delete(n);
	}
}