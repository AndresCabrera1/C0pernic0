package co.com.qvision.test.stepdefinitions;

import co.com.qvision.test.questions.EncontroPalabra;
import co.com.qvision.test.tasks.*;
import co.com.qvision.test.userinterface.HomeGoogle;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class BuscarPalabraGoogleStepDefinitions {
    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("^El usuario ingresa al navegador google$")
    public void las_indicaciones_del_papa_de_ana() {
    theActorCalled("Ana").attemptsTo(
            GoogleHomeTask.openUrl(HomeGoogle.UrlGoogle)
    );
    }

    @Cuando("^ingresa la palabra a buscar$")
    public void megan_visualiza_el_producto_y_genera_la_compra() {
        theActorInTheSpotlight().attemptsTo(BuscarPalabraGoogleTasks.buscar());
    }


    @Entonces("^verifica la palabra buscada$")
    public void envia_el_comprobande_de_la_transaccion() {
        theActorInTheSpotlight().should(seeThat(" La busqueda ",EncontroPalabra.verificar(), is(true)).because(" deberia ser exitosa "));
    }

}
