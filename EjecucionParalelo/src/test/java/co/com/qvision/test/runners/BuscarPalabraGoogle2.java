package co.com.qvision.test.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/buscar_palabra_google.feature",
        glue = "co.com.qvision.test.stepdefinitions",
        snippets = SnippetType.CAMELCASE

)

public class BuscarPalabraGoogle2 {
}
