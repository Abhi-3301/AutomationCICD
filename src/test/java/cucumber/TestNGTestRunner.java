package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@CucumberOptions(features = "src/test/java/cucumber", glue = "abhishek.cucmbers.stepDefinations",
//monochrome = true, plugin = {"html:target/cucmber.html"})
//public class TestNGTestRunner extends AbstractTestNGCucumberTests {
//
//}

@CucumberOptions(features = "src/test/java/cucumber", glue = "abhishek.cucmbers.stepDefinations",
monochrome = true, tags = "@ErrorValidation", plugin = {"html:target/cucmber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}