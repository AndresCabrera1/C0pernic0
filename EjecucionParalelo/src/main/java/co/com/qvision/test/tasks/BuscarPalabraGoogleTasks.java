package co.com.qvision.test.tasks;

import co.com.qvision.test.userinterface.BuscadorGoogle;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

public class BuscarPalabraGoogleTasks implements Task {
    @Override
    @Step("{0} ingresa la palabra a buscar ")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BuscadorGoogle.TXTBUSCADORGOOGLE),
                Enter.theValue("Hola Mundo en java").into(BuscadorGoogle.TXTBUSCADORGOOGLE),
                Click.on(BuscadorGoogle.BTNBUSCARGOOGLE)
        );
    }

    public static Performable buscar(){
        return Tasks.instrumented(BuscarPalabraGoogleTasks.class);
    }
}
