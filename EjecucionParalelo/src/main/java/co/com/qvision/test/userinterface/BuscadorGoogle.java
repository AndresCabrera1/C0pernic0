package co.com.qvision.test.userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BuscadorGoogle {
    public static final Target TXTBUSCADORGOOGLE = Target.the("{0} Busca la palabra en google ").located(By.xpath("//input[@class='gLFyf gsfi']"));
    public static final Target BTNBUSCARGOOGLE = Target.the("{0} Selecciona la opcion de Buscar en google ").located(By.xpath("(//input[@name='btnK'])[1]"));
    public static final Target TXTPALABARAENCONTRADA = Target.the("{0} visualiza la palabra buscada ").located(By.xpath("(//h3[@class='LC20lb DKV0Md'])[1]"));
    }
