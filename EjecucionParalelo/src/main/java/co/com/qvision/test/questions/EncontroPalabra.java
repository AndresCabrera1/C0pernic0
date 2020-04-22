package co.com.qvision.test.questions;

import co.com.qvision.test.userinterface.BuscadorGoogle;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

@Subject("Verifica la palabra buscada")
public class EncontroPalabra implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        System.out.print("-->" + Text.of(BuscadorGoogle.TXTPALABARAENCONTRADA).viewedBy(actor).asString());
        return Text.of(BuscadorGoogle.TXTPALABARAENCONTRADA).viewedBy(actor).asString().equals("Hola Mundo en Java - Línea de Código");
    }

    public static EncontroPalabra verificar(){
        return new EncontroPalabra();
    }
}
