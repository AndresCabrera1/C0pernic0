package co.com.qvision.test.tasks;

import co.com.qvision.test.userinterface.HomeGoogle;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.configurers.WebDriverConfigurer;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

public class GoogleHomeTask implements Task {

    private HomeGoogle homeGoogle;

    public GoogleHomeTask(HomeGoogle homeGoogle){
        this.homeGoogle = homeGoogle;
    }

    public static Performable openUrl(HomeGoogle homeGoogle){
        return Tasks.instrumented(GoogleHomeTask.class, homeGoogle);
    }

    @Override
    @Step("{0} ingresa a Qvision Shoping ")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Open.url(homeGoogle.Url())
        );
    }
}
